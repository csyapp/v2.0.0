import React, { useEffect, useState } from 'react';
import { BarChart, Bar, XAxis, YAxis, CartesianGrid, Tooltip, Legend, ResponsiveContainer } from 'recharts';
import { Card, CardBody, CardHeader } from 'reactstrap';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getReportingJour } from 'app/entities/reporting/reporting-my-suffix.reducer';
import { ValidatedField, ValidatedForm } from 'react-jhipster';
import { getMonthsInFrench, getTodayMonth, getTodayYear } from 'app/shared/util/date-utils';

export const DaysPage = () => {
  const getIntroOfPage = () => {
    return 'Carte(s) générée(s)';
  };

  const CustomTooltip = ({ active, payload, label }: any) => {
    if (active && payload && payload.length) {
      return (
        <div className="custom-tooltip">
          <p className="label">{`${label} : ${payload[0].value}`}</p>
          <p className="intro">{getIntroOfPage()}</p>
        </div>
      );
    }
    return null;
  };

  const dispatch = useAppDispatch();

  let [mois, setMois] = useState(getTodayMonth());
  let [annee, setAnnee] = useState(getTodayYear);
  let [isImprime, setIsImprime] = useState('Oui');
  const monthsMap = getMonthsInFrench();
  const booleanMap: Map<number, string> = new Map([
    [1, 'Oui'],
    [2, 'Non'],
  ]);
  const reportingList = useAppSelector(state => state.reporting.entities);

  const getAllEntities = (mois: string, annee: string, isImprime: string) => {
    dispatch(
      getReportingJour({
        mois: mois,
        anneeInf: annee,
        isImprime: isImprime,
      }),
    );
  };

  useEffect(() => {
    getAllEntities(mois, annee, isImprime);
  }, [mois, annee, isImprime]);

  const handleInputChange = (e: { target: { name: any; value: any } }) => {
    const { name, value } = e.target;
    console.log('==>>> handleInputChange ....');
    if (name === 'mois') {
      setMois(value);
    } else if (name === 'annee') {
      setAnnee(value);
    } else if (name === 'isImprime') {
      console.log('==>>> handleInputChange isImprime');
      setIsImprime(value);
    }
  };

  const handleSubmit = (e: { preventDefault: () => void }) => {
    e.preventDefault();
    getAllEntities(mois, annee, isImprime);
  };

  return (
    <Card>
      <CardHeader>
        <ValidatedForm onSubmit={handleSubmit}>
          <div style={{ display: 'flex', gap: '1rem', alignItems: 'center' }}>
            <ValidatedField
              id="reporting-days-mois"
              name="mois"
              data-cy="mois"
              label="Mois"
              type="select"
              value={mois}
              onChange={handleInputChange}
            >
              <option value="" key="0" />
              {Object.entries(monthsMap).map(([key, value]) => (
                <option value={key} key={key}>
                  {key}: {value}
                </option>
              ))}
            </ValidatedField>

            <ValidatedField
              id="reporting-days-annee"
              name="annee"
              data-cy="annee"
              label="Année"
              type="text"
              value={annee}
              onChange={handleInputChange}
            />

            <ValidatedField
              id="reporting-days-isImprime"
              name="isImprime"
              data-cy="isImprime"
              label=" Imprimé ?"
              type="select"
              value={isImprime}
              onChange={handleInputChange}
            >
              <option value="" key="0" />
              {[...booleanMap.entries()].map(([key, value]) => (
                <option value={value} key={key}>
                  {value}
                </option>
              ))}
            </ValidatedField>
          </div>
        </ValidatedForm>
      </CardHeader>
      <CardBody>
        <ResponsiveContainer width={'100%'} height={300}>
          <BarChart width={24} height={8118} data={reportingList}>
            <CartesianGrid strokeDasharray="3 3" />
            <XAxis dataKey="nom" />
            <YAxis />
            <Tooltip content={<CustomTooltip />} />
            <Legend />
            <Bar dataKey="quantite" barSize={38} fill="#8884d8" />
          </BarChart>
        </ResponsiveContainer>
      </CardBody>
    </Card>
  );
};

export default DaysPage;
