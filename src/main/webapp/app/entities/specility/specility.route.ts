import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Specility } from 'app/shared/model/specility.model';
import { SpecilityService } from './specility.service';
import { SpecilityComponent } from './specility.component';
import { SpecilityDetailComponent } from './specility-detail.component';
import { SpecilityUpdateComponent } from './specility-update.component';
import { SpecilityDeletePopupComponent } from './specility-delete-dialog.component';
import { ISpecility } from 'app/shared/model/specility.model';

@Injectable({ providedIn: 'root' })
export class SpecilityResolve implements Resolve<ISpecility> {
  constructor(private service: SpecilityService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<ISpecility> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<Specility>) => response.ok),
        map((specility: HttpResponse<Specility>) => specility.body)
      );
    }
    return of(new Specility());
  }
}

export const specilityRoute: Routes = [
  {
    path: '',
    component: SpecilityComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'Specilities'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: SpecilityDetailComponent,
    resolve: {
      specility: SpecilityResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'Specilities'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: SpecilityUpdateComponent,
    resolve: {
      specility: SpecilityResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'Specilities'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: SpecilityUpdateComponent,
    resolve: {
      specility: SpecilityResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'Specilities'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const specilityPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: SpecilityDeletePopupComponent,
    resolve: {
      specility: SpecilityResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'Specilities'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
