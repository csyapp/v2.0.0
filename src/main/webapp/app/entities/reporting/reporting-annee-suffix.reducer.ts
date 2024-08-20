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

const apiUrlReportingAnnee = 'api/reporting/annee';

// Actions
export const getReportingAnnee = createAsyncThunk('carte/reporting_anne', async ({ anneeInf, isImprime }: IQueryReportParams) => {
  let requestUrl = `${apiUrlReportingAnnee}/` + `${anneeInf}`;
  if (isImprime.toLowerCase() === 'Non'.toLowerCase()) {
    requestUrl += `/` + `${isImprime}`;
  }
  return axios.get<IReportingMySuffix[]>(requestUrl);
});

// slice
export const ReportingAnneSuffixSlice = createEntitySlice({
  name: 'reportingAnne',
  initialState,
  extraReducers(builder) {
    builder
      .addMatcher(isFulfilled(getReportingAnnee), (state, action) => {
        const { data, headers } = action.payload;
        return {
          ...state,
          loading: false,
          entities: data,
          totalItems: parseInt(headers['x-total-count'], 10),
        };
      })
      .addMatcher(isPending(getReportingAnnee), state => {
        state.errorMessage = null;
        state.updateSuccess = false;
        state.loading = true;
      });
  },
});

export const { reset } = ReportingAnneSuffixSlice.actions;

// Reducer
export default ReportingAnneSuffixSlice.reducer;
