import axios from 'axios';
import { createAsyncThunk, isFulfilled, isPending } from '@reduxjs/toolkit';
import { cleanEntity } from 'app/shared/util/entity-utils';
import { IQueryParams, createEntitySlice, EntityState, serializeAxiosError } from 'app/shared/reducers/reducer.utils';
import { ITemplateMySuffix, defaultValue } from 'app/shared/model/template-my-suffix.model';

const initialState: EntityState<ITemplateMySuffix> = {
  loading: false,
  errorMessage: null,
  entities: [],
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false,
};

const apiUrl = 'api/templates';
const apiUrlSearch = 'api/templates/search';

// Actions

export const getEntities = createAsyncThunk(
  'template/fetch_entity_list',
  async ({ page, size, sort }: IQueryParams) => {
    const requestUrl = `${apiUrl}?${sort ? `page=${page}&size=${size}&sort=${sort}&` : ''}cacheBuster=${new Date().getTime()}`;
    return axios.get<ITemplateMySuffix[]>(requestUrl);
  },
  { serializeError: serializeAxiosError },
);

export const getEntity = createAsyncThunk(
  'template/fetch_entity',
  async (id: string | number) => {
    const requestUrl = `${apiUrl}/${id}`;
    return axios.get<ITemplateMySuffix>(requestUrl);
  },
  { serializeError: serializeAxiosError },
);

export const getSearchedEntities = createAsyncThunk('template/fetch_entity_list', async ({ page, size, sort, searchKey }: IQueryParams) => {
  let requestUrl = `${apiUrlSearch}/`;
  searchKey = searchKey.replace(/[./%#;,><?]/g, ' ');
  if (searchKey == null || Object.keys(searchKey).length === 0) {
    requestUrl += `a?`;
  } else {
    requestUrl += `${searchKey}?`;
  }
  requestUrl += `&${sort ? `page=${page}&size=${size}` : ''}`;
  requestUrl += `cacheBuster=${new Date().getTime()}`;
  return axios.get<ITemplateMySuffix[]>(requestUrl);
});

export const createEntity = createAsyncThunk(
  'template/create_entity',
  async (entity: ITemplateMySuffix, thunkAPI) => {
    const result = await axios.post<ITemplateMySuffix>(apiUrl, cleanEntity(entity));
    thunkAPI.dispatch(getEntities({}));
    return result;
  },
  { serializeError: serializeAxiosError },
);

export const updateEntity = createAsyncThunk(
  'template/update_entity',
  async (entity: ITemplateMySuffix, thunkAPI) => {
    const result = await axios.put<ITemplateMySuffix>(`${apiUrl}/${entity.libelle}`, cleanEntity(entity));
    thunkAPI.dispatch(getEntities({}));
    return result;
  },
  { serializeError: serializeAxiosError },
);

export const partialUpdateEntity = createAsyncThunk(
  'template/partial_update_entity',
  async (entity: ITemplateMySuffix, thunkAPI) => {
    const result = await axios.patch<ITemplateMySuffix>(`${apiUrl}/${entity.libelle}`, cleanEntity(entity));
    thunkAPI.dispatch(getEntities({}));
    return result;
  },
  { serializeError: serializeAxiosError },
);

export const deleteEntity = createAsyncThunk(
  'template/delete_entity',
  async (id: string | number, thunkAPI) => {
    const requestUrl = `${apiUrl}/${id}`;
    const result = await axios.delete<ITemplateMySuffix>(requestUrl);
    thunkAPI.dispatch(getEntities({}));
    return result;
  },
  { serializeError: serializeAxiosError },
);

// slice

export const TemplateMySuffixSlice = createEntitySlice({
  name: 'template',
  initialState,
  extraReducers(builder) {
    builder
      .addCase(getEntity.fulfilled, (state, action) => {
        state.loading = false;
        state.entity = action.payload.data;
      })
      .addCase(deleteEntity.fulfilled, state => {
        state.updating = false;
        state.updateSuccess = true;
        state.entity = {};
      })
      .addMatcher(isFulfilled(getEntities), (state, action) => {
        const { data, headers } = action.payload;

        return {
          ...state,
          loading: false,
          entities: data,
          totalItems: parseInt(headers['x-total-count'], 10),
        };
      })
      .addMatcher(isFulfilled(createEntity, updateEntity, partialUpdateEntity), (state, action) => {
        state.updating = false;
        state.loading = false;
        state.updateSuccess = true;
        state.entity = action.payload.data;
      })
      .addMatcher(isPending(getEntities, getEntity), state => {
        state.errorMessage = null;
        state.updateSuccess = false;
        state.loading = true;
      })
      .addMatcher(isPending(createEntity, updateEntity, partialUpdateEntity, deleteEntity), state => {
        state.errorMessage = null;
        state.updateSuccess = false;
        state.updating = true;
      });
  },
});

export const { reset } = TemplateMySuffixSlice.actions;

// Reducer
export default TemplateMySuffixSlice.reducer;
