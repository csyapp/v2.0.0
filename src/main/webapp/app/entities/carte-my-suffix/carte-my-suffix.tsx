import React, { useState, useEffect } from 'react';
import { Link, useLocation, useNavigate } from 'react-router-dom';
import { Button, Table } from 'reactstrap';
import { openFile, byteSize, Translate, TextFormat, getPaginationState, JhiPagination, JhiItemCount } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faSort, faSortUp, faSortDown } from '@fortawesome/free-solid-svg-icons';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT, AUTHORITIES } from 'app/config/constants';
import { ASC, DESC, ITEMS_PER_PAGE, SORT } from 'app/shared/util/pagination.constants';
import { overridePaginationStateWithQueryParams } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntities, getSearchedEntities } from './carte-my-suffix.reducer';
import { hasAnyAuthority } from 'app/shared/auth/private-route';

export const CarteMySuffix = () => {
  const dispatch = useAppDispatch();

  const pageLocation = useLocation();
  const navigate = useNavigate();

  const [paginationState, setPaginationState] = useState(
    overridePaginationStateWithQueryParams(getPaginationState(pageLocation, ITEMS_PER_PAGE, 'matricule'), pageLocation.search),
  );

  const carteList = useAppSelector(state => state.carte.entities);
  const loading = useAppSelector(state => state.carte.loading);
  const totalItems = useAppSelector(state => state.carte.totalItems);

  const isAdministrateur = useAppSelector(state =>
    hasAnyAuthority(state.authentication.account.authorities, [AUTHORITIES.ADMIN, AUTHORITIES.ADMINISTRATEUR]),
  );

  const [searchKeyy, setSearchKeyy] = useState('');

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

  const sort = p => () => {
    setPaginationState({
      ...paginationState,
      order: paginationState.order === ASC ? DESC : ASC,
      sort: p,
    });
  };

  const handlePagination = currentPage =>
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
      <h2 id="carte-my-suffix-heading" data-cy="CarteHeading">
        Cartes
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

          <Link to="/carte-my-suffix/new" className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp; Créer
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {carteList && carteList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th className="hand" onClick={sort('matricule')}>
                  Matricule <FontAwesomeIcon icon={getSortIconByFieldName('matricule')} />
                </th>
                <th className="hand" onClick={sort('name')}>
                  Nom <FontAwesomeIcon icon={getSortIconByFieldName('name')} />
                </th>
                <th className="hand" onClick={sort('surname')}>
                  Prénom <FontAwesomeIcon icon={getSortIconByFieldName('surname')} />
                </th>
                <th className="hand" onClick={sort('email')}>
                  Email <FontAwesomeIcon icon={getSortIconByFieldName('email')} />
                </th>
                <th className="hand" onClick={sort('villeResidence')}>
                  Ville <FontAwesomeIcon icon={getSortIconByFieldName('villeResidence')} />
                </th>
                <th className="hand" onClick={sort('birthdate')}>
                  Date Naissance <FontAwesomeIcon icon={getSortIconByFieldName('birthdate')} />
                </th>
                <th className="hand" onClick={sort('telephone1')}>
                  Telephone 1 <FontAwesomeIcon icon={getSortIconByFieldName('telephone1')} />
                </th>
                <th className="hand" onClick={sort('telephone2')}>
                  Telephone 2 <FontAwesomeIcon icon={getSortIconByFieldName('telephone2')} />
                </th>
                <th className="hand" onClick={sort('isImprime')}>
                  Imprime? <FontAwesomeIcon icon={getSortIconByFieldName('isImprime')} />
                </th>
                <th className="hand" onClick={sort('dateDelivrance')}>
                  Date Delivrance <FontAwesomeIcon icon={getSortIconByFieldName('dateDelivrance')} />
                </th>
                <th className="hand" onClick={sort('dateExpiration')}>
                  Date Expiration <FontAwesomeIcon icon={getSortIconByFieldName('dateExpiration')} />
                </th>
                <th>
                  Type <FontAwesomeIcon icon="sort" />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {carteList.map((carte, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`/carte-my-suffix/${carte.matricule}`} color="link" size="sm">
                      {carte.matricule}
                    </Button>
                  </td>
                  <td>{carte.name}</td>
                  <td>{carte.surname}</td>
                  <td>{carte.email}</td>
                  <td>{carte.villeResidence}</td>
                  <td>{carte.birthdate ? <TextFormat type="date" value={carte.birthdate} format={APP_DATE_FORMAT} /> : null}</td>
                  <td>{carte.telephone1}</td>
                  <td>{carte.telephone2}</td>
                  <td>{carte.isImprime ? 'true' : 'false'}</td>
                  <td>
                    {carte.dateDelivrance ? <TextFormat type="date" value={carte.dateDelivrance} format={APP_LOCAL_DATE_FORMAT} /> : null}
                  </td>
                  <td>
                    {carte.dateExpiration ? <TextFormat type="date" value={carte.dateExpiration} format={APP_LOCAL_DATE_FORMAT} /> : null}
                  </td>
                  <td>
                    {carte.template ? <Link to={`/template-my-suffix/${carte.template.libelle}`}>{carte.template.libelle}</Link> : ''}
                  </td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button
                        onClick={() => (window.location.href = `/carte-my-suffix/${carte.matricule}/print?`)}
                        color="btn btn-success"
                        size="sm"
                        data-cy="entityDetailsButton"
                      >
                        <FontAwesomeIcon icon="print" /> <span className="d-none d-md-inline">Imprimer</span>
                      </Button>

                      <Button tag={Link} to={`/carte-my-suffix/${carte.matricule}`} color="info" size="sm" data-cy="entityDetailsButton">
                        <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">Voir</span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`/carte-my-suffix/${carte.matricule}/edit?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`}
                        color="btn btn-warning"
                        size="sm"
                        data-cy="entityEditButton"
                        disabled={!isAdministrateur}
                      >
                        <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Editer</span>
                      </Button>
                      <Button
                        onClick={() =>
                          (window.location.href = `/carte-my-suffix/${carte.matricule}/delete?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`)
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
          !loading && <div className="alert alert-warning">Aucun Carte trouvé</div>
        )}
      </div>
      {totalItems ? (
        <div className={carteList && carteList.length > 0 ? '' : 'd-none'}>
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

export default CarteMySuffix;
