package com.zmfx.csc.service;

import com.zmfx.csc.service.dto.TemplateDTO;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.zmfx.csc.domain.Template}.
 */
public interface TemplateService {
    /**
     * Save a template.
     *
     * @param templateDTO the entity to save.
     * @return the persisted entity.
     */
    TemplateDTO save(TemplateDTO templateDTO);

    /**
     * Updates a template.
     *
     * @param templateDTO the entity to update.
     * @return the persisted entity.
     */
    TemplateDTO update(TemplateDTO templateDTO);

    /**
     * Partially updates a template.
     *
     * @param templateDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<TemplateDTO> partialUpdate(TemplateDTO templateDTO);

    /**
     * Get the "id" template.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<TemplateDTO> findOne(String id);

    /**
     * Delete the "id" template.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
