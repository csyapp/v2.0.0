import axios from 'axios';
import { createAsyncThunk, isFulfilled, isPending } from '@reduxjs/toolkit';
import { IQueryReportParams, createEntitySlice, EntityState } from 'app/shared/reducers/reducer.utils';

import { IReportingMySuffix, defaultValue } from 'app/shared/model/reporting-my-suffix.model';

const initialState: EntityState<IReportingMySuffix> = {
  loading: false,
  errorMessage: null,
  entities: [],
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false,
};

const apiUrlReportingJour = 'api/reporting/jour';

// Actions
export const getReportingJour = createAsyncThunk('carte/reporting_jour', async ({ mois, anneeInf, isImprime }: IQueryReportParams) => {
  let requestUrl = `${apiUrlReportingJour}/` + `${anneeInf}` + `/` + `${mois}`;
  if (isImprime.toLowerCase() === 'Non'.toLowerCase()) {
    requestUrl += `/` + `${isImprime}`;
  }
  return axios.get<IReportingMySuffix[]>(requestUrl);
});

// slice
export const ReportingMySuffixSlice = createEntitySlice({
  name: 'reporting',
  initialState,
  extraReducers(builder) {
    builder
      .addMatcher(isFulfilled(getReportingJour), (state, action) => {
        const { data, headers } = action.payload;
        return {
          ...state,
          loading: false,
          entities: data,
          totalItems: parseInt(headers['x-total-count'], 10),
        };
      })
      .addMatcher(isPending(getReportingJour), state => {
        state.errorMessage = null;
        state.updateSuccess = false;
        state.loading = true;
      });
  },
});

export const { reset } = ReportingMySuffixSlice.actions;

// Reducer
export default ReportingMySuffixSlice.reducer;
