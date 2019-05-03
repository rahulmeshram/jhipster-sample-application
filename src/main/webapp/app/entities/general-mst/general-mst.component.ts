import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IGeneralMst } from 'app/shared/model/general-mst.model';
import { AccountService } from 'app/core';
import { GeneralMstService } from './general-mst.service';

@Component({
  selector: 'jhi-general-mst',
  templateUrl: './general-mst.component.html'
})
export class GeneralMstComponent implements OnInit, OnDestroy {
  generalMsts: IGeneralMst[];
  currentAccount: any;
  eventSubscriber: Subscription;

  constructor(
    protected generalMstService: GeneralMstService,
    protected jhiAlertService: JhiAlertService,
    protected eventManager: JhiEventManager,
    protected accountService: AccountService
  ) {}

  loadAll() {
    this.generalMstService
      .query()
      .pipe(
        filter((res: HttpResponse<IGeneralMst[]>) => res.ok),
        map((res: HttpResponse<IGeneralMst[]>) => res.body)
      )
      .subscribe(
        (res: IGeneralMst[]) => {
          this.generalMsts = res;
        },
        (res: HttpErrorResponse) => this.onError(res.message)
      );
  }

  ngOnInit() {
    this.loadAll();
    this.accountService.identity().then(account => {
      this.currentAccount = account;
    });
    this.registerChangeInGeneralMsts();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: IGeneralMst) {
    return item.id;
  }

  registerChangeInGeneralMsts() {
    this.eventSubscriber = this.eventManager.subscribe('generalMstListModification', response => this.loadAll());
  }

  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }
}
