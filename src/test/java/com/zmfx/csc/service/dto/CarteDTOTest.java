package com.zmfx.csc.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.zmfx.csc.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class CarteDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CarteDTO.class);
        CarteDTO carteDTO1 = new CarteDTO();
        carteDTO1.setMatricule("id1");
        CarteDTO carteDTO2 = new CarteDTO();
        assertThat(carteDTO1).isNotEqualTo(carteDTO2);
        carteDTO2.setMatricule(carteDTO1.getMatricule());
        assertThat(carteDTO1).isEqualTo(carteDTO2);
        carteDTO2.setMatricule("id2");
        assertThat(carteDTO1).isNotEqualTo(carteDTO2);
        carteDTO1.setMatricule(null);
        assertThat(carteDTO1).isNotEqualTo(carteDTO2);
    }
}
