import React, { useEffect, useState } from 'react';
import { useLocation, useNavigate, useParams } from 'react-router-dom';
import { Modal, ModalHeader, ModalBody, ModalFooter, Button } from 'reactstrap';

import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { useAppDispatch, useAppSelector } from 'app/config/store';
import { pdfjs } from 'react-pdf';
import PdfViewer from 'app/entities/carte-my-suffix/carte-my-suffix-pdfview';
import { deleteEntity, getEntity, notifyEntity } from 'app/entities/carte-my-suffix/carte-my-suffix.reducer';

pdfjs.GlobalWorkerOptions.workerSrc = new URL('pdfjs-dist/build/pdf.worker.min.js', import.meta.url).toString();
export const CarteMySuffixPrintDialog = () => {
  const dispatch = useAppDispatch();
  useAppDispatch();
  const pageLocation = useLocation();
  const navigate = useNavigate();
  const { id } = useParams<'id'>();

  const [loadModal, setLoadModal] = useState(false);
  const carteEntity = useAppSelector(state => state.carte.entity);
  const updateSuccess = useAppSelector(state => state.carte.updateSuccess);
  const handleClose = () => {
    navigate('/carte-my-suffix' + pageLocation.search);
  };

  const notifySupporter = () => {
    dispatch(notifyEntity(id));
    handleClose();
  };

  useEffect(() => {
    if (updateSuccess && loadModal) {
      handleClose();
      notifySupporter();
      setLoadModal(false);
    }
  }, [updateSuccess]);
  return (
    <Modal isOpen toggle={handleClose}>
      <ModalHeader toggle={handleClose} data-cy="carteDeleteDialogHeading">
        Carte à imprimer
      </ModalHeader>

      <ModalBody id="cscApp.carte.delete.question">
        <Translate contentKey="cscApp.carte.print.question" interpolate={{ id: carteEntity.matricule }}>
          Are you sure you want to print this Carte?
        </Translate>
        <PdfViewer id={id} />
      </ModalBody>
      <ModalFooter>
        <div className="d-flex justify-content-lg-center">
          <Button className="me-1" color="danger" onClick={notifySupporter}>
            <FontAwesomeIcon icon="envelope" />
            &nbsp; Informer le Supporter
          </Button>
        </div>
      </ModalFooter>
    </Modal>
  );
};

export default CarteMySuffixPrintDialog;
