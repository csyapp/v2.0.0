import React, { useState, useEffect } from 'react';
import { Link, useLocation, useNavigate } from 'react-router-dom';
import { Button, Table } from 'reactstrap';
import { openFile, byteSize, TextFormat, getPaginationState, JhiPagination, JhiItemCount } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faSort, faSortUp, faSortDown } from '@fortawesome/free-solid-svg-icons';
import { APP_DATE_FORMAT, AUTHORITIES } from 'app/config/constants';
import { ASC, DESC, ITEMS_PER_PAGE, SORT } from 'app/shared/util/pagination.constants';
import { overridePaginationStateWithQueryParams } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntities, getSearchedEntities } from './template-my-suffix.reducer';
import { hasAnyAuthority } from 'app/shared/auth/private-route';

export const TemplateMySuffix = () => {
  const dispatch = useAppDispatch();

  const pageLocation = useLocation();
  const navigate = useNavigate();

  const [paginationState, setPaginationState] = useState(
    overridePaginationStateWithQueryParams(getPaginationState(pageLocation, ITEMS_PER_PAGE, 'libelle'), pageLocation.search),
  );

  const templateList = useAppSelector(state => state.template.entities);
  const loading = useAppSelector(state => state.template.loading);
  const totalItems = useAppSelector(state => state.template.totalItems);

  const [searchKeyy, setSearchKeyy] = useState('');
  const isAdministrateur = useAppSelector(state =>
    hasAnyAuthority(state.authentication.account.authorities, [AUTHORITIES.ADMINISTRATEUR, AUTHORITIES.ADMIN]),
  );

  const getAllEntities = () => {
    dispatch(
      getEntities({
        page: paginationState.activePage - 1,
        size: paginationState.itemsPerPage,
        sort: `${paginationState.sort},${paginationState.order}`,
      }),
    );
  };

  const getAllSearchedEntities = () => {
    dispatch(
      getSearchedEntities({
        page: paginationState.activePage - 1,
        size: paginationState.itemsPerPage,
        sort: `${paginationState.sort},${paginationState.order}`,
        searchKey: searchKeyy,
      }),
    );
  };

  const sortEntities = () => {
    getAllEntities();
    const endURL = `?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`;
    if (pageLocation.search !== endURL) {
      navigate(`${pageLocation.pathname}${endURL}`);
    }
  };

  const searchEntities = () => {
    getAllSearchedEntities();
    const endURL = `?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`;
    if (pageLocation.search !== endURL) {
      navigate(`${pageLocation.pathname}${endURL}`);
    }
  };

  useEffect(() => {
    sortEntities();
  }, [paginationState.activePage, paginationState.order, paginationState.sort]);

  useEffect(() => {
    searchEntities();
  }, [searchKeyy]);

  useEffect(() => {
    const params = new URLSearchParams(pageLocation.search);
    const page = params.get('page');
    const sort = params.get(SORT);
    if (page && sort) {
      const sortSplit = sort.split(',');
      setPaginationState({
        ...paginationState,
        activePage: +page,
        sort: sortSplit[0],
        order: sortSplit[1],
      });
    }
  }, [pageLocation.search]);

  const sort = (p: any) => () => {
    setPaginationState({
      ...paginationState,
      order: paginationState.order === ASC ? DESC : ASC,
      sort: p,
    });
  };

  const handlePagination = (currentPage: any) =>
    setPaginationState({
      ...paginationState,
      activePage: currentPage,
    });

  const handleSyncList = () => {
    sortEntities();
  };

  const getSortIconByFieldName = (fieldName: string) => {
    const sortFieldName = paginationState.sort;
    const order = paginationState.order;
    if (sortFieldName !== fieldName) {
      return faSort;
    } else {
      return order === ASC ? faSortUp : faSortDown;
    }
  };

  return (
    <div>
      <h2 id="template-my-suffix-heading" data-cy="TemplateHeading">
        Templates
        <div className="d-flex justify-content-end">
          <div className="container btn-lg">
            <input
              type="text"
              className="form-control"
              id="floatingInputGroup1"
              placeholder="Mot(s) clé(s)"
              value={searchKeyy}
              onChange={e => setSearchKeyy(e.target.value)}
            />
          </div>
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} /> Actualiser
          </Button>
          <Link
            to="/template-my-suffix/new"
            className="btn btn-primary jh-create-entity"
            id="jh-create-entity"
            data-cy="entityCreateButton"
          >
            <FontAwesomeIcon icon="plus" />
            &nbsp; Créer
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {templateList && templateList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th className="hand" onClick={sort('libelle')}>
                  Libelle <FontAwesomeIcon icon={getSortIconByFieldName('libelle')} />
                </th>
                <th className="hand" onClick={sort('imageHeigth')}>
                  Image Heigth <FontAwesomeIcon icon={getSortIconByFieldName('imageHeigth')} />
                </th>
                <th className="hand" onClick={sort('imageWidth')}>
                  Image Width <FontAwesomeIcon icon={getSortIconByFieldName('imageWidth')} />
                </th>
                <th className="hand" onClick={sort('imageX')}>
                  Image X <FontAwesomeIcon icon={getSortIconByFieldName('imageX')} />
                </th>
                <th className="hand" onClick={sort('imageY')}>
                  Image Y <FontAwesomeIcon icon={getSortIconByFieldName('imageY')} />
                </th>
                <th className="hand" onClick={sort('matriculeX')}>
                  Matricule X <FontAwesomeIcon icon={getSortIconByFieldName('matriculeX')} />
                </th>
                <th className="hand" onClick={sort('matriculeY')}>
                  Matricule Y <FontAwesomeIcon icon={getSortIconByFieldName('matriculeY')} />
                </th>
                <th className="hand" onClick={sort('membreX')}>
                  Membre X <FontAwesomeIcon icon={getSortIconByFieldName('membreX')} />
                </th>
                <th className="hand" onClick={sort('membreY')}>
                  Membre Y <FontAwesomeIcon icon={getSortIconByFieldName('membreY')} />
                </th>
                <th className="hand" onClick={sort('nomX')}>
                  Nom X <FontAwesomeIcon icon={getSortIconByFieldName('nomX')} />
                </th>
                <th className="hand" onClick={sort('nomY')}>
                  Nom Y <FontAwesomeIcon icon={getSortIconByFieldName('nomY')} />
                </th>
                <th className="hand" onClick={sort('prenomX')}>
                  Prenom X <FontAwesomeIcon icon={getSortIconByFieldName('prenomX')} />
                </th>
                <th className="hand" onClick={sort('prenomY')}>
                  Prenom Y <FontAwesomeIcon icon={getSortIconByFieldName('prenomY')} />
                </th>
                <th className="hand" onClick={sort('dateDelivranceX')}>
                  Date Delivrance X <FontAwesomeIcon icon={getSortIconByFieldName('dateDelivranceX')} />
                </th>
                <th className="hand" onClick={sort('dateDelivranceY')}>
                  Date Delivrance Y <FontAwesomeIcon icon={getSortIconByFieldName('dateDelivranceY')} />
                </th>
                <th className="hand" onClick={sort('dateExpirationX')}>
                  Date Expiration X <FontAwesomeIcon icon={getSortIconByFieldName('dateExpirationX')} />
                </th>
                <th className="hand" onClick={sort('dateExpirationY')}>
                  Date Expiration Y <FontAwesomeIcon icon={getSortIconByFieldName('dateExpirationY')} />
                </th>
                <th className="hand" onClick={sort('sampleLargeur')}>
                  Largeur <FontAwesomeIcon icon={getSortIconByFieldName('sampleLargeur')} />
                </th>
                <th className="hand" onClick={sort('sampleLongueur')}>
                  Longueur <FontAwesomeIcon icon={getSortIconByFieldName('sampleLongueur')} />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {templateList.map((template, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`/template-my-suffix/${template.libelle}`} color="link" size="sm">
                      {template.libelle}
                    </Button>
                  </td>
                  <td>{template.imageHeigth}</td>
                  <td>{template.imageWidth}</td>
                  <td>{template.imageX}</td>
                  <td>{template.imageY}</td>
                  <td>{template.matriculeX}</td>
                  <td>{template.matriculeY}</td>
                  <td>{template.membreX}</td>
                  <td>{template.membreY}</td>
                  <td>{template.nomX}</td>
                  <td>{template.nomY}</td>
                  <td>{template.prenomX}</td>
                  <td>{template.prenomY}</td>
                  <td>{template.dateDelivranceX}</td>
                  <td>{template.dateDelivranceY}</td>
                  <td>{template.dateExpirationX}</td>
                  <td>{template.dateExpirationY}</td>
                  <td>{template.sampleLargeur}</td>
                  <td>{template.sampleLongueur}</td>

                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button
                        tag={Link}
                        to={`/template-my-suffix/${template.libelle}`}
                        color="info"
                        size="sm"
                        data-cy="entityDetailsButton"
                      >
                        <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">Voir</span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`/template-my-suffix/${template.libelle}/edit?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`}
                        color="primary"
                        size="sm"
                        data-cy="entityEditButton"
                        disabled={!isAdministrateur}
                      >
                        <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Editer</span>
                      </Button>
                      <Button
                        onClick={() =>
                          (window.location.href = `/template-my-suffix/${template.libelle}/delete?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`)
                        }
                        color="danger"
                        size="sm"
                        data-cy="entityDeleteButton"
                        disabled={!isAdministrateur}
                      >
                        <FontAwesomeIcon icon="trash" /> <span className="d-none d-md-inline">Supprimer</span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        ) : (
          !loading && <div className="alert alert-warning">Aucun Template trouvé</div>
        )}
      </div>
      {totalItems ? (
        <div className={templateList && templateList.length > 0 ? '' : 'd-none'}>
          <div className="justify-content-center d-flex">
            <JhiItemCount page={paginationState.activePage} total={totalItems} itemsPerPage={paginationState.itemsPerPage} />
          </div>
          <div className="justify-content-center d-flex">
            <JhiPagination
              activePage={paginationState.activePage}
              onSelect={handlePagination}
              maxButtons={5}
              itemsPerPage={paginationState.itemsPerPage}
              totalItems={totalItems}
            />
          </div>
        </div>
      ) : (
        ''
      )}
    </div>
  );
};

export default TemplateMySuffix;
