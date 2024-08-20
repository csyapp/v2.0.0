import dayjs from 'dayjs';
import { ITemplateMySuffix } from 'app/shared/model/template-my-suffix.model';

export interface IReportingMySuffix {
  date?: dayjs.Dayjs | null;
  quantite?: number | null;
  nom?: string | null;
  month?: string | null;
  year?: string | null;
}

export const defaultValue: Readonly<IReportingMySuffix> = {
  year: '2023',
  month: '01',
};
