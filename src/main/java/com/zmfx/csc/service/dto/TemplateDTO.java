package com.zmfx.csc.service.dto;

import jakarta.persistence.Lob;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A DTO for the {@link com.zmfx.csc.domain.Template} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class TemplateDTO implements Serializable {

    private String libelle;

    private String description;

    private Double imageHeigth;

    private Double imageWidth;

    private Double imageX;

    private Double imageY;

    private Double matriculeX;

    private Double matriculeY;

    private Double membreX;

    private Double membreY;

    private Double nomX;

    private Double nomY;

    private Double prenomX;

    private Double prenomY;

    private Double qrHeight;

    private Double qrWidth;

    private Double qrX;

    private Double qrY;

    private Double dateDelivranceX;

    private Double dateDelivranceY;

    private Double dateExpirationX;

    private Double dateExpirationY;

    private Double sampleLargeur;

    private Double sampleLongueur;

    @Lob
    private byte[] pdfContentRecto;

    private String pdfContentRectoContentType;

    @Lob
    private byte[] pdfContentVerso;

    private String pdfContentVersoContentType;

    private String createdBy;

    private Instant createdDate;

    private String lastModifiedBy;

    private Instant lastModifiedDate;

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getImageHeigth() {
        return imageHeigth;
    }

    public void setImageHeigth(Double imageHeigth) {
        this.imageHeigth = imageHeigth;
    }

    public Double getImageWidth() {
        return imageWidth;
    }

    public void setImageWidth(Double imageWidth) {
        this.imageWidth = imageWidth;
    }

    public Double getImageX() {
        return imageX;
    }

    public void setImageX(Double imageX) {
        this.imageX = imageX;
    }

    public Double getImageY() {
        return imageY;
    }

    public void setImageY(Double imageY) {
        this.imageY = imageY;
    }

    public Double getMatriculeX() {
        return matriculeX;
    }

    public void setMatriculeX(Double matriculeX) {
        this.matriculeX = matriculeX;
    }

    public Double getMatriculeY() {
        return matriculeY;
    }

    public void setMatriculeY(Double matriculeY) {
        this.matriculeY = matriculeY;
    }

    public Double getMembreX() {
        return membreX;
    }

    public void setMembreX(Double membreX) {
        this.membreX = membreX;
    }

    public Double getMembreY() {
        return membreY;
    }

    public void setMembreY(Double membreY) {
        this.membreY = membreY;
    }

    public Double getNomX() {
        return nomX;
    }

    public void setNomX(Double nomX) {
        this.nomX = nomX;
    }

    public Double getNomY() {
        return nomY;
    }

    public void setNomY(Double nomY) {
        this.nomY = nomY;
    }

    public Double getPrenomX() {
        return prenomX;
    }

    public void setPrenomX(Double prenomX) {
        this.prenomX = prenomX;
    }

    public Double getPrenomY() {
        return prenomY;
    }

    public void setPrenomY(Double prenomY) {
        this.prenomY = prenomY;
    }

    public Double getQrHeight() {
        return qrHeight;
    }

    public void setQrHeight(Double qrHeight) {
        this.qrHeight = qrHeight;
    }

    public Double getQrWidth() {
        return qrWidth;
    }

    public void setQrWidth(Double qrWidth) {
        this.qrWidth = qrWidth;
    }

    public Double getQrX() {
        return qrX;
    }

    public void setQrX(Double qrX) {
        this.qrX = qrX;
    }

    public Double getQrY() {
        return qrY;
    }

    public void setQrY(Double qrY) {
        this.qrY = qrY;
    }

    public Double getDateDelivranceX() {
        return dateDelivranceX;
    }

    public void setDateDelivranceX(Double dateDelivranceX) {
        this.dateDelivranceX = dateDelivranceX;
    }

    public Double getDateDelivranceY() {
        return dateDelivranceY;
    }

    public void setDateDelivranceY(Double dateDelivranceY) {
        this.dateDelivranceY = dateDelivranceY;
    }

    public Double getDateExpirationX() {
        return dateExpirationX;
    }

    public void setDateExpirationX(Double dateExpirationX) {
        this.dateExpirationX = dateExpirationX;
    }

    public Double getDateExpirationY() {
        return dateExpirationY;
    }

    public void setDateExpirationY(Double dateExpirationY) {
        this.dateExpirationY = dateExpirationY;
    }

    public Double getSampleLargeur() {
        return sampleLargeur;
    }

    public void setSampleLargeur(Double sampleLargeur) {
        this.sampleLargeur = sampleLargeur;
    }

    public Double getSampleLongueur() {
        return sampleLongueur;
    }

    public void setSampleLongueur(Double sampleLongueur) {
        this.sampleLongueur = sampleLongueur;
    }

    public byte[] getPdfContentRecto() {
        return pdfContentRecto;
    }

    public void setPdfContentRecto(byte[] pdfContentRecto) {
        this.pdfContentRecto = pdfContentRecto;
    }

    public String getPdfContentRectoContentType() {
        return pdfContentRectoContentType;
    }

    public void setPdfContentRectoContentType(String pdfContentRectoContentType) {
        this.pdfContentRectoContentType = pdfContentRectoContentType;
    }

    public byte[] getPdfContentVerso() {
        return pdfContentVerso;
    }

    public void setPdfContentVerso(byte[] pdfContentVerso) {
        this.pdfContentVerso = pdfContentVerso;
    }

    public String getPdfContentVersoContentType() {
        return pdfContentVersoContentType;
    }

    public void setPdfContentVersoContentType(String pdfContentVersoContentType) {
        this.pdfContentVersoContentType = pdfContentVersoContentType;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TemplateDTO)) {
            return false;
        }

        TemplateDTO templateDTO = (TemplateDTO) o;
        if (this.libelle == null) {
            return false;
        }
        return Objects.equals(this.libelle, templateDTO.libelle);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.libelle);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TemplateDTO{" +
            "libelle='" + getLibelle() + "'" +
            ", description='" + getDescription() + "'" +
            ", imageHeigth=" + getImageHeigth() +
            ", imageWidth=" + getImageWidth() +
            ", imageX=" + getImageX() +
            ", imageY=" + getImageY() +
            ", matriculeX=" + getMatriculeX() +
            ", matriculeY=" + getMatriculeY() +
            ", membreX=" + getMembreX() +
            ", membreY=" + getMembreY() +
            ", nomX=" + getNomX() +
            ", nomY=" + getNomY() +
            ", prenomX=" + getPrenomX() +
            ", prenomY=" + getPrenomY() +
            ", qrHeight=" + getQrHeight() +
            ", qrWidth=" + getQrWidth() +
            ", qrX=" + getQrX() +
            ", qrY=" + getQrY() +
            ", dateDelivranceX=" + getDateDelivranceX() +
            ", dateDelivranceY=" + getDateDelivranceY() +
            ", dateExpirationX=" + getDateExpirationX() +
            ", dateExpirationY=" + getDateExpirationY() +
            ", sampleLargeur=" + getSampleLargeur() +
            ", sampleLongueur=" + getSampleLongueur() +
            ", pdfContentRecto='" + getPdfContentRecto() + "'" +
            ", pdfContentVerso='" + getPdfContentVerso() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", lastModifiedBy='" + getLastModifiedBy() + "'" +
            ", lastModifiedDate='" + getLastModifiedDate() + "'" +
            "}";
    }
}
