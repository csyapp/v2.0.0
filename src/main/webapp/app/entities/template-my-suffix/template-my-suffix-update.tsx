import React, { useState, useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, ValidatedField, ValidatedForm, ValidatedBlobField } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { ITemplateMySuffix } from 'app/shared/model/template-my-suffix.model';
import { getEntity, updateEntity, createEntity, reset } from './template-my-suffix.reducer';

export const TemplateMySuffixUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const templateEntity = useAppSelector(state => state.template.entity);
  const loading = useAppSelector(state => state.template.loading);
  const updating = useAppSelector(state => state.template.updating);
  const updateSuccess = useAppSelector(state => state.template.updateSuccess);

  const handleClose = () => {
    navigate('/template-my-suffix' + location.search);
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(id));
    }
  }, []);

  useEffect(() => {
    if (updateSuccess) {
      handleClose();
    }
  }, [updateSuccess]);

  // eslint-disable-next-line complexity
  const saveEntity = values => {
    if (values.imageHeigth !== undefined && typeof values.imageHeigth !== 'number') {
      values.imageHeigth = Number(values.imageHeigth);
    }
    if (values.imageWidth !== undefined && typeof values.imageWidth !== 'number') {
      values.imageWidth = Number(values.imageWidth);
    }
    if (values.imageX !== undefined && typeof values.imageX !== 'number') {
      values.imageX = Number(values.imageX);
    }
    if (values.imageY !== undefined && typeof values.imageY !== 'number') {
      values.imageY = Number(values.imageY);
    }
    if (values.matriculeX !== undefined && typeof values.matriculeX !== 'number') {
      values.matriculeX = Number(values.matriculeX);
    }
    if (values.matriculeY !== undefined && typeof values.matriculeY !== 'number') {
      values.matriculeY = Number(values.matriculeY);
    }
    if (values.membreX !== undefined && typeof values.membreX !== 'number') {
      values.membreX = Number(values.membreX);
    }
    if (values.membreY !== undefined && typeof values.membreY !== 'number') {
      values.membreY = Number(values.membreY);
    }
    if (values.nomX !== undefined && typeof values.nomX !== 'number') {
      values.nomX = Number(values.nomX);
    }
    if (values.nomY !== undefined && typeof values.nomY !== 'number') {
      values.nomY = Number(values.nomY);
    }
    if (values.prenomX !== undefined && typeof values.prenomX !== 'number') {
      values.prenomX = Number(values.prenomX);
    }
    if (values.prenomY !== undefined && typeof values.prenomY !== 'number') {
      values.prenomY = Number(values.prenomY);
    }
    if (values.qrHeight !== undefined && typeof values.qrHeight !== 'number') {
      values.qrHeight = Number(values.qrHeight);
    }
    if (values.qrWidth !== undefined && typeof values.qrWidth !== 'number') {
      values.qrWidth = Number(values.qrWidth);
    }
    if (values.qrX !== undefined && typeof values.qrX !== 'number') {
      values.qrX = Number(values.qrX);
    }
    if (values.qrY !== undefined && typeof values.qrY !== 'number') {
      values.qrY = Number(values.qrY);
    }
    if (values.dateDelivranceX !== undefined && typeof values.dateDelivranceX !== 'number') {
      values.dateDelivranceX = Number(values.dateDelivranceX);
    }
    if (values.dateDelivranceY !== undefined && typeof values.dateDelivranceY !== 'number') {
      values.dateDelivranceY = Number(values.dateDelivranceY);
    }
    if (values.dateExpirationX !== undefined && typeof values.dateExpirationX !== 'number') {
      values.dateExpirationX = Number(values.dateExpirationX);
    }
    if (values.dateExpirationY !== undefined && typeof values.dateExpirationY !== 'number') {
      values.dateExpirationY = Number(values.dateExpirationY);
    }
    if (values.sampleLargeur !== undefined && typeof values.sampleLargeur !== 'number') {
      values.sampleLargeur = Number(values.sampleLargeur);
    }
    if (values.sampleLongueur !== undefined && typeof values.sampleLongueur !== 'number') {
      values.sampleLongueur = Number(values.sampleLongueur);
    }
    values.createdDate = convertDateTimeToServer(values.createdDate);
    values.lastModifiedDate = convertDateTimeToServer(values.lastModifiedDate);

    const entity = {
      ...templateEntity,
      ...values,
    };

    if (isNew) {
      dispatch(createEntity(entity));
    } else {
      dispatch(updateEntity(entity));
    }
  };

  const defaultValues = () =>
    isNew
      ? {
          createdDate: displayDefaultDateTime(),
          lastModifiedDate: displayDefaultDateTime(),
        }
      : {
          ...templateEntity,
          createdDate: convertDateTimeFromServer(templateEntity.createdDate),
          lastModifiedDate: convertDateTimeFromServer(templateEntity.lastModifiedDate),
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="cscApp.template.home.createOrEditLabel" data-cy="TemplateCreateUpdateHeading">
            Créer ou éditer un Template
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <ValidatedForm defaultValues={defaultValues()} onSubmit={saveEntity}>
              {isNew ? (
                <ValidatedField name="libelle" required id="template-my-suffix-libelle" label="Libelle" validate={{ required: true }} />
              ) : (
                <ValidatedField
                  name="libelle"
                  required
                  readOnly
                  id="template-my-suffix-libelle"
                  label="Libelle"
                  validate={{ required: true }}
                />
              )}
              <ValidatedField
                label="Description"
                id="template-my-suffix-description"
                name="description"
                data-cy="description"
                type="text"
              />
              <ValidatedField
                label="Image Heigth"
                id="template-my-suffix-imageHeigth"
                name="imageHeigth"
                data-cy="imageHeigth"
                type="text"
              />
              <ValidatedField label="Image Width" id="template-my-suffix-imageWidth" name="imageWidth" data-cy="imageWidth" type="text" />
              <ValidatedField label="Image X" id="template-my-suffix-imageX" name="imageX" data-cy="imageX" type="text" />
              <ValidatedField label="Image Y" id="template-my-suffix-imageY" name="imageY" data-cy="imageY" type="text" />
              <ValidatedField label="Matricule X" id="template-my-suffix-matriculeX" name="matriculeX" data-cy="matriculeX" type="text" />
              <ValidatedField label="Matricule Y" id="template-my-suffix-matriculeY" name="matriculeY" data-cy="matriculeY" type="text" />
              <ValidatedField label="Membre X" id="template-my-suffix-membreX" name="membreX" data-cy="membreX" type="text" />
              <ValidatedField label="Membre Y" id="template-my-suffix-membreY" name="membreY" data-cy="membreY" type="text" />
              <ValidatedField label="Nom X" id="template-my-suffix-nomX" name="nomX" data-cy="nomX" type="text" />
              <ValidatedField label="Nom Y" id="template-my-suffix-nomY" name="nomY" data-cy="nomY" type="text" />
              <ValidatedField label="Prenom X" id="template-my-suffix-prenomX" name="prenomX" data-cy="prenomX" type="text" />
              <ValidatedField label="Prenom Y" id="template-my-suffix-prenomY" name="prenomY" data-cy="prenomY" type="text" />
              <ValidatedField label="Qr Height" id="template-my-suffix-qrHeight" name="qrHeight" data-cy="qrHeight" type="text" />
              <ValidatedField label="Qr Width" id="template-my-suffix-qrWidth" name="qrWidth" data-cy="qrWidth" type="text" />
              <ValidatedField label="Qr X" id="template-my-suffix-qrX" name="qrX" data-cy="qrX" type="text" />
              <ValidatedField label="Qr Y" id="template-my-suffix-qrY" name="qrY" data-cy="qrY" type="text" />
              <ValidatedField
                label="Date Delivrance X"
                id="template-my-suffix-dateDelivranceX"
                name="dateDelivranceX"
                data-cy="dateDelivranceX"
                type="text"
              />
              <ValidatedField
                label="Date Delivrance Y"
                id="template-my-suffix-dateDelivranceY"
                name="dateDelivranceY"
                data-cy="dateDelivranceY"
                type="text"
              />
              <ValidatedField
                label="Date Expiration X"
                id="template-my-suffix-dateExpirationX"
                name="dateExpirationX"
                data-cy="dateExpirationX"
                type="text"
              />
              <ValidatedField
                label="Date Expiration Y"
                id="template-my-suffix-dateExpirationY"
                name="dateExpirationY"
                data-cy="dateExpirationY"
                type="text"
              />
              <ValidatedField
                label="Sample Largeur"
                id="template-my-suffix-sampleLargeur"
                name="sampleLargeur"
                data-cy="sampleLargeur"
                type="text"
              />
              <ValidatedField
                label="Sample Longueur"
                id="template-my-suffix-sampleLongueur"
                name="sampleLongueur"
                data-cy="sampleLongueur"
                type="text"
              />
              <ValidatedBlobField
                label="Pdf Content Recto"
                id="template-my-suffix-pdfContentRecto"
                name="pdfContentRecto"
                data-cy="pdfContentRecto"
                openActionLabel="Ouvrir"
              />
              <ValidatedBlobField
                label="Pdf Content Verso"
                id="template-my-suffix-pdfContentVerso"
                name="pdfContentVerso"
                data-cy="pdfContentVerso"
                openActionLabel="Ouvrir"
              />
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/template-my-suffix" replace color="info">
                <FontAwesomeIcon icon="arrow-left" />
                &nbsp;
                <span className="d-none d-md-inline">Retour</span>
              </Button>
              &nbsp;
              <Button color="primary" id="save-entity" data-cy="entityCreateSaveButton" type="submit" disabled={updating}>
                <FontAwesomeIcon icon="save" />
                &nbsp; Sauvegarder
              </Button>
            </ValidatedForm>
          )}
        </Col>
      </Row>
    </div>
  );
};

export default TemplateMySuffixUpdate;
