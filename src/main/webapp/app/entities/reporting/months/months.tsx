import React, { useEffect, useState } from 'react';
import { BarChart, Bar, XAxis, YAxis, CartesianGrid, Tooltip, Legend, ResponsiveContainer } from 'recharts';
import { Card, CardBody, CardHeader } from 'reactstrap';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getReportingMois } from 'app/entities/reporting/reporting-mois-suffix.reducer';
import { ValidatedField, ValidatedForm } from 'react-jhipster';
import { getTodayYear } from 'app/shared/util/date-utils';
import { booleanMap } from 'app/shared/reducers/reducer.utils';

export const MonthsPage = () => {
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
  const [annee, setAnnee] = useState(getTodayYear);
  const [isImprime, setIsImprime] = useState('Oui');
  const reportingList = useAppSelector(state => state.reportingMois.entities);
  const getAllEntities = () => {
    dispatch(
      getReportingMois({
        anneeInf: annee,
        isImprime,
      }),
    );
  };
  useEffect(() => {
    getAllEntities();
  }, [annee, isImprime]);

  const handleInputChange = (e: { target: { name: any; value: any } }) => {
    const { name, value } = e.target;
    if (name === 'annee') {
      setAnnee(value);
    } else if (name === 'isImprime') {
      setIsImprime(value);
    }
  };
  const handleSubmit = (e: { preventDefault: () => void }) => {
    e.preventDefault();
    getAllEntities();
  };

  return (
    <Card>
      <CardHeader>
        <ValidatedForm onSubmit={handleSubmit}>
          <div style={{ display: 'flex', gap: '1rem', alignItems: 'center' }}>
            <ValidatedField
              id="reporting-mois-annee"
              name="annee"
              data-cy="annee"
              label="Année"
              type="number"
              value={annee}
              onChange={handleInputChange}
            />
            <ValidatedField
              id="reporting-mois-isImprime"
              name="isImprime"
              data-cy="isImprime"
              label=" Imprimé?"
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
export default MonthsPage;
