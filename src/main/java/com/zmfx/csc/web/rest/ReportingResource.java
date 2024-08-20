package com.zmfx.csc.web.rest;

import com.zmfx.csc.service.ReportingQueryService;
import com.zmfx.csc.service.dto.ReportingAnneeDTO;
import com.zmfx.csc.service.dto.ReportingJourDTO;
import com.zmfx.csc.service.dto.ReportingMoisDTO;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller for managing {@link com.zmfx.csc.domain.Carte}.
 */
@RestController
@RequestMapping("/api/reporting")
public class ReportingResource {

    private static final Logger log = LoggerFactory.getLogger(ReportingResource.class);

    private final ReportingQueryService reportingQueryService;

    public ReportingResource(ReportingQueryService reportingQueryService) {
        this.reportingQueryService = reportingQueryService;
    }

    /**
     * {@code GET  /cartes} : get reporting Jour des cartes.
     *
     * @param annee year of daily report.
     * @param mois the criteria which the requested  should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of cartes in body.
     */
    @GetMapping("/jour/{annee}/{mois}")
    public ResponseEntity<List<ReportingJourDTO>> getReportingJour(@PathVariable("annee") String annee, @PathVariable("mois") String mois) {
        log.debug("REST de Reporting jour avec les paramètres : {} , {} ", annee, mois);

        List<ReportingJourDTO> page = reportingQueryService.getReportingJourDTOListTrue(annee, mois);
        return ResponseEntity.ok().body(page);
    }

    /**
     * {@code GET  /cartes} : get reporting Jour des cartes.
     *
     * @param annee year of daily report.
     * @param mois the criteria which the requested  should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of cartes in body.
     */
    @GetMapping("/jour/{annee}/{mois}/{isImprime}")
    public ResponseEntity<List<ReportingJourDTO>> getReportingJourFalse(
        @PathVariable("annee") String annee,
        @PathVariable("mois") String mois,
        @PathVariable("isImprime") String isImprime
    ) {
        log.debug("REST de Reporting jour avec les paramètres : {} , {} , {}", annee, mois, isImprime);
        List<ReportingJourDTO> page = reportingQueryService.getReportingJourDTOListFalse(annee, mois, isImprime);
        return ResponseEntity.ok().body(page);
    }

    /**
     * {@code GET  /cartes} : get reporting par Mois des cartes imprimées.
     *
     * @param annee year of daily report.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of cartes in body.
     */
    @GetMapping("/mois/{annee}")
    public ResponseEntity<List<ReportingMoisDTO>> getReportingMoisTrue(@PathVariable("annee") String annee) {
        log.debug("REST de Reporting Mois avec les paramètres : {} ", annee);
        List<ReportingMoisDTO> page = reportingQueryService.getReportingMoisDTOListTrue(annee);
        return ResponseEntity.ok().body(page);
    }

    /**
     * {@code GET  /cartes} : get reporting Par Mois des cartes non imprimées.
     *
     * @param annee year of daily report.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of cartes in body.
     */
    @GetMapping("/mois/{annee}/{isImprime}")
    public ResponseEntity<List<ReportingMoisDTO>> getReportingMoisFalse(
        @PathVariable("annee") String annee,
        @PathVariable("isImprime") String isImprime
    ) {
        log.debug("REST de Reporting Mois avec les paramètres : {} , {} ", annee, isImprime);
        List<ReportingMoisDTO> page = reportingQueryService.getReportingMoisDTOListFalse(annee, isImprime);
        return ResponseEntity.ok().body(page);
    }

    /**
     * {@code GET  /cartes} : get reporting par Année des cartes imprimées.
     *
     * @param annee year of daily report.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of cartes in body.
     */
    @GetMapping("/annee/{annee}")
    public ResponseEntity<List<ReportingAnneeDTO>> getReportingAnneeTrue(@PathVariable("annee") String annee) {
        log.debug("REST de Reporting Annee avec les paramètres : {} ", annee);
        List<ReportingAnneeDTO> page = reportingQueryService.getReportingAnneeDTOListTrue(annee);
        return ResponseEntity.ok().body(page);
    }

    /**
     * {@code GET  /cartes} : get reporting Par Année des cartes non imprimées.
     *
     * @param annee year of daily report.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of cartes in body.
     */
    @GetMapping("/annee/{annee}/{isImprime}")
    public ResponseEntity<List<ReportingAnneeDTO>> getReportingAnneeFalse(
        @PathVariable("annee") String annee,
        @PathVariable("isImprime") String isImprime
    ) {
        log.debug("REST de Reporting Annee avec les paramètres : {} , {} ", annee, isImprime);
        List<ReportingAnneeDTO> page = reportingQueryService.getReportingAnneeDTOListFalse(annee, isImprime);
        return ResponseEntity.ok().body(page);
    }
}
