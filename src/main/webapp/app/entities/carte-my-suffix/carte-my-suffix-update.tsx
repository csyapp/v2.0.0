import React, { useState, useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, ValidatedField, ValidatedForm, ValidatedBlobField } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { ITemplateMySuffix } from 'app/shared/model/template-my-suffix.model';
import { getEntities as getTemplates } from 'app/entities/template-my-suffix/template-my-suffix.reducer';
import { ICarteMySuffix } from 'app/shared/model/carte-my-suffix.model';
import { getEntity, updateEntity, createEntity, reset } from './carte-my-suffix.reducer';

export const CarteMySuffixUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const templates = useAppSelector(state => state.template.entities);
  const carteEntity = useAppSelector(state => state.carte.entity);
  const loading = useAppSelector(state => state.carte.loading);
  const updating = useAppSelector(state => state.carte.updating);
  const updateSuccess = useAppSelector(state => state.carte.updateSuccess);

  const handleClose = () => {
    navigate('/carte-my-suffix' + location.search);
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(id));
    }

    dispatch(getTemplates({}));
  }, []);

  useEffect(() => {
    if (updateSuccess) {
      handleClose();
    }
  }, [updateSuccess]);

  // eslint-disable-next-line complexity
  const saveEntity = values => {
    values.birthdate = convertDateTimeToServer(values.birthdate);
    values.createdDate = convertDateTimeToServer(values.createdDate);
    values.lastModifiedDate = convertDateTimeToServer(values.lastModifiedDate);

    const entity = {
      ...carteEntity,
      ...values,
      template: templates.find(it => it.libelle.toString() === values.template?.toString()),
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
          birthdate: displayDefaultDateTime(),
          createdDate: displayDefaultDateTime(),
          lastModifiedDate: displayDefaultDateTime(),
        }
      : {
          ...carteEntity,
          birthdate: convertDateTimeFromServer(carteEntity.birthdate),
          createdDate: convertDateTimeFromServer(carteEntity.createdDate),
          lastModifiedDate: convertDateTimeFromServer(carteEntity.lastModifiedDate),
          template: carteEntity?.template?.libelle,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="cscApp.carte.home.createOrEditLabel" data-cy="CarteCreateUpdateHeading">
            Créer ou éditer un Carte
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <ValidatedForm defaultValues={defaultValues()} onSubmit={saveEntity}>
              <ValidatedField id="carte-my-suffix-template" name="template" data-cy="template" label="Template" type="select">
                <option value="" key="0" />
                {templates
                  ? templates.map(otherEntity => (
                      <option value={otherEntity.libelle} key={otherEntity.libelle}>
                        {otherEntity.libelle}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              {!isNew ? (
                <ValidatedField
                  name="matricule"
                  required
                  readOnly
                  id="carte-my-suffix-matricule"
                  label="Matricule"
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField label="Name" id="carte-my-suffix-name" name="name" data-cy="name" type="text" />
              <ValidatedField label="Surname" id="carte-my-suffix-surname" name="surname" data-cy="surname" type="text" />
              <ValidatedField label="Email" id="carte-my-suffix-email" name="email" data-cy="email" type="text" />
              <ValidatedField
                label="Ville Residence"
                id="carte-my-suffix-villeResidence"
                name="villeResidence"
                data-cy="villeResidence"
                type="text"
              />
              <ValidatedField
                label="Birthdate"
                id="carte-my-suffix-birthdate"
                name="birthdate"
                data-cy="birthdate"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <ValidatedField label="Telephone 1" id="carte-my-suffix-telephone1" name="telephone1" data-cy="telephone1" type="text" />
              <ValidatedField label="Telephone 2" id="carte-my-suffix-telephone2" name="telephone2" data-cy="telephone2" type="text" />
              <ValidatedBlobField label="Picture" id="carte-my-suffix-picture" name="picture" data-cy="picture" openActionLabel="Ouvrir" />
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/carte-my-suffix" replace color="info">
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

export default CarteMySuffixUpdate;
