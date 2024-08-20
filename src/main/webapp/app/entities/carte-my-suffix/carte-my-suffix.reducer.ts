import axios from 'axios';
import { createAsyncThunk, isFulfilled, isPending } from '@reduxjs/toolkit';
import { cleanEntity } from 'app/shared/util/entity-utils';
import { IQueryParams, createEntitySlice, EntityState, serializeAxiosError } from 'app/shared/reducers/reducer.utils';
import { ICarteMySuffix, defaultValue } from 'app/shared/model/carte-my-suffix.model';

const initialState: EntityState<ICarteMySuffix> = {
  loading: false,
  errorMessage: null,
  entities: [],
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false,
};

const apiUrl = 'api/cartes';
const apiUrlPdf = 'api/cartes/pdf';
const apiUrlSearch = 'api/cartes/search';

// Actions
export const getEntities = createAsyncThunk('carte/fetch_entity_list', async ({ page, size, sort }: IQueryParams) => {
  const requestUrl = `${apiUrl}?${sort ? `page=${page}&size=${size}&sort=${sort}&` : ''}cacheBuster=${new Date().getTime()}`;
  return axios.get<ICarteMySuffix[]>(requestUrl);
});

export const getSearchedEntities = createAsyncThunk('carte/fetch_entity_list', async ({ page, size, sort, searchKey }: IQueryParams) => {
  let requestUrl = `${apiUrlSearch}/`;
  searchKey = searchKey.replace(/[./%#;,><?]/g, ' ');
  if (searchKey == null || Object.keys(searchKey).length === 0) {
    requestUrl += `a?`;
  } else {
    requestUrl += `${searchKey}?`;
  }
  requestUrl += `&${sort ? `page=${page}&size=${size}` : ''}`;
  requestUrl += `cacheBuster=${new Date().getTime()}`;
  return axios.get<ICarteMySuffix[]>(requestUrl);
});

export const getPdfArray = createAsyncThunk(
  'carte/',
  async (id: number, thunkAPI) => {
    const requestUrl = `${apiUrlPdf}/${id}`;
    try {
      const response = await axios.get(requestUrl, {
        responseType: 'arraybuffer',
      });
      const base64Data = btoa(new Uint8Array(response.data).reduce((data, byte) => data + String.fromCharCode(byte), ''));
      const pdfUrl = `data:application/pdf;base64,${base64Data}`;
      return { pdfUrl };
    } catch (error) {
      return thunkAPI.rejectWithValue(error.response?.data);
    }
  },
  { serializeError: serializeAxiosError },
);

export const getEntity = createAsyncThunk(
  'carte/fetch_entity',
  async (id: string | number) => {
    const requestUrl = `${apiUrl}/${id}`;
    return axios.get<ICarteMySuffix>(requestUrl);
  },
  { serializeError: serializeAxiosError },
);

export const createEntity = createAsyncThunk(
  'carte/create_entity',
  async (entity: ICarteMySuffix, thunkAPI) => {
    const result = await axios.post<ICarteMySuffix>(apiUrl, cleanEntity(entity));
    thunkAPI.dispatch(getEntities({}));
    return result;
  },
  { serializeError: serializeAxiosError },
);

export const updateEntity = createAsyncThunk(
  'carte/update_entity',
  async (entity: ICarteMySuffix, thunkAPI) => {
    const result = await axios.put<ICarteMySuffix>(`${apiUrl}/${entity.matricule}`, cleanEntity(entity));
    thunkAPI.dispatch(getEntities({}));
    return result;
  },
  { serializeError: serializeAxiosError },
);

const apiUrlNotify = 'api/cartes/notify';

export const notifyEntity = createAsyncThunk(
  'carte/',
  async (id: string, thunkAPI) => {
    const requestUrl = `${apiUrlNotify}/${id}`;
    try {
      await axios.get(requestUrl);
      thunkAPI.dispatch(getEntities({}));
    } catch (error) {
      return thunkAPI.rejectWithValue(error.response?.data);
    }
  },
  { serializeError: serializeAxiosError },
);

export const partialUpdateEntity = createAsyncThunk(
  'carte/partial_update_entity',
  async (entity: ICarteMySuffix, thunkAPI) => {
    const result = await axios.patch<ICarteMySuffix>(`${apiUrl}/${entity.matricule}`, cleanEntity(entity));
    thunkAPI.dispatch(getEntities({}));
    return result;
  },
  { serializeError: serializeAxiosError },
);

export const deleteEntity = createAsyncThunk(
  'carte/delete_entity',
  async (id: string | number, thunkAPI) => {
    const requestUrl = `${apiUrl}/${id}`;
    const result = await axios.delete<ICarteMySuffix>(requestUrl);
    thunkAPI.dispatch(getEntities({}));
    return result;
  },
  { serializeError: serializeAxiosError },
);

// slice
export const CarteMySuffixSlice = createEntitySlice({
  name: 'carte',
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

export const { reset } = CarteMySuffixSlice.actions;

// Reducer
export default CarteMySuffixSlice.reducer;
