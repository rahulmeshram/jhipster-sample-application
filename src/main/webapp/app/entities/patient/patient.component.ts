import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IPatient } from 'app/shared/model/patient.model';
import { AccountService } from 'app/core';
import { PatientService } from './patient.service';

@Component({
  selector: 'jhi-patient',
  templateUrl: './patient.component.html'
})
export class PatientComponent implements OnInit, OnDestroy {
  patients: IPatient[];
  currentAccount: any;
  eventSubscriber: Subscription;

  constructor(
    protected patientService: PatientService,
    protected jhiAlertService: JhiAlertService,
    protected eventManager: JhiEventManager,
    protected accountService: AccountService
  ) {}

  loadAll() {
    this.patientService
      .query()
      .pipe(
        filter((res: HttpResponse<IPatient[]>) => res.ok),
        map((res: HttpResponse<IPatient[]>) => res.body)
      )
      .subscribe(
        (res: IPatient[]) => {
          this.patients = res;
        },
        (res: HttpErrorResponse) => this.onError(res.message)
      );
  }

  ngOnInit() {
    this.loadAll();
    this.accountService.identity().then(account => {
      this.currentAccount = account;
    });
    this.registerChangeInPatients();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: IPatient) {
    return item.id;
  }

  registerChangeInPatients() {
    this.eventSubscriber = this.eventManager.subscribe('patientListModification', response => this.loadAll());
  }

  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }
}
