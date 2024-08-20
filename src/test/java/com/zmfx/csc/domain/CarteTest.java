package com.zmfx.csc.domain;

import static com.zmfx.csc.domain.CarteTestSamples.*;
import static com.zmfx.csc.domain.TemplateTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.zmfx.csc.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class CarteTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Carte.class);
        Carte carte1 = getCarteSample1();
        Carte carte2 = new Carte();
        assertThat(carte1).isNotEqualTo(carte2);

        carte2.setMatricule(carte1.getMatricule());
        assertThat(carte1).isEqualTo(carte2);

        carte2 = getCarteSample2();
        assertThat(carte1).isNotEqualTo(carte2);
    }

    @Test
    void templateTest() {
        Carte carte = getCarteRandomSampleGenerator();
        Template templateBack = getTemplateRandomSampleGenerator();

        carte.setTemplate(templateBack);
        assertThat(carte.getTemplate()).isEqualTo(templateBack);

        carte.template(null);
        assertThat(carte.getTemplate()).isNull();
    }
}
