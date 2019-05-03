import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterSampleApplicationSharedModule } from 'app/shared';
import {
  GeneralMstComponent,
  GeneralMstDetailComponent,
  GeneralMstUpdateComponent,
  GeneralMstDeletePopupComponent,
  GeneralMstDeleteDialogComponent,
  generalMstRoute,
  generalMstPopupRoute
} from './';

const ENTITY_STATES = [...generalMstRoute, ...generalMstPopupRoute];

@NgModule({
  imports: [JhipsterSampleApplicationSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    GeneralMstComponent,
    GeneralMstDetailComponent,
    GeneralMstUpdateComponent,
    GeneralMstDeleteDialogComponent,
    GeneralMstDeletePopupComponent
  ],
  entryComponents: [GeneralMstComponent, GeneralMstUpdateComponent, GeneralMstDeleteDialogComponent, GeneralMstDeletePopupComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JhipsterSampleApplicationGeneralMstModule {}
