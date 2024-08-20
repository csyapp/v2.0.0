package com.zmfx.csc.service.criteria;

import java.io.Serializable;
import java.util.Objects;
import java.util.Optional;
import org.springdoc.core.annotations.ParameterObject;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.*;

/**
 * Criteria class for the {@link com.zmfx.csc.domain.Template} entity. This class is used
 * in {@link com.zmfx.csc.web.rest.TemplateResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /templates?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
@SuppressWarnings("common-java:DuplicatedBlocks")
public class TemplateCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private StringFilter libelle;

    private StringFilter description;

    private DoubleFilter imageHeigth;

    private DoubleFilter imageWidth;

    private DoubleFilter imageX;

    private DoubleFilter imageY;

    private DoubleFilter matriculeX;

    private DoubleFilter matriculeY;

    private DoubleFilter membreX;

    private DoubleFilter membreY;

    private DoubleFilter nomX;

    private DoubleFilter nomY;

    private DoubleFilter prenomX;

    private DoubleFilter prenomY;

    private DoubleFilter qrHeight;

    private DoubleFilter qrWidth;

    private DoubleFilter qrX;

    private DoubleFilter qrY;

    private DoubleFilter dateDelivranceX;

    private DoubleFilter dateDelivranceY;

    private DoubleFilter dateExpirationX;

    private DoubleFilter dateExpirationY;

    private DoubleFilter sampleLargeur;

    private DoubleFilter sampleLongueur;

    private StringFilter createdBy;

    private InstantFilter createdDate;

    private StringFilter lastModifiedBy;

    private InstantFilter lastModifiedDate;

    private Boolean distinct;

    public TemplateCriteria() {}

    public TemplateCriteria(TemplateCriteria other) {
        this.libelle = other.optionalLibelle().map(StringFilter::copy).orElse(null);
        this.description = other.optionalDescription().map(StringFilter::copy).orElse(null);
        this.imageHeigth = other.optionalImageHeigth().map(DoubleFilter::copy).orElse(null);
        this.imageWidth = other.optionalImageWidth().map(DoubleFilter::copy).orElse(null);
        this.imageX = other.optionalImageX().map(DoubleFilter::copy).orElse(null);
        this.imageY = other.optionalImageY().map(DoubleFilter::copy).orElse(null);
        this.matriculeX = other.optionalMatriculeX().map(DoubleFilter::copy).orElse(null);
        this.matriculeY = other.optionalMatriculeY().map(DoubleFilter::copy).orElse(null);
        this.membreX = other.optionalMembreX().map(DoubleFilter::copy).orElse(null);
        this.membreY = other.optionalMembreY().map(DoubleFilter::copy).orElse(null);
        this.nomX = other.optionalNomX().map(DoubleFilter::copy).orElse(null);
        this.nomY = other.optionalNomY().map(DoubleFilter::copy).orElse(null);
        this.prenomX = other.optionalPrenomX().map(DoubleFilter::copy).orElse(null);
        this.prenomY = other.optionalPrenomY().map(DoubleFilter::copy).orElse(null);
        this.qrHeight = other.optionalQrHeight().map(DoubleFilter::copy).orElse(null);
        this.qrWidth = other.optionalQrWidth().map(DoubleFilter::copy).orElse(null);
        this.qrX = other.optionalQrX().map(DoubleFilter::copy).orElse(null);
        this.qrY = other.optionalQrY().map(DoubleFilter::copy).orElse(null);
        this.dateDelivranceX = other.optionalDateDelivranceX().map(DoubleFilter::copy).orElse(null);
        this.dateDelivranceY = other.optionalDateDelivranceY().map(DoubleFilter::copy).orElse(null);
        this.dateExpirationX = other.optionalDateExpirationX().map(DoubleFilter::copy).orElse(null);
        this.dateExpirationY = other.optionalDateExpirationY().map(DoubleFilter::copy).orElse(null);
        this.sampleLargeur = other.optionalSampleLargeur().map(DoubleFilter::copy).orElse(null);
        this.sampleLongueur = other.optionalSampleLongueur().map(DoubleFilter::copy).orElse(null);
        this.createdBy = other.optionalCreatedBy().map(StringFilter::copy).orElse(null);
        this.createdDate = other.optionalCreatedDate().map(InstantFilter::copy).orElse(null);
        this.lastModifiedBy = other.optionalLastModifiedBy().map(StringFilter::copy).orElse(null);
        this.lastModifiedDate = other.optionalLastModifiedDate().map(InstantFilter::copy).orElse(null);
        this.distinct = other.distinct;
    }

    @Override
    public TemplateCriteria copy() {
        return new TemplateCriteria(this);
    }

    public StringFilter getLibelle() {
        return libelle;
    }

    public Optional<StringFilter> optionalLibelle() {
        return Optional.ofNullable(libelle);
    }

    public StringFilter libelle() {
        if (libelle == null) {
            setLibelle(new StringFilter());
        }
        return libelle;
    }

    public void setLibelle(StringFilter libelle) {
        this.libelle = libelle;
    }

    public StringFilter getDescription() {
        return description;
    }

    public Optional<StringFilter> optionalDescription() {
        return Optional.ofNullable(description);
    }

    public StringFilter description() {
        if (description == null) {
            setDescription(new StringFilter());
        }
        return description;
    }

    public void setDescription(StringFilter description) {
        this.description = description;
    }

    public DoubleFilter getImageHeigth() {
        return imageHeigth;
    }

    public Optional<DoubleFilter> optionalImageHeigth() {
        return Optional.ofNullable(imageHeigth);
    }

    public DoubleFilter imageHeigth() {
        if (imageHeigth == null) {
            setImageHeigth(new DoubleFilter());
        }
        return imageHeigth;
    }

    public void setImageHeigth(DoubleFilter imageHeigth) {
        this.imageHeigth = imageHeigth;
    }

    public DoubleFilter getImageWidth() {
        return imageWidth;
    }

    public Optional<DoubleFilter> optionalImageWidth() {
        return Optional.ofNullable(imageWidth);
    }

    public DoubleFilter imageWidth() {
        if (imageWidth == null) {
            setImageWidth(new DoubleFilter());
        }
        return imageWidth;
    }

    public void setImageWidth(DoubleFilter imageWidth) {
        this.imageWidth = imageWidth;
    }

    public DoubleFilter getImageX() {
        return imageX;
    }

    public Optional<DoubleFilter> optionalImageX() {
        return Optional.ofNullable(imageX);
    }

    public DoubleFilter imageX() {
        if (imageX == null) {
            setImageX(new DoubleFilter());
        }
        return imageX;
    }

    public void setImageX(DoubleFilter imageX) {
        this.imageX = imageX;
    }

    public DoubleFilter getImageY() {
        return imageY;
    }

    public Optional<DoubleFilter> optionalImageY() {
        return Optional.ofNullable(imageY);
    }

    public DoubleFilter imageY() {
        if (imageY == null) {
            setImageY(new DoubleFilter());
        }
        return imageY;
    }

    public void setImageY(DoubleFilter imageY) {
        this.imageY = imageY;
    }

    public DoubleFilter getMatriculeX() {
        return matriculeX;
    }

    public Optional<DoubleFilter> optionalMatriculeX() {
        return Optional.ofNullable(matriculeX);
    }

    public DoubleFilter matriculeX() {
        if (matriculeX == null) {
            setMatriculeX(new DoubleFilter());
        }
        return matriculeX;
    }

    public void setMatriculeX(DoubleFilter matriculeX) {
        this.matriculeX = matriculeX;
    }

    public DoubleFilter getMatriculeY() {
        return matriculeY;
    }

    public Optional<DoubleFilter> optionalMatriculeY() {
        return Optional.ofNullable(matriculeY);
    }

    public DoubleFilter matriculeY() {
        if (matriculeY == null) {
            setMatriculeY(new DoubleFilter());
        }
        return matriculeY;
    }

    public void setMatriculeY(DoubleFilter matriculeY) {
        this.matriculeY = matriculeY;
    }

    public DoubleFilter getMembreX() {
        return membreX;
    }

    public Optional<DoubleFilter> optionalMembreX() {
        return Optional.ofNullable(membreX);
    }

    public DoubleFilter membreX() {
        if (membreX == null) {
            setMembreX(new DoubleFilter());
        }
        return membreX;
    }

    public void setMembreX(DoubleFilter membreX) {
        this.membreX = membreX;
    }

    public DoubleFilter getMembreY() {
        return membreY;
    }

    public Optional<DoubleFilter> optionalMembreY() {
        return Optional.ofNullable(membreY);
    }

    public DoubleFilter membreY() {
        if (membreY == null) {
            setMembreY(new DoubleFilter());
        }
        return membreY;
    }

    public void setMembreY(DoubleFilter membreY) {
        this.membreY = membreY;
    }

    public DoubleFilter getNomX() {
        return nomX;
    }

    public Optional<DoubleFilter> optionalNomX() {
        return Optional.ofNullable(nomX);
    }

    public DoubleFilter nomX() {
        if (nomX == null) {
            setNomX(new DoubleFilter());
        }
        return nomX;
    }

    public void setNomX(DoubleFilter nomX) {
        this.nomX = nomX;
    }

    public DoubleFilter getNomY() {
        return nomY;
    }

    public Optional<DoubleFilter> optionalNomY() {
        return Optional.ofNullable(nomY);
    }

    public DoubleFilter nomY() {
        if (nomY == null) {
            setNomY(new DoubleFilter());
        }
        return nomY;
    }

    public void setNomY(DoubleFilter nomY) {
        this.nomY = nomY;
    }

    public DoubleFilter getPrenomX() {
        return prenomX;
    }

    public Optional<DoubleFilter> optionalPrenomX() {
        return Optional.ofNullable(prenomX);
    }

    public DoubleFilter prenomX() {
        if (prenomX == null) {
            setPrenomX(new DoubleFilter());
        }
        return prenomX;
    }

    public void setPrenomX(DoubleFilter prenomX) {
        this.prenomX = prenomX;
    }

    public DoubleFilter getPrenomY() {
        return prenomY;
    }

    public Optional<DoubleFilter> optionalPrenomY() {
        return Optional.ofNullable(prenomY);
    }

    public DoubleFilter prenomY() {
        if (prenomY == null) {
            setPrenomY(new DoubleFilter());
        }
        return prenomY;
    }

    public void setPrenomY(DoubleFilter prenomY) {
        this.prenomY = prenomY;
    }

    public DoubleFilter getQrHeight() {
        return qrHeight;
    }

    public Optional<DoubleFilter> optionalQrHeight() {
        return Optional.ofNullable(qrHeight);
    }

    public DoubleFilter qrHeight() {
        if (qrHeight == null) {
            setQrHeight(new DoubleFilter());
        }
        return qrHeight;
    }

    public void setQrHeight(DoubleFilter qrHeight) {
        this.qrHeight = qrHeight;
    }

    public DoubleFilter getQrWidth() {
        return qrWidth;
    }

    public Optional<DoubleFilter> optionalQrWidth() {
        return Optional.ofNullable(qrWidth);
    }

    public DoubleFilter qrWidth() {
        if (qrWidth == null) {
            setQrWidth(new DoubleFilter());
        }
        return qrWidth;
    }

    public void setQrWidth(DoubleFilter qrWidth) {
        this.qrWidth = qrWidth;
    }

    public DoubleFilter getQrX() {
        return qrX;
    }

    public Optional<DoubleFilter> optionalQrX() {
        return Optional.ofNullable(qrX);
    }

    public DoubleFilter qrX() {
        if (qrX == null) {
            setQrX(new DoubleFilter());
        }
        return qrX;
    }

    public void setQrX(DoubleFilter qrX) {
        this.qrX = qrX;
    }

    public DoubleFilter getQrY() {
        return qrY;
    }

    public Optional<DoubleFilter> optionalQrY() {
        return Optional.ofNullable(qrY);
    }

    public DoubleFilter qrY() {
        if (qrY == null) {
            setQrY(new DoubleFilter());
        }
        return qrY;
    }

    public void setQrY(DoubleFilter qrY) {
        this.qrY = qrY;
    }

    public DoubleFilter getDateDelivranceX() {
        return dateDelivranceX;
    }

    public Optional<DoubleFilter> optionalDateDelivranceX() {
        return Optional.ofNullable(dateDelivranceX);
    }

    public DoubleFilter dateDelivranceX() {
        if (dateDelivranceX == null) {
            setDateDelivranceX(new DoubleFilter());
        }
        return dateDelivranceX;
    }

    public void setDateDelivranceX(DoubleFilter dateDelivranceX) {
        this.dateDelivranceX = dateDelivranceX;
    }

    public DoubleFilter getDateDelivranceY() {
        return dateDelivranceY;
    }

    public Optional<DoubleFilter> optionalDateDelivranceY() {
        return Optional.ofNullable(dateDelivranceY);
    }

    public DoubleFilter dateDelivranceY() {
        if (dateDelivranceY == null) {
            setDateDelivranceY(new DoubleFilter());
        }
        return dateDelivranceY;
    }

    public void setDateDelivranceY(DoubleFilter dateDelivranceY) {
        this.dateDelivranceY = dateDelivranceY;
    }

    public DoubleFilter getDateExpirationX() {
        return dateExpirationX;
    }

    public Optional<DoubleFilter> optionalDateExpirationX() {
        return Optional.ofNullable(dateExpirationX);
    }

    public DoubleFilter dateExpirationX() {
        if (dateExpirationX == null) {
            setDateExpirationX(new DoubleFilter());
        }
        return dateExpirationX;
    }

    public void setDateExpirationX(DoubleFilter dateExpirationX) {
        this.dateExpirationX = dateExpirationX;
    }

    public DoubleFilter getDateExpirationY() {
        return dateExpirationY;
    }

    public Optional<DoubleFilter> optionalDateExpirationY() {
        return Optional.ofNullable(dateExpirationY);
    }

    public DoubleFilter dateExpirationY() {
        if (dateExpirationY == null) {
            setDateExpirationY(new DoubleFilter());
        }
        return dateExpirationY;
    }

    public void setDateExpirationY(DoubleFilter dateExpirationY) {
        this.dateExpirationY = dateExpirationY;
    }

    public DoubleFilter getSampleLargeur() {
        return sampleLargeur;
    }

    public Optional<DoubleFilter> optionalSampleLargeur() {
        return Optional.ofNullable(sampleLargeur);
    }

    public DoubleFilter sampleLargeur() {
        if (sampleLargeur == null) {
            setSampleLargeur(new DoubleFilter());
        }
        return sampleLargeur;
    }

    public void setSampleLargeur(DoubleFilter sampleLargeur) {
        this.sampleLargeur = sampleLargeur;
    }

    public DoubleFilter getSampleLongueur() {
        return sampleLongueur;
    }

    public Optional<DoubleFilter> optionalSampleLongueur() {
        return Optional.ofNullable(sampleLongueur);
    }

    public DoubleFilter sampleLongueur() {
        if (sampleLongueur == null) {
            setSampleLongueur(new DoubleFilter());
        }
        return sampleLongueur;
    }

    public void setSampleLongueur(DoubleFilter sampleLongueur) {
        this.sampleLongueur = sampleLongueur;
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
        final TemplateCriteria that = (TemplateCriteria) o;
        return (
            Objects.equals(libelle, that.libelle) &&
            Objects.equals(description, that.description) &&
            Objects.equals(imageHeigth, that.imageHeigth) &&
            Objects.equals(imageWidth, that.imageWidth) &&
            Objects.equals(imageX, that.imageX) &&
            Objects.equals(imageY, that.imageY) &&
            Objects.equals(matriculeX, that.matriculeX) &&
            Objects.equals(matriculeY, that.matriculeY) &&
            Objects.equals(membreX, that.membreX) &&
            Objects.equals(membreY, that.membreY) &&
            Objects.equals(nomX, that.nomX) &&
            Objects.equals(nomY, that.nomY) &&
            Objects.equals(prenomX, that.prenomX) &&
            Objects.equals(prenomY, that.prenomY) &&
            Objects.equals(qrHeight, that.qrHeight) &&
            Objects.equals(qrWidth, that.qrWidth) &&
            Objects.equals(qrX, that.qrX) &&
            Objects.equals(qrY, that.qrY) &&
            Objects.equals(dateDelivranceX, that.dateDelivranceX) &&
            Objects.equals(dateDelivranceY, that.dateDelivranceY) &&
            Objects.equals(dateExpirationX, that.dateExpirationX) &&
            Objects.equals(dateExpirationY, that.dateExpirationY) &&
            Objects.equals(sampleLargeur, that.sampleLargeur) &&
            Objects.equals(sampleLongueur, that.sampleLongueur) &&
            Objects.equals(createdBy, that.createdBy) &&
            Objects.equals(createdDate, that.createdDate) &&
            Objects.equals(lastModifiedBy, that.lastModifiedBy) &&
            Objects.equals(lastModifiedDate, that.lastModifiedDate) &&
            Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            libelle,
            description,
            imageHeigth,
            imageWidth,
            imageX,
            imageY,
            matriculeX,
            matriculeY,
            membreX,
            membreY,
            nomX,
            nomY,
            prenomX,
            prenomY,
            qrHeight,
            qrWidth,
            qrX,
            qrY,
            dateDelivranceX,
            dateDelivranceY,
            dateExpirationX,
            dateExpirationY,
            sampleLargeur,
            sampleLongueur,
            createdBy,
            createdDate,
            lastModifiedBy,
            lastModifiedDate,
            distinct
        );
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TemplateCriteria{" +
            optionalLibelle().map(f -> "libelle=" + f + ", ").orElse("") +
            optionalDescription().map(f -> "description=" + f + ", ").orElse("") +
            optionalImageHeigth().map(f -> "imageHeigth=" + f + ", ").orElse("") +
            optionalImageWidth().map(f -> "imageWidth=" + f + ", ").orElse("") +
            optionalImageX().map(f -> "imageX=" + f + ", ").orElse("") +
            optionalImageY().map(f -> "imageY=" + f + ", ").orElse("") +
            optionalMatriculeX().map(f -> "matriculeX=" + f + ", ").orElse("") +
            optionalMatriculeY().map(f -> "matriculeY=" + f + ", ").orElse("") +
            optionalMembreX().map(f -> "membreX=" + f + ", ").orElse("") +
            optionalMembreY().map(f -> "membreY=" + f + ", ").orElse("") +
            optionalNomX().map(f -> "nomX=" + f + ", ").orElse("") +
            optionalNomY().map(f -> "nomY=" + f + ", ").orElse("") +
            optionalPrenomX().map(f -> "prenomX=" + f + ", ").orElse("") +
            optionalPrenomY().map(f -> "prenomY=" + f + ", ").orElse("") +
            optionalQrHeight().map(f -> "qrHeight=" + f + ", ").orElse("") +
            optionalQrWidth().map(f -> "qrWidth=" + f + ", ").orElse("") +
            optionalQrX().map(f -> "qrX=" + f + ", ").orElse("") +
            optionalQrY().map(f -> "qrY=" + f + ", ").orElse("") +
            optionalDateDelivranceX().map(f -> "dateDelivranceX=" + f + ", ").orElse("") +
            optionalDateDelivranceY().map(f -> "dateDelivranceY=" + f + ", ").orElse("") +
            optionalDateExpirationX().map(f -> "dateExpirationX=" + f + ", ").orElse("") +
            optionalDateExpirationY().map(f -> "dateExpirationY=" + f + ", ").orElse("") +
            optionalSampleLargeur().map(f -> "sampleLargeur=" + f + ", ").orElse("") +
            optionalSampleLongueur().map(f -> "sampleLongueur=" + f + ", ").orElse("") +
            optionalCreatedBy().map(f -> "createdBy=" + f + ", ").orElse("") +
            optionalCreatedDate().map(f -> "createdDate=" + f + ", ").orElse("") +
            optionalLastModifiedBy().map(f -> "lastModifiedBy=" + f + ", ").orElse("") +
            optionalLastModifiedDate().map(f -> "lastModifiedDate=" + f + ", ").orElse("") +
            optionalDistinct().map(f -> "distinct=" + f + ", ").orElse("") +
        "}";
    }
}
