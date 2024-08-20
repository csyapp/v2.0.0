package com.zmfx.csc.service.dto;

import java.io.Serializable;
import java.time.Year;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A DTO for the {@link com.zmfx.csc.domain.Template} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ReportingAnneeDTO implements Serializable {

    private static final Logger log = LoggerFactory.getLogger(ReportingAnneeDTO.class);
    private String year;
    private Long quantite;
    private String nom;
    private Integer profondeur;

    public ReportingAnneeDTO() {}

    public ReportingAnneeDTO(String year, Long quantite, String nom, Integer profondeur) {
        this.year = year;
        this.quantite = quantite;
        this.nom = nom;
        this.profondeur = profondeur;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public Long getQuantite() {
        return quantite;
    }

    public void setQuantite(Long quantite) {
        this.quantite = quantite;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Integer getProfondeur() {
        return profondeur;
    }

    public void setProfondeur(Integer profondeur) {
        this.profondeur = profondeur;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
