import './home.scss';

import React from 'react';
import { Link } from 'react-router-dom';

import { Row, Col, Alert } from 'reactstrap';

import { useAppSelector } from 'app/config/store';

export const Home = () => {
  const account = useAppSelector(state => state.authentication.account);

  return (
    <Row>
      <Col md="3" className="pad">
        <span className="hipster rounded" />
      </Col>
      <Col md="9">
        <h1 className="display-4">Bienvenue, KPA KUM !!!</h1>
        <p className="lead">Ceci est votre page d&apos;accueil</p>
        {account?.login ? (
          <div>
            <Alert color="success">Vous êtes connecté en tant que &quot;{account.login}&quot;.</Alert>
          </div>
        ) : (
          <div>
            <Alert color="warning">
              Si vous voulez vous
              <span>&nbsp;</span>
              <Link to="/login" className="alert-link">
                connecter
              </Link>
              , vous pouvez utiliser les roles suivants :
              <br /> - ROLE_ADMINISTRATEUR (pour les opérations de monitoring et de forçage)
              <br /> - ROLE_IMPRIMEUR (pour les opérations créations/impression/consultation de cartes de supporters ).
            </Alert>

            <Alert color="warning">
              Vous n&apos;avez pas encore de compte ?&nbsp;
              <Link to="/account/register" className="alert-link">
                Créer un compte
              </Link>
            </Alert>
          </div>
        )}

        <p>Toutes les informations sur la Canon Sportif De Yaoundé:</p>

        <ul>
          <li>
            <a href="https://www.jhipster.tech/" target="_blank" rel="noopener noreferrer">
              Page d&apos;acceuil KPA KUM
            </a>
          </li>
          <li>
            <a href="#" target="_blank" rel="noopener noreferrer">
              Page facebook
            </a>
          </li>
          <li>
            <a href="#" target="_blank" rel="noopener noreferrer">
              Page Twitter
            </a>
          </li>
        </ul>
      </Col>
    </Row>
  );
};

export default Home;
