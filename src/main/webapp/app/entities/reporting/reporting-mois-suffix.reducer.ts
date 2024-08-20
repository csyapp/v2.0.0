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

const apiUrlReportingMois = 'api/reporting/mois';

// Actions
export const getReportingMois = createAsyncThunk('carte/reporting_mois', async ({ anneeInf, isImprime }: IQueryReportParams) => {
  let requestUrl = `${apiUrlReportingMois}/` + `${anneeInf}`;
  if (isImprime.toLowerCase() === 'Non'.toLowerCase()) {
    requestUrl += `/` + `${isImprime}`;
  }
  return axios.get<IReportingMySuffix[]>(requestUrl);
});

// slice
export const ReportingMoisSuffixSlice = createEntitySlice({
  name: 'reportingMois',
  initialState,
  extraReducers(builder) {
    builder
      .addMatcher(isFulfilled(getReportingMois), (state, action) => {
        const { data, headers } = action.payload;
        return {
          ...state,
          loading: false,
          entities: data,
          totalItems: parseInt(headers['x-total-count'], 10),
        };
      })
      .addMatcher(isPending(getReportingMois), state => {
        state.errorMessage = null;
        state.updateSuccess = false;
        state.loading = true;
      });
  },
});

export const { reset } = ReportingMoisSuffixSlice.actions;

// Reducer
export default ReportingMoisSuffixSlice.reducer;
