package com.zmfx.csc.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.zmfx.csc.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TemplateDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TemplateDTO.class);
        TemplateDTO templateDTO1 = new TemplateDTO();
        templateDTO1.setLibelle("id1");
        TemplateDTO templateDTO2 = new TemplateDTO();
        assertThat(templateDTO1).isNotEqualTo(templateDTO2);
        templateDTO2.setLibelle(templateDTO1.getLibelle());
        assertThat(templateDTO1).isEqualTo(templateDTO2);
        templateDTO2.setLibelle("id2");
        assertThat(templateDTO1).isNotEqualTo(templateDTO2);
        templateDTO1.setLibelle(null);
        assertThat(templateDTO1).isNotEqualTo(templateDTO2);
    }
}
