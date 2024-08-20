package com.zmfx.csc.service.dto;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A DTO for the {@link com.zmfx.csc.domain.Template} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ReportingJourDTO implements Serializable {

    private static final Logger log = LoggerFactory.getLogger(ReportingJourDTO.class);

    private LocalDate day;
    private Long quantite;
    private String nom;
    private Month month;
    private Integer year;

    public ReportingJourDTO() {}

    public ReportingJourDTO(LocalDate day, Long quantite, String nom, Month month, Integer year) {
        this.day = day;
        this.quantite = quantite;
        this.nom = nom;
        this.month = month;
        this.year = year;
    }

    public ReportingJourDTO(Instant day, Long quantite) {
        this.day = day.atZone(ZoneId.systemDefault()).toLocalDate();
        this.quantite = quantite;
        log.debug("Collect des données du reporting Date: {} - Quantité : {}", day, quantite);
    }

    public LocalDate getDay() {
        return day;
    }

    public void setDay(LocalDate day) {
        this.day = day;
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

    public Month getMonth() {
        return month;
    }

    public void setMonth(Month month) {
        this.month = month;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
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
