package com.zmfx.csc.web.rest;

import static com.zmfx.csc.domain.TemplateAsserts.*;
import static com.zmfx.csc.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zmfx.csc.IntegrationTest;
import com.zmfx.csc.domain.Template;
import com.zmfx.csc.repository.TemplateRepository;
import com.zmfx.csc.service.dto.TemplateDTO;
import com.zmfx.csc.service.mapper.TemplateMapper;
import jakarta.persistence.EntityManager;
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
 * Integration tests for the {@link TemplateResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class TemplateResourceIT {

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final Double DEFAULT_IMAGE_HEIGTH = 1D;
    private static final Double UPDATED_IMAGE_HEIGTH = 2D;
    private static final Double SMALLER_IMAGE_HEIGTH = 1D - 1D;

    private static final Double DEFAULT_IMAGE_WIDTH = 1D;
    private static final Double UPDATED_IMAGE_WIDTH = 2D;
    private static final Double SMALLER_IMAGE_WIDTH = 1D - 1D;

    private static final Double DEFAULT_IMAGE_X = 1D;
    private static final Double UPDATED_IMAGE_X = 2D;
    private static final Double SMALLER_IMAGE_X = 1D - 1D;

    private static final Double DEFAULT_IMAGE_Y = 1D;
    private static final Double UPDATED_IMAGE_Y = 2D;
    private static final Double SMALLER_IMAGE_Y = 1D - 1D;

    private static final Double DEFAULT_MATRICULE_X = 1D;
    private static final Double UPDATED_MATRICULE_X = 2D;
    private static final Double SMALLER_MATRICULE_X = 1D - 1D;

    private static final Double DEFAULT_MATRICULE_Y = 1D;
    private static final Double UPDATED_MATRICULE_Y = 2D;
    private static final Double SMALLER_MATRICULE_Y = 1D - 1D;

    private static final Double DEFAULT_MEMBRE_X = 1D;
    private static final Double UPDATED_MEMBRE_X = 2D;
    private static final Double SMALLER_MEMBRE_X = 1D - 1D;

    private static final Double DEFAULT_MEMBRE_Y = 1D;
    private static final Double UPDATED_MEMBRE_Y = 2D;
    private static final Double SMALLER_MEMBRE_Y = 1D - 1D;

    private static final Double DEFAULT_NOM_X = 1D;
    private static final Double UPDATED_NOM_X = 2D;
    private static final Double SMALLER_NOM_X = 1D - 1D;

    private static final Double DEFAULT_NOM_Y = 1D;
    private static final Double UPDATED_NOM_Y = 2D;
    private static final Double SMALLER_NOM_Y = 1D - 1D;

    private static final Double DEFAULT_PRENOM_X = 1D;
    private static final Double UPDATED_PRENOM_X = 2D;
    private static final Double SMALLER_PRENOM_X = 1D - 1D;

    private static final Double DEFAULT_PRENOM_Y = 1D;
    private static final Double UPDATED_PRENOM_Y = 2D;
    private static final Double SMALLER_PRENOM_Y = 1D - 1D;

    private static final Double DEFAULT_QR_HEIGHT = 1D;
    private static final Double UPDATED_QR_HEIGHT = 2D;
    private static final Double SMALLER_QR_HEIGHT = 1D - 1D;

    private static final Double DEFAULT_QR_WIDTH = 1D;
    private static final Double UPDATED_QR_WIDTH = 2D;
    private static final Double SMALLER_QR_WIDTH = 1D - 1D;

    private static final Double DEFAULT_QR_X = 1D;
    private static final Double UPDATED_QR_X = 2D;
    private static final Double SMALLER_QR_X = 1D - 1D;

    private static final Double DEFAULT_QR_Y = 1D;
    private static final Double UPDATED_QR_Y = 2D;
    private static final Double SMALLER_QR_Y = 1D - 1D;

    private static final Double DEFAULT_DATE_DELIVRANCE_X = 1D;
    private static final Double UPDATED_DATE_DELIVRANCE_X = 2D;
    private static final Double SMALLER_DATE_DELIVRANCE_X = 1D - 1D;

    private static final Double DEFAULT_DATE_DELIVRANCE_Y = 1D;
    private static final Double UPDATED_DATE_DELIVRANCE_Y = 2D;
    private static final Double SMALLER_DATE_DELIVRANCE_Y = 1D - 1D;

    private static final Double DEFAULT_DATE_EXPIRATION_X = 1D;
    private static final Double UPDATED_DATE_EXPIRATION_X = 2D;
    private static final Double SMALLER_DATE_EXPIRATION_X = 1D - 1D;

    private static final Double DEFAULT_DATE_EXPIRATION_Y = 1D;
    private static final Double UPDATED_DATE_EXPIRATION_Y = 2D;
    private static final Double SMALLER_DATE_EXPIRATION_Y = 1D - 1D;

    private static final Double DEFAULT_SAMPLE_LARGEUR = 1D;
    private static final Double UPDATED_SAMPLE_LARGEUR = 2D;
    private static final Double SMALLER_SAMPLE_LARGEUR = 1D - 1D;

    private static final Double DEFAULT_SAMPLE_LONGUEUR = 1D;
    private static final Double UPDATED_SAMPLE_LONGUEUR = 2D;
    private static final Double SMALLER_SAMPLE_LONGUEUR = 1D - 1D;

    private static final byte[] DEFAULT_PDF_CONTENT_RECTO = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_PDF_CONTENT_RECTO = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_PDF_CONTENT_RECTO_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_PDF_CONTENT_RECTO_CONTENT_TYPE = "image/png";

    private static final byte[] DEFAULT_PDF_CONTENT_VERSO = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_PDF_CONTENT_VERSO = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_PDF_CONTENT_VERSO_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_PDF_CONTENT_VERSO_CONTENT_TYPE = "image/png";

    private static final String ENTITY_API_URL = "/api/templates";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{libelle}";

    @Autowired
    private ObjectMapper om;

    @Autowired
    private TemplateRepository templateRepository;

    @Autowired
    private TemplateMapper templateMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTemplateMockMvc;

    private Template template;

    private Template insertedTemplate;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Template createEntity(EntityManager em) {
        Template template = new Template()
            .libelle(UUID.randomUUID().toString())
            .description(DEFAULT_DESCRIPTION)
            .imageHeigth(DEFAULT_IMAGE_HEIGTH)
            .imageWidth(DEFAULT_IMAGE_WIDTH)
            .imageX(DEFAULT_IMAGE_X)
            .imageY(DEFAULT_IMAGE_Y)
            .matriculeX(DEFAULT_MATRICULE_X)
            .matriculeY(DEFAULT_MATRICULE_Y)
            .membreX(DEFAULT_MEMBRE_X)
            .membreY(DEFAULT_MEMBRE_Y)
            .nomX(DEFAULT_NOM_X)
            .nomY(DEFAULT_NOM_Y)
            .prenomX(DEFAULT_PRENOM_X)
            .prenomY(DEFAULT_PRENOM_Y)
            .qrHeight(DEFAULT_QR_HEIGHT)
            .qrWidth(DEFAULT_QR_WIDTH)
            .qrX(DEFAULT_QR_X)
            .qrY(DEFAULT_QR_Y)
            .dateDelivranceX(DEFAULT_DATE_DELIVRANCE_X)
            .dateDelivranceY(DEFAULT_DATE_DELIVRANCE_Y)
            .dateExpirationX(DEFAULT_DATE_EXPIRATION_X)
            .dateExpirationY(DEFAULT_DATE_EXPIRATION_Y)
            .sampleLargeur(DEFAULT_SAMPLE_LARGEUR)
            .sampleLongueur(DEFAULT_SAMPLE_LONGUEUR)
            .pdfContentRecto(DEFAULT_PDF_CONTENT_RECTO)
            .pdfContentRectoContentType(DEFAULT_PDF_CONTENT_RECTO_CONTENT_TYPE)
            .pdfContentVerso(DEFAULT_PDF_CONTENT_VERSO)
            .pdfContentVersoContentType(DEFAULT_PDF_CONTENT_VERSO_CONTENT_TYPE);
        return template;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Template createUpdatedEntity(EntityManager em) {
        Template template = new Template()
            .libelle(UUID.randomUUID().toString())
            .description(UPDATED_DESCRIPTION)
            .imageHeigth(UPDATED_IMAGE_HEIGTH)
            .imageWidth(UPDATED_IMAGE_WIDTH)
            .imageX(UPDATED_IMAGE_X)
            .imageY(UPDATED_IMAGE_Y)
            .matriculeX(UPDATED_MATRICULE_X)
            .matriculeY(UPDATED_MATRICULE_Y)
            .membreX(UPDATED_MEMBRE_X)
            .membreY(UPDATED_MEMBRE_Y)
            .nomX(UPDATED_NOM_X)
            .nomY(UPDATED_NOM_Y)
            .prenomX(UPDATED_PRENOM_X)
            .prenomY(UPDATED_PRENOM_Y)
            .qrHeight(UPDATED_QR_HEIGHT)
            .qrWidth(UPDATED_QR_WIDTH)
            .qrX(UPDATED_QR_X)
            .qrY(UPDATED_QR_Y)
            .dateDelivranceX(UPDATED_DATE_DELIVRANCE_X)
            .dateDelivranceY(UPDATED_DATE_DELIVRANCE_Y)
            .dateExpirationX(UPDATED_DATE_EXPIRATION_X)
            .dateExpirationY(UPDATED_DATE_EXPIRATION_Y)
            .sampleLargeur(UPDATED_SAMPLE_LARGEUR)
            .sampleLongueur(UPDATED_SAMPLE_LONGUEUR)
            .pdfContentRecto(UPDATED_PDF_CONTENT_RECTO)
            .pdfContentRectoContentType(UPDATED_PDF_CONTENT_RECTO_CONTENT_TYPE)
            .pdfContentVerso(UPDATED_PDF_CONTENT_VERSO)
            .pdfContentVersoContentType(UPDATED_PDF_CONTENT_VERSO_CONTENT_TYPE);
        return template;
    }

    @BeforeEach
    public void initTest() {
        template = createEntity(em);
    }

    @AfterEach
    public void cleanup() {
        if (insertedTemplate != null) {
            templateRepository.delete(insertedTemplate);
            insertedTemplate = null;
        }
    }

    @Test
    @Transactional
    void createTemplate() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the Template
        TemplateDTO templateDTO = templateMapper.toDto(template);
        var returnedTemplateDTO = om.readValue(
            restTemplateMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(templateDTO)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            TemplateDTO.class
        );

        // Validate the Template in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedTemplate = templateMapper.toEntity(returnedTemplateDTO);
        assertTemplateUpdatableFieldsEquals(returnedTemplate, getPersistedTemplate(returnedTemplate));

        insertedTemplate = returnedTemplate;
    }

    @Test
    @Transactional
    void createTemplateWithExistingId() throws Exception {
        // Create the Template with an existing ID
        insertedTemplate = templateRepository.saveAndFlush(template);
        TemplateDTO templateDTO = templateMapper.toDto(template);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restTemplateMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(templateDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Template in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllTemplates() throws Exception {
        // Initialize the database
        template.setLibelle(UUID.randomUUID().toString());
        insertedTemplate = templateRepository.saveAndFlush(template);

        // Get all the templateList
        restTemplateMockMvc
            .perform(get(ENTITY_API_URL + "?sort=libelle,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].libelle").value(hasItem(template.getLibelle())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].imageHeigth").value(hasItem(DEFAULT_IMAGE_HEIGTH.doubleValue())))
            .andExpect(jsonPath("$.[*].imageWidth").value(hasItem(DEFAULT_IMAGE_WIDTH.doubleValue())))
            .andExpect(jsonPath("$.[*].imageX").value(hasItem(DEFAULT_IMAGE_X.doubleValue())))
            .andExpect(jsonPath("$.[*].imageY").value(hasItem(DEFAULT_IMAGE_Y.doubleValue())))
            .andExpect(jsonPath("$.[*].matriculeX").value(hasItem(DEFAULT_MATRICULE_X.doubleValue())))
            .andExpect(jsonPath("$.[*].matriculeY").value(hasItem(DEFAULT_MATRICULE_Y.doubleValue())))
            .andExpect(jsonPath("$.[*].membreX").value(hasItem(DEFAULT_MEMBRE_X.doubleValue())))
            .andExpect(jsonPath("$.[*].membreY").value(hasItem(DEFAULT_MEMBRE_Y.doubleValue())))
            .andExpect(jsonPath("$.[*].nomX").value(hasItem(DEFAULT_NOM_X.doubleValue())))
            .andExpect(jsonPath("$.[*].nomY").value(hasItem(DEFAULT_NOM_Y.doubleValue())))
            .andExpect(jsonPath("$.[*].prenomX").value(hasItem(DEFAULT_PRENOM_X.doubleValue())))
            .andExpect(jsonPath("$.[*].prenomY").value(hasItem(DEFAULT_PRENOM_Y.doubleValue())))
            .andExpect(jsonPath("$.[*].qrHeight").value(hasItem(DEFAULT_QR_HEIGHT.doubleValue())))
            .andExpect(jsonPath("$.[*].qrWidth").value(hasItem(DEFAULT_QR_WIDTH.doubleValue())))
            .andExpect(jsonPath("$.[*].qrX").value(hasItem(DEFAULT_QR_X.doubleValue())))
            .andExpect(jsonPath("$.[*].qrY").value(hasItem(DEFAULT_QR_Y.doubleValue())))
            .andExpect(jsonPath("$.[*].dateDelivranceX").value(hasItem(DEFAULT_DATE_DELIVRANCE_X.doubleValue())))
            .andExpect(jsonPath("$.[*].dateDelivranceY").value(hasItem(DEFAULT_DATE_DELIVRANCE_Y.doubleValue())))
            .andExpect(jsonPath("$.[*].dateExpirationX").value(hasItem(DEFAULT_DATE_EXPIRATION_X.doubleValue())))
            .andExpect(jsonPath("$.[*].dateExpirationY").value(hasItem(DEFAULT_DATE_EXPIRATION_Y.doubleValue())))
            .andExpect(jsonPath("$.[*].sampleLargeur").value(hasItem(DEFAULT_SAMPLE_LARGEUR.doubleValue())))
            .andExpect(jsonPath("$.[*].sampleLongueur").value(hasItem(DEFAULT_SAMPLE_LONGUEUR.doubleValue())))
            .andExpect(jsonPath("$.[*].pdfContentRectoContentType").value(hasItem(DEFAULT_PDF_CONTENT_RECTO_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].pdfContentRecto").value(hasItem(Base64.getEncoder().encodeToString(DEFAULT_PDF_CONTENT_RECTO))))
            .andExpect(jsonPath("$.[*].pdfContentVersoContentType").value(hasItem(DEFAULT_PDF_CONTENT_VERSO_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].pdfContentVerso").value(hasItem(Base64.getEncoder().encodeToString(DEFAULT_PDF_CONTENT_VERSO))));
    }

    @Test
    @Transactional
    void getTemplate() throws Exception {
        // Initialize the database
        template.setLibelle(UUID.randomUUID().toString());
        insertedTemplate = templateRepository.saveAndFlush(template);

        // Get the template
        restTemplateMockMvc
            .perform(get(ENTITY_API_URL_ID, template.getLibelle()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.libelle").value(template.getLibelle()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.imageHeigth").value(DEFAULT_IMAGE_HEIGTH.doubleValue()))
            .andExpect(jsonPath("$.imageWidth").value(DEFAULT_IMAGE_WIDTH.doubleValue()))
            .andExpect(jsonPath("$.imageX").value(DEFAULT_IMAGE_X.doubleValue()))
            .andExpect(jsonPath("$.imageY").value(DEFAULT_IMAGE_Y.doubleValue()))
            .andExpect(jsonPath("$.matriculeX").value(DEFAULT_MATRICULE_X.doubleValue()))
            .andExpect(jsonPath("$.matriculeY").value(DEFAULT_MATRICULE_Y.doubleValue()))
            .andExpect(jsonPath("$.membreX").value(DEFAULT_MEMBRE_X.doubleValue()))
            .andExpect(jsonPath("$.membreY").value(DEFAULT_MEMBRE_Y.doubleValue()))
            .andExpect(jsonPath("$.nomX").value(DEFAULT_NOM_X.doubleValue()))
            .andExpect(jsonPath("$.nomY").value(DEFAULT_NOM_Y.doubleValue()))
            .andExpect(jsonPath("$.prenomX").value(DEFAULT_PRENOM_X.doubleValue()))
            .andExpect(jsonPath("$.prenomY").value(DEFAULT_PRENOM_Y.doubleValue()))
            .andExpect(jsonPath("$.qrHeight").value(DEFAULT_QR_HEIGHT.doubleValue()))
            .andExpect(jsonPath("$.qrWidth").value(DEFAULT_QR_WIDTH.doubleValue()))
            .andExpect(jsonPath("$.qrX").value(DEFAULT_QR_X.doubleValue()))
            .andExpect(jsonPath("$.qrY").value(DEFAULT_QR_Y.doubleValue()))
            .andExpect(jsonPath("$.dateDelivranceX").value(DEFAULT_DATE_DELIVRANCE_X.doubleValue()))
            .andExpect(jsonPath("$.dateDelivranceY").value(DEFAULT_DATE_DELIVRANCE_Y.doubleValue()))
            .andExpect(jsonPath("$.dateExpirationX").value(DEFAULT_DATE_EXPIRATION_X.doubleValue()))
            .andExpect(jsonPath("$.dateExpirationY").value(DEFAULT_DATE_EXPIRATION_Y.doubleValue()))
            .andExpect(jsonPath("$.sampleLargeur").value(DEFAULT_SAMPLE_LARGEUR.doubleValue()))
            .andExpect(jsonPath("$.sampleLongueur").value(DEFAULT_SAMPLE_LONGUEUR.doubleValue()))
            .andExpect(jsonPath("$.pdfContentRectoContentType").value(DEFAULT_PDF_CONTENT_RECTO_CONTENT_TYPE))
            .andExpect(jsonPath("$.pdfContentRecto").value(Base64.getEncoder().encodeToString(DEFAULT_PDF_CONTENT_RECTO)))
            .andExpect(jsonPath("$.pdfContentVersoContentType").value(DEFAULT_PDF_CONTENT_VERSO_CONTENT_TYPE))
            .andExpect(jsonPath("$.pdfContentVerso").value(Base64.getEncoder().encodeToString(DEFAULT_PDF_CONTENT_VERSO)));
    }

    @Test
    @Transactional
    void getTemplatesByIdFiltering() throws Exception {
        // Initialize the database
        insertedTemplate = templateRepository.saveAndFlush(template);

        String id = template.getLibelle();

        defaultTemplateFiltering("libelle.equals=" + id, "libelle.notEquals=" + id);
    }

    @Test
    @Transactional
    void getAllTemplatesByDescriptionIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedTemplate = templateRepository.saveAndFlush(template);

        // Get all the templateList where description equals to
        defaultTemplateFiltering("description.equals=" + DEFAULT_DESCRIPTION, "description.equals=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    void getAllTemplatesByDescriptionIsInShouldWork() throws Exception {
        // Initialize the database
        insertedTemplate = templateRepository.saveAndFlush(template);

        // Get all the templateList where description in
        defaultTemplateFiltering(
            "description.in=" + DEFAULT_DESCRIPTION + "," + UPDATED_DESCRIPTION,
            "description.in=" + UPDATED_DESCRIPTION
        );
    }

    @Test
    @Transactional
    void getAllTemplatesByDescriptionIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedTemplate = templateRepository.saveAndFlush(template);

        // Get all the templateList where description is not null
        defaultTemplateFiltering("description.specified=true", "description.specified=false");
    }

    @Test
    @Transactional
    void getAllTemplatesByDescriptionContainsSomething() throws Exception {
        // Initialize the database
        insertedTemplate = templateRepository.saveAndFlush(template);

        // Get all the templateList where description contains
        defaultTemplateFiltering("description.contains=" + DEFAULT_DESCRIPTION, "description.contains=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    void getAllTemplatesByDescriptionNotContainsSomething() throws Exception {
        // Initialize the database
        insertedTemplate = templateRepository.saveAndFlush(template);

        // Get all the templateList where description does not contain
        defaultTemplateFiltering("description.doesNotContain=" + UPDATED_DESCRIPTION, "description.doesNotContain=" + DEFAULT_DESCRIPTION);
    }

    @Test
    @Transactional
    void getAllTemplatesByImageHeigthIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedTemplate = templateRepository.saveAndFlush(template);

        // Get all the templateList where imageHeigth equals to
        defaultTemplateFiltering("imageHeigth.equals=" + DEFAULT_IMAGE_HEIGTH, "imageHeigth.equals=" + UPDATED_IMAGE_HEIGTH);
    }

    @Test
    @Transactional
    void getAllTemplatesByImageHeigthIsInShouldWork() throws Exception {
        // Initialize the database
        insertedTemplate = templateRepository.saveAndFlush(template);

        // Get all the templateList where imageHeigth in
        defaultTemplateFiltering(
            "imageHeigth.in=" + DEFAULT_IMAGE_HEIGTH + "," + UPDATED_IMAGE_HEIGTH,
            "imageHeigth.in=" + UPDATED_IMAGE_HEIGTH
        );
    }

    @Test
    @Transactional
    void getAllTemplatesByImageHeigthIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedTemplate = templateRepository.saveAndFlush(template);

        // Get all the templateList where imageHeigth is not null
        defaultTemplateFiltering("imageHeigth.specified=true", "imageHeigth.specified=false");
    }

    @Test
    @Transactional
    void getAllTemplatesByImageHeigthIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedTemplate = templateRepository.saveAndFlush(template);

        // Get all the templateList where imageHeigth is greater than or equal to
        defaultTemplateFiltering(
            "imageHeigth.greaterThanOrEqual=" + DEFAULT_IMAGE_HEIGTH,
            "imageHeigth.greaterThanOrEqual=" + UPDATED_IMAGE_HEIGTH
        );
    }

    @Test
    @Transactional
    void getAllTemplatesByImageHeigthIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedTemplate = templateRepository.saveAndFlush(template);

        // Get all the templateList where imageHeigth is less than or equal to
        defaultTemplateFiltering(
            "imageHeigth.lessThanOrEqual=" + DEFAULT_IMAGE_HEIGTH,
            "imageHeigth.lessThanOrEqual=" + SMALLER_IMAGE_HEIGTH
        );
    }

    @Test
    @Transactional
    void getAllTemplatesByImageHeigthIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedTemplate = templateRepository.saveAndFlush(template);

        // Get all the templateList where imageHeigth is less than
        defaultTemplateFiltering("imageHeigth.lessThan=" + UPDATED_IMAGE_HEIGTH, "imageHeigth.lessThan=" + DEFAULT_IMAGE_HEIGTH);
    }

    @Test
    @Transactional
    void getAllTemplatesByImageHeigthIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedTemplate = templateRepository.saveAndFlush(template);

        // Get all the templateList where imageHeigth is greater than
        defaultTemplateFiltering("imageHeigth.greaterThan=" + SMALLER_IMAGE_HEIGTH, "imageHeigth.greaterThan=" + DEFAULT_IMAGE_HEIGTH);
    }

    @Test
    @Transactional
    void getAllTemplatesByImageWidthIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedTemplate = templateRepository.saveAndFlush(template);

        // Get all the templateList where imageWidth equals to
        defaultTemplateFiltering("imageWidth.equals=" + DEFAULT_IMAGE_WIDTH, "imageWidth.equals=" + UPDATED_IMAGE_WIDTH);
    }

    @Test
    @Transactional
    void getAllTemplatesByImageWidthIsInShouldWork() throws Exception {
        // Initialize the database
        insertedTemplate = templateRepository.saveAndFlush(template);

        // Get all the templateList where imageWidth in
        defaultTemplateFiltering(
            "imageWidth.in=" + DEFAULT_IMAGE_WIDTH + "," + UPDATED_IMAGE_WIDTH,
            "imageWidth.in=" + UPDATED_IMAGE_WIDTH
        );
    }

    @Test
    @Transactional
    void getAllTemplatesByImageWidthIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedTemplate = templateRepository.saveAndFlush(template);

        // Get all the templateList where imageWidth is not null
        defaultTemplateFiltering("imageWidth.specified=true", "imageWidth.specified=false");
    }

    @Test
    @Transactional
    void getAllTemplatesByImageWidthIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedTemplate = templateRepository.saveAndFlush(template);

        // Get all the templateList where imageWidth is greater than or equal to
        defaultTemplateFiltering(
            "imageWidth.greaterThanOrEqual=" + DEFAULT_IMAGE_WIDTH,
            "imageWidth.greaterThanOrEqual=" + UPDATED_IMAGE_WIDTH
        );
    }

    @Test
    @Transactional
    void getAllTemplatesByImageWidthIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedTemplate = templateRepository.saveAndFlush(template);

        // Get all the templateList where imageWidth is less than or equal to
        defaultTemplateFiltering("imageWidth.lessThanOrEqual=" + DEFAULT_IMAGE_WIDTH, "imageWidth.lessThanOrEqual=" + SMALLER_IMAGE_WIDTH);
    }

    @Test
    @Transactional
    void getAllTemplatesByImageWidthIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedTemplate = templateRepository.saveAndFlush(template);

        // Get all the templateList where imageWidth is less than
        defaultTemplateFiltering("imageWidth.lessThan=" + UPDATED_IMAGE_WIDTH, "imageWidth.lessThan=" + DEFAULT_IMAGE_WIDTH);
    }

    @Test
    @Transactional
    void getAllTemplatesByImageWidthIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedTemplate = templateRepository.saveAndFlush(template);

        // Get all the templateList where imageWidth is greater than
        defaultTemplateFiltering("imageWidth.greaterThan=" + SMALLER_IMAGE_WIDTH, "imageWidth.greaterThan=" + DEFAULT_IMAGE_WIDTH);
    }

    @Test
    @Transactional
    void getAllTemplatesByImageXIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedTemplate = templateRepository.saveAndFlush(template);

        // Get all the templateList where imageX equals to
        defaultTemplateFiltering("imageX.equals=" + DEFAULT_IMAGE_X, "imageX.equals=" + UPDATED_IMAGE_X);
    }

    @Test
    @Transactional
    void getAllTemplatesByImageXIsInShouldWork() throws Exception {
        // Initialize the database
        insertedTemplate = templateRepository.saveAndFlush(template);

        // Get all the templateList where imageX in
        defaultTemplateFiltering("imageX.in=" + DEFAULT_IMAGE_X + "," + UPDATED_IMAGE_X, "imageX.in=" + UPDATED_IMAGE_X);
    }

    @Test
    @Transactional
    void getAllTemplatesByImageXIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedTemplate = templateRepository.saveAndFlush(template);

        // Get all the templateList where imageX is not null
        defaultTemplateFiltering("imageX.specified=true", "imageX.specified=false");
    }

    @Test
    @Transactional
    void getAllTemplatesByImageXIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedTemplate = templateRepository.saveAndFlush(template);

        // Get all the templateList where imageX is greater than or equal to
        defaultTemplateFiltering("imageX.greaterThanOrEqual=" + DEFAULT_IMAGE_X, "imageX.greaterThanOrEqual=" + UPDATED_IMAGE_X);
    }

    @Test
    @Transactional
    void getAllTemplatesByImageXIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedTemplate = templateRepository.saveAndFlush(template);

        // Get all the templateList where imageX is less than or equal to
        defaultTemplateFiltering("imageX.lessThanOrEqual=" + DEFAULT_IMAGE_X, "imageX.lessThanOrEqual=" + SMALLER_IMAGE_X);
    }

    @Test
    @Transactional
    void getAllTemplatesByImageXIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedTemplate = templateRepository.saveAndFlush(template);

        // Get all the templateList where imageX is less than
        defaultTemplateFiltering("imageX.lessThan=" + UPDATED_IMAGE_X, "imageX.lessThan=" + DEFAULT_IMAGE_X);
    }

    @Test
    @Transactional
    void getAllTemplatesByImageXIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedTemplate = templateRepository.saveAndFlush(template);

        // Get all the templateList where imageX is greater than
        defaultTemplateFiltering("imageX.greaterThan=" + SMALLER_IMAGE_X, "imageX.greaterThan=" + DEFAULT_IMAGE_X);
    }

    @Test
    @Transactional
    void getAllTemplatesByImageYIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedTemplate = templateRepository.saveAndFlush(template);

        // Get all the templateList where imageY equals to
        defaultTemplateFiltering("imageY.equals=" + DEFAULT_IMAGE_Y, "imageY.equals=" + UPDATED_IMAGE_Y);
    }

    @Test
    @Transactional
    void getAllTemplatesByImageYIsInShouldWork() throws Exception {
        // Initialize the database
        insertedTemplate = templateRepository.saveAndFlush(template);

        // Get all the templateList where imageY in
        defaultTemplateFiltering("imageY.in=" + DEFAULT_IMAGE_Y + "," + UPDATED_IMAGE_Y, "imageY.in=" + UPDATED_IMAGE_Y);
    }

    @Test
    @Transactional
    void getAllTemplatesByImageYIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedTemplate = templateRepository.saveAndFlush(template);

        // Get all the templateList where imageY is not null
        defaultTemplateFiltering("imageY.specified=true", "imageY.specified=false");
    }

    @Test
    @Transactional
    void getAllTemplatesByImageYIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedTemplate = templateRepository.saveAndFlush(template);

        // Get all the templateList where imageY is greater than or equal to
        defaultTemplateFiltering("imageY.greaterThanOrEqual=" + DEFAULT_IMAGE_Y, "imageY.greaterThanOrEqual=" + UPDATED_IMAGE_Y);
    }

    @Test
    @Transactional
    void getAllTemplatesByImageYIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedTemplate = templateRepository.saveAndFlush(template);

        // Get all the templateList where imageY is less than or equal to
        defaultTemplateFiltering("imageY.lessThanOrEqual=" + DEFAULT_IMAGE_Y, "imageY.lessThanOrEqual=" + SMALLER_IMAGE_Y);
    }

    @Test
    @Transactional
    void getAllTemplatesByImageYIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedTemplate = templateRepository.saveAndFlush(template);

        // Get all the templateList where imageY is less than
        defaultTemplateFiltering("imageY.lessThan=" + UPDATED_IMAGE_Y, "imageY.lessThan=" + DEFAULT_IMAGE_Y);
    }

    @Test
    @Transactional
    void getAllTemplatesByImageYIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedTemplate = templateRepository.saveAndFlush(template);

        // Get all the templateList where imageY is greater than
        defaultTemplateFiltering("imageY.greaterThan=" + SMALLER_IMAGE_Y, "imageY.greaterThan=" + DEFAULT_IMAGE_Y);
    }

    @Test
    @Transactional
    void getAllTemplatesByMatriculeXIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedTemplate = templateRepository.saveAndFlush(template);

        // Get all the templateList where matriculeX equals to
        defaultTemplateFiltering("matriculeX.equals=" + DEFAULT_MATRICULE_X, "matriculeX.equals=" + UPDATED_MATRICULE_X);
    }

    @Test
    @Transactional
    void getAllTemplatesByMatriculeXIsInShouldWork() throws Exception {
        // Initialize the database
        insertedTemplate = templateRepository.saveAndFlush(template);

        // Get all the templateList where matriculeX in
        defaultTemplateFiltering(
            "matriculeX.in=" + DEFAULT_MATRICULE_X + "," + UPDATED_MATRICULE_X,
            "matriculeX.in=" + UPDATED_MATRICULE_X
        );
    }

    @Test
    @Transactional
    void getAllTemplatesByMatriculeXIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedTemplate = templateRepository.saveAndFlush(template);

        // Get all the templateList where matriculeX is not null
        defaultTemplateFiltering("matriculeX.specified=true", "matriculeX.specified=false");
    }

    @Test
    @Transactional
    void getAllTemplatesByMatriculeXIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedTemplate = templateRepository.saveAndFlush(template);

        // Get all the templateList where matriculeX is greater than or equal to
        defaultTemplateFiltering(
            "matriculeX.greaterThanOrEqual=" + DEFAULT_MATRICULE_X,
            "matriculeX.greaterThanOrEqual=" + UPDATED_MATRICULE_X
        );
    }

    @Test
    @Transactional
    void getAllTemplatesByMatriculeXIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedTemplate = templateRepository.saveAndFlush(template);

        // Get all the templateList where matriculeX is less than or equal to
        defaultTemplateFiltering("matriculeX.lessThanOrEqual=" + DEFAULT_MATRICULE_X, "matriculeX.lessThanOrEqual=" + SMALLER_MATRICULE_X);
    }

    @Test
    @Transactional
    void getAllTemplatesByMatriculeXIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedTemplate = templateRepository.saveAndFlush(template);

        // Get all the templateList where matriculeX is less than
        defaultTemplateFiltering("matriculeX.lessThan=" + UPDATED_MATRICULE_X, "matriculeX.lessThan=" + DEFAULT_MATRICULE_X);
    }

    @Test
    @Transactional
    void getAllTemplatesByMatriculeXIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedTemplate = templateRepository.saveAndFlush(template);

        // Get all the templateList where matriculeX is greater than
        defaultTemplateFiltering("matriculeX.greaterThan=" + SMALLER_MATRICULE_X, "matriculeX.greaterThan=" + DEFAULT_MATRICULE_X);
    }

    @Test
    @Transactional
    void getAllTemplatesByMatriculeYIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedTemplate = templateRepository.saveAndFlush(template);

        // Get all the templateList where matriculeY equals to
        defaultTemplateFiltering("matriculeY.equals=" + DEFAULT_MATRICULE_Y, "matriculeY.equals=" + UPDATED_MATRICULE_Y);
    }

    @Test
    @Transactional
    void getAllTemplatesByMatriculeYIsInShouldWork() throws Exception {
        // Initialize the database
        insertedTemplate = templateRepository.saveAndFlush(template);

        // Get all the templateList where matriculeY in
        defaultTemplateFiltering(
            "matriculeY.in=" + DEFAULT_MATRICULE_Y + "," + UPDATED_MATRICULE_Y,
            "matriculeY.in=" + UPDATED_MATRICULE_Y
        );
    }

    @Test
    @Transactional
    void getAllTemplatesByMatriculeYIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedTemplate = templateRepository.saveAndFlush(template);

        // Get all the templateList where matriculeY is not null
        defaultTemplateFiltering("matriculeY.specified=true", "matriculeY.specified=false");
    }

    @Test
    @Transactional
    void getAllTemplatesByMatriculeYIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedTemplate = templateRepository.saveAndFlush(template);

        // Get all the templateList where matriculeY is greater than or equal to
        defaultTemplateFiltering(
            "matriculeY.greaterThanOrEqual=" + DEFAULT_MATRICULE_Y,
            "matriculeY.greaterThanOrEqual=" + UPDATED_MATRICULE_Y
        );
    }

    @Test
    @Transactional
    void getAllTemplatesByMatriculeYIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedTemplate = templateRepository.saveAndFlush(template);

        // Get all the templateList where matriculeY is less than or equal to
        defaultTemplateFiltering("matriculeY.lessThanOrEqual=" + DEFAULT_MATRICULE_Y, "matriculeY.lessThanOrEqual=" + SMALLER_MATRICULE_Y);
    }

    @Test
    @Transactional
    void getAllTemplatesByMatriculeYIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedTemplate = templateRepository.saveAndFlush(template);

        // Get all the templateList where matriculeY is less than
        defaultTemplateFiltering("matriculeY.lessThan=" + UPDATED_MATRICULE_Y, "matriculeY.lessThan=" + DEFAULT_MATRICULE_Y);
    }

    @Test
    @Transactional
    void getAllTemplatesByMatriculeYIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedTemplate = templateRepository.saveAndFlush(template);

        // Get all the templateList where matriculeY is greater than
        defaultTemplateFiltering("matriculeY.greaterThan=" + SMALLER_MATRICULE_Y, "matriculeY.greaterThan=" + DEFAULT_MATRICULE_Y);
    }

    @Test
    @Transactional
    void getAllTemplatesByMembreXIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedTemplate = templateRepository.saveAndFlush(template);

        // Get all the templateList where membreX equals to
        defaultTemplateFiltering("membreX.equals=" + DEFAULT_MEMBRE_X, "membreX.equals=" + UPDATED_MEMBRE_X);
    }

    @Test
    @Transactional
    void getAllTemplatesByMembreXIsInShouldWork() throws Exception {
        // Initialize the database
        insertedTemplate = templateRepository.saveAndFlush(template);

        // Get all the templateList where membreX in
        defaultTemplateFiltering("membreX.in=" + DEFAULT_MEMBRE_X + "," + UPDATED_MEMBRE_X, "membreX.in=" + UPDATED_MEMBRE_X);
    }

    @Test
    @Transactional
    void getAllTemplatesByMembreXIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedTemplate = templateRepository.saveAndFlush(template);

        // Get all the templateList where membreX is not null
        defaultTemplateFiltering("membreX.specified=true", "membreX.specified=false");
    }

    @Test
    @Transactional
    void getAllTemplatesByMembreXIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedTemplate = templateRepository.saveAndFlush(template);

        // Get all the templateList where membreX is greater than or equal to
        defaultTemplateFiltering("membreX.greaterThanOrEqual=" + DEFAULT_MEMBRE_X, "membreX.greaterThanOrEqual=" + UPDATED_MEMBRE_X);
    }

    @Test
    @Transactional
    void getAllTemplatesByMembreXIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedTemplate = templateRepository.saveAndFlush(template);

        // Get all the templateList where membreX is less than or equal to
        defaultTemplateFiltering("membreX.lessThanOrEqual=" + DEFAULT_MEMBRE_X, "membreX.lessThanOrEqual=" + SMALLER_MEMBRE_X);
    }

    @Test
    @Transactional
    void getAllTemplatesByMembreXIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedTemplate = templateRepository.saveAndFlush(template);

        // Get all the templateList where membreX is less than
        defaultTemplateFiltering("membreX.lessThan=" + UPDATED_MEMBRE_X, "membreX.lessThan=" + DEFAULT_MEMBRE_X);
    }

    @Test
    @Transactional
    void getAllTemplatesByMembreXIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedTemplate = templateRepository.saveAndFlush(template);

        // Get all the templateList where membreX is greater than
        defaultTemplateFiltering("membreX.greaterThan=" + SMALLER_MEMBRE_X, "membreX.greaterThan=" + DEFAULT_MEMBRE_X);
    }

    @Test
    @Transactional
    void getAllTemplatesByMembreYIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedTemplate = templateRepository.saveAndFlush(template);

        // Get all the templateList where membreY equals to
        defaultTemplateFiltering("membreY.equals=" + DEFAULT_MEMBRE_Y, "membreY.equals=" + UPDATED_MEMBRE_Y);
    }

    @Test
    @Transactional
    void getAllTemplatesByMembreYIsInShouldWork() throws Exception {
        // Initialize the database
        insertedTemplate = templateRepository.saveAndFlush(template);

        // Get all the templateList where membreY in
        defaultTemplateFiltering("membreY.in=" + DEFAULT_MEMBRE_Y + "," + UPDATED_MEMBRE_Y, "membreY.in=" + UPDATED_MEMBRE_Y);
    }

    @Test
    @Transactional
    void getAllTemplatesByMembreYIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedTemplate = templateRepository.saveAndFlush(template);

        // Get all the templateList where membreY is not null
        defaultTemplateFiltering("membreY.specified=true", "membreY.specified=false");
    }

    @Test
    @Transactional
    void getAllTemplatesByMembreYIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedTemplate = templateRepository.saveAndFlush(template);

        // Get all the templateList where membreY is greater than or equal to
        defaultTemplateFiltering("membreY.greaterThanOrEqual=" + DEFAULT_MEMBRE_Y, "membreY.greaterThanOrEqual=" + UPDATED_MEMBRE_Y);
    }

    @Test
    @Transactional
    void getAllTemplatesByMembreYIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedTemplate = templateRepository.saveAndFlush(template);

        // Get all the templateList where membreY is less than or equal to
        defaultTemplateFiltering("membreY.lessThanOrEqual=" + DEFAULT_MEMBRE_Y, "membreY.lessThanOrEqual=" + SMALLER_MEMBRE_Y);
    }

    @Test
    @Transactional
    void getAllTemplatesByMembreYIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedTemplate = templateRepository.saveAndFlush(template);

        // Get all the templateList where membreY is less than
        defaultTemplateFiltering("membreY.lessThan=" + UPDATED_MEMBRE_Y, "membreY.lessThan=" + DEFAULT_MEMBRE_Y);
    }

    @Test
    @Transactional
    void getAllTemplatesByMembreYIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedTemplate = templateRepository.saveAndFlush(template);

        // Get all the templateList where membreY is greater than
        defaultTemplateFiltering("membreY.greaterThan=" + SMALLER_MEMBRE_Y, "membreY.greaterThan=" + DEFAULT_MEMBRE_Y);
    }

    @Test
    @Transactional
    void getAllTemplatesByNomXIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedTemplate = templateRepository.saveAndFlush(template);

        // Get all the templateList where nomX equals to
        defaultTemplateFiltering("nomX.equals=" + DEFAULT_NOM_X, "nomX.equals=" + UPDATED_NOM_X);
    }

    @Test
    @Transactional
    void getAllTemplatesByNomXIsInShouldWork() throws Exception {
        // Initialize the database
        insertedTemplate = templateRepository.saveAndFlush(template);

        // Get all the templateList where nomX in
        defaultTemplateFiltering("nomX.in=" + DEFAULT_NOM_X + "," + UPDATED_NOM_X, "nomX.in=" + UPDATED_NOM_X);
    }

    @Test
    @Transactional
    void getAllTemplatesByNomXIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedTemplate = templateRepository.saveAndFlush(template);

        // Get all the templateList where nomX is not null
        defaultTemplateFiltering("nomX.specified=true", "nomX.specified=false");
    }

    @Test
    @Transactional
    void getAllTemplatesByNomXIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedTemplate = templateRepository.saveAndFlush(template);

        // Get all the templateList where nomX is greater than or equal to
        defaultTemplateFiltering("nomX.greaterThanOrEqual=" + DEFAULT_NOM_X, "nomX.greaterThanOrEqual=" + UPDATED_NOM_X);
    }

    @Test
    @Transactional
    void getAllTemplatesByNomXIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedTemplate = templateRepository.saveAndFlush(template);

        // Get all the templateList where nomX is less than or equal to
        defaultTemplateFiltering("nomX.lessThanOrEqual=" + DEFAULT_NOM_X, "nomX.lessThanOrEqual=" + SMALLER_NOM_X);
    }

    @Test
    @Transactional
    void getAllTemplatesByNomXIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedTemplate = templateRepository.saveAndFlush(template);

        // Get all the templateList where nomX is less than
        defaultTemplateFiltering("nomX.lessThan=" + UPDATED_NOM_X, "nomX.lessThan=" + DEFAULT_NOM_X);
    }

    @Test
    @Transactional
    void getAllTemplatesByNomXIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedTemplate = templateRepository.saveAndFlush(template);

        // Get all the templateList where nomX is greater than
        defaultTemplateFiltering("nomX.greaterThan=" + SMALLER_NOM_X, "nomX.greaterThan=" + DEFAULT_NOM_X);
    }

    @Test
    @Transactional
    void getAllTemplatesByNomYIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedTemplate = templateRepository.saveAndFlush(template);

        // Get all the templateList where nomY equals to
        defaultTemplateFiltering("nomY.equals=" + DEFAULT_NOM_Y, "nomY.equals=" + UPDATED_NOM_Y);
    }

    @Test
    @Transactional
    void getAllTemplatesByNomYIsInShouldWork() throws Exception {
        // Initialize the database
        insertedTemplate = templateRepository.saveAndFlush(template);

        // Get all the templateList where nomY in
        defaultTemplateFiltering("nomY.in=" + DEFAULT_NOM_Y + "," + UPDATED_NOM_Y, "nomY.in=" + UPDATED_NOM_Y);
    }

    @Test
    @Transactional
    void getAllTemplatesByNomYIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedTemplate = templateRepository.saveAndFlush(template);

        // Get all the templateList where nomY is not null
        defaultTemplateFiltering("nomY.specified=true", "nomY.specified=false");
    }

    @Test
    @Transactional
    void getAllTemplatesByNomYIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedTemplate = templateRepository.saveAndFlush(template);

        // Get all the templateList where nomY is greater than or equal to
        defaultTemplateFiltering("nomY.greaterThanOrEqual=" + DEFAULT_NOM_Y, "nomY.greaterThanOrEqual=" + UPDATED_NOM_Y);
    }

    @Test
    @Transactional
    void getAllTemplatesByNomYIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedTemplate = templateRepository.saveAndFlush(template);

        // Get all the templateList where nomY is less than or equal to
        defaultTemplateFiltering("nomY.lessThanOrEqual=" + DEFAULT_NOM_Y, "nomY.lessThanOrEqual=" + SMALLER_NOM_Y);
    }

    @Test
    @Transactional
    void getAllTemplatesByNomYIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedTemplate = templateRepository.saveAndFlush(template);

        // Get all the templateList where nomY is less than
        defaultTemplateFiltering("nomY.lessThan=" + UPDATED_NOM_Y, "nomY.lessThan=" + DEFAULT_NOM_Y);
    }

    @Test
    @Transactional
    void getAllTemplatesByNomYIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedTemplate = templateRepository.saveAndFlush(template);

        // Get all the templateList where nomY is greater than
        defaultTemplateFiltering("nomY.greaterThan=" + SMALLER_NOM_Y, "nomY.greaterThan=" + DEFAULT_NOM_Y);
    }

    @Test
    @Transactional
    void getAllTemplatesByPrenomXIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedTemplate = templateRepository.saveAndFlush(template);

        // Get all the templateList where prenomX equals to
        defaultTemplateFiltering("prenomX.equals=" + DEFAULT_PRENOM_X, "prenomX.equals=" + UPDATED_PRENOM_X);
    }

    @Test
    @Transactional
    void getAllTemplatesByPrenomXIsInShouldWork() throws Exception {
        // Initialize the database
        insertedTemplate = templateRepository.saveAndFlush(template);

        // Get all the templateList where prenomX in
        defaultTemplateFiltering("prenomX.in=" + DEFAULT_PRENOM_X + "," + UPDATED_PRENOM_X, "prenomX.in=" + UPDATED_PRENOM_X);
    }

    @Test
    @Transactional
    void getAllTemplatesByPrenomXIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedTemplate = templateRepository.saveAndFlush(template);

        // Get all the templateList where prenomX is not null
        defaultTemplateFiltering("prenomX.specified=true", "prenomX.specified=false");
    }

    @Test
    @Transactional
    void getAllTemplatesByPrenomXIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedTemplate = templateRepository.saveAndFlush(template);

        // Get all the templateList where prenomX is greater than or equal to
        defaultTemplateFiltering("prenomX.greaterThanOrEqual=" + DEFAULT_PRENOM_X, "prenomX.greaterThanOrEqual=" + UPDATED_PRENOM_X);
    }

    @Test
    @Transactional
    void getAllTemplatesByPrenomXIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedTemplate = templateRepository.saveAndFlush(template);

        // Get all the templateList where prenomX is less than or equal to
        defaultTemplateFiltering("prenomX.lessThanOrEqual=" + DEFAULT_PRENOM_X, "prenomX.lessThanOrEqual=" + SMALLER_PRENOM_X);
    }

    @Test
    @Transactional
    void getAllTemplatesByPrenomXIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedTemplate = templateRepository.saveAndFlush(template);

        // Get all the templateList where prenomX is less than
        defaultTemplateFiltering("prenomX.lessThan=" + UPDATED_PRENOM_X, "prenomX.lessThan=" + DEFAULT_PRENOM_X);
    }

    @Test
    @Transactional
    void getAllTemplatesByPrenomXIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedTemplate = templateRepository.saveAndFlush(template);

        // Get all the templateList where prenomX is greater than
        defaultTemplateFiltering("prenomX.greaterThan=" + SMALLER_PRENOM_X, "prenomX.greaterThan=" + DEFAULT_PRENOM_X);
    }

    @Test
    @Transactional
    void getAllTemplatesByPrenomYIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedTemplate = templateRepository.saveAndFlush(template);

        // Get all the templateList where prenomY equals to
        defaultTemplateFiltering("prenomY.equals=" + DEFAULT_PRENOM_Y, "prenomY.equals=" + UPDATED_PRENOM_Y);
    }

    @Test
    @Transactional
    void getAllTemplatesByPrenomYIsInShouldWork() throws Exception {
        // Initialize the database
        insertedTemplate = templateRepository.saveAndFlush(template);

        // Get all the templateList where prenomY in
        defaultTemplateFiltering("prenomY.in=" + DEFAULT_PRENOM_Y + "," + UPDATED_PRENOM_Y, "prenomY.in=" + UPDATED_PRENOM_Y);
    }

    @Test
    @Transactional
    void getAllTemplatesByPrenomYIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedTemplate = templateRepository.saveAndFlush(template);

        // Get all the templateList where prenomY is not null
        defaultTemplateFiltering("prenomY.specified=true", "prenomY.specified=false");
    }

    @Test
    @Transactional
    void getAllTemplatesByPrenomYIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedTemplate = templateRepository.saveAndFlush(template);

        // Get all the templateList where prenomY is greater than or equal to
        defaultTemplateFiltering("prenomY.greaterThanOrEqual=" + DEFAULT_PRENOM_Y, "prenomY.greaterThanOrEqual=" + UPDATED_PRENOM_Y);
    }

    @Test
    @Transactional
    void getAllTemplatesByPrenomYIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedTemplate = templateRepository.saveAndFlush(template);

        // Get all the templateList where prenomY is less than or equal to
        defaultTemplateFiltering("prenomY.lessThanOrEqual=" + DEFAULT_PRENOM_Y, "prenomY.lessThanOrEqual=" + SMALLER_PRENOM_Y);
    }

    @Test
    @Transactional
    void getAllTemplatesByPrenomYIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedTemplate = templateRepository.saveAndFlush(template);

        // Get all the templateList where prenomY is less than
        defaultTemplateFiltering("prenomY.lessThan=" + UPDATED_PRENOM_Y, "prenomY.lessThan=" + DEFAULT_PRENOM_Y);
    }

    @Test
    @Transactional
    void getAllTemplatesByPrenomYIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedTemplate = templateRepository.saveAndFlush(template);

        // Get all the templateList where prenomY is greater than
        defaultTemplateFiltering("prenomY.greaterThan=" + SMALLER_PRENOM_Y, "prenomY.greaterThan=" + DEFAULT_PRENOM_Y);
    }

    @Test
    @Transactional
    void getAllTemplatesByQrHeightIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedTemplate = templateRepository.saveAndFlush(template);

        // Get all the templateList where qrHeight equals to
        defaultTemplateFiltering("qrHeight.equals=" + DEFAULT_QR_HEIGHT, "qrHeight.equals=" + UPDATED_QR_HEIGHT);
    }

    @Test
    @Transactional
    void getAllTemplatesByQrHeightIsInShouldWork() throws Exception {
        // Initialize the database
        insertedTemplate = templateRepository.saveAndFlush(template);

        // Get all the templateList where qrHeight in
        defaultTemplateFiltering("qrHeight.in=" + DEFAULT_QR_HEIGHT + "," + UPDATED_QR_HEIGHT, "qrHeight.in=" + UPDATED_QR_HEIGHT);
    }

    @Test
    @Transactional
    void getAllTemplatesByQrHeightIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedTemplate = templateRepository.saveAndFlush(template);

        // Get all the templateList where qrHeight is not null
        defaultTemplateFiltering("qrHeight.specified=true", "qrHeight.specified=false");
    }

    @Test
    @Transactional
    void getAllTemplatesByQrHeightIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedTemplate = templateRepository.saveAndFlush(template);

        // Get all the templateList where qrHeight is greater than or equal to
        defaultTemplateFiltering("qrHeight.greaterThanOrEqual=" + DEFAULT_QR_HEIGHT, "qrHeight.greaterThanOrEqual=" + UPDATED_QR_HEIGHT);
    }

    @Test
    @Transactional
    void getAllTemplatesByQrHeightIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedTemplate = templateRepository.saveAndFlush(template);

        // Get all the templateList where qrHeight is less than or equal to
        defaultTemplateFiltering("qrHeight.lessThanOrEqual=" + DEFAULT_QR_HEIGHT, "qrHeight.lessThanOrEqual=" + SMALLER_QR_HEIGHT);
    }

    @Test
    @Transactional
    void getAllTemplatesByQrHeightIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedTemplate = templateRepository.saveAndFlush(template);

        // Get all the templateList where qrHeight is less than
        defaultTemplateFiltering("qrHeight.lessThan=" + UPDATED_QR_HEIGHT, "qrHeight.lessThan=" + DEFAULT_QR_HEIGHT);
    }

    @Test
    @Transactional
    void getAllTemplatesByQrHeightIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedTemplate = templateRepository.saveAndFlush(template);

        // Get all the templateList where qrHeight is greater than
        defaultTemplateFiltering("qrHeight.greaterThan=" + SMALLER_QR_HEIGHT, "qrHeight.greaterThan=" + DEFAULT_QR_HEIGHT);
    }

    @Test
    @Transactional
    void getAllTemplatesByQrWidthIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedTemplate = templateRepository.saveAndFlush(template);

        // Get all the templateList where qrWidth equals to
        defaultTemplateFiltering("qrWidth.equals=" + DEFAULT_QR_WIDTH, "qrWidth.equals=" + UPDATED_QR_WIDTH);
    }

    @Test
    @Transactional
    void getAllTemplatesByQrWidthIsInShouldWork() throws Exception {
        // Initialize the database
        insertedTemplate = templateRepository.saveAndFlush(template);

        // Get all the templateList where qrWidth in
        defaultTemplateFiltering("qrWidth.in=" + DEFAULT_QR_WIDTH + "," + UPDATED_QR_WIDTH, "qrWidth.in=" + UPDATED_QR_WIDTH);
    }

    @Test
    @Transactional
    void getAllTemplatesByQrWidthIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedTemplate = templateRepository.saveAndFlush(template);

        // Get all the templateList where qrWidth is not null
        defaultTemplateFiltering("qrWidth.specified=true", "qrWidth.specified=false");
    }

    @Test
    @Transactional
    void getAllTemplatesByQrWidthIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedTemplate = templateRepository.saveAndFlush(template);

        // Get all the templateList where qrWidth is greater than or equal to
        defaultTemplateFiltering("qrWidth.greaterThanOrEqual=" + DEFAULT_QR_WIDTH, "qrWidth.greaterThanOrEqual=" + UPDATED_QR_WIDTH);
    }

    @Test
    @Transactional
    void getAllTemplatesByQrWidthIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedTemplate = templateRepository.saveAndFlush(template);

        // Get all the templateList where qrWidth is less than or equal to
        defaultTemplateFiltering("qrWidth.lessThanOrEqual=" + DEFAULT_QR_WIDTH, "qrWidth.lessThanOrEqual=" + SMALLER_QR_WIDTH);
    }

    @Test
    @Transactional
    void getAllTemplatesByQrWidthIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedTemplate = templateRepository.saveAndFlush(template);

        // Get all the templateList where qrWidth is less than
        defaultTemplateFiltering("qrWidth.lessThan=" + UPDATED_QR_WIDTH, "qrWidth.lessThan=" + DEFAULT_QR_WIDTH);
    }

    @Test
    @Transactional
    void getAllTemplatesByQrWidthIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedTemplate = templateRepository.saveAndFlush(template);

        // Get all the templateList where qrWidth is greater than
        defaultTemplateFiltering("qrWidth.greaterThan=" + SMALLER_QR_WIDTH, "qrWidth.greaterThan=" + DEFAULT_QR_WIDTH);
    }

    @Test
    @Transactional
    void getAllTemplatesByQrXIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedTemplate = templateRepository.saveAndFlush(template);

        // Get all the templateList where qrX equals to
        defaultTemplateFiltering("qrX.equals=" + DEFAULT_QR_X, "qrX.equals=" + UPDATED_QR_X);
    }

    @Test
    @Transactional
    void getAllTemplatesByQrXIsInShouldWork() throws Exception {
        // Initialize the database
        insertedTemplate = templateRepository.saveAndFlush(template);

        // Get all the templateList where qrX in
        defaultTemplateFiltering("qrX.in=" + DEFAULT_QR_X + "," + UPDATED_QR_X, "qrX.in=" + UPDATED_QR_X);
    }

    @Test
    @Transactional
    void getAllTemplatesByQrXIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedTemplate = templateRepository.saveAndFlush(template);

        // Get all the templateList where qrX is not null
        defaultTemplateFiltering("qrX.specified=true", "qrX.specified=false");
    }

    @Test
    @Transactional
    void getAllTemplatesByQrXIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedTemplate = templateRepository.saveAndFlush(template);

        // Get all the templateList where qrX is greater than or equal to
        defaultTemplateFiltering("qrX.greaterThanOrEqual=" + DEFAULT_QR_X, "qrX.greaterThanOrEqual=" + UPDATED_QR_X);
    }

    @Test
    @Transactional
    void getAllTemplatesByQrXIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedTemplate = templateRepository.saveAndFlush(template);

        // Get all the templateList where qrX is less than or equal to
        defaultTemplateFiltering("qrX.lessThanOrEqual=" + DEFAULT_QR_X, "qrX.lessThanOrEqual=" + SMALLER_QR_X);
    }

    @Test
    @Transactional
    void getAllTemplatesByQrXIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedTemplate = templateRepository.saveAndFlush(template);

        // Get all the templateList where qrX is less than
        defaultTemplateFiltering("qrX.lessThan=" + UPDATED_QR_X, "qrX.lessThan=" + DEFAULT_QR_X);
    }

    @Test
    @Transactional
    void getAllTemplatesByQrXIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedTemplate = templateRepository.saveAndFlush(template);

        // Get all the templateList where qrX is greater than
        defaultTemplateFiltering("qrX.greaterThan=" + SMALLER_QR_X, "qrX.greaterThan=" + DEFAULT_QR_X);
    }

    @Test
    @Transactional
    void getAllTemplatesByQrYIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedTemplate = templateRepository.saveAndFlush(template);

        // Get all the templateList where qrY equals to
        defaultTemplateFiltering("qrY.equals=" + DEFAULT_QR_Y, "qrY.equals=" + UPDATED_QR_Y);
    }

    @Test
    @Transactional
    void getAllTemplatesByQrYIsInShouldWork() throws Exception {
        // Initialize the database
        insertedTemplate = templateRepository.saveAndFlush(template);

        // Get all the templateList where qrY in
        defaultTemplateFiltering("qrY.in=" + DEFAULT_QR_Y + "," + UPDATED_QR_Y, "qrY.in=" + UPDATED_QR_Y);
    }

    @Test
    @Transactional
    void getAllTemplatesByQrYIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedTemplate = templateRepository.saveAndFlush(template);

        // Get all the templateList where qrY is not null
        defaultTemplateFiltering("qrY.specified=true", "qrY.specified=false");
    }

    @Test
    @Transactional
    void getAllTemplatesByQrYIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedTemplate = templateRepository.saveAndFlush(template);

        // Get all the templateList where qrY is greater than or equal to
        defaultTemplateFiltering("qrY.greaterThanOrEqual=" + DEFAULT_QR_Y, "qrY.greaterThanOrEqual=" + UPDATED_QR_Y);
    }

    @Test
    @Transactional
    void getAllTemplatesByQrYIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedTemplate = templateRepository.saveAndFlush(template);

        // Get all the templateList where qrY is less than or equal to
        defaultTemplateFiltering("qrY.lessThanOrEqual=" + DEFAULT_QR_Y, "qrY.lessThanOrEqual=" + SMALLER_QR_Y);
    }

    @Test
    @Transactional
    void getAllTemplatesByQrYIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedTemplate = templateRepository.saveAndFlush(template);

        // Get all the templateList where qrY is less than
        defaultTemplateFiltering("qrY.lessThan=" + UPDATED_QR_Y, "qrY.lessThan=" + DEFAULT_QR_Y);
    }

    @Test
    @Transactional
    void getAllTemplatesByQrYIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedTemplate = templateRepository.saveAndFlush(template);

        // Get all the templateList where qrY is greater than
        defaultTemplateFiltering("qrY.greaterThan=" + SMALLER_QR_Y, "qrY.greaterThan=" + DEFAULT_QR_Y);
    }

    @Test
    @Transactional
    void getAllTemplatesByDateDelivranceXIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedTemplate = templateRepository.saveAndFlush(template);

        // Get all the templateList where dateDelivranceX equals to
        defaultTemplateFiltering(
            "dateDelivranceX.equals=" + DEFAULT_DATE_DELIVRANCE_X,
            "dateDelivranceX.equals=" + UPDATED_DATE_DELIVRANCE_X
        );
    }

    @Test
    @Transactional
    void getAllTemplatesByDateDelivranceXIsInShouldWork() throws Exception {
        // Initialize the database
        insertedTemplate = templateRepository.saveAndFlush(template);

        // Get all the templateList where dateDelivranceX in
        defaultTemplateFiltering(
            "dateDelivranceX.in=" + DEFAULT_DATE_DELIVRANCE_X + "," + UPDATED_DATE_DELIVRANCE_X,
            "dateDelivranceX.in=" + UPDATED_DATE_DELIVRANCE_X
        );
    }

    @Test
    @Transactional
    void getAllTemplatesByDateDelivranceXIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedTemplate = templateRepository.saveAndFlush(template);

        // Get all the templateList where dateDelivranceX is not null
        defaultTemplateFiltering("dateDelivranceX.specified=true", "dateDelivranceX.specified=false");
    }

    @Test
    @Transactional
    void getAllTemplatesByDateDelivranceXIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedTemplate = templateRepository.saveAndFlush(template);

        // Get all the templateList where dateDelivranceX is greater than or equal to
        defaultTemplateFiltering(
            "dateDelivranceX.greaterThanOrEqual=" + DEFAULT_DATE_DELIVRANCE_X,
            "dateDelivranceX.greaterThanOrEqual=" + UPDATED_DATE_DELIVRANCE_X
        );
    }

    @Test
    @Transactional
    void getAllTemplatesByDateDelivranceXIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedTemplate = templateRepository.saveAndFlush(template);

        // Get all the templateList where dateDelivranceX is less than or equal to
        defaultTemplateFiltering(
            "dateDelivranceX.lessThanOrEqual=" + DEFAULT_DATE_DELIVRANCE_X,
            "dateDelivranceX.lessThanOrEqual=" + SMALLER_DATE_DELIVRANCE_X
        );
    }

    @Test
    @Transactional
    void getAllTemplatesByDateDelivranceXIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedTemplate = templateRepository.saveAndFlush(template);

        // Get all the templateList where dateDelivranceX is less than
        defaultTemplateFiltering(
            "dateDelivranceX.lessThan=" + UPDATED_DATE_DELIVRANCE_X,
            "dateDelivranceX.lessThan=" + DEFAULT_DATE_DELIVRANCE_X
        );
    }

    @Test
    @Transactional
    void getAllTemplatesByDateDelivranceXIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedTemplate = templateRepository.saveAndFlush(template);

        // Get all the templateList where dateDelivranceX is greater than
        defaultTemplateFiltering(
            "dateDelivranceX.greaterThan=" + SMALLER_DATE_DELIVRANCE_X,
            "dateDelivranceX.greaterThan=" + DEFAULT_DATE_DELIVRANCE_X
        );
    }

    @Test
    @Transactional
    void getAllTemplatesByDateDelivranceYIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedTemplate = templateRepository.saveAndFlush(template);

        // Get all the templateList where dateDelivranceY equals to
        defaultTemplateFiltering(
            "dateDelivranceY.equals=" + DEFAULT_DATE_DELIVRANCE_Y,
            "dateDelivranceY.equals=" + UPDATED_DATE_DELIVRANCE_Y
        );
    }

    @Test
    @Transactional
    void getAllTemplatesByDateDelivranceYIsInShouldWork() throws Exception {
        // Initialize the database
        insertedTemplate = templateRepository.saveAndFlush(template);

        // Get all the templateList where dateDelivranceY in
        defaultTemplateFiltering(
            "dateDelivranceY.in=" + DEFAULT_DATE_DELIVRANCE_Y + "," + UPDATED_DATE_DELIVRANCE_Y,
            "dateDelivranceY.in=" + UPDATED_DATE_DELIVRANCE_Y
        );
    }

    @Test
    @Transactional
    void getAllTemplatesByDateDelivranceYIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedTemplate = templateRepository.saveAndFlush(template);

        // Get all the templateList where dateDelivranceY is not null
        defaultTemplateFiltering("dateDelivranceY.specified=true", "dateDelivranceY.specified=false");
    }

    @Test
    @Transactional
    void getAllTemplatesByDateDelivranceYIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedTemplate = templateRepository.saveAndFlush(template);

        // Get all the templateList where dateDelivranceY is greater than or equal to
        defaultTemplateFiltering(
            "dateDelivranceY.greaterThanOrEqual=" + DEFAULT_DATE_DELIVRANCE_Y,
            "dateDelivranceY.greaterThanOrEqual=" + UPDATED_DATE_DELIVRANCE_Y
        );
    }

    @Test
    @Transactional
    void getAllTemplatesByDateDelivranceYIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedTemplate = templateRepository.saveAndFlush(template);

        // Get all the templateList where dateDelivranceY is less than or equal to
        defaultTemplateFiltering(
            "dateDelivranceY.lessThanOrEqual=" + DEFAULT_DATE_DELIVRANCE_Y,
            "dateDelivranceY.lessThanOrEqual=" + SMALLER_DATE_DELIVRANCE_Y
        );
    }

    @Test
    @Transactional
    void getAllTemplatesByDateDelivranceYIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedTemplate = templateRepository.saveAndFlush(template);

        // Get all the templateList where dateDelivranceY is less than
        defaultTemplateFiltering(
            "dateDelivranceY.lessThan=" + UPDATED_DATE_DELIVRANCE_Y,
            "dateDelivranceY.lessThan=" + DEFAULT_DATE_DELIVRANCE_Y
        );
    }

    @Test
    @Transactional
    void getAllTemplatesByDateDelivranceYIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedTemplate = templateRepository.saveAndFlush(template);

        // Get all the templateList where dateDelivranceY is greater than
        defaultTemplateFiltering(
            "dateDelivranceY.greaterThan=" + SMALLER_DATE_DELIVRANCE_Y,
            "dateDelivranceY.greaterThan=" + DEFAULT_DATE_DELIVRANCE_Y
        );
    }

    @Test
    @Transactional
    void getAllTemplatesByDateExpirationXIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedTemplate = templateRepository.saveAndFlush(template);

        // Get all the templateList where dateExpirationX equals to
        defaultTemplateFiltering(
            "dateExpirationX.equals=" + DEFAULT_DATE_EXPIRATION_X,
            "dateExpirationX.equals=" + UPDATED_DATE_EXPIRATION_X
        );
    }

    @Test
    @Transactional
    void getAllTemplatesByDateExpirationXIsInShouldWork() throws Exception {
        // Initialize the database
        insertedTemplate = templateRepository.saveAndFlush(template);

        // Get all the templateList where dateExpirationX in
        defaultTemplateFiltering(
            "dateExpirationX.in=" + DEFAULT_DATE_EXPIRATION_X + "," + UPDATED_DATE_EXPIRATION_X,
            "dateExpirationX.in=" + UPDATED_DATE_EXPIRATION_X
        );
    }

    @Test
    @Transactional
    void getAllTemplatesByDateExpirationXIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedTemplate = templateRepository.saveAndFlush(template);

        // Get all the templateList where dateExpirationX is not null
        defaultTemplateFiltering("dateExpirationX.specified=true", "dateExpirationX.specified=false");
    }

    @Test
    @Transactional
    void getAllTemplatesByDateExpirationXIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedTemplate = templateRepository.saveAndFlush(template);

        // Get all the templateList where dateExpirationX is greater than or equal to
        defaultTemplateFiltering(
            "dateExpirationX.greaterThanOrEqual=" + DEFAULT_DATE_EXPIRATION_X,
            "dateExpirationX.greaterThanOrEqual=" + UPDATED_DATE_EXPIRATION_X
        );
    }

    @Test
    @Transactional
    void getAllTemplatesByDateExpirationXIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedTemplate = templateRepository.saveAndFlush(template);

        // Get all the templateList where dateExpirationX is less than or equal to
        defaultTemplateFiltering(
            "dateExpirationX.lessThanOrEqual=" + DEFAULT_DATE_EXPIRATION_X,
            "dateExpirationX.lessThanOrEqual=" + SMALLER_DATE_EXPIRATION_X
        );
    }

    @Test
    @Transactional
    void getAllTemplatesByDateExpirationXIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedTemplate = templateRepository.saveAndFlush(template);

        // Get all the templateList where dateExpirationX is less than
        defaultTemplateFiltering(
            "dateExpirationX.lessThan=" + UPDATED_DATE_EXPIRATION_X,
            "dateExpirationX.lessThan=" + DEFAULT_DATE_EXPIRATION_X
        );
    }

    @Test
    @Transactional
    void getAllTemplatesByDateExpirationXIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedTemplate = templateRepository.saveAndFlush(template);

        // Get all the templateList where dateExpirationX is greater than
        defaultTemplateFiltering(
            "dateExpirationX.greaterThan=" + SMALLER_DATE_EXPIRATION_X,
            "dateExpirationX.greaterThan=" + DEFAULT_DATE_EXPIRATION_X
        );
    }

    @Test
    @Transactional
    void getAllTemplatesByDateExpirationYIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedTemplate = templateRepository.saveAndFlush(template);

        // Get all the templateList where dateExpirationY equals to
        defaultTemplateFiltering(
            "dateExpirationY.equals=" + DEFAULT_DATE_EXPIRATION_Y,
            "dateExpirationY.equals=" + UPDATED_DATE_EXPIRATION_Y
        );
    }

    @Test
    @Transactional
    void getAllTemplatesByDateExpirationYIsInShouldWork() throws Exception {
        // Initialize the database
        insertedTemplate = templateRepository.saveAndFlush(template);

        // Get all the templateList where dateExpirationY in
        defaultTemplateFiltering(
            "dateExpirationY.in=" + DEFAULT_DATE_EXPIRATION_Y + "," + UPDATED_DATE_EXPIRATION_Y,
            "dateExpirationY.in=" + UPDATED_DATE_EXPIRATION_Y
        );
    }

    @Test
    @Transactional
    void getAllTemplatesByDateExpirationYIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedTemplate = templateRepository.saveAndFlush(template);

        // Get all the templateList where dateExpirationY is not null
        defaultTemplateFiltering("dateExpirationY.specified=true", "dateExpirationY.specified=false");
    }

    @Test
    @Transactional
    void getAllTemplatesByDateExpirationYIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedTemplate = templateRepository.saveAndFlush(template);

        // Get all the templateList where dateExpirationY is greater than or equal to
        defaultTemplateFiltering(
            "dateExpirationY.greaterThanOrEqual=" + DEFAULT_DATE_EXPIRATION_Y,
            "dateExpirationY.greaterThanOrEqual=" + UPDATED_DATE_EXPIRATION_Y
        );
    }

    @Test
    @Transactional
    void getAllTemplatesByDateExpirationYIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedTemplate = templateRepository.saveAndFlush(template);

        // Get all the templateList where dateExpirationY is less than or equal to
        defaultTemplateFiltering(
            "dateExpirationY.lessThanOrEqual=" + DEFAULT_DATE_EXPIRATION_Y,
            "dateExpirationY.lessThanOrEqual=" + SMALLER_DATE_EXPIRATION_Y
        );
    }

    @Test
    @Transactional
    void getAllTemplatesByDateExpirationYIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedTemplate = templateRepository.saveAndFlush(template);

        // Get all the templateList where dateExpirationY is less than
        defaultTemplateFiltering(
            "dateExpirationY.lessThan=" + UPDATED_DATE_EXPIRATION_Y,
            "dateExpirationY.lessThan=" + DEFAULT_DATE_EXPIRATION_Y
        );
    }

    @Test
    @Transactional
    void getAllTemplatesByDateExpirationYIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedTemplate = templateRepository.saveAndFlush(template);

        // Get all the templateList where dateExpirationY is greater than
        defaultTemplateFiltering(
            "dateExpirationY.greaterThan=" + SMALLER_DATE_EXPIRATION_Y,
            "dateExpirationY.greaterThan=" + DEFAULT_DATE_EXPIRATION_Y
        );
    }

    @Test
    @Transactional
    void getAllTemplatesBySampleLargeurIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedTemplate = templateRepository.saveAndFlush(template);

        // Get all the templateList where sampleLargeur equals to
        defaultTemplateFiltering("sampleLargeur.equals=" + DEFAULT_SAMPLE_LARGEUR, "sampleLargeur.equals=" + UPDATED_SAMPLE_LARGEUR);
    }

    @Test
    @Transactional
    void getAllTemplatesBySampleLargeurIsInShouldWork() throws Exception {
        // Initialize the database
        insertedTemplate = templateRepository.saveAndFlush(template);

        // Get all the templateList where sampleLargeur in
        defaultTemplateFiltering(
            "sampleLargeur.in=" + DEFAULT_SAMPLE_LARGEUR + "," + UPDATED_SAMPLE_LARGEUR,
            "sampleLargeur.in=" + UPDATED_SAMPLE_LARGEUR
        );
    }

    @Test
    @Transactional
    void getAllTemplatesBySampleLargeurIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedTemplate = templateRepository.saveAndFlush(template);

        // Get all the templateList where sampleLargeur is not null
        defaultTemplateFiltering("sampleLargeur.specified=true", "sampleLargeur.specified=false");
    }

    @Test
    @Transactional
    void getAllTemplatesBySampleLargeurIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedTemplate = templateRepository.saveAndFlush(template);

        // Get all the templateList where sampleLargeur is greater than or equal to
        defaultTemplateFiltering(
            "sampleLargeur.greaterThanOrEqual=" + DEFAULT_SAMPLE_LARGEUR,
            "sampleLargeur.greaterThanOrEqual=" + UPDATED_SAMPLE_LARGEUR
        );
    }

    @Test
    @Transactional
    void getAllTemplatesBySampleLargeurIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedTemplate = templateRepository.saveAndFlush(template);

        // Get all the templateList where sampleLargeur is less than or equal to
        defaultTemplateFiltering(
            "sampleLargeur.lessThanOrEqual=" + DEFAULT_SAMPLE_LARGEUR,
            "sampleLargeur.lessThanOrEqual=" + SMALLER_SAMPLE_LARGEUR
        );
    }

    @Test
    @Transactional
    void getAllTemplatesBySampleLargeurIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedTemplate = templateRepository.saveAndFlush(template);

        // Get all the templateList where sampleLargeur is less than
        defaultTemplateFiltering("sampleLargeur.lessThan=" + UPDATED_SAMPLE_LARGEUR, "sampleLargeur.lessThan=" + DEFAULT_SAMPLE_LARGEUR);
    }

    @Test
    @Transactional
    void getAllTemplatesBySampleLargeurIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedTemplate = templateRepository.saveAndFlush(template);

        // Get all the templateList where sampleLargeur is greater than
        defaultTemplateFiltering(
            "sampleLargeur.greaterThan=" + SMALLER_SAMPLE_LARGEUR,
            "sampleLargeur.greaterThan=" + DEFAULT_SAMPLE_LARGEUR
        );
    }

    @Test
    @Transactional
    void getAllTemplatesBySampleLongueurIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedTemplate = templateRepository.saveAndFlush(template);

        // Get all the templateList where sampleLongueur equals to
        defaultTemplateFiltering("sampleLongueur.equals=" + DEFAULT_SAMPLE_LONGUEUR, "sampleLongueur.equals=" + UPDATED_SAMPLE_LONGUEUR);
    }

    @Test
    @Transactional
    void getAllTemplatesBySampleLongueurIsInShouldWork() throws Exception {
        // Initialize the database
        insertedTemplate = templateRepository.saveAndFlush(template);

        // Get all the templateList where sampleLongueur in
        defaultTemplateFiltering(
            "sampleLongueur.in=" + DEFAULT_SAMPLE_LONGUEUR + "," + UPDATED_SAMPLE_LONGUEUR,
            "sampleLongueur.in=" + UPDATED_SAMPLE_LONGUEUR
        );
    }

    @Test
    @Transactional
    void getAllTemplatesBySampleLongueurIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedTemplate = templateRepository.saveAndFlush(template);

        // Get all the templateList where sampleLongueur is not null
        defaultTemplateFiltering("sampleLongueur.specified=true", "sampleLongueur.specified=false");
    }

    @Test
    @Transactional
    void getAllTemplatesBySampleLongueurIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedTemplate = templateRepository.saveAndFlush(template);

        // Get all the templateList where sampleLongueur is greater than or equal to
        defaultTemplateFiltering(
            "sampleLongueur.greaterThanOrEqual=" + DEFAULT_SAMPLE_LONGUEUR,
            "sampleLongueur.greaterThanOrEqual=" + UPDATED_SAMPLE_LONGUEUR
        );
    }

    @Test
    @Transactional
    void getAllTemplatesBySampleLongueurIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedTemplate = templateRepository.saveAndFlush(template);

        // Get all the templateList where sampleLongueur is less than or equal to
        defaultTemplateFiltering(
            "sampleLongueur.lessThanOrEqual=" + DEFAULT_SAMPLE_LONGUEUR,
            "sampleLongueur.lessThanOrEqual=" + SMALLER_SAMPLE_LONGUEUR
        );
    }

    @Test
    @Transactional
    void getAllTemplatesBySampleLongueurIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedTemplate = templateRepository.saveAndFlush(template);

        // Get all the templateList where sampleLongueur is less than
        defaultTemplateFiltering(
            "sampleLongueur.lessThan=" + UPDATED_SAMPLE_LONGUEUR,
            "sampleLongueur.lessThan=" + DEFAULT_SAMPLE_LONGUEUR
        );
    }

    @Test
    @Transactional
    void getAllTemplatesBySampleLongueurIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedTemplate = templateRepository.saveAndFlush(template);

        // Get all the templateList where sampleLongueur is greater than
        defaultTemplateFiltering(
            "sampleLongueur.greaterThan=" + SMALLER_SAMPLE_LONGUEUR,
            "sampleLongueur.greaterThan=" + DEFAULT_SAMPLE_LONGUEUR
        );
    }

    private void defaultTemplateFiltering(String shouldBeFound, String shouldNotBeFound) throws Exception {
        defaultTemplateShouldBeFound(shouldBeFound);
        defaultTemplateShouldNotBeFound(shouldNotBeFound);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultTemplateShouldBeFound(String filter) throws Exception {
        restTemplateMockMvc
            .perform(get(ENTITY_API_URL + "?sort=libelle,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].libelle").value(hasItem(template.getLibelle())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].imageHeigth").value(hasItem(DEFAULT_IMAGE_HEIGTH.doubleValue())))
            .andExpect(jsonPath("$.[*].imageWidth").value(hasItem(DEFAULT_IMAGE_WIDTH.doubleValue())))
            .andExpect(jsonPath("$.[*].imageX").value(hasItem(DEFAULT_IMAGE_X.doubleValue())))
            .andExpect(jsonPath("$.[*].imageY").value(hasItem(DEFAULT_IMAGE_Y.doubleValue())))
            .andExpect(jsonPath("$.[*].matriculeX").value(hasItem(DEFAULT_MATRICULE_X.doubleValue())))
            .andExpect(jsonPath("$.[*].matriculeY").value(hasItem(DEFAULT_MATRICULE_Y.doubleValue())))
            .andExpect(jsonPath("$.[*].membreX").value(hasItem(DEFAULT_MEMBRE_X.doubleValue())))
            .andExpect(jsonPath("$.[*].membreY").value(hasItem(DEFAULT_MEMBRE_Y.doubleValue())))
            .andExpect(jsonPath("$.[*].nomX").value(hasItem(DEFAULT_NOM_X.doubleValue())))
            .andExpect(jsonPath("$.[*].nomY").value(hasItem(DEFAULT_NOM_Y.doubleValue())))
            .andExpect(jsonPath("$.[*].prenomX").value(hasItem(DEFAULT_PRENOM_X.doubleValue())))
            .andExpect(jsonPath("$.[*].prenomY").value(hasItem(DEFAULT_PRENOM_Y.doubleValue())))
            .andExpect(jsonPath("$.[*].qrHeight").value(hasItem(DEFAULT_QR_HEIGHT.doubleValue())))
            .andExpect(jsonPath("$.[*].qrWidth").value(hasItem(DEFAULT_QR_WIDTH.doubleValue())))
            .andExpect(jsonPath("$.[*].qrX").value(hasItem(DEFAULT_QR_X.doubleValue())))
            .andExpect(jsonPath("$.[*].qrY").value(hasItem(DEFAULT_QR_Y.doubleValue())))
            .andExpect(jsonPath("$.[*].dateDelivranceX").value(hasItem(DEFAULT_DATE_DELIVRANCE_X.doubleValue())))
            .andExpect(jsonPath("$.[*].dateDelivranceY").value(hasItem(DEFAULT_DATE_DELIVRANCE_Y.doubleValue())))
            .andExpect(jsonPath("$.[*].dateExpirationX").value(hasItem(DEFAULT_DATE_EXPIRATION_X.doubleValue())))
            .andExpect(jsonPath("$.[*].dateExpirationY").value(hasItem(DEFAULT_DATE_EXPIRATION_Y.doubleValue())))
            .andExpect(jsonPath("$.[*].sampleLargeur").value(hasItem(DEFAULT_SAMPLE_LARGEUR.doubleValue())))
            .andExpect(jsonPath("$.[*].sampleLongueur").value(hasItem(DEFAULT_SAMPLE_LONGUEUR.doubleValue())))
            .andExpect(jsonPath("$.[*].pdfContentRectoContentType").value(hasItem(DEFAULT_PDF_CONTENT_RECTO_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].pdfContentRecto").value(hasItem(Base64.getEncoder().encodeToString(DEFAULT_PDF_CONTENT_RECTO))))
            .andExpect(jsonPath("$.[*].pdfContentVersoContentType").value(hasItem(DEFAULT_PDF_CONTENT_VERSO_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].pdfContentVerso").value(hasItem(Base64.getEncoder().encodeToString(DEFAULT_PDF_CONTENT_VERSO))));

        // Check, that the count call also returns 1
        restTemplateMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=libelle,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultTemplateShouldNotBeFound(String filter) throws Exception {
        restTemplateMockMvc
            .perform(get(ENTITY_API_URL + "?sort=libelle,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restTemplateMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=libelle,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    void getNonExistingTemplate() throws Exception {
        // Get the template
        restTemplateMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingTemplate() throws Exception {
        // Initialize the database
        template.setLibelle(UUID.randomUUID().toString());
        insertedTemplate = templateRepository.saveAndFlush(template);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the template
        Template updatedTemplate = templateRepository.findById(template.getLibelle()).orElseThrow();
        // Disconnect from session so that the updates on updatedTemplate are not directly saved in db
        em.detach(updatedTemplate);
        updatedTemplate
            .description(UPDATED_DESCRIPTION)
            .imageHeigth(UPDATED_IMAGE_HEIGTH)
            .imageWidth(UPDATED_IMAGE_WIDTH)
            .imageX(UPDATED_IMAGE_X)
            .imageY(UPDATED_IMAGE_Y)
            .matriculeX(UPDATED_MATRICULE_X)
            .matriculeY(UPDATED_MATRICULE_Y)
            .membreX(UPDATED_MEMBRE_X)
            .membreY(UPDATED_MEMBRE_Y)
            .nomX(UPDATED_NOM_X)
            .nomY(UPDATED_NOM_Y)
            .prenomX(UPDATED_PRENOM_X)
            .prenomY(UPDATED_PRENOM_Y)
            .qrHeight(UPDATED_QR_HEIGHT)
            .qrWidth(UPDATED_QR_WIDTH)
            .qrX(UPDATED_QR_X)
            .qrY(UPDATED_QR_Y)
            .dateDelivranceX(UPDATED_DATE_DELIVRANCE_X)
            .dateDelivranceY(UPDATED_DATE_DELIVRANCE_Y)
            .dateExpirationX(UPDATED_DATE_EXPIRATION_X)
            .dateExpirationY(UPDATED_DATE_EXPIRATION_Y)
            .sampleLargeur(UPDATED_SAMPLE_LARGEUR)
            .sampleLongueur(UPDATED_SAMPLE_LONGUEUR)
            .pdfContentRecto(UPDATED_PDF_CONTENT_RECTO)
            .pdfContentRectoContentType(UPDATED_PDF_CONTENT_RECTO_CONTENT_TYPE)
            .pdfContentVerso(UPDATED_PDF_CONTENT_VERSO)
            .pdfContentVersoContentType(UPDATED_PDF_CONTENT_VERSO_CONTENT_TYPE);
        TemplateDTO templateDTO = templateMapper.toDto(updatedTemplate);

        restTemplateMockMvc
            .perform(
                put(ENTITY_API_URL_ID, templateDTO.getLibelle())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(templateDTO))
            )
            .andExpect(status().isOk());

        // Validate the Template in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedTemplateToMatchAllProperties(updatedTemplate);
    }

    @Test
    @Transactional
    void putNonExistingTemplate() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        template.setLibelle(UUID.randomUUID().toString());

        // Create the Template
        TemplateDTO templateDTO = templateMapper.toDto(template);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTemplateMockMvc
            .perform(
                put(ENTITY_API_URL_ID, templateDTO.getLibelle())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(templateDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Template in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchTemplate() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        template.setLibelle(UUID.randomUUID().toString());

        // Create the Template
        TemplateDTO templateDTO = templateMapper.toDto(template);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTemplateMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(templateDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Template in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamTemplate() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        template.setLibelle(UUID.randomUUID().toString());

        // Create the Template
        TemplateDTO templateDTO = templateMapper.toDto(template);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTemplateMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(templateDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Template in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateTemplateWithPatch() throws Exception {
        // Initialize the database
        template.setLibelle(UUID.randomUUID().toString());
        insertedTemplate = templateRepository.saveAndFlush(template);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the template using partial update
        Template partialUpdatedTemplate = new Template();
        partialUpdatedTemplate.setLibelle(template.getLibelle());

        partialUpdatedTemplate
            .description(UPDATED_DESCRIPTION)
            .imageWidth(UPDATED_IMAGE_WIDTH)
            .imageY(UPDATED_IMAGE_Y)
            .matriculeX(UPDATED_MATRICULE_X)
            .matriculeY(UPDATED_MATRICULE_Y)
            .membreX(UPDATED_MEMBRE_X)
            .nomX(UPDATED_NOM_X)
            .prenomX(UPDATED_PRENOM_X)
            .qrX(UPDATED_QR_X)
            .dateExpirationX(UPDATED_DATE_EXPIRATION_X)
            .dateExpirationY(UPDATED_DATE_EXPIRATION_Y)
            .sampleLongueur(UPDATED_SAMPLE_LONGUEUR)
            .pdfContentRecto(UPDATED_PDF_CONTENT_RECTO)
            .pdfContentRectoContentType(UPDATED_PDF_CONTENT_RECTO_CONTENT_TYPE);

        restTemplateMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTemplate.getLibelle())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedTemplate))
            )
            .andExpect(status().isOk());

        // Validate the Template in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertTemplateUpdatableFieldsEquals(createUpdateProxyForBean(partialUpdatedTemplate, template), getPersistedTemplate(template));
    }

    @Test
    @Transactional
    void fullUpdateTemplateWithPatch() throws Exception {
        // Initialize the database
        template.setLibelle(UUID.randomUUID().toString());
        insertedTemplate = templateRepository.saveAndFlush(template);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the template using partial update
        Template partialUpdatedTemplate = new Template();
        partialUpdatedTemplate.setLibelle(template.getLibelle());

        partialUpdatedTemplate
            .description(UPDATED_DESCRIPTION)
            .imageHeigth(UPDATED_IMAGE_HEIGTH)
            .imageWidth(UPDATED_IMAGE_WIDTH)
            .imageX(UPDATED_IMAGE_X)
            .imageY(UPDATED_IMAGE_Y)
            .matriculeX(UPDATED_MATRICULE_X)
            .matriculeY(UPDATED_MATRICULE_Y)
            .membreX(UPDATED_MEMBRE_X)
            .membreY(UPDATED_MEMBRE_Y)
            .nomX(UPDATED_NOM_X)
            .nomY(UPDATED_NOM_Y)
            .prenomX(UPDATED_PRENOM_X)
            .prenomY(UPDATED_PRENOM_Y)
            .qrHeight(UPDATED_QR_HEIGHT)
            .qrWidth(UPDATED_QR_WIDTH)
            .qrX(UPDATED_QR_X)
            .qrY(UPDATED_QR_Y)
            .dateDelivranceX(UPDATED_DATE_DELIVRANCE_X)
            .dateDelivranceY(UPDATED_DATE_DELIVRANCE_Y)
            .dateExpirationX(UPDATED_DATE_EXPIRATION_X)
            .dateExpirationY(UPDATED_DATE_EXPIRATION_Y)
            .sampleLargeur(UPDATED_SAMPLE_LARGEUR)
            .sampleLongueur(UPDATED_SAMPLE_LONGUEUR)
            .pdfContentRecto(UPDATED_PDF_CONTENT_RECTO)
            .pdfContentRectoContentType(UPDATED_PDF_CONTENT_RECTO_CONTENT_TYPE)
            .pdfContentVerso(UPDATED_PDF_CONTENT_VERSO)
            .pdfContentVersoContentType(UPDATED_PDF_CONTENT_VERSO_CONTENT_TYPE);

        restTemplateMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTemplate.getLibelle())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedTemplate))
            )
            .andExpect(status().isOk());

        // Validate the Template in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertTemplateUpdatableFieldsEquals(partialUpdatedTemplate, getPersistedTemplate(partialUpdatedTemplate));
    }

    @Test
    @Transactional
    void patchNonExistingTemplate() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        template.setLibelle(UUID.randomUUID().toString());

        // Create the Template
        TemplateDTO templateDTO = templateMapper.toDto(template);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTemplateMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, templateDTO.getLibelle())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(templateDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Template in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchTemplate() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        template.setLibelle(UUID.randomUUID().toString());

        // Create the Template
        TemplateDTO templateDTO = templateMapper.toDto(template);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTemplateMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(templateDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Template in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamTemplate() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        template.setLibelle(UUID.randomUUID().toString());

        // Create the Template
        TemplateDTO templateDTO = templateMapper.toDto(template);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTemplateMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(templateDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Template in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteTemplate() throws Exception {
        // Initialize the database
        template.setLibelle(UUID.randomUUID().toString());
        insertedTemplate = templateRepository.saveAndFlush(template);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the template
        restTemplateMockMvc
            .perform(delete(ENTITY_API_URL_ID, template.getLibelle()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return templateRepository.count();
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

    protected Template getPersistedTemplate(Template template) {
        return templateRepository.findById(template.getLibelle()).orElseThrow();
    }

    protected void assertPersistedTemplateToMatchAllProperties(Template expectedTemplate) {
        assertTemplateAllPropertiesEquals(expectedTemplate, getPersistedTemplate(expectedTemplate));
    }

    protected void assertPersistedTemplateToMatchUpdatableProperties(Template expectedTemplate) {
        assertTemplateAllUpdatablePropertiesEquals(expectedTemplate, getPersistedTemplate(expectedTemplate));
    }
}
