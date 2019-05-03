import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { GeneralMst } from 'app/shared/model/general-mst.model';
import { GeneralMstService } from './general-mst.service';
import { GeneralMstComponent } from './general-mst.component';
import { GeneralMstDetailComponent } from './general-mst-detail.component';
import { GeneralMstUpdateComponent } from './general-mst-update.component';
import { GeneralMstDeletePopupComponent } from './general-mst-delete-dialog.component';
import { IGeneralMst } from 'app/shared/model/general-mst.model';

@Injectable({ providedIn: 'root' })
export class GeneralMstResolve implements Resolve<IGeneralMst> {
  constructor(private service: GeneralMstService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IGeneralMst> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<GeneralMst>) => response.ok),
        map((generalMst: HttpResponse<GeneralMst>) => generalMst.body)
      );
    }
    return of(new GeneralMst());
  }
}

export const generalMstRoute: Routes = [
  {
    path: '',
    component: GeneralMstComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'GeneralMsts'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: GeneralMstDetailComponent,
    resolve: {
      generalMst: GeneralMstResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'GeneralMsts'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: GeneralMstUpdateComponent,
    resolve: {
      generalMst: GeneralMstResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'GeneralMsts'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: GeneralMstUpdateComponent,
    resolve: {
      generalMst: GeneralMstResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'GeneralMsts'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const generalMstPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: GeneralMstDeletePopupComponent,
    resolve: {
      generalMst: GeneralMstResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'GeneralMsts'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
