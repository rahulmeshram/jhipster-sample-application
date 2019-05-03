import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'general-mst',
        loadChildren: './general-mst/general-mst.module#JhipsterSampleApplicationGeneralMstModule'
      },
      {
        path: 'patient',
        loadChildren: './patient/patient.module#JhipsterSampleApplicationPatientModule'
      },
      {
        path: 'specility',
        loadChildren: './specility/specility.module#JhipsterSampleApplicationSpecilityModule'
      },
      {
        path: 'doctor',
        loadChildren: './doctor/doctor.module#JhipsterSampleApplicationDoctorModule'
      }
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ])
  ],
  declarations: [],
  entryComponents: [],
  providers: [],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JhipsterSampleApplicationEntityModule {}
