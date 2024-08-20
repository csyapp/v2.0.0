package com.zmfx.csc.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.data.domain.Persistable;

/**
 * A Carte.
 */
@Entity
@Table(name = "carte")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@JsonIgnoreProperties(value = { "new", "id" })
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Carte extends AbstractAuditingEntity<String> implements Serializable, Persistable<String> {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "matricule")
    private String matricule;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "email")
    private String email;

    @Column(name = "ville_residence")
    private String villeResidence;

    @Column(name = "birthdate")
    private ZonedDateTime birthdate;

    @Column(name = "telephone_1")
    private String telephone1;

    @Column(name = "telephone_2")
    private String telephone2;

    @Column(name = "is_imprime")
    private Boolean isImprime;

    @Column(name = "picture_extension")
    private String pictureExtension;

    @Lob
    @Column(name = "picture")
    private byte[] picture;

    @Column(name = "picture_content_type")
    private String pictureContentType;

    @Column(name = "date_delivrance")
    private LocalDate dateDelivrance;

    @Column(name = "date_expiration")
    private LocalDate dateExpiration;

    // Inherited createdBy definition
    // Inherited createdDate definition
    // Inherited lastModifiedBy definition
    // Inherited lastModifiedDate definition
    @Transient
    private boolean isPersisted;

    @ManyToOne(fetch = FetchType.EAGER)
    private Template template;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getMatricule() {
        return this.matricule;
    }

    public Carte matricule(String matricule) {
        this.setMatricule(matricule);
        return this;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getName() {
        return this.name;
    }

    public Carte name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return this.surname;
    }

    public Carte surname(String surname) {
        this.setSurname(surname);
        return this;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return this.email;
    }

    public Carte email(String email) {
        this.setEmail(email);
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getVilleResidence() {
        return this.villeResidence;
    }

    public Carte villeResidence(String villeResidence) {
        this.setVilleResidence(villeResidence);
        return this;
    }

    public void setVilleResidence(String villeResidence) {
        this.villeResidence = villeResidence;
    }

    public ZonedDateTime getBirthdate() {
        return this.birthdate;
    }

    public Carte birthdate(ZonedDateTime birthdate) {
        this.setBirthdate(birthdate);
        return this;
    }

    public void setBirthdate(ZonedDateTime birthdate) {
        this.birthdate = birthdate;
    }

    public String getTelephone1() {
        return this.telephone1;
    }

    public Carte telephone1(String telephone1) {
        this.setTelephone1(telephone1);
        return this;
    }

    public void setTelephone1(String telephone1) {
        this.telephone1 = telephone1;
    }

    public String getTelephone2() {
        return this.telephone2;
    }

    public Carte telephone2(String telephone2) {
        this.setTelephone2(telephone2);
        return this;
    }

    public void setTelephone2(String telephone2) {
        this.telephone2 = telephone2;
    }

    public Boolean getIsImprime() {
        return this.isImprime;
    }

    public Carte isImprime(Boolean isImprime) {
        this.setIsImprime(isImprime);
        return this;
    }

    public void setIsImprime(Boolean isImprime) {
        this.isImprime = isImprime;
    }

    public String getPictureExtension() {
        return this.pictureExtension;
    }

    public Carte pictureExtension(String pictureExtension) {
        this.setPictureExtension(pictureExtension);
        return this;
    }

    public void setPictureExtension(String pictureExtension) {
        this.pictureExtension = pictureExtension;
    }

    public byte[] getPicture() {
        return this.picture;
    }

    public Carte picture(byte[] picture) {
        this.setPicture(picture);
        return this;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }

    public String getPictureContentType() {
        return this.pictureContentType;
    }

    public Carte pictureContentType(String pictureContentType) {
        this.pictureContentType = pictureContentType;
        return this;
    }

    public void setPictureContentType(String pictureContentType) {
        this.pictureContentType = pictureContentType;
    }

    public LocalDate getDateDelivrance() {
        return this.dateDelivrance;
    }

    public Carte dateDelivrance(LocalDate dateDelivrance) {
        this.setDateDelivrance(dateDelivrance);
        return this;
    }

    public void setDateDelivrance(LocalDate dateDelivrance) {
        this.dateDelivrance = dateDelivrance;
    }

    public LocalDate getDateExpiration() {
        return this.dateExpiration;
    }

    public Carte dateExpiration(LocalDate dateExpiration) {
        this.setDateExpiration(dateExpiration);
        return this;
    }

    public void setDateExpiration(LocalDate dateExpiration) {
        this.dateExpiration = dateExpiration;
    }

    // Inherited createdBy methods
    public Carte createdBy(String createdBy) {
        this.setCreatedBy(createdBy);
        return this;
    }

    // Inherited createdDate methods
    public Carte createdDate(Instant createdDate) {
        this.setCreatedDate(createdDate);
        return this;
    }

    // Inherited lastModifiedBy methods
    public Carte lastModifiedBy(String lastModifiedBy) {
        this.setLastModifiedBy(lastModifiedBy);
        return this;
    }

    // Inherited lastModifiedDate methods
    public Carte lastModifiedDate(Instant lastModifiedDate) {
        this.setLastModifiedDate(lastModifiedDate);
        return this;
    }

    @PostLoad
    @PostPersist
    public void updateEntityState() {
        this.setIsPersisted();
    }

    @Override
    public String getId() {
        return this.matricule;
    }

    @Transient
    @Override
    public boolean isNew() {
        return !this.isPersisted;
    }

    public Carte setIsPersisted() {
        this.isPersisted = true;
        return this;
    }

    public Template getTemplate() {
        return this.template;
    }

    public void setTemplate(Template template) {
        this.template = template;
    }

    public Carte template(Template template) {
        this.setTemplate(template);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Carte)) {
            return false;
        }
        return getMatricule() != null && getMatricule().equals(((Carte) o).getMatricule());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Carte{" +
            "matricule=" + getMatricule() +
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
            ", pictureContentType='" + getPictureContentType() + "'" +
            ", dateDelivrance='" + getDateDelivrance() + "'" +
            ", dateExpiration='" + getDateExpiration() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", lastModifiedBy='" + getLastModifiedBy() + "'" +
            ", lastModifiedDate='" + getLastModifiedDate() + "'" +
            "}";
    }
}
