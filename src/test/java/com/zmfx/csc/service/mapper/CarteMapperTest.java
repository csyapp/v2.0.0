package com.zmfx.csc.service.mapper;

import static com.zmfx.csc.domain.CarteAsserts.*;
import static com.zmfx.csc.domain.CarteTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CarteMapperTest {

    private CarteMapper carteMapper;

    @BeforeEach
    void setUp() {
        carteMapper = new CarteMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getCarteSample1();
        var actual = carteMapper.toEntity(carteMapper.toDto(expected));
        assertCarteAllPropertiesEquals(expected, actual);
    }
}
