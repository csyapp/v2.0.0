import dayjs from 'dayjs';

export interface ITemplateMySuffix {
  libelle?: string;
  description?: string | null;
  imageHeigth?: number | null;
  imageWidth?: number | null;
  imageX?: number | null;
  imageY?: number | null;
  matriculeX?: number | null;
  matriculeY?: number | null;
  membreX?: number | null;
  membreY?: number | null;
  nomX?: number | null;
  nomY?: number | null;
  prenomX?: number | null;
  prenomY?: number | null;
  qrHeight?: number | null;
  qrWidth?: number | null;
  qrX?: number | null;
  qrY?: number | null;
  dateDelivranceX?: number | null;
  dateDelivranceY?: number | null;
  dateExpirationX?: number | null;
  dateExpirationY?: number | null;
  sampleLargeur?: number | null;
  sampleLongueur?: number | null;
  pdfContentRectoContentType?: string | null;
  pdfContentRecto?: string | null;
  pdfContentVersoContentType?: string | null;
  pdfContentVerso?: string | null;
  createdBy?: string;
  createdDate?: dayjs.Dayjs;
  lastModifiedBy?: string;
  lastModifiedDate?: dayjs.Dayjs;
}

export const defaultValue: Readonly<ITemplateMySuffix> = {};
