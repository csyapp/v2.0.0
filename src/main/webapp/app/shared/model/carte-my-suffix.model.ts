import dayjs from 'dayjs';
import { ITemplateMySuffix } from 'app/shared/model/template-my-suffix.model';

export interface ICarteMySuffix {
  matricule?: string;
  name?: string | null;
  surname?: string | null;
  email?: string | null;
  villeResidence?: string | null;
  birthdate?: dayjs.Dayjs | null;
  telephone1?: string | null;
  telephone2?: string | null;
  isImprime?: boolean | null;
  pictureExtension?: string | null;
  pictureContentType?: string | null;
  picture?: string | null;
  dateDelivrance?: dayjs.Dayjs | null;
  dateExpiration?: dayjs.Dayjs | null;
  createdBy?: string;
  createdDate?: dayjs.Dayjs;
  lastModifiedBy?: string;
  lastModifiedDate?: dayjs.Dayjs;
  template?: ITemplateMySuffix | null;
}

export const defaultValue: Readonly<ICarteMySuffix> = {
  isImprime: false,
};
