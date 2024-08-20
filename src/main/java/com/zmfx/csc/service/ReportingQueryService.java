package com.zmfx.csc.service;

import com.zmfx.csc.repository.CarteRepository;
import com.zmfx.csc.service.dto.ReportingAnneeDTO;
import com.zmfx.csc.service.dto.ReportingJourDTO;
import com.zmfx.csc.service.dto.ReportingMoisDTO;
import java.time.Instant;
import java.time.Month;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.service.QueryService;

/**
 * Service for executing complex queries for {@link ReportingJourDTO} data.
 */
@Service
@Transactional(readOnly = true)
public class ReportingQueryService extends QueryService<ReportingJourDTO> {

    private static final Logger log = LoggerFactory.getLogger(ReportingQueryService.class);

    private final CarteRepository carteRepository;

    public ReportingQueryService(CarteRepository carteRepository) {
        this.carteRepository = carteRepository;
    }

    public List<ReportingJourDTO> getReportingJourDTOListTrue(String annee, String mois) {
        log.debug("getReportingJourDTOList : {} , {} ", annee, mois);
        return carteRepository
            .getReportingJourTrue(annee, mois)
            .stream()
            .map(
                x ->
                    new ReportingJourDTO(
                        ((Instant) x[0]).atZone(ZoneId.systemDefault()).toLocalDate(),
                        (Long) x[1],
                        ((Instant) x[0]).atZone(ZoneId.systemDefault())
                            .toLocalDate()
                            .format(DateTimeFormatter.ofPattern("E. dd-MM-yyyy", Locale.forLanguageTag("fr"))),
                        ((Instant) x[0]).atZone(ZoneId.systemDefault()).toLocalDate().getMonth(),
                        ((Instant) x[0]).atZone(ZoneId.systemDefault()).toLocalDate().getYear()
                    )
            )
            .collect(Collectors.groupingBy(ReportingJourDTO::getDay, Collectors.summingLong(ReportingJourDTO::getQuantite)))
            .entrySet()
            .stream()
            .map(
                e ->
                    new ReportingJourDTO(
                        e.getKey(),
                        e.getValue(),
                        e.getKey().format(DateTimeFormatter.ofPattern("E. dd-MM-yyyy", Locale.forLanguageTag("fr"))),
                        Month.of(Integer.parseInt(mois)),
                        Integer.parseInt(annee)
                    )
            )
            .collect(Collectors.toList());
    }

    public List<ReportingJourDTO> getReportingJourDTOListFalse(String annee, String mois, String isImprime) {
        log.debug("getReportingJourDTOList : {} , {} ,{}", annee, mois, isImprime);

        return carteRepository
            .getReportingJourFalse(annee, mois)
            .stream()
            .map(
                x ->
                    new ReportingJourDTO(
                        ((Instant) x[0]).atZone(ZoneId.systemDefault()).toLocalDate(),
                        (Long) x[1],
                        ((Instant) x[0]).atZone(ZoneId.systemDefault())
                            .toLocalDate()
                            .format(DateTimeFormatter.ofPattern("E. dd-MM-yyyy", Locale.forLanguageTag("fr"))),
                        ((Instant) x[0]).atZone(ZoneId.systemDefault()).toLocalDate().getMonth(),
                        ((Instant) x[0]).atZone(ZoneId.systemDefault()).toLocalDate().getYear()
                    )
            )
            .collect(Collectors.groupingBy(ReportingJourDTO::getDay, Collectors.summingLong(ReportingJourDTO::getQuantite)))
            .entrySet()
            .stream()
            .map(
                e ->
                    new ReportingJourDTO(
                        e.getKey(),
                        e.getValue(),
                        e.getKey().format(DateTimeFormatter.ofPattern("E. dd-MM-yyyy", Locale.forLanguageTag("fr"))),
                        Month.of(Integer.parseInt(mois)),
                        Integer.parseInt(annee)
                    )
            )
            .collect(Collectors.toList());
    }

    public List<ReportingMoisDTO> getReportingMoisDTOListTrue(String annee) {
        log.debug("getReportingMoisDTOList : {} ", annee);
        return carteRepository
            .getReportingMoisTrue(annee)
            .stream()
            .map(x -> {
                Month month = Month.of(Integer.parseInt((String) x[0]));
                return new ReportingMoisDTO(
                    month,
                    (Long) x[1],
                    month.getDisplayName(TextStyle.FULL, Locale.FRENCH),
                    Integer.parseInt(annee)
                );
            })
            .collect(Collectors.toList());
    }

    public List<ReportingMoisDTO> getReportingMoisDTOListFalse(String annee, String isImprime) {
        log.debug("getReportingMoisDTOList : {}, {} ", annee, isImprime);
        return carteRepository
            .getReportingMoisFalse(annee)
            .stream()
            .map(x -> {
                Month month = Month.of(Integer.parseInt((String) x[0]));
                return new ReportingMoisDTO(
                    month,
                    (Long) x[1],
                    month.getDisplayName(TextStyle.FULL, Locale.FRENCH),
                    Integer.parseInt(annee)
                );
            })
            .collect(Collectors.toList());
    }

    public List<ReportingAnneeDTO> getReportingAnneeDTOListTrue(String annee) {
        log.debug("getReportingAnneeDTOList : {} ", annee);
        String anneeInf = String.valueOf(Integer.parseInt(annee) - 12);
        return carteRepository
            .getReportingAnneeTrue(anneeInf, annee)
            .stream()
            .map(x -> new ReportingAnneeDTO(x[0].toString(), (Long) x[1], x[0].toString(), Integer.parseInt(annee)))
            .collect(Collectors.toList());
    }

    public List<ReportingAnneeDTO> getReportingAnneeDTOListFalse(String annee, String isImprime) {
        log.debug("getReportingAnneeDTOList : {}, {} ", annee, isImprime);
        String anneeInf = String.valueOf(Integer.parseInt(annee) - 12);
        return carteRepository
            .getReportingAnneeFalse(anneeInf, annee)
            .stream()
            .map(x -> new ReportingAnneeDTO(x[0].toString(), (Long) x[1], x[0].toString(), Integer.parseInt(annee)))
            .collect(Collectors.toList());
    }
}
