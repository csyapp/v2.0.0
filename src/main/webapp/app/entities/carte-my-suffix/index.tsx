import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import CarteMySuffix from './carte-my-suffix';
import CarteMySuffixDetail from './carte-my-suffix-detail';
import CarteMySuffixUpdate from './carte-my-suffix-update';
import CarteMySuffixDeleteDialog from './carte-my-suffix-delete-dialog';
import CarteMySuffixPrintDialog from './carte-my-suffix-print-dialog';

const CarteMySuffixRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<CarteMySuffix />} />
    <Route path="new" element={<CarteMySuffixUpdate />} />
    <Route path="/api/cartes/pdf" element={<CarteMySuffixPrintDialog />} />
    <Route path=":id">
      <Route index element={<CarteMySuffixDetail />} />
      <Route path="edit" element={<CarteMySuffixUpdate />} />
      <Route path="delete" element={<CarteMySuffixDeleteDialog />} />
      <Route path="print" element={<CarteMySuffixPrintDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default CarteMySuffixRoutes;
