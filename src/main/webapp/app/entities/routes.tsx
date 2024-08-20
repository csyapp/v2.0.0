import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import TemplateMySuffix from './template-my-suffix';
import CarteMySuffix from './carte-my-suffix';
import ReportingRoutes from './reporting';
/* jhipster-needle-add-route-import - JHipster will add routes here */

export default () => {
  return (
    <div>
      <ErrorBoundaryRoutes>
        {/* prettier-ignore */}
        <Route path="template-my-suffix/*" element={<TemplateMySuffix />} />
        <Route path="carte-my-suffix/*" element={<CarteMySuffix />} />
        <Route path="reporting/*" element={<ReportingRoutes />} />
        {/* jhipster-needle-add-route-path - JHipster will add routes here */}
      </ErrorBoundaryRoutes>
    </div>
  );
};
