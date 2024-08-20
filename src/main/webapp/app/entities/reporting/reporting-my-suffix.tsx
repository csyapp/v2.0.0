import React, { useState, useEffect } from 'react';
import { hasAnyAuthority } from 'app/shared/auth/private-route';
import { Button, Table } from 'reactstrap';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import DaysPage from 'app/entities/reporting/days/days';
import Days from 'app/entities/reporting/days/days';
import { useAppDispatch, useAppSelector } from 'app/config/store';
import { AUTHORITIES } from 'app/config/constants';
import Years from 'app/entities/reporting/years/years';
import Months from 'app/entities/reporting/months/months';
import { ResponsiveContainer } from 'recharts';

export const ReportingMySuffix = () => {
  const dispatch = useAppDispatch();
  const [displayValue, setDisplayValue] = useState('1');
  return (
    <div>
      <h2 id="carte-my-suffix-heading" data-cy="CarteHeading">
        Reporting des cartes
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={e => setDisplayValue('1')}>
            Par Jours
          </Button>
          &nbsp;
          <Button className="me-2" color="info" onClick={e => setDisplayValue('2')}>
            Par Mois
          </Button>
          &nbsp;
          <Button className="me-2" color="info" onClick={e => setDisplayValue('3')}>
            Par Années
          </Button>
        </div>
      </h2>
      <div>
        {displayValue.startsWith('1') ? <th>Par jours</th> : displayValue.startsWith('2') ? <th>Par Mois </th> : <th>Par Année</th>}

        {displayValue.startsWith('1') ? <DaysPage /> : displayValue.startsWith('2') ? <Months /> : <Years />}
      </div>
    </div>
  );
};

export default ReportingMySuffix;
