package com.zmfx.csc.service.impl;

import static com.zmfx.csc.service.impl.ToolsServiceImpl.formatStringWithLeadingZeros;

import com.zmfx.csc.domain.Carte;
import com.zmfx.csc.repository.CarteRepository;
import com.zmfx.csc.service.CarteService;
import com.zmfx.csc.service.dto.CarteDTO;
import com.zmfx.csc.service.mapper.CarteMapper;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.zmfx.csc.domain.Carte}.
 */
@Service
@Transactional
public class CarteServiceImpl implements CarteService {

    private static final Logger log = LoggerFactory.getLogger(CarteServiceImpl.class);

    private final CarteRepository carteRepository;

    private final CarteMapper carteMapper;

    public CarteServiceImpl(CarteRepository carteRepository, CarteMapper carteMapper) {
        this.carteRepository = carteRepository;
        this.carteMapper = carteMapper;
    }

    @Override
    public CarteDTO parameterizeCardDTO(CarteDTO carteDTO) {
        carteDTO.setMatricule(generateCardCode());
        LocalDate deliveryDate = LocalDate.now().plusDays(5);
        carteDTO.setDateDelivrance(deliveryDate);
        carteDTO.setDateExpiration(deliveryDate.plusYears(1));
        return carteDTO;
    }

    @Override
    public CarteDTO save(CarteDTO carteDTO) {
        log.debug("Request to save Carte : {}", carteDTO);
        return carteMapper.toDto(carteRepository.save(carteMapper.toEntity(carteDTO)));
    }

    @Override
    public CarteDTO update(CarteDTO carteDTO) {
        log.debug("Request to update Carte : {}", carteDTO);
        Carte carte = carteMapper.toEntity(carteDTO);
        carte.setIsPersisted();
        carte = carteRepository.save(carte);
        return carteMapper.toDto(carte);
    }

    @Override
    public Optional<CarteDTO> partialUpdate(CarteDTO carteDTO) {
        log.debug("Request to partially update Carte : {}", carteDTO);

        return carteRepository
            .findById(carteDTO.getMatricule())
            .map(existingCarte -> {
                carteMapper.partialUpdate(existingCarte, carteDTO);

                return existingCarte;
            })
            .map(carteRepository::save)
            .map(carteMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<CarteDTO> findOne(String id) {
        log.debug("Request to get Carte : {}", id);
        return carteRepository.findById(id).map(carteMapper::toDto);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete Carte : {}", id);
        carteRepository.deleteById(id);
    }

    @Override
    public Long countAllCards() {
        return carteRepository.count();
    }

    public String generateCardCode() {
        return (
            "CSY" +
            String.valueOf(LocalDate.now().getYear()).substring(2) +
            formatStringWithLeadingZeros(String.valueOf(LocalDate.now().getMonthValue()), 2) +
            formatStringWithLeadingZeros(String.valueOf(numberOfPrintedCardThisMonth()), 7)
        );
    }

    public Integer numberOfPrintedCardThisMonth() {
        Instant instant1 = LocalDate.now().withDayOfMonth(1).atStartOfDay(ZoneId.of("UTC")).toInstant();
        Instant instant2 = LocalDate.now()
            .withDayOfMonth(LocalDate.now().lengthOfMonth())
            .plusDays(1)
            .atStartOfDay(ZoneId.of("UTC"))
            .toInstant();
        return carteRepository.countCartesByCreatedDateBetween(instant1, instant2);
    }
}
