package com.zmfx.csc.domain;

import java.util.UUID;

public class CarteTestSamples {

    public static Carte getCarteSample1() {
        return new Carte()
            .matricule("matricule1")
            .name("name1")
            .surname("surname1")
            .email("email1")
            .villeResidence("villeResidence1")
            .telephone1("telephone11")
            .telephone2("telephone21")
            .pictureExtension("pictureExtension1")
            .createdBy("createdBy1")
            .lastModifiedBy("lastModifiedBy1");
    }

    public static Carte getCarteSample2() {
        return new Carte()
            .matricule("matricule2")
            .name("name2")
            .surname("surname2")
            .email("email2")
            .villeResidence("villeResidence2")
            .telephone1("telephone12")
            .telephone2("telephone22")
            .pictureExtension("pictureExtension2")
            .createdBy("createdBy2")
            .lastModifiedBy("lastModifiedBy2");
    }

    public static Carte getCarteRandomSampleGenerator() {
        return new Carte()
            .matricule(UUID.randomUUID().toString())
            .name(UUID.randomUUID().toString())
            .surname(UUID.randomUUID().toString())
            .email(UUID.randomUUID().toString())
            .villeResidence(UUID.randomUUID().toString())
            .telephone1(UUID.randomUUID().toString())
            .telephone2(UUID.randomUUID().toString())
            .pictureExtension(UUID.randomUUID().toString())
            .createdBy(UUID.randomUUID().toString())
            .lastModifiedBy(UUID.randomUUID().toString());
    }
}
