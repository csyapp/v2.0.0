package com.zmfx.csc.service;

import com.zmfx.csc.service.dto.CarteDTO;
import com.zmfx.csc.service.dto.ReportingAnneeDTO;
import com.zmfx.csc.service.dto.ReportingJourDTO;
import com.zmfx.csc.service.dto.ReportingMoisDTO;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.zmfx.csc.domain.Carte}.
 */
public interface CarteService {
    CarteDTO parameterizeCardDTO(CarteDTO carteDTO);

    /**
     * Save a carte.
     *
     * @param carteDTO the entity to save.
     * @return the persisted entity.
     */
    CarteDTO save(CarteDTO carteDTO);

    /**
     * Updates a carte.
     *
     * @param carteDTO the entity to update.
     * @return the persisted entity.
     */
    CarteDTO update(CarteDTO carteDTO);

    /**
     * Partially updates a carte.
     *
     * @param carteDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<CarteDTO> partialUpdate(CarteDTO carteDTO);

    /**
     * Get the "id" carte.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<CarteDTO> findOne(String id);

    /**
     * Delete the "id" carte.
     *
     * @param id the id of the entity.
     */
    void delete(String id);

    Long countAllCards();

    /**
     * Get daily base reporting on cards.
     *
     * @param annee, mois search criteria.
     * @return list of quantity per day.
     */
    List<ReportingJourDTO> getJourReporting(String annee, String mois);

    /**
     * Get monthly base reporting on cards.
     *
     * @param annee search criteria .
     * @return list of quantities per month.
     */
    List<ReportingMoisDTO> getMoisReporting(String annee);

    /**
     * Get annual based reporting on cards.
     *
     * @param annee, profonduer.
     * @return list of  quantities per month.
     */
    List<ReportingAnneeDTO> getAnneeReporting(Integer annee, Integer profondeur);
}
