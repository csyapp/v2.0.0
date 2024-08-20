import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { openFile, byteSize, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT, AUTHORITIES } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './carte-my-suffix.reducer';
import { hasAnyAuthority } from 'app/shared/auth/private-route';

export const CarteMySuffixDetail = () => {
  const dispatch = useAppDispatch();

  const isAdministrateur = useAppSelector(state =>
    hasAnyAuthority(state.authentication.account.authorities, [AUTHORITIES.ADMINISTRATEUR, AUTHORITIES.ADMIN]),
  );
  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const carteEntity = useAppSelector(state => state.carte.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="carteDetailsHeading">Carte</h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="matricule">Matricule</span>
          </dt>
          <dd>{carteEntity.matricule}</dd>
          <dt>
            <span id="name">Name</span>
          </dt>
          <dd>{carteEntity.name}</dd>
          <dt>
            <span id="surname">Surname</span>
          </dt>
          <dd>{carteEntity.surname}</dd>
          <dt>
            <span id="email">Email</span>
          </dt>
          <dd>{carteEntity.email}</dd>
          <dt>
            <span id="villeResidence">Ville Residence</span>
          </dt>
          <dd>{carteEntity.villeResidence}</dd>
          <dt>
            <span id="birthdate">Birthdate</span>
          </dt>
          <dd>{carteEntity.birthdate ? <TextFormat value={carteEntity.birthdate} type="date" format={APP_DATE_FORMAT} /> : null}</dd>
          <dt>
            <span id="telephone1">Telephone 1</span>
          </dt>
          <dd>{carteEntity.telephone1}</dd>
          <dt>
            <span id="telephone2">Telephone 2</span>
          </dt>
          <dd>{carteEntity.telephone2}</dd>
          <dt>
            <span id="isImprime">Is Imprime</span>
          </dt>
          <dd>{carteEntity.isImprime ? 'true' : 'false'}</dd>
          <dt>
            <span id="pictureExtension">Picture Extension</span>
          </dt>
          <dd>{carteEntity.pictureExtension}</dd>
          <dt>
            <span id="picture">Picture</span>
          </dt>
          <dd>
            {carteEntity.picture ? (
              <div>
                {carteEntity.pictureContentType ? (
                  <a onClick={openFile(carteEntity.pictureContentType, carteEntity.picture)}>Ouvrir&nbsp;</a>
                ) : null}
                <span>
                  {carteEntity.pictureContentType}, {byteSize(carteEntity.picture)}
                </span>
              </div>
            ) : null}
          </dd>
          <dt>
            <span id="dateDelivrance">Date Delivrance</span>
          </dt>
          <dd>
            {carteEntity.dateDelivrance ? (
              <TextFormat value={carteEntity.dateDelivrance} type="date" format={APP_LOCAL_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="dateExpiration">Date Expiration</span>
          </dt>
          <dd>
            {carteEntity.dateExpiration ? (
              <TextFormat value={carteEntity.dateExpiration} type="date" format={APP_LOCAL_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="createdBy">Created By</span>
          </dt>
          <dd>{carteEntity.createdBy}</dd>
          <dt>
            <span id="createdDate">Created Date</span>
          </dt>
          <dd>{carteEntity.createdDate ? <TextFormat value={carteEntity.createdDate} type="date" format={APP_DATE_FORMAT} /> : null}</dd>
          <dt>
            <span id="lastModifiedBy">Last Modified By</span>
          </dt>
          <dd>{carteEntity.lastModifiedBy}</dd>
          <dt>
            <span id="lastModifiedDate">Last Modified Date</span>
          </dt>
          <dd>
            {carteEntity.lastModifiedDate ? <TextFormat value={carteEntity.lastModifiedDate} type="date" format={APP_DATE_FORMAT} /> : null}
          </dd>
          <dt>Template</dt>
          <dd>{carteEntity.template ? carteEntity.template.libelle : ''}</dd>
        </dl>
        <Button tag={Link} to="/carte-my-suffix" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Retour</span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/carte-my-suffix/${carteEntity.matricule}/edit`} replace color="primary" disabled={!isAdministrateur}>
          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Editer</span>
        </Button>
      </Col>
    </Row>
  );
};

export default CarteMySuffixDetail;
