package com.zmfx.csc.web.rest;

import static com.zmfx.csc.domain.CarteAsserts.*;
import static com.zmfx.csc.web.rest.TestUtil.createUpdateProxyForBean;
import static com.zmfx.csc.web.rest.TestUtil.sameInstant;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zmfx.csc.IntegrationTest;
import com.zmfx.csc.domain.Carte;
import com.zmfx.csc.domain.Template;
import com.zmfx.csc.repository.CarteRepository;
import com.zmfx.csc.service.dto.CarteDTO;
import com.zmfx.csc.service.mapper.CarteMapper;
import jakarta.persistence.EntityManager;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Base64;
import java.util.UUID;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link CarteResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class CarteResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_SURNAME = "AAAAAAAAAA";
    private static final String UPDATED_SURNAME = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_VILLE_RESIDENCE = "AAAAAAAAAA";
    private static final String UPDATED_VILLE_RESIDENCE = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_BIRTHDATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_BIRTHDATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);
    private static final ZonedDateTime SMALLER_BIRTHDATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(-1L), ZoneOffset.UTC);

    private static final String DEFAULT_TELEPHONE_1 = "AAAAAAAAAA";
    private static final String UPDATED_TELEPHONE_1 = "BBBBBBBBBB";

    private static final String DEFAULT_TELEPHONE_2 = "AAAAAAAAAA";
    private static final String UPDATED_TELEPHONE_2 = "BBBBBBBBBB";

    private static final Boolean DEFAULT_IS_IMPRIME = false;
    private static final Boolean UPDATED_IS_IMPRIME = true;

    private static final String DEFAULT_PICTURE_EXTENSION = "AAAAAAAAAA";
    private static final String UPDATED_PICTURE_EXTENSION = "BBBBBBBBBB";

    private static final byte[] DEFAULT_PICTURE = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_PICTURE = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_PICTURE_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_PICTURE_CONTENT_TYPE = "image/png";

    private static final LocalDate DEFAULT_DATE_DELIVRANCE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_DELIVRANCE = LocalDate.now(ZoneId.systemDefault());
    private static final LocalDate SMALLER_DATE_DELIVRANCE = LocalDate.ofEpochDay(-1L);

    private static final LocalDate DEFAULT_DATE_EXPIRATION = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_EXPIRATION = LocalDate.now(ZoneId.systemDefault());
    private static final LocalDate SMALLER_DATE_EXPIRATION = LocalDate.ofEpochDay(-1L);

    private static final String ENTITY_API_URL = "/api/cartes";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{matricule}";

    @Autowired
    private ObjectMapper om;

    @Autowired
    private CarteRepository carteRepository;

    @Autowired
    private CarteMapper carteMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCarteMockMvc;

    private Carte carte;

    private Carte insertedCarte;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Carte createEntity(EntityManager em) {
        Carte carte = new Carte()
            .matricule(UUID.randomUUID().toString())
            .name(DEFAULT_NAME)
            .surname(DEFAULT_SURNAME)
            .email(DEFAULT_EMAIL)
            .villeResidence(DEFAULT_VILLE_RESIDENCE)
            .birthdate(DEFAULT_BIRTHDATE)
            .telephone1(DEFAULT_TELEPHONE_1)
            .telephone2(DEFAULT_TELEPHONE_2)
            .isImprime(DEFAULT_IS_IMPRIME)
            .pictureExtension(DEFAULT_PICTURE_EXTENSION)
            .picture(DEFAULT_PICTURE)
            .pictureContentType(DEFAULT_PICTURE_CONTENT_TYPE)
            .dateDelivrance(DEFAULT_DATE_DELIVRANCE)
            .dateExpiration(DEFAULT_DATE_EXPIRATION);
        return carte;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Carte createUpdatedEntity(EntityManager em) {
        Carte carte = new Carte()
            .matricule(UUID.randomUUID().toString())
            .name(UPDATED_NAME)
            .surname(UPDATED_SURNAME)
            .email(UPDATED_EMAIL)
            .villeResidence(UPDATED_VILLE_RESIDENCE)
            .birthdate(UPDATED_BIRTHDATE)
            .telephone1(UPDATED_TELEPHONE_1)
            .telephone2(UPDATED_TELEPHONE_2)
            .isImprime(UPDATED_IS_IMPRIME)
            .pictureExtension(UPDATED_PICTURE_EXTENSION)
            .picture(UPDATED_PICTURE)
            .pictureContentType(UPDATED_PICTURE_CONTENT_TYPE)
            .dateDelivrance(UPDATED_DATE_DELIVRANCE)
            .dateExpiration(UPDATED_DATE_EXPIRATION);
        return carte;
    }

    @BeforeEach
    public void initTest() {
        carte = createEntity(em);
    }

    @AfterEach
    public void cleanup() {
        if (insertedCarte != null) {
            carteRepository.delete(insertedCarte);
            insertedCarte = null;
        }
    }

    @Test
    @Transactional
    void createCarte() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the Carte
        CarteDTO carteDTO = carteMapper.toDto(carte);
        var returnedCarteDTO = om.readValue(
            restCarteMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(carteDTO)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            CarteDTO.class
        );

        // Validate the Carte in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedCarte = carteMapper.toEntity(returnedCarteDTO);
        assertCarteUpdatableFieldsEquals(returnedCarte, getPersistedCarte(returnedCarte));

        insertedCarte = returnedCarte;
    }

    @Test
    @Transactional
    void createCarteWithExistingId() throws Exception {
        // Create the Carte with an existing ID
        insertedCarte = carteRepository.saveAndFlush(carte);
        CarteDTO carteDTO = carteMapper.toDto(carte);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restCarteMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(carteDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Carte in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllCartes() throws Exception {
        // Initialize the database
        carte.setMatricule(UUID.randomUUID().toString());
        insertedCarte = carteRepository.saveAndFlush(carte);

        // Get all the carteList
        restCarteMockMvc
            .perform(get(ENTITY_API_URL + "?sort=matricule,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].matricule").value(hasItem(carte.getMatricule())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].surname").value(hasItem(DEFAULT_SURNAME)))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL)))
            .andExpect(jsonPath("$.[*].villeResidence").value(hasItem(DEFAULT_VILLE_RESIDENCE)))
            .andExpect(jsonPath("$.[*].birthdate").value(hasItem(sameInstant(DEFAULT_BIRTHDATE))))
            .andExpect(jsonPath("$.[*].telephone1").value(hasItem(DEFAULT_TELEPHONE_1)))
            .andExpect(jsonPath("$.[*].telephone2").value(hasItem(DEFAULT_TELEPHONE_2)))
            .andExpect(jsonPath("$.[*].isImprime").value(hasItem(DEFAULT_IS_IMPRIME.booleanValue())))
            .andExpect(jsonPath("$.[*].pictureExtension").value(hasItem(DEFAULT_PICTURE_EXTENSION)))
            .andExpect(jsonPath("$.[*].pictureContentType").value(hasItem(DEFAULT_PICTURE_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].picture").value(hasItem(Base64.getEncoder().encodeToString(DEFAULT_PICTURE))))
            .andExpect(jsonPath("$.[*].dateDelivrance").value(hasItem(DEFAULT_DATE_DELIVRANCE.toString())))
            .andExpect(jsonPath("$.[*].dateExpiration").value(hasItem(DEFAULT_DATE_EXPIRATION.toString())));
    }

    @Test
    @Transactional
    void getCarte() throws Exception {
        // Initialize the database
        carte.setMatricule(UUID.randomUUID().toString());
        insertedCarte = carteRepository.saveAndFlush(carte);

        // Get the carte
        restCarteMockMvc
            .perform(get(ENTITY_API_URL_ID, carte.getMatricule()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.matricule").value(carte.getMatricule()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.surname").value(DEFAULT_SURNAME))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL))
            .andExpect(jsonPath("$.villeResidence").value(DEFAULT_VILLE_RESIDENCE))
            .andExpect(jsonPath("$.birthdate").value(sameInstant(DEFAULT_BIRTHDATE)))
            .andExpect(jsonPath("$.telephone1").value(DEFAULT_TELEPHONE_1))
            .andExpect(jsonPath("$.telephone2").value(DEFAULT_TELEPHONE_2))
            .andExpect(jsonPath("$.isImprime").value(DEFAULT_IS_IMPRIME.booleanValue()))
            .andExpect(jsonPath("$.pictureExtension").value(DEFAULT_PICTURE_EXTENSION))
            .andExpect(jsonPath("$.pictureContentType").value(DEFAULT_PICTURE_CONTENT_TYPE))
            .andExpect(jsonPath("$.picture").value(Base64.getEncoder().encodeToString(DEFAULT_PICTURE)))
            .andExpect(jsonPath("$.dateDelivrance").value(DEFAULT_DATE_DELIVRANCE.toString()))
            .andExpect(jsonPath("$.dateExpiration").value(DEFAULT_DATE_EXPIRATION.toString()));
    }

    @Test
    @Transactional
    void getCartesByIdFiltering() throws Exception {
        // Initialize the database
        insertedCarte = carteRepository.saveAndFlush(carte);

        String id = carte.getMatricule();

        defaultCarteFiltering("matricule.equals=" + id, "matricule.notEquals=" + id);
    }

    @Test
    @Transactional
    void getAllCartesByNameIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedCarte = carteRepository.saveAndFlush(carte);

        // Get all the carteList where name equals to
        defaultCarteFiltering("name.equals=" + DEFAULT_NAME, "name.equals=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    void getAllCartesByNameIsInShouldWork() throws Exception {
        // Initialize the database
        insertedCarte = carteRepository.saveAndFlush(carte);

        // Get all the carteList where name in
        defaultCarteFiltering("name.in=" + DEFAULT_NAME + "," + UPDATED_NAME, "name.in=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    void getAllCartesByNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedCarte = carteRepository.saveAndFlush(carte);

        // Get all the carteList where name is not null
        defaultCarteFiltering("name.specified=true", "name.specified=false");
    }

    @Test
    @Transactional
    void getAllCartesByNameContainsSomething() throws Exception {
        // Initialize the database
        insertedCarte = carteRepository.saveAndFlush(carte);

        // Get all the carteList where name contains
        defaultCarteFiltering("name.contains=" + DEFAULT_NAME, "name.contains=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    void getAllCartesByNameNotContainsSomething() throws Exception {
        // Initialize the database
        insertedCarte = carteRepository.saveAndFlush(carte);

        // Get all the carteList where name does not contain
        defaultCarteFiltering("name.doesNotContain=" + UPDATED_NAME, "name.doesNotContain=" + DEFAULT_NAME);
    }

    @Test
    @Transactional
    void getAllCartesBySurnameIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedCarte = carteRepository.saveAndFlush(carte);

        // Get all the carteList where surname equals to
        defaultCarteFiltering("surname.equals=" + DEFAULT_SURNAME, "surname.equals=" + UPDATED_SURNAME);
    }

    @Test
    @Transactional
    void getAllCartesBySurnameIsInShouldWork() throws Exception {
        // Initialize the database
        insertedCarte = carteRepository.saveAndFlush(carte);

        // Get all the carteList where surname in
        defaultCarteFiltering("surname.in=" + DEFAULT_SURNAME + "," + UPDATED_SURNAME, "surname.in=" + UPDATED_SURNAME);
    }

    @Test
    @Transactional
    void getAllCartesBySurnameIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedCarte = carteRepository.saveAndFlush(carte);

        // Get all the carteList where surname is not null
        defaultCarteFiltering("surname.specified=true", "surname.specified=false");
    }

    @Test
    @Transactional
    void getAllCartesBySurnameContainsSomething() throws Exception {
        // Initialize the database
        insertedCarte = carteRepository.saveAndFlush(carte);

        // Get all the carteList where surname contains
        defaultCarteFiltering("surname.contains=" + DEFAULT_SURNAME, "surname.contains=" + UPDATED_SURNAME);
    }

    @Test
    @Transactional
    void getAllCartesBySurnameNotContainsSomething() throws Exception {
        // Initialize the database
        insertedCarte = carteRepository.saveAndFlush(carte);

        // Get all the carteList where surname does not contain
        defaultCarteFiltering("surname.doesNotContain=" + UPDATED_SURNAME, "surname.doesNotContain=" + DEFAULT_SURNAME);
    }

    @Test
    @Transactional
    void getAllCartesByEmailIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedCarte = carteRepository.saveAndFlush(carte);

        // Get all the carteList where email equals to
        defaultCarteFiltering("email.equals=" + DEFAULT_EMAIL, "email.equals=" + UPDATED_EMAIL);
    }

    @Test
    @Transactional
    void getAllCartesByEmailIsInShouldWork() throws Exception {
        // Initialize the database
        insertedCarte = carteRepository.saveAndFlush(carte);

        // Get all the carteList where email in
        defaultCarteFiltering("email.in=" + DEFAULT_EMAIL + "," + UPDATED_EMAIL, "email.in=" + UPDATED_EMAIL);
    }

    @Test
    @Transactional
    void getAllCartesByEmailIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedCarte = carteRepository.saveAndFlush(carte);

        // Get all the carteList where email is not null
        defaultCarteFiltering("email.specified=true", "email.specified=false");
    }

    @Test
    @Transactional
    void getAllCartesByEmailContainsSomething() throws Exception {
        // Initialize the database
        insertedCarte = carteRepository.saveAndFlush(carte);

        // Get all the carteList where email contains
        defaultCarteFiltering("email.contains=" + DEFAULT_EMAIL, "email.contains=" + UPDATED_EMAIL);
    }

    @Test
    @Transactional
    void getAllCartesByEmailNotContainsSomething() throws Exception {
        // Initialize the database
        insertedCarte = carteRepository.saveAndFlush(carte);

        // Get all the carteList where email does not contain
        defaultCarteFiltering("email.doesNotContain=" + UPDATED_EMAIL, "email.doesNotContain=" + DEFAULT_EMAIL);
    }

    @Test
    @Transactional
    void getAllCartesByVilleResidenceIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedCarte = carteRepository.saveAndFlush(carte);

        // Get all the carteList where villeResidence equals to
        defaultCarteFiltering("villeResidence.equals=" + DEFAULT_VILLE_RESIDENCE, "villeResidence.equals=" + UPDATED_VILLE_RESIDENCE);
    }

    @Test
    @Transactional
    void getAllCartesByVilleResidenceIsInShouldWork() throws Exception {
        // Initialize the database
        insertedCarte = carteRepository.saveAndFlush(carte);

        // Get all the carteList where villeResidence in
        defaultCarteFiltering(
            "villeResidence.in=" + DEFAULT_VILLE_RESIDENCE + "," + UPDATED_VILLE_RESIDENCE,
            "villeResidence.in=" + UPDATED_VILLE_RESIDENCE
        );
    }

    @Test
    @Transactional
    void getAllCartesByVilleResidenceIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedCarte = carteRepository.saveAndFlush(carte);

        // Get all the carteList where villeResidence is not null
        defaultCarteFiltering("villeResidence.specified=true", "villeResidence.specified=false");
    }

    @Test
    @Transactional
    void getAllCartesByVilleResidenceContainsSomething() throws Exception {
        // Initialize the database
        insertedCarte = carteRepository.saveAndFlush(carte);

        // Get all the carteList where villeResidence contains
        defaultCarteFiltering("villeResidence.contains=" + DEFAULT_VILLE_RESIDENCE, "villeResidence.contains=" + UPDATED_VILLE_RESIDENCE);
    }

    @Test
    @Transactional
    void getAllCartesByVilleResidenceNotContainsSomething() throws Exception {
        // Initialize the database
        insertedCarte = carteRepository.saveAndFlush(carte);

        // Get all the carteList where villeResidence does not contain
        defaultCarteFiltering(
            "villeResidence.doesNotContain=" + UPDATED_VILLE_RESIDENCE,
            "villeResidence.doesNotContain=" + DEFAULT_VILLE_RESIDENCE
        );
    }

    @Test
    @Transactional
    void getAllCartesByBirthdateIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedCarte = carteRepository.saveAndFlush(carte);

        // Get all the carteList where birthdate equals to
        defaultCarteFiltering("birthdate.equals=" + DEFAULT_BIRTHDATE, "birthdate.equals=" + UPDATED_BIRTHDATE);
    }

    @Test
    @Transactional
    void getAllCartesByBirthdateIsInShouldWork() throws Exception {
        // Initialize the database
        insertedCarte = carteRepository.saveAndFlush(carte);

        // Get all the carteList where birthdate in
        defaultCarteFiltering("birthdate.in=" + DEFAULT_BIRTHDATE + "," + UPDATED_BIRTHDATE, "birthdate.in=" + UPDATED_BIRTHDATE);
    }

    @Test
    @Transactional
    void getAllCartesByBirthdateIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedCarte = carteRepository.saveAndFlush(carte);

        // Get all the carteList where birthdate is not null
        defaultCarteFiltering("birthdate.specified=true", "birthdate.specified=false");
    }

    @Test
    @Transactional
    void getAllCartesByBirthdateIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedCarte = carteRepository.saveAndFlush(carte);

        // Get all the carteList where birthdate is greater than or equal to
        defaultCarteFiltering("birthdate.greaterThanOrEqual=" + DEFAULT_BIRTHDATE, "birthdate.greaterThanOrEqual=" + UPDATED_BIRTHDATE);
    }

    @Test
    @Transactional
    void getAllCartesByBirthdateIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedCarte = carteRepository.saveAndFlush(carte);

        // Get all the carteList where birthdate is less than or equal to
        defaultCarteFiltering("birthdate.lessThanOrEqual=" + DEFAULT_BIRTHDATE, "birthdate.lessThanOrEqual=" + SMALLER_BIRTHDATE);
    }

    @Test
    @Transactional
    void getAllCartesByBirthdateIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedCarte = carteRepository.saveAndFlush(carte);

        // Get all the carteList where birthdate is less than
        defaultCarteFiltering("birthdate.lessThan=" + UPDATED_BIRTHDATE, "birthdate.lessThan=" + DEFAULT_BIRTHDATE);
    }

    @Test
    @Transactional
    void getAllCartesByBirthdateIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedCarte = carteRepository.saveAndFlush(carte);

        // Get all the carteList where birthdate is greater than
        defaultCarteFiltering("birthdate.greaterThan=" + SMALLER_BIRTHDATE, "birthdate.greaterThan=" + DEFAULT_BIRTHDATE);
    }

    @Test
    @Transactional
    void getAllCartesByTelephone1IsEqualToSomething() throws Exception {
        // Initialize the database
        insertedCarte = carteRepository.saveAndFlush(carte);

        // Get all the carteList where telephone1 equals to
        defaultCarteFiltering("telephone1.equals=" + DEFAULT_TELEPHONE_1, "telephone1.equals=" + UPDATED_TELEPHONE_1);
    }

    @Test
    @Transactional
    void getAllCartesByTelephone1IsInShouldWork() throws Exception {
        // Initialize the database
        insertedCarte = carteRepository.saveAndFlush(carte);

        // Get all the carteList where telephone1 in
        defaultCarteFiltering("telephone1.in=" + DEFAULT_TELEPHONE_1 + "," + UPDATED_TELEPHONE_1, "telephone1.in=" + UPDATED_TELEPHONE_1);
    }

    @Test
    @Transactional
    void getAllCartesByTelephone1IsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedCarte = carteRepository.saveAndFlush(carte);

        // Get all the carteList where telephone1 is not null
        defaultCarteFiltering("telephone1.specified=true", "telephone1.specified=false");
    }

    @Test
    @Transactional
    void getAllCartesByTelephone1ContainsSomething() throws Exception {
        // Initialize the database
        insertedCarte = carteRepository.saveAndFlush(carte);

        // Get all the carteList where telephone1 contains
        defaultCarteFiltering("telephone1.contains=" + DEFAULT_TELEPHONE_1, "telephone1.contains=" + UPDATED_TELEPHONE_1);
    }

    @Test
    @Transactional
    void getAllCartesByTelephone1NotContainsSomething() throws Exception {
        // Initialize the database
        insertedCarte = carteRepository.saveAndFlush(carte);

        // Get all the carteList where telephone1 does not contain
        defaultCarteFiltering("telephone1.doesNotContain=" + UPDATED_TELEPHONE_1, "telephone1.doesNotContain=" + DEFAULT_TELEPHONE_1);
    }

    @Test
    @Transactional
    void getAllCartesByTelephone2IsEqualToSomething() throws Exception {
        // Initialize the database
        insertedCarte = carteRepository.saveAndFlush(carte);

        // Get all the carteList where telephone2 equals to
        defaultCarteFiltering("telephone2.equals=" + DEFAULT_TELEPHONE_2, "telephone2.equals=" + UPDATED_TELEPHONE_2);
    }

    @Test
    @Transactional
    void getAllCartesByTelephone2IsInShouldWork() throws Exception {
        // Initialize the database
        insertedCarte = carteRepository.saveAndFlush(carte);

        // Get all the carteList where telephone2 in
        defaultCarteFiltering("telephone2.in=" + DEFAULT_TELEPHONE_2 + "," + UPDATED_TELEPHONE_2, "telephone2.in=" + UPDATED_TELEPHONE_2);
    }

    @Test
    @Transactional
    void getAllCartesByTelephone2IsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedCarte = carteRepository.saveAndFlush(carte);

        // Get all the carteList where telephone2 is not null
        defaultCarteFiltering("telephone2.specified=true", "telephone2.specified=false");
    }

    @Test
    @Transactional
    void getAllCartesByTelephone2ContainsSomething() throws Exception {
        // Initialize the database
        insertedCarte = carteRepository.saveAndFlush(carte);

        // Get all the carteList where telephone2 contains
        defaultCarteFiltering("telephone2.contains=" + DEFAULT_TELEPHONE_2, "telephone2.contains=" + UPDATED_TELEPHONE_2);
    }

    @Test
    @Transactional
    void getAllCartesByTelephone2NotContainsSomething() throws Exception {
        // Initialize the database
        insertedCarte = carteRepository.saveAndFlush(carte);

        // Get all the carteList where telephone2 does not contain
        defaultCarteFiltering("telephone2.doesNotContain=" + UPDATED_TELEPHONE_2, "telephone2.doesNotContain=" + DEFAULT_TELEPHONE_2);
    }

    @Test
    @Transactional
    void getAllCartesByIsImprimeIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedCarte = carteRepository.saveAndFlush(carte);

        // Get all the carteList where isImprime equals to
        defaultCarteFiltering("isImprime.equals=" + DEFAULT_IS_IMPRIME, "isImprime.equals=" + UPDATED_IS_IMPRIME);
    }

    @Test
    @Transactional
    void getAllCartesByIsImprimeIsInShouldWork() throws Exception {
        // Initialize the database
        insertedCarte = carteRepository.saveAndFlush(carte);

        // Get all the carteList where isImprime in
        defaultCarteFiltering("isImprime.in=" + DEFAULT_IS_IMPRIME + "," + UPDATED_IS_IMPRIME, "isImprime.in=" + UPDATED_IS_IMPRIME);
    }

    @Test
    @Transactional
    void getAllCartesByIsImprimeIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedCarte = carteRepository.saveAndFlush(carte);

        // Get all the carteList where isImprime is not null
        defaultCarteFiltering("isImprime.specified=true", "isImprime.specified=false");
    }

    @Test
    @Transactional
    void getAllCartesByPictureExtensionIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedCarte = carteRepository.saveAndFlush(carte);

        // Get all the carteList where pictureExtension equals to
        defaultCarteFiltering(
            "pictureExtension.equals=" + DEFAULT_PICTURE_EXTENSION,
            "pictureExtension.equals=" + UPDATED_PICTURE_EXTENSION
        );
    }

    @Test
    @Transactional
    void getAllCartesByPictureExtensionIsInShouldWork() throws Exception {
        // Initialize the database
        insertedCarte = carteRepository.saveAndFlush(carte);

        // Get all the carteList where pictureExtension in
        defaultCarteFiltering(
            "pictureExtension.in=" + DEFAULT_PICTURE_EXTENSION + "," + UPDATED_PICTURE_EXTENSION,
            "pictureExtension.in=" + UPDATED_PICTURE_EXTENSION
        );
    }

    @Test
    @Transactional
    void getAllCartesByPictureExtensionIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedCarte = carteRepository.saveAndFlush(carte);

        // Get all the carteList where pictureExtension is not null
        defaultCarteFiltering("pictureExtension.specified=true", "pictureExtension.specified=false");
    }

    @Test
    @Transactional
    void getAllCartesByPictureExtensionContainsSomething() throws Exception {
        // Initialize the database
        insertedCarte = carteRepository.saveAndFlush(carte);

        // Get all the carteList where pictureExtension contains
        defaultCarteFiltering(
            "pictureExtension.contains=" + DEFAULT_PICTURE_EXTENSION,
            "pictureExtension.contains=" + UPDATED_PICTURE_EXTENSION
        );
    }

    @Test
    @Transactional
    void getAllCartesByPictureExtensionNotContainsSomething() throws Exception {
        // Initialize the database
        insertedCarte = carteRepository.saveAndFlush(carte);

        // Get all the carteList where pictureExtension does not contain
        defaultCarteFiltering(
            "pictureExtension.doesNotContain=" + UPDATED_PICTURE_EXTENSION,
            "pictureExtension.doesNotContain=" + DEFAULT_PICTURE_EXTENSION
        );
    }

    @Test
    @Transactional
    void getAllCartesByDateDelivranceIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedCarte = carteRepository.saveAndFlush(carte);

        // Get all the carteList where dateDelivrance equals to
        defaultCarteFiltering("dateDelivrance.equals=" + DEFAULT_DATE_DELIVRANCE, "dateDelivrance.equals=" + UPDATED_DATE_DELIVRANCE);
    }

    @Test
    @Transactional
    void getAllCartesByDateDelivranceIsInShouldWork() throws Exception {
        // Initialize the database
        insertedCarte = carteRepository.saveAndFlush(carte);

        // Get all the carteList where dateDelivrance in
        defaultCarteFiltering(
            "dateDelivrance.in=" + DEFAULT_DATE_DELIVRANCE + "," + UPDATED_DATE_DELIVRANCE,
            "dateDelivrance.in=" + UPDATED_DATE_DELIVRANCE
        );
    }

    @Test
    @Transactional
    void getAllCartesByDateDelivranceIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedCarte = carteRepository.saveAndFlush(carte);

        // Get all the carteList where dateDelivrance is not null
        defaultCarteFiltering("dateDelivrance.specified=true", "dateDelivrance.specified=false");
    }

    @Test
    @Transactional
    void getAllCartesByDateDelivranceIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedCarte = carteRepository.saveAndFlush(carte);

        // Get all the carteList where dateDelivrance is greater than or equal to
        defaultCarteFiltering(
            "dateDelivrance.greaterThanOrEqual=" + DEFAULT_DATE_DELIVRANCE,
            "dateDelivrance.greaterThanOrEqual=" + UPDATED_DATE_DELIVRANCE
        );
    }

    @Test
    @Transactional
    void getAllCartesByDateDelivranceIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedCarte = carteRepository.saveAndFlush(carte);

        // Get all the carteList where dateDelivrance is less than or equal to
        defaultCarteFiltering(
            "dateDelivrance.lessThanOrEqual=" + DEFAULT_DATE_DELIVRANCE,
            "dateDelivrance.lessThanOrEqual=" + SMALLER_DATE_DELIVRANCE
        );
    }

    @Test
    @Transactional
    void getAllCartesByDateDelivranceIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedCarte = carteRepository.saveAndFlush(carte);

        // Get all the carteList where dateDelivrance is less than
        defaultCarteFiltering("dateDelivrance.lessThan=" + UPDATED_DATE_DELIVRANCE, "dateDelivrance.lessThan=" + DEFAULT_DATE_DELIVRANCE);
    }

    @Test
    @Transactional
    void getAllCartesByDateDelivranceIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedCarte = carteRepository.saveAndFlush(carte);

        // Get all the carteList where dateDelivrance is greater than
        defaultCarteFiltering(
            "dateDelivrance.greaterThan=" + SMALLER_DATE_DELIVRANCE,
            "dateDelivrance.greaterThan=" + DEFAULT_DATE_DELIVRANCE
        );
    }

    @Test
    @Transactional
    void getAllCartesByDateExpirationIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedCarte = carteRepository.saveAndFlush(carte);

        // Get all the carteList where dateExpiration equals to
        defaultCarteFiltering("dateExpiration.equals=" + DEFAULT_DATE_EXPIRATION, "dateExpiration.equals=" + UPDATED_DATE_EXPIRATION);
    }

    @Test
    @Transactional
    void getAllCartesByDateExpirationIsInShouldWork() throws Exception {
        // Initialize the database
        insertedCarte = carteRepository.saveAndFlush(carte);

        // Get all the carteList where dateExpiration in
        defaultCarteFiltering(
            "dateExpiration.in=" + DEFAULT_DATE_EXPIRATION + "," + UPDATED_DATE_EXPIRATION,
            "dateExpiration.in=" + UPDATED_DATE_EXPIRATION
        );
    }

    @Test
    @Transactional
    void getAllCartesByDateExpirationIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedCarte = carteRepository.saveAndFlush(carte);

        // Get all the carteList where dateExpiration is not null
        defaultCarteFiltering("dateExpiration.specified=true", "dateExpiration.specified=false");
    }

    @Test
    @Transactional
    void getAllCartesByDateExpirationIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedCarte = carteRepository.saveAndFlush(carte);

        // Get all the carteList where dateExpiration is greater than or equal to
        defaultCarteFiltering(
            "dateExpiration.greaterThanOrEqual=" + DEFAULT_DATE_EXPIRATION,
            "dateExpiration.greaterThanOrEqual=" + UPDATED_DATE_EXPIRATION
        );
    }

    @Test
    @Transactional
    void getAllCartesByDateExpirationIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedCarte = carteRepository.saveAndFlush(carte);

        // Get all the carteList where dateExpiration is less than or equal to
        defaultCarteFiltering(
            "dateExpiration.lessThanOrEqual=" + DEFAULT_DATE_EXPIRATION,
            "dateExpiration.lessThanOrEqual=" + SMALLER_DATE_EXPIRATION
        );
    }

    @Test
    @Transactional
    void getAllCartesByDateExpirationIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedCarte = carteRepository.saveAndFlush(carte);

        // Get all the carteList where dateExpiration is less than
        defaultCarteFiltering("dateExpiration.lessThan=" + UPDATED_DATE_EXPIRATION, "dateExpiration.lessThan=" + DEFAULT_DATE_EXPIRATION);
    }

    @Test
    @Transactional
    void getAllCartesByDateExpirationIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedCarte = carteRepository.saveAndFlush(carte);

        // Get all the carteList where dateExpiration is greater than
        defaultCarteFiltering(
            "dateExpiration.greaterThan=" + SMALLER_DATE_EXPIRATION,
            "dateExpiration.greaterThan=" + DEFAULT_DATE_EXPIRATION
        );
    }

    @Test
    @Transactional
    void getAllCartesByTemplateIsEqualToSomething() throws Exception {
        Template template;
        if (TestUtil.findAll(em, Template.class).isEmpty()) {
            carteRepository.saveAndFlush(carte);
            template = TemplateResourceIT.createEntity(em);
        } else {
            template = TestUtil.findAll(em, Template.class).get(0);
        }
        em.persist(template);
        em.flush();
        carte.setTemplate(template);
        carteRepository.saveAndFlush(carte);
        String templateId = template.getLibelle();
        // Get all the carteList where template equals to templateId
        defaultCarteShouldBeFound("templateId.equals=" + templateId);

        // Get all the carteList where template equals to "invalid-id"
        defaultCarteShouldNotBeFound("templateId.equals=" + "invalid-id");
    }

    private void defaultCarteFiltering(String shouldBeFound, String shouldNotBeFound) throws Exception {
        defaultCarteShouldBeFound(shouldBeFound);
        defaultCarteShouldNotBeFound(shouldNotBeFound);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultCarteShouldBeFound(String filter) throws Exception {
        restCarteMockMvc
            .perform(get(ENTITY_API_URL + "?sort=matricule,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].matricule").value(hasItem(carte.getMatricule())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].surname").value(hasItem(DEFAULT_SURNAME)))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL)))
            .andExpect(jsonPath("$.[*].villeResidence").value(hasItem(DEFAULT_VILLE_RESIDENCE)))
            .andExpect(jsonPath("$.[*].birthdate").value(hasItem(sameInstant(DEFAULT_BIRTHDATE))))
            .andExpect(jsonPath("$.[*].telephone1").value(hasItem(DEFAULT_TELEPHONE_1)))
            .andExpect(jsonPath("$.[*].telephone2").value(hasItem(DEFAULT_TELEPHONE_2)))
            .andExpect(jsonPath("$.[*].isImprime").value(hasItem(DEFAULT_IS_IMPRIME.booleanValue())))
            .andExpect(jsonPath("$.[*].pictureExtension").value(hasItem(DEFAULT_PICTURE_EXTENSION)))
            .andExpect(jsonPath("$.[*].pictureContentType").value(hasItem(DEFAULT_PICTURE_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].picture").value(hasItem(Base64.getEncoder().encodeToString(DEFAULT_PICTURE))))
            .andExpect(jsonPath("$.[*].dateDelivrance").value(hasItem(DEFAULT_DATE_DELIVRANCE.toString())))
            .andExpect(jsonPath("$.[*].dateExpiration").value(hasItem(DEFAULT_DATE_EXPIRATION.toString())));

        // Check, that the count call also returns 1
        restCarteMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=matricule,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultCarteShouldNotBeFound(String filter) throws Exception {
        restCarteMockMvc
            .perform(get(ENTITY_API_URL + "?sort=matricule,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restCarteMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=matricule,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    void getNonExistingCarte() throws Exception {
        // Get the carte
        restCarteMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingCarte() throws Exception {
        // Initialize the database
        carte.setMatricule(UUID.randomUUID().toString());
        insertedCarte = carteRepository.saveAndFlush(carte);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the carte
        Carte updatedCarte = carteRepository.findById(carte.getMatricule()).orElseThrow();
        // Disconnect from session so that the updates on updatedCarte are not directly saved in db
        em.detach(updatedCarte);
        updatedCarte
            .name(UPDATED_NAME)
            .surname(UPDATED_SURNAME)
            .email(UPDATED_EMAIL)
            .villeResidence(UPDATED_VILLE_RESIDENCE)
            .birthdate(UPDATED_BIRTHDATE)
            .telephone1(UPDATED_TELEPHONE_1)
            .telephone2(UPDATED_TELEPHONE_2)
            .isImprime(UPDATED_IS_IMPRIME)
            .pictureExtension(UPDATED_PICTURE_EXTENSION)
            .picture(UPDATED_PICTURE)
            .pictureContentType(UPDATED_PICTURE_CONTENT_TYPE)
            .dateDelivrance(UPDATED_DATE_DELIVRANCE)
            .dateExpiration(UPDATED_DATE_EXPIRATION);
        CarteDTO carteDTO = carteMapper.toDto(updatedCarte);

        restCarteMockMvc
            .perform(
                put(ENTITY_API_URL_ID, carteDTO.getMatricule())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(carteDTO))
            )
            .andExpect(status().isOk());

        // Validate the Carte in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedCarteToMatchAllProperties(updatedCarte);
    }

    @Test
    @Transactional
    void putNonExistingCarte() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        carte.setMatricule(UUID.randomUUID().toString());

        // Create the Carte
        CarteDTO carteDTO = carteMapper.toDto(carte);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCarteMockMvc
            .perform(
                put(ENTITY_API_URL_ID, carteDTO.getMatricule())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(carteDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Carte in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchCarte() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        carte.setMatricule(UUID.randomUUID().toString());

        // Create the Carte
        CarteDTO carteDTO = carteMapper.toDto(carte);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCarteMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(carteDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Carte in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamCarte() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        carte.setMatricule(UUID.randomUUID().toString());

        // Create the Carte
        CarteDTO carteDTO = carteMapper.toDto(carte);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCarteMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(carteDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Carte in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateCarteWithPatch() throws Exception {
        // Initialize the database
        carte.setMatricule(UUID.randomUUID().toString());
        insertedCarte = carteRepository.saveAndFlush(carte);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the carte using partial update
        Carte partialUpdatedCarte = new Carte();
        partialUpdatedCarte.setMatricule(carte.getMatricule());

        partialUpdatedCarte
            .name(UPDATED_NAME)
            .email(UPDATED_EMAIL)
            .villeResidence(UPDATED_VILLE_RESIDENCE)
            .birthdate(UPDATED_BIRTHDATE)
            .telephone1(UPDATED_TELEPHONE_1)
            .telephone2(UPDATED_TELEPHONE_2)
            .dateDelivrance(UPDATED_DATE_DELIVRANCE);

        restCarteMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedCarte.getMatricule())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedCarte))
            )
            .andExpect(status().isOk());

        // Validate the Carte in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertCarteUpdatableFieldsEquals(createUpdateProxyForBean(partialUpdatedCarte, carte), getPersistedCarte(carte));
    }

    @Test
    @Transactional
    void fullUpdateCarteWithPatch() throws Exception {
        // Initialize the database
        carte.setMatricule(UUID.randomUUID().toString());
        insertedCarte = carteRepository.saveAndFlush(carte);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the carte using partial update
        Carte partialUpdatedCarte = new Carte();
        partialUpdatedCarte.setMatricule(carte.getMatricule());

        partialUpdatedCarte
            .name(UPDATED_NAME)
            .surname(UPDATED_SURNAME)
            .email(UPDATED_EMAIL)
            .villeResidence(UPDATED_VILLE_RESIDENCE)
            .birthdate(UPDATED_BIRTHDATE)
            .telephone1(UPDATED_TELEPHONE_1)
            .telephone2(UPDATED_TELEPHONE_2)
            .isImprime(UPDATED_IS_IMPRIME)
            .pictureExtension(UPDATED_PICTURE_EXTENSION)
            .picture(UPDATED_PICTURE)
            .pictureContentType(UPDATED_PICTURE_CONTENT_TYPE)
            .dateDelivrance(UPDATED_DATE_DELIVRANCE)
            .dateExpiration(UPDATED_DATE_EXPIRATION);

        restCarteMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedCarte.getMatricule())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedCarte))
            )
            .andExpect(status().isOk());

        // Validate the Carte in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertCarteUpdatableFieldsEquals(partialUpdatedCarte, getPersistedCarte(partialUpdatedCarte));
    }

    @Test
    @Transactional
    void patchNonExistingCarte() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        carte.setMatricule(UUID.randomUUID().toString());

        // Create the Carte
        CarteDTO carteDTO = carteMapper.toDto(carte);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCarteMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, carteDTO.getMatricule())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(carteDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Carte in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchCarte() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        carte.setMatricule(UUID.randomUUID().toString());

        // Create the Carte
        CarteDTO carteDTO = carteMapper.toDto(carte);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCarteMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(carteDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Carte in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamCarte() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        carte.setMatricule(UUID.randomUUID().toString());

        // Create the Carte
        CarteDTO carteDTO = carteMapper.toDto(carte);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCarteMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(carteDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Carte in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteCarte() throws Exception {
        // Initialize the database
        carte.setMatricule(UUID.randomUUID().toString());
        insertedCarte = carteRepository.saveAndFlush(carte);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the carte
        restCarteMockMvc
            .perform(delete(ENTITY_API_URL_ID, carte.getMatricule()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return carteRepository.count();
    }

    protected void assertIncrementedRepositoryCount(long countBefore) {
        assertThat(countBefore + 1).isEqualTo(getRepositoryCount());
    }

    protected void assertDecrementedRepositoryCount(long countBefore) {
        assertThat(countBefore - 1).isEqualTo(getRepositoryCount());
    }

    protected void assertSameRepositoryCount(long countBefore) {
        assertThat(countBefore).isEqualTo(getRepositoryCount());
    }

    protected Carte getPersistedCarte(Carte carte) {
        return carteRepository.findById(carte.getMatricule()).orElseThrow();
    }

    protected void assertPersistedCarteToMatchAllProperties(Carte expectedCarte) {
        assertCarteAllPropertiesEquals(expectedCarte, getPersistedCarte(expectedCarte));
    }

    protected void assertPersistedCarteToMatchUpdatableProperties(Carte expectedCarte) {
        assertCarteAllUpdatablePropertiesEquals(expectedCarte, getPersistedCarte(expectedCarte));
    }
}
