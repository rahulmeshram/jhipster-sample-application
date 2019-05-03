import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { ISpecility } from 'app/shared/model/specility.model';
import { AccountService } from 'app/core';
import { SpecilityService } from './specility.service';

@Component({
  selector: 'jhi-specility',
  templateUrl: './specility.component.html'
})
export class SpecilityComponent implements OnInit, OnDestroy {
  specilities: ISpecility[];
  currentAccount: any;
  eventSubscriber: Subscription;

  constructor(
    protected specilityService: SpecilityService,
    protected jhiAlertService: JhiAlertService,
    protected eventManager: JhiEventManager,
    protected accountService: AccountService
  ) {}

  loadAll() {
    this.specilityService
      .query()
      .pipe(
        filter((res: HttpResponse<ISpecility[]>) => res.ok),
        map((res: HttpResponse<ISpecility[]>) => res.body)
      )
      .subscribe(
        (res: ISpecility[]) => {
          this.specilities = res;
        },
        (res: HttpErrorResponse) => this.onError(res.message)
      );
  }

  ngOnInit() {
    this.loadAll();
    this.accountService.identity().then(account => {
      this.currentAccount = account;
    });
    this.registerChangeInSpecilities();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: ISpecility) {
    return item.id;
  }

  registerChangeInSpecilities() {
    this.eventSubscriber = this.eventManager.subscribe('specilityListModification', response => this.loadAll());
  }

  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }
}
