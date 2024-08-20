package com.zmfx.csc.web.rest;

import com.zmfx.csc.repository.CarteRepository;
import com.zmfx.csc.service.CarteQueryService;
import com.zmfx.csc.service.CarteService;
import com.zmfx.csc.service.criteria.CarteCriteria;
import com.zmfx.csc.service.dto.CarteDTO;
import com.zmfx.csc.web.rest.errors.BadRequestAlertException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.zmfx.csc.domain.Carte}.
 */
@RestController
@RequestMapping("/api/cartes")
public class CarteResource {

    private static final Logger log = LoggerFactory.getLogger(CarteResource.class);

    private static final String ENTITY_NAME = "carte";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CarteService carteService;

    private final CarteRepository carteRepository;

    private final CarteQueryService carteQueryService;

    public CarteResource(CarteService carteService, CarteRepository carteRepository, CarteQueryService carteQueryService) {
        this.carteService = carteService;
        this.carteRepository = carteRepository;
        this.carteQueryService = carteQueryService;
    }

    /**
     * {@code POST  /cartes} : Create a new carte.
     *
     * @param carteDTO the carteDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new carteDTO, or with status {@code 400 (Bad Request)} if the carte has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<CarteDTO> createCarte(@RequestBody CarteDTO carteDTO) throws URISyntaxException {
        log.debug("REST request to create Carte : {}", carteDTO);
        carteDTO = carteService.parameterizeCardDTO(carteDTO);
        if (carteRepository.existsById(carteDTO.getMatricule())) {
            throw new BadRequestAlertException("carte already exists", ENTITY_NAME, "idexists");
        }
        carteDTO = carteService.save(carteDTO);
        return ResponseEntity.created(new URI("/api/cartes/" + carteDTO.getMatricule()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, carteDTO.getMatricule()))
            .body(carteDTO);
    }

    /**
     * {@code PUT  /cartes/:matricule} : Updates an existing carte.
     *
     * @param matricule the id of the carteDTO to save.
     * @param carteDTO the carteDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated carteDTO,
     * or with status {@code 400 (Bad Request)} if the carteDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the carteDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{matricule}")
    public ResponseEntity<CarteDTO> updateCarte(
        @PathVariable(value = "matricule", required = false) final String matricule,
        @RequestBody CarteDTO carteDTO
    ) throws URISyntaxException {
        log.debug("REST request to update Carte : {}, {}", matricule, carteDTO);
        if (carteDTO.getMatricule() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(matricule, carteDTO.getMatricule())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!carteRepository.existsById(matricule)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        carteDTO = carteService.update(carteDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, carteDTO.getMatricule()))
            .body(carteDTO);
    }

    /**
     * {@code PATCH  /cartes/:matricule} : Partial updates given fields of an existing carte, field will ignore if it is null
     *
     * @param matricule the id of the carteDTO to save.
     * @param carteDTO the carteDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated carteDTO,
     * or with status {@code 400 (Bad Request)} if the carteDTO is not valid,
     * or with status {@code 404 (Not Found)} if the carteDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the carteDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{matricule}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<CarteDTO> partialUpdateCarte(
        @PathVariable(value = "matricule", required = false) final String matricule,
        @RequestBody CarteDTO carteDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update Carte partially : {}, {}", matricule, carteDTO);
        if (carteDTO.getMatricule() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(matricule, carteDTO.getMatricule())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!carteRepository.existsById(matricule)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<CarteDTO> result = carteService.partialUpdate(carteDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, carteDTO.getMatricule())
        );
    }

    /**
     * {@code GET  /cartes} : get all the cartes.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of cartes in body.
     */
    @GetMapping("")
    public ResponseEntity<List<CarteDTO>> getAllCartes(
        CarteCriteria criteria,
        @org.springdoc.core.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to getAllCartes by criteria: {}", criteria);

        Page<CarteDTO> page = carteQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /cartes/count} : count all the cartes.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/count")
    public ResponseEntity<Long> countCartes(CarteCriteria criteria) {
        log.debug("REST request to count Cartes by criteria: {}", criteria);
        return ResponseEntity.ok().body(carteQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /cartes/:id} : get the "id" carte.
     *
     * @param id the id of the carteDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the carteDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<CarteDTO> getCarte(@PathVariable("id") String id) {
        log.debug("REST request to get Carte : {}", id);
        Optional<CarteDTO> carteDTO = carteService.findOne(id);
        return ResponseUtil.wrapOrNotFound(carteDTO);
    }

    /**
     * {@code DELETE  /cartes/:id} : delete the "id" carte.
     *
     * @param id the id of the carteDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCarte(@PathVariable("id") String id) {
        log.debug("REST request to delete Carte : {}", id);
        carteService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id)).build();
    }

    /**
     * {@code GET  /cartes/pdf/:id} : get the "id" carte.
     *
     * @param id the id of the PDF to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the carteDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/pdf/{id}")
    public ResponseEntity<InputStreamResource> getPrintedCarte(@PathVariable("id") String id) throws FileNotFoundException {
        log.debug("REST request to get Card's pdf file : {}", id);
        File file = carteQueryService.getPdfArray(id);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=" + file.getName());

        return ResponseEntity.ok()
            .allow(HttpMethod.GET)
            .headers(headers)
            .contentType(MediaType.APPLICATION_PDF)
            .body(new InputStreamResource(new FileInputStream(file)));
    }

    /**
     * {@code GET  /cartes/notify/:id} : get the "id" carte.
     *
     * @param id the id of the PDF to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the carteDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/notify/{id}")
    public ResponseEntity<CarteDTO> getNotifiedCarte(@PathVariable("id") String id) throws FileNotFoundException {
        log.debug("REST de notification du supporter : {}", id);

        Optional<CarteDTO> carteDTOOptional = carteService.findOne(id);
        CarteDTO cto = carteDTOOptional.orElseThrow();
        cto.setIsImprime(true);

        log.debug("REST de MAJ IsImprime : {}", id);
        carteService.update(cto);
        carteQueryService.getNotifiedCard(carteDTOOptional.orElseThrow());
        return ResponseUtil.wrapOrNotFound(carteDTOOptional);
    }

    /**
     * {@code GET  /cartes} : get all the cartes.
     *
     * @param pageable the pagination information.
     * @param  searchKey string which should be contained in entity's attributes.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of cartes in body.
     */
    @GetMapping("/search/{searchKey}")
    public ResponseEntity<List<CarteDTO>> getAllSearchedCartes(
        @PathVariable("searchKey") String searchKey,
        @org.springdoc.core.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get Cartes by criteria: {}", searchKey);
        Page<CarteDTO> page = carteQueryService.findByCriteriaSearchKey(pageable, searchKey);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }
}
