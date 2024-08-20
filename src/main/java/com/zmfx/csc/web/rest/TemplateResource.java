package com.zmfx.csc.web.rest;

import com.zmfx.csc.repository.TemplateRepository;
import com.zmfx.csc.service.TemplateQueryService;
import com.zmfx.csc.service.TemplateService;
import com.zmfx.csc.service.criteria.TemplateCriteria;
import com.zmfx.csc.service.dto.TemplateDTO;
import com.zmfx.csc.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.zmfx.csc.domain.Template}.
 */
@RestController
@RequestMapping("/api/templates")
public class TemplateResource {

    private static final Logger log = LoggerFactory.getLogger(TemplateResource.class);

    private static final String ENTITY_NAME = "template";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TemplateService templateService;

    private final TemplateRepository templateRepository;

    private final TemplateQueryService templateQueryService;

    public TemplateResource(
        TemplateService templateService,
        TemplateRepository templateRepository,
        TemplateQueryService templateQueryService
    ) {
        this.templateService = templateService;
        this.templateRepository = templateRepository;
        this.templateQueryService = templateQueryService;
    }

    /**
     * {@code POST  /templates} : Create a new template.
     *
     * @param templateDTO the templateDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new templateDTO, or with status {@code 400 (Bad Request)} if the template has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<TemplateDTO> createTemplate(@RequestBody TemplateDTO templateDTO) throws URISyntaxException {
        log.debug("REST request to save Template : {}", templateDTO);
        if (templateRepository.existsById(templateDTO.getLibelle())) {
            throw new BadRequestAlertException("template already exists", ENTITY_NAME, "idexists");
        }
        templateDTO = templateService.save(templateDTO);
        return ResponseEntity.created(new URI("/api/templates/" + templateDTO.getLibelle()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, templateDTO.getLibelle()))
            .body(templateDTO);
    }

    /**
     * {@code PUT  /templates/:libelle} : Updates an existing template.
     *
     * @param libelle the id of the templateDTO to save.
     * @param templateDTO the templateDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated templateDTO,
     * or with status {@code 400 (Bad Request)} if the templateDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the templateDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{libelle}")
    public ResponseEntity<TemplateDTO> updateTemplate(
        @PathVariable(value = "libelle", required = false) final String libelle,
        @RequestBody TemplateDTO templateDTO
    ) throws URISyntaxException {
        log.debug("REST request to update Template : {}, {}", libelle, templateDTO);
        if (templateDTO.getLibelle() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(libelle, templateDTO.getLibelle())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!templateRepository.existsById(libelle)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        templateDTO = templateService.update(templateDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, templateDTO.getLibelle()))
            .body(templateDTO);
    }

    /**
     * {@code PATCH  /templates/:libelle} : Partial updates given fields of an existing template, field will ignore if it is null
     *
     * @param libelle the id of the templateDTO to save.
     * @param templateDTO the templateDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated templateDTO,
     * or with status {@code 400 (Bad Request)} if the templateDTO is not valid,
     * or with status {@code 404 (Not Found)} if the templateDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the templateDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{libelle}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<TemplateDTO> partialUpdateTemplate(
        @PathVariable(value = "libelle", required = false) final String libelle,
        @RequestBody TemplateDTO templateDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update Template partially : {}, {}", libelle, templateDTO);
        if (templateDTO.getLibelle() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(libelle, templateDTO.getLibelle())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!templateRepository.existsById(libelle)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<TemplateDTO> result = templateService.partialUpdate(templateDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, templateDTO.getLibelle())
        );
    }

    /**
     * {@code GET  /templates} : get all the templates.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of templates in body.
     */
    @GetMapping("")
    public ResponseEntity<List<TemplateDTO>> getAllTemplates(
        TemplateCriteria criteria,
        @org.springdoc.core.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get Templates by criteria: {}", criteria);

        Page<TemplateDTO> page = templateQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /templates/count} : count all the templates.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/count")
    public ResponseEntity<Long> countTemplates(TemplateCriteria criteria) {
        log.debug("REST request to count Templates by criteria: {}", criteria);
        return ResponseEntity.ok().body(templateQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /templates/:id} : get the "id" template.
     *
     * @param id the id of the templateDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the templateDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<TemplateDTO> getTemplate(@PathVariable("id") String id) {
        log.debug("REST request to get Template : {}", id);
        Optional<TemplateDTO> templateDTO = templateService.findOne(id);
        return ResponseUtil.wrapOrNotFound(templateDTO);
    }

    /**
     * {@code DELETE  /templates/:id} : delete the "id" template.
     *
     * @param id the id of the templateDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTemplate(@PathVariable("id") String id) {
        log.debug("REST request to delete Template : {}", id);
        templateService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }

    /**
     * {@code GET  /templates} : get all the templates.
     *
     * @param pageable the pagination information.
     * @param searchKey the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of templates in body.
     */
    @GetMapping("/search/{searchKey}")
    public ResponseEntity<List<TemplateDTO>> getAllTemplates(
        @PathVariable("searchKey") String searchKey,
        @org.springdoc.core.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get Templates by searchKey: {}", searchKey);
        Page<TemplateDTO> page = templateQueryService.findByCriteriaSearchKey(null, pageable, searchKey);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }
}
