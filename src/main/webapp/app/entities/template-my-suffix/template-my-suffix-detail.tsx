import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { openFile, byteSize, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT, AUTHORITIES } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './template-my-suffix.reducer';
import { hasAnyAuthority } from 'app/shared/auth/private-route';

export const TemplateMySuffixDetail = () => {
  const dispatch = useAppDispatch();
  const isAdministrateur = useAppSelector(state =>
    hasAnyAuthority(state.authentication.account.authorities, [AUTHORITIES.ADMINISTRATEUR, AUTHORITIES.ADMIN]),
  );

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const templateEntity = useAppSelector(state => state.template.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="templateDetailsHeading">Template</h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="libelle">Libelle</span>
          </dt>
          <dd>{templateEntity.libelle}</dd>
          <dt>
            <span id="description">Description</span>
          </dt>
          <dd>{templateEntity.description}</dd>
          <dt>
            <span id="imageHeigth">Image Heigth</span>
          </dt>
          <dd>{templateEntity.imageHeigth}</dd>
          <dt>
            <span id="imageWidth">Image Width</span>
          </dt>
          <dd>{templateEntity.imageWidth}</dd>
          <dt>
            <span id="imageX">Image X</span>
          </dt>
          <dd>{templateEntity.imageX}</dd>
          <dt>
            <span id="imageY">Image Y</span>
          </dt>
          <dd>{templateEntity.imageY}</dd>
          <dt>
            <span id="matriculeX">Matricule X</span>
          </dt>
          <dd>{templateEntity.matriculeX}</dd>
          <dt>
            <span id="matriculeY">Matricule Y</span>
          </dt>
          <dd>{templateEntity.matriculeY}</dd>
          <dt>
            <span id="membreX">Membre X</span>
          </dt>
          <dd>{templateEntity.membreX}</dd>
          <dt>
            <span id="membreY">Membre Y</span>
          </dt>
          <dd>{templateEntity.membreY}</dd>
          <dt>
            <span id="nomX">Nom X</span>
          </dt>
          <dd>{templateEntity.nomX}</dd>
          <dt>
            <span id="nomY">Nom Y</span>
          </dt>
          <dd>{templateEntity.nomY}</dd>
          <dt>
            <span id="prenomX">Prenom X</span>
          </dt>
          <dd>{templateEntity.prenomX}</dd>
          <dt>
            <span id="prenomY">Prenom Y</span>
          </dt>
          <dd>{templateEntity.prenomY}</dd>
          <dt>
            <span id="qrHeight">Qr Height</span>
          </dt>
          <dd>{templateEntity.qrHeight}</dd>
          <dt>
            <span id="qrWidth">Qr Width</span>
          </dt>
          <dd>{templateEntity.qrWidth}</dd>
          <dt>
            <span id="qrX">Qr X</span>
          </dt>
          <dd>{templateEntity.qrX}</dd>
          <dt>
            <span id="qrY">Qr Y</span>
          </dt>
          <dd>{templateEntity.qrY}</dd>
          <dt>
            <span id="dateDelivranceX">Date Delivrance X</span>
          </dt>
          <dd>{templateEntity.dateDelivranceX}</dd>
          <dt>
            <span id="dateDelivranceY">Date Delivrance Y</span>
          </dt>
          <dd>{templateEntity.dateDelivranceY}</dd>
          <dt>
            <span id="dateExpirationX">Date Expiration X</span>
          </dt>
          <dd>{templateEntity.dateExpirationX}</dd>
          <dt>
            <span id="dateExpirationY">Date Expiration Y</span>
          </dt>
          <dd>{templateEntity.dateExpirationY}</dd>
          <dt>
            <span id="sampleLargeur">Sample Largeur</span>
          </dt>
          <dd>{templateEntity.sampleLargeur}</dd>
          <dt>
            <span id="sampleLongueur">Sample Longueur</span>
          </dt>
          <dd>{templateEntity.sampleLongueur}</dd>
          <dt>
            <span id="pdfContentRecto">Pdf Content Recto</span>
          </dt>
          <dd>
            {templateEntity.pdfContentRecto ? (
              <div>
                {templateEntity.pdfContentRectoContentType ? (
                  <a onClick={openFile(templateEntity.pdfContentRectoContentType, templateEntity.pdfContentRecto)}>Ouvrir&nbsp;</a>
                ) : null}
                <span>
                  {templateEntity.pdfContentRectoContentType}, {byteSize(templateEntity.pdfContentRecto)}
                </span>
              </div>
            ) : null}
          </dd>
          <dt>
            <span id="pdfContentVerso">Pdf Content Verso</span>
          </dt>
          <dd>
            {templateEntity.pdfContentVerso ? (
              <div>
                {templateEntity.pdfContentVersoContentType ? (
                  <a onClick={openFile(templateEntity.pdfContentVersoContentType, templateEntity.pdfContentVerso)}>Ouvrir&nbsp;</a>
                ) : null}
                <span>
                  {templateEntity.pdfContentVersoContentType}, {byteSize(templateEntity.pdfContentVerso)}
                </span>
              </div>
            ) : null}
          </dd>
          <dt>
            <span id="createdBy">Created By</span>
          </dt>
          <dd>{templateEntity.createdBy}</dd>
          <dt>
            <span id="createdDate">Created Date</span>
          </dt>
          <dd>
            {templateEntity.createdDate ? <TextFormat value={templateEntity.createdDate} type="date" format={APP_DATE_FORMAT} /> : null}
          </dd>
          <dt>
            <span id="lastModifiedBy">Last Modified By</span>
          </dt>
          <dd>{templateEntity.lastModifiedBy}</dd>
          <dt>
            <span id="lastModifiedDate">Date de la dernière modifcation</span>
          </dt>
          <dd>
            {templateEntity.lastModifiedDate ? (
              <TextFormat value={templateEntity.lastModifiedDate} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
        </dl>
        <Button tag={Link} to="/template-my-suffix" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Retour</span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/template-my-suffix/${templateEntity.libelle}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Editer</span>
        </Button>
      </Col>
    </Row>
  );
};

export default TemplateMySuffixDetail;
