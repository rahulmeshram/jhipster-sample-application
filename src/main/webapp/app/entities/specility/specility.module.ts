import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterSampleApplicationSharedModule } from 'app/shared';
import {
  SpecilityComponent,
  SpecilityDetailComponent,
  SpecilityUpdateComponent,
  SpecilityDeletePopupComponent,
  SpecilityDeleteDialogComponent,
  specilityRoute,
  specilityPopupRoute
} from './';

const ENTITY_STATES = [...specilityRoute, ...specilityPopupRoute];

@NgModule({
  imports: [JhipsterSampleApplicationSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    SpecilityComponent,
    SpecilityDetailComponent,
    SpecilityUpdateComponent,
    SpecilityDeleteDialogComponent,
    SpecilityDeletePopupComponent
  ],
  entryComponents: [SpecilityComponent, SpecilityUpdateComponent, SpecilityDeleteDialogComponent, SpecilityDeletePopupComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JhipsterSampleApplicationSpecilityModule {}
