import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import Years from 'app/entities/reporting/years/years';
import Days from 'app/entities/reporting/days/days';
import Months from 'app/entities/reporting/months/months';
import ReportingMySuffix from 'app/entities/reporting/reporting-my-suffix';

const ReportingRoutes = () => (
  <div>
    <ErrorBoundaryRoutes>
      <Route index element={<ReportingMySuffix />} />
      <Route path="days" element={<Days />} />
      <Route path="months" element={<Months />} />
      <Route path="years" element={<Years />} />
    </ErrorBoundaryRoutes>
  </div>
);

export default ReportingRoutes;
