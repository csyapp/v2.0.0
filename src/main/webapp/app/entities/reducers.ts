import template from 'app/entities/template-my-suffix/template-my-suffix.reducer';
import carte from 'app/entities/carte-my-suffix/carte-my-suffix.reducer';
import reporting from 'app/entities/reporting/reporting-my-suffix.reducer';
import reportingMois from 'app/entities/reporting/reporting-mois-suffix.reducer';
import reportingAnnee from 'app/entities/reporting/reporting-annee-suffix.reducer';
/* jhipster-needle-add-reducer-import - JHipster will add reducer here */

const entitiesReducers = {
  template,
  carte,
  reporting,
  reportingMois,
  reportingAnnee,
  /* jhipster-needle-add-reducer-combine - JHipster will add reducer here */
};

export default entitiesReducers;
