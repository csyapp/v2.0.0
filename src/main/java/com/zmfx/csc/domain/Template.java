package com.zmfx.csc.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.data.domain.Persistable;

/**
 * A Template.
 */
@Entity
@Table(name = "template")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@JsonIgnoreProperties(value = { "new", "id" })
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Template extends AbstractAuditingEntity<String> implements Serializable, Persistable<String> {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "libelle")
    private String libelle;

    @Column(name = "description")
    private String description;

    @Column(name = "image_heigth")
    private Double imageHeigth;

    @Column(name = "image_width")
    private Double imageWidth;

    @Column(name = "image_x")
    private Double imageX;

    @Column(name = "image_y")
    private Double imageY;

    @Column(name = "matricule_x")
    private Double matriculeX;

    @Column(name = "matricule_y")
    private Double matriculeY;

    @Column(name = "membre_x")
    private Double membreX;

    @Column(name = "membre_y")
    private Double membreY;

    @Column(name = "nom_x")
    private Double nomX;

    @Column(name = "nom_y")
    private Double nomY;

    @Column(name = "prenom_x")
    private Double prenomX;

    @Column(name = "prenom_y")
    private Double prenomY;

    @Column(name = "qr_height")
    private Double qrHeight;

    @Column(name = "qr_width")
    private Double qrWidth;

    @Column(name = "qr_x")
    private Double qrX;

    @Column(name = "qr_y")
    private Double qrY;

    @Column(name = "date_delivrance_x")
    private Double dateDelivranceX;

    @Column(name = "date_delivrance_y")
    private Double dateDelivranceY;

    @Column(name = "date_expiration_x")
    private Double dateExpirationX;

    @Column(name = "date_expiration_y")
    private Double dateExpirationY;

    @Column(name = "sample_largeur")
    private Double sampleLargeur;

    @Column(name = "sample_longueur")
    private Double sampleLongueur;

    @Lob
    @Column(name = "pdf_content_recto")
    private byte[] pdfContentRecto;

    @Column(name = "pdf_content_recto_content_type")
    private String pdfContentRectoContentType;

    @Lob
    @Column(name = "pdf_content_verso")
    private byte[] pdfContentVerso;

    @Column(name = "pdf_content_verso_content_type")
    private String pdfContentVersoContentType;

    // Inherited createdBy definition
    // Inherited createdDate definition
    // Inherited lastModifiedBy definition
    // Inherited lastModifiedDate definition
    @Transient
    private boolean isPersisted;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getLibelle() {
        return this.libelle;
    }

    public Template libelle(String libelle) {
        this.setLibelle(libelle);
        return this;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getDescription() {
        return this.description;
    }

    public Template description(String description) {
        this.setDescription(description);
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getImageHeigth() {
        return this.imageHeigth;
    }

    public Template imageHeigth(Double imageHeigth) {
        this.setImageHeigth(imageHeigth);
        return this;
    }

    public void setImageHeigth(Double imageHeigth) {
        this.imageHeigth = imageHeigth;
    }

    public Double getImageWidth() {
        return this.imageWidth;
    }

    public Template imageWidth(Double imageWidth) {
        this.setImageWidth(imageWidth);
        return this;
    }

    public void setImageWidth(Double imageWidth) {
        this.imageWidth = imageWidth;
    }

    public Double getImageX() {
        return this.imageX;
    }

    public Template imageX(Double imageX) {
        this.setImageX(imageX);
        return this;
    }

    public void setImageX(Double imageX) {
        this.imageX = imageX;
    }

    public Double getImageY() {
        return this.imageY;
    }

    public Template imageY(Double imageY) {
        this.setImageY(imageY);
        return this;
    }

    public void setImageY(Double imageY) {
        this.imageY = imageY;
    }

    public Double getMatriculeX() {
        return this.matriculeX;
    }

    public Template matriculeX(Double matriculeX) {
        this.setMatriculeX(matriculeX);
        return this;
    }

    public void setMatriculeX(Double matriculeX) {
        this.matriculeX = matriculeX;
    }

    public Double getMatriculeY() {
        return this.matriculeY;
    }

    public Template matriculeY(Double matriculeY) {
        this.setMatriculeY(matriculeY);
        return this;
    }

    public void setMatriculeY(Double matriculeY) {
        this.matriculeY = matriculeY;
    }

    public Double getMembreX() {
        return this.membreX;
    }

    public Template membreX(Double membreX) {
        this.setMembreX(membreX);
        return this;
    }

    public void setMembreX(Double membreX) {
        this.membreX = membreX;
    }

    public Double getMembreY() {
        return this.membreY;
    }

    public Template membreY(Double membreY) {
        this.setMembreY(membreY);
        return this;
    }

    public void setMembreY(Double membreY) {
        this.membreY = membreY;
    }

    public Double getNomX() {
        return this.nomX;
    }

    public Template nomX(Double nomX) {
        this.setNomX(nomX);
        return this;
    }

    public void setNomX(Double nomX) {
        this.nomX = nomX;
    }

    public Double getNomY() {
        return this.nomY;
    }

    public Template nomY(Double nomY) {
        this.setNomY(nomY);
        return this;
    }

    public void setNomY(Double nomY) {
        this.nomY = nomY;
    }

    public Double getPrenomX() {
        return this.prenomX;
    }

    public Template prenomX(Double prenomX) {
        this.setPrenomX(prenomX);
        return this;
    }

    public void setPrenomX(Double prenomX) {
        this.prenomX = prenomX;
    }

    public Double getPrenomY() {
        return this.prenomY;
    }

    public Template prenomY(Double prenomY) {
        this.setPrenomY(prenomY);
        return this;
    }

    public void setPrenomY(Double prenomY) {
        this.prenomY = prenomY;
    }

    public Double getQrHeight() {
        return this.qrHeight;
    }

    public Template qrHeight(Double qrHeight) {
        this.setQrHeight(qrHeight);
        return this;
    }

    public void setQrHeight(Double qrHeight) {
        this.qrHeight = qrHeight;
    }

    public Double getQrWidth() {
        return this.qrWidth;
    }

    public Template qrWidth(Double qrWidth) {
        this.setQrWidth(qrWidth);
        return this;
    }

    public void setQrWidth(Double qrWidth) {
        this.qrWidth = qrWidth;
    }

    public Double getQrX() {
        return this.qrX;
    }

    public Template qrX(Double qrX) {
        this.setQrX(qrX);
        return this;
    }

    public void setQrX(Double qrX) {
        this.qrX = qrX;
    }

    public Double getQrY() {
        return this.qrY;
    }

    public Template qrY(Double qrY) {
        this.setQrY(qrY);
        return this;
    }

    public void setQrY(Double qrY) {
        this.qrY = qrY;
    }

    public Double getDateDelivranceX() {
        return this.dateDelivranceX;
    }

    public Template dateDelivranceX(Double dateDelivranceX) {
        this.setDateDelivranceX(dateDelivranceX);
        return this;
    }

    public void setDateDelivranceX(Double dateDelivranceX) {
        this.dateDelivranceX = dateDelivranceX;
    }

    public Double getDateDelivranceY() {
        return this.dateDelivranceY;
    }

    public Template dateDelivranceY(Double dateDelivranceY) {
        this.setDateDelivranceY(dateDelivranceY);
        return this;
    }

    public void setDateDelivranceY(Double dateDelivranceY) {
        this.dateDelivranceY = dateDelivranceY;
    }

    public Double getDateExpirationX() {
        return this.dateExpirationX;
    }

    public Template dateExpirationX(Double dateExpirationX) {
        this.setDateExpirationX(dateExpirationX);
        return this;
    }

    public void setDateExpirationX(Double dateExpirationX) {
        this.dateExpirationX = dateExpirationX;
    }

    public Double getDateExpirationY() {
        return this.dateExpirationY;
    }

    public Template dateExpirationY(Double dateExpirationY) {
        this.setDateExpirationY(dateExpirationY);
        return this;
    }

    public void setDateExpirationY(Double dateExpirationY) {
        this.dateExpirationY = dateExpirationY;
    }

    public Double getSampleLargeur() {
        return this.sampleLargeur;
    }

    public Template sampleLargeur(Double sampleLargeur) {
        this.setSampleLargeur(sampleLargeur);
        return this;
    }

    public void setSampleLargeur(Double sampleLargeur) {
        this.sampleLargeur = sampleLargeur;
    }

    public Double getSampleLongueur() {
        return this.sampleLongueur;
    }

    public Template sampleLongueur(Double sampleLongueur) {
        this.setSampleLongueur(sampleLongueur);
        return this;
    }

    public void setSampleLongueur(Double sampleLongueur) {
        this.sampleLongueur = sampleLongueur;
    }

    public byte[] getPdfContentRecto() {
        return this.pdfContentRecto;
    }

    public Template pdfContentRecto(byte[] pdfContentRecto) {
        this.setPdfContentRecto(pdfContentRecto);
        return this;
    }

    public void setPdfContentRecto(byte[] pdfContentRecto) {
        this.pdfContentRecto = pdfContentRecto;
    }

    public String getPdfContentRectoContentType() {
        return this.pdfContentRectoContentType;
    }

    public Template pdfContentRectoContentType(String pdfContentRectoContentType) {
        this.pdfContentRectoContentType = pdfContentRectoContentType;
        return this;
    }

    public void setPdfContentRectoContentType(String pdfContentRectoContentType) {
        this.pdfContentRectoContentType = pdfContentRectoContentType;
    }

    public byte[] getPdfContentVerso() {
        return this.pdfContentVerso;
    }

    public Template pdfContentVerso(byte[] pdfContentVerso) {
        this.setPdfContentVerso(pdfContentVerso);
        return this;
    }

    public void setPdfContentVerso(byte[] pdfContentVerso) {
        this.pdfContentVerso = pdfContentVerso;
    }

    public String getPdfContentVersoContentType() {
        return this.pdfContentVersoContentType;
    }

    public Template pdfContentVersoContentType(String pdfContentVersoContentType) {
        this.pdfContentVersoContentType = pdfContentVersoContentType;
        return this;
    }

    public void setPdfContentVersoContentType(String pdfContentVersoContentType) {
        this.pdfContentVersoContentType = pdfContentVersoContentType;
    }

    // Inherited createdBy methods
    public Template createdBy(String createdBy) {
        this.setCreatedBy(createdBy);
        return this;
    }

    // Inherited createdDate methods
    public Template createdDate(Instant createdDate) {
        this.setCreatedDate(createdDate);
        return this;
    }

    // Inherited lastModifiedBy methods
    public Template lastModifiedBy(String lastModifiedBy) {
        this.setLastModifiedBy(lastModifiedBy);
        return this;
    }

    // Inherited lastModifiedDate methods
    public Template lastModifiedDate(Instant lastModifiedDate) {
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
        return this.libelle;
    }

    @Transient
    @Override
    public boolean isNew() {
        return !this.isPersisted;
    }

    public Template setIsPersisted() {
        this.isPersisted = true;
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Template)) {
            return false;
        }
        return getLibelle() != null && getLibelle().equals(((Template) o).getLibelle());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Template{" +
            "libelle=" + getLibelle() +
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
            ", pdfContentRectoContentType='" + getPdfContentRectoContentType() + "'" +
            ", pdfContentVerso='" + getPdfContentVerso() + "'" +
            ", pdfContentVersoContentType='" + getPdfContentVersoContentType() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", lastModifiedBy='" + getLastModifiedBy() + "'" +
            ", lastModifiedDate='" + getLastModifiedDate() + "'" +
            "}";
    }
}
