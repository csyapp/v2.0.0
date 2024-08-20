package com.zmfx.csc.domain;

import java.util.UUID;

public class TemplateTestSamples {

    public static Template getTemplateSample1() {
        return new Template().libelle("libelle1").description("description1").createdBy("createdBy1").lastModifiedBy("lastModifiedBy1");
    }

    public static Template getTemplateSample2() {
        return new Template().libelle("libelle2").description("description2").createdBy("createdBy2").lastModifiedBy("lastModifiedBy2");
    }

    public static Template getTemplateRandomSampleGenerator() {
        return new Template()
            .libelle(UUID.randomUUID().toString())
            .description(UUID.randomUUID().toString())
            .createdBy(UUID.randomUUID().toString())
            .lastModifiedBy(UUID.randomUUID().toString());
    }
}
