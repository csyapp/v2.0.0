import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import TemplateMySuffix from './template-my-suffix';
import TemplateMySuffixDetail from './template-my-suffix-detail';
import TemplateMySuffixUpdate from './template-my-suffix-update';
import TemplateMySuffixDeleteDialog from './template-my-suffix-delete-dialog';

const TemplateMySuffixRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<TemplateMySuffix />} />
    <Route path="new" element={<TemplateMySuffixUpdate />} />
    <Route path=":id">
      <Route index element={<TemplateMySuffixDetail />} />
      <Route path="edit" element={<TemplateMySuffixUpdate />} />
      <Route path="delete" element={<TemplateMySuffixDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default TemplateMySuffixRoutes;
