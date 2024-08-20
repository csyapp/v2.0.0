package com.zmfx.csc.service.dto;

import java.io.Serializable;
import java.time.Month;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A DTO for the {@link com.zmfx.csc.domain.Template} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ReportingMoisDTO implements Serializable {

    private static final Logger log = LoggerFactory.getLogger(ReportingMoisDTO.class);
    private Month month;
    private Long quantite;
    private String nom;
    private Integer annee;

    public ReportingMoisDTO() {}

    public ReportingMoisDTO(Month month, Long quantite, String nom, Integer annee) {
        this.month = month;
        this.quantite = quantite;
        this.nom = nom;
        this.annee = annee;
    }

    public ReportingMoisDTO(String month, Long quantite) {
        this.month = Month.of(Integer.parseInt(month));
        this.quantite = quantite;
    }

    public Month getMonth() {
        return month;
    }

    public void setMonth(Month month) {
        this.month = month;
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

    public Integer getAnnee() {
        return annee;
    }

    public void setAnnee(Integer annee) {
        this.annee = annee;
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
