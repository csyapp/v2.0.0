import dayjs from 'dayjs';

import { APP_LOCAL_DATE_FORMAT, APP_LOCAL_DATETIME_FORMAT } from 'app/config/constants';

export const convertDateTimeFromServer = date => (date ? dayjs(date).format(APP_LOCAL_DATETIME_FORMAT) : null);

export const convertDateTimeToServer = (date?: string): dayjs.Dayjs | null => (date ? dayjs(date) : null);

export const displayDefaultDateTime = () => dayjs().startOf('day').format(APP_LOCAL_DATETIME_FORMAT);

export const getMonthsInFrench = () => {
  const monthsMap: { [key: string]: string } = {};
  dayjs.locale('fr');
  for (let i = 0; i < 12; i++) {
    const monthKey = (i + 1).toString().padStart(2, '0'); // Ensure keys are '01', '02', etc.
    monthsMap[monthKey] = dayjs().month(i).format('MMMM');
  }
  return monthsMap;
};

export const getTodayMonth = () => (dayjs().month() + 1).toString().padStart(2, '0');

export const getTodayYear = () => dayjs().year().toString();
