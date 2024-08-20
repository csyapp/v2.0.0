package com.zmfx.csc.service.criteria;

import java.io.Serializable;
import java.util.Objects;
import java.util.Optional;
import org.springdoc.core.annotations.ParameterObject;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.*;

/**
 * Criteria class for the {@link com.zmfx.csc.domain.Carte} entity. This class is used
 * in {@link com.zmfx.csc.web.rest.CarteResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /cartes?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
@SuppressWarnings("common-java:DuplicatedBlocks")
public class CarteCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private StringFilter matricule;

    private StringFilter name;

    private StringFilter surname;

    private StringFilter email;

    private StringFilter villeResidence;

    private ZonedDateTimeFilter birthdate;

    private StringFilter telephone1;

    private StringFilter telephone2;

    private BooleanFilter isImprime;

    private StringFilter pictureExtension;

    private LocalDateFilter dateDelivrance;

    private LocalDateFilter dateExpiration;

    private StringFilter createdBy;

    private InstantFilter createdDate;

    private StringFilter lastModifiedBy;

    private InstantFilter lastModifiedDate;

    private StringFilter templateId;

    private Boolean distinct;

    public CarteCriteria() {}

    public CarteCriteria(CarteCriteria other) {
        this.matricule = other.optionalMatricule().map(StringFilter::copy).orElse(null);
        this.name = other.optionalName().map(StringFilter::copy).orElse(null);
        this.surname = other.optionalSurname().map(StringFilter::copy).orElse(null);
        this.email = other.optionalEmail().map(StringFilter::copy).orElse(null);
        this.villeResidence = other.optionalVilleResidence().map(StringFilter::copy).orElse(null);
        this.birthdate = other.optionalBirthdate().map(ZonedDateTimeFilter::copy).orElse(null);
        this.telephone1 = other.optionalTelephone1().map(StringFilter::copy).orElse(null);
        this.telephone2 = other.optionalTelephone2().map(StringFilter::copy).orElse(null);
        this.isImprime = other.optionalIsImprime().map(BooleanFilter::copy).orElse(null);
        this.pictureExtension = other.optionalPictureExtension().map(StringFilter::copy).orElse(null);
        this.dateDelivrance = other.optionalDateDelivrance().map(LocalDateFilter::copy).orElse(null);
        this.dateExpiration = other.optionalDateExpiration().map(LocalDateFilter::copy).orElse(null);
        this.createdBy = other.optionalCreatedBy().map(StringFilter::copy).orElse(null);
        this.createdDate = other.optionalCreatedDate().map(InstantFilter::copy).orElse(null);
        this.lastModifiedBy = other.optionalLastModifiedBy().map(StringFilter::copy).orElse(null);
        this.lastModifiedDate = other.optionalLastModifiedDate().map(InstantFilter::copy).orElse(null);
        this.templateId = other.optionalTemplateId().map(StringFilter::copy).orElse(null);
        this.distinct = other.distinct;
    }

    @Override
    public CarteCriteria copy() {
        return new CarteCriteria(this);
    }

    public StringFilter getMatricule() {
        return matricule;
    }

    public Optional<StringFilter> optionalMatricule() {
        return Optional.ofNullable(matricule);
    }

    public StringFilter matricule() {
        if (matricule == null) {
            setMatricule(new StringFilter());
        }
        return matricule;
    }

    public void setMatricule(StringFilter matricule) {
        this.matricule = matricule;
    }

    public StringFilter getName() {
        return name;
    }

    public Optional<StringFilter> optionalName() {
        return Optional.ofNullable(name);
    }

    public StringFilter name() {
        if (name == null) {
            setName(new StringFilter());
        }
        return name;
    }

    public void setName(StringFilter name) {
        this.name = name;
    }

    public StringFilter getSurname() {
        return surname;
    }

    public Optional<StringFilter> optionalSurname() {
        return Optional.ofNullable(surname);
    }

    public StringFilter surname() {
        if (surname == null) {
            setSurname(new StringFilter());
        }
        return surname;
    }

    public void setSurname(StringFilter surname) {
        this.surname = surname;
    }

    public StringFilter getEmail() {
        return email;
    }

    public Optional<StringFilter> optionalEmail() {
        return Optional.ofNullable(email);
    }

    public StringFilter email() {
        if (email == null) {
            setEmail(new StringFilter());
        }
        return email;
    }

    public void setEmail(StringFilter email) {
        this.email = email;
    }

    public StringFilter getVilleResidence() {
        return villeResidence;
    }

    public Optional<StringFilter> optionalVilleResidence() {
        return Optional.ofNullable(villeResidence);
    }

    public StringFilter villeResidence() {
        if (villeResidence == null) {
            setVilleResidence(new StringFilter());
        }
        return villeResidence;
    }

    public void setVilleResidence(StringFilter villeResidence) {
        this.villeResidence = villeResidence;
    }

    public ZonedDateTimeFilter getBirthdate() {
        return birthdate;
    }

    public Optional<ZonedDateTimeFilter> optionalBirthdate() {
        return Optional.ofNullable(birthdate);
    }

    public ZonedDateTimeFilter birthdate() {
        if (birthdate == null) {
            setBirthdate(new ZonedDateTimeFilter());
        }
        return birthdate;
    }

    public void setBirthdate(ZonedDateTimeFilter birthdate) {
        this.birthdate = birthdate;
    }

    public StringFilter getTelephone1() {
        return telephone1;
    }

    public Optional<StringFilter> optionalTelephone1() {
        return Optional.ofNullable(telephone1);
    }

    public StringFilter telephone1() {
        if (telephone1 == null) {
            setTelephone1(new StringFilter());
        }
        return telephone1;
    }

    public void setTelephone1(StringFilter telephone1) {
        this.telephone1 = telephone1;
    }

    public StringFilter getTelephone2() {
        return telephone2;
    }

    public Optional<StringFilter> optionalTelephone2() {
        return Optional.ofNullable(telephone2);
    }

    public StringFilter telephone2() {
        if (telephone2 == null) {
            setTelephone2(new StringFilter());
        }
        return telephone2;
    }

    public void setTelephone2(StringFilter telephone2) {
        this.telephone2 = telephone2;
    }

    public BooleanFilter getIsImprime() {
        return isImprime;
    }

    public Optional<BooleanFilter> optionalIsImprime() {
        return Optional.ofNullable(isImprime);
    }

    public BooleanFilter isImprime() {
        if (isImprime == null) {
            setIsImprime(new BooleanFilter());
        }
        return isImprime;
    }

    public void setIsImprime(BooleanFilter isImprime) {
        this.isImprime = isImprime;
    }

    public StringFilter getPictureExtension() {
        return pictureExtension;
    }

    public Optional<StringFilter> optionalPictureExtension() {
        return Optional.ofNullable(pictureExtension);
    }

    public StringFilter pictureExtension() {
        if (pictureExtension == null) {
            setPictureExtension(new StringFilter());
        }
        return pictureExtension;
    }

    public void setPictureExtension(StringFilter pictureExtension) {
        this.pictureExtension = pictureExtension;
    }

    public LocalDateFilter getDateDelivrance() {
        return dateDelivrance;
    }

    public Optional<LocalDateFilter> optionalDateDelivrance() {
        return Optional.ofNullable(dateDelivrance);
    }

    public LocalDateFilter dateDelivrance() {
        if (dateDelivrance == null) {
            setDateDelivrance(new LocalDateFilter());
        }
        return dateDelivrance;
    }

    public void setDateDelivrance(LocalDateFilter dateDelivrance) {
        this.dateDelivrance = dateDelivrance;
    }

    public LocalDateFilter getDateExpiration() {
        return dateExpiration;
    }

    public Optional<LocalDateFilter> optionalDateExpiration() {
        return Optional.ofNullable(dateExpiration);
    }

    public LocalDateFilter dateExpiration() {
        if (dateExpiration == null) {
            setDateExpiration(new LocalDateFilter());
        }
        return dateExpiration;
    }

    public void setDateExpiration(LocalDateFilter dateExpiration) {
        this.dateExpiration = dateExpiration;
    }

    public StringFilter getCreatedBy() {
        return createdBy;
    }

    public Optional<StringFilter> optionalCreatedBy() {
        return Optional.ofNullable(createdBy);
    }

    public StringFilter createdBy() {
        if (createdBy == null) {
            setCreatedBy(new StringFilter());
        }
        return createdBy;
    }

    public void setCreatedBy(StringFilter createdBy) {
        this.createdBy = createdBy;
    }

    public InstantFilter getCreatedDate() {
        return createdDate;
    }

    public Optional<InstantFilter> optionalCreatedDate() {
        return Optional.ofNullable(createdDate);
    }

    public InstantFilter createdDate() {
        if (createdDate == null) {
            setCreatedDate(new InstantFilter());
        }
        return createdDate;
    }

    public void setCreatedDate(InstantFilter createdDate) {
        this.createdDate = createdDate;
    }

    public StringFilter getLastModifiedBy() {
        return lastModifiedBy;
    }

    public Optional<StringFilter> optionalLastModifiedBy() {
        return Optional.ofNullable(lastModifiedBy);
    }

    public StringFilter lastModifiedBy() {
        if (lastModifiedBy == null) {
            setLastModifiedBy(new StringFilter());
        }
        return lastModifiedBy;
    }

    public void setLastModifiedBy(StringFilter lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public InstantFilter getLastModifiedDate() {
        return lastModifiedDate;
    }

    public Optional<InstantFilter> optionalLastModifiedDate() {
        return Optional.ofNullable(lastModifiedDate);
    }

    public InstantFilter lastModifiedDate() {
        if (lastModifiedDate == null) {
            setLastModifiedDate(new InstantFilter());
        }
        return lastModifiedDate;
    }

    public void setLastModifiedDate(InstantFilter lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public StringFilter getTemplateId() {
        return templateId;
    }

    public Optional<StringFilter> optionalTemplateId() {
        return Optional.ofNullable(templateId);
    }

    public StringFilter templateId() {
        if (templateId == null) {
            setTemplateId(new StringFilter());
        }
        return templateId;
    }

    public void setTemplateId(StringFilter templateId) {
        this.templateId = templateId;
    }

    public Boolean getDistinct() {
        return distinct;
    }

    public Optional<Boolean> optionalDistinct() {
        return Optional.ofNullable(distinct);
    }

    public Boolean distinct() {
        if (distinct == null) {
            setDistinct(true);
        }
        return distinct;
    }

    public void setDistinct(Boolean distinct) {
        this.distinct = distinct;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final CarteCriteria that = (CarteCriteria) o;
        return (
            Objects.equals(matricule, that.matricule) &&
            Objects.equals(name, that.name) &&
            Objects.equals(surname, that.surname) &&
            Objects.equals(email, that.email) &&
            Objects.equals(villeResidence, that.villeResidence) &&
            Objects.equals(birthdate, that.birthdate) &&
            Objects.equals(telephone1, that.telephone1) &&
            Objects.equals(telephone2, that.telephone2) &&
            Objects.equals(isImprime, that.isImprime) &&
            Objects.equals(pictureExtension, that.pictureExtension) &&
            Objects.equals(dateDelivrance, that.dateDelivrance) &&
            Objects.equals(dateExpiration, that.dateExpiration) &&
            Objects.equals(createdBy, that.createdBy) &&
            Objects.equals(createdDate, that.createdDate) &&
            Objects.equals(lastModifiedBy, that.lastModifiedBy) &&
            Objects.equals(lastModifiedDate, that.lastModifiedDate) &&
            Objects.equals(templateId, that.templateId) &&
            Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            matricule,
            name,
            surname,
            email,
            villeResidence,
            birthdate,
            telephone1,
            telephone2,
            isImprime,
            pictureExtension,
            dateDelivrance,
            dateExpiration,
            createdBy,
            createdDate,
            lastModifiedBy,
            lastModifiedDate,
            templateId,
            distinct
        );
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CarteCriteria{" +
            optionalMatricule().map(f -> "matricule=" + f + ", ").orElse("") +
            optionalName().map(f -> "name=" + f + ", ").orElse("") +
            optionalSurname().map(f -> "surname=" + f + ", ").orElse("") +
            optionalEmail().map(f -> "email=" + f + ", ").orElse("") +
            optionalVilleResidence().map(f -> "villeResidence=" + f + ", ").orElse("") +
            optionalBirthdate().map(f -> "birthdate=" + f + ", ").orElse("") +
            optionalTelephone1().map(f -> "telephone1=" + f + ", ").orElse("") +
            optionalTelephone2().map(f -> "telephone2=" + f + ", ").orElse("") +
            optionalIsImprime().map(f -> "isImprime=" + f + ", ").orElse("") +
            optionalPictureExtension().map(f -> "pictureExtension=" + f + ", ").orElse("") +
            optionalDateDelivrance().map(f -> "dateDelivrance=" + f + ", ").orElse("") +
            optionalDateExpiration().map(f -> "dateExpiration=" + f + ", ").orElse("") +
            optionalCreatedBy().map(f -> "createdBy=" + f + ", ").orElse("") +
            optionalCreatedDate().map(f -> "createdDate=" + f + ", ").orElse("") +
            optionalLastModifiedBy().map(f -> "lastModifiedBy=" + f + ", ").orElse("") +
            optionalLastModifiedDate().map(f -> "lastModifiedDate=" + f + ", ").orElse("") +
            optionalTemplateId().map(f -> "templateId=" + f + ", ").orElse("") +
            optionalDistinct().map(f -> "distinct=" + f + ", ").orElse("") +
        "}";
    }
}
