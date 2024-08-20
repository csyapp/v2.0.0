package com.zmfx.csc.service.dto;

import jakarta.persistence.Lob;
import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * A DTO for the {@link com.zmfx.csc.domain.Carte} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class CarteDTO implements Serializable {

    private String matricule;

    private String name;

    private String surname;

    private String email;

    private String villeResidence;

    private ZonedDateTime birthdate;

    private String telephone1;

    private String telephone2;

    private Boolean isImprime;

    private String pictureExtension;

    @Lob
    private byte[] picture;

    private String pictureContentType;

    private LocalDate dateDelivrance;

    private LocalDate dateExpiration;

    private String createdBy;

    private Instant createdDate;

    private String lastModifiedBy;

    private Instant lastModifiedDate;

    private TemplateDTO template;

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getVilleResidence() {
        return villeResidence;
    }

    public void setVilleResidence(String villeResidence) {
        this.villeResidence = villeResidence;
    }

    public ZonedDateTime getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(ZonedDateTime birthdate) {
        this.birthdate = birthdate;
    }

    public String getTelephone1() {
        return telephone1;
    }

    public void setTelephone1(String telephone1) {
        this.telephone1 = telephone1;
    }

    public String getTelephone2() {
        return telephone2;
    }

    public void setTelephone2(String telephone2) {
        this.telephone2 = telephone2;
    }

    public Boolean getIsImprime() {
        return isImprime;
    }

    public void setIsImprime(Boolean isImprime) {
        this.isImprime = isImprime;
    }

    public String getPictureExtension() {
        return pictureExtension;
    }

    public void setPictureExtension(String pictureExtension) {
        this.pictureExtension = pictureExtension;
    }

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }

    public String getPictureContentType() {
        return pictureContentType;
    }

    public void setPictureContentType(String pictureContentType) {
        this.pictureContentType = pictureContentType;
    }

    public LocalDate getDateDelivrance() {
        return dateDelivrance;
    }

    public void setDateDelivrance(LocalDate dateDelivrance) {
        this.dateDelivrance = dateDelivrance;
    }

    public LocalDate getDateExpiration() {
        return dateExpiration;
    }

    public void setDateExpiration(LocalDate dateExpiration) {
        this.dateExpiration = dateExpiration;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public Instant getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Instant lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public TemplateDTO getTemplate() {
        return template;
    }

    public void setTemplate(TemplateDTO template) {
        this.template = template;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CarteDTO)) {
            return false;
        }

        CarteDTO carteDTO = (CarteDTO) o;
        if (this.matricule == null) {
            return false;
        }
        return Objects.equals(this.matricule, carteDTO.matricule);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.matricule);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CarteDTO{" +
            "matricule='" + getMatricule() + "'" +
            ", name='" + getName() + "'" +
            ", surname='" + getSurname() + "'" +
            ", email='" + getEmail() + "'" +
            ", villeResidence='" + getVilleResidence() + "'" +
            ", birthdate='" + getBirthdate() + "'" +
            ", telephone1='" + getTelephone1() + "'" +
            ", telephone2='" + getTelephone2() + "'" +
            ", isImprime='" + getIsImprime() + "'" +
            ", pictureExtension='" + getPictureExtension() + "'" +
            ", picture='" + getPicture() + "'" +
            ", dateDelivrance='" + getDateDelivrance() + "'" +
            ", dateExpiration='" + getDateExpiration() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", lastModifiedBy='" + getLastModifiedBy() + "'" +
            ", lastModifiedDate='" + getLastModifiedDate() + "'" +
            ", template=" + getTemplate() +
            "}";
    }
}
