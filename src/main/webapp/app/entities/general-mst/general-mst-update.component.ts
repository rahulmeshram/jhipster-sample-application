import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';
import { IGeneralMst, GeneralMst } from 'app/shared/model/general-mst.model';
import { GeneralMstService } from './general-mst.service';
import { IPatient } from 'app/shared/model/patient.model';
import { PatientService } from 'app/entities/patient';

@Component({
  selector: 'jhi-general-mst-update',
  templateUrl: './general-mst-update.component.html'
})
export class GeneralMstUpdateComponent implements OnInit {
  generalMst: IGeneralMst;
  isSaving: boolean;

  patients: IPatient[];

  editForm = this.fb.group({
    id: [],
    orgid: [null, [Validators.required]],
    genid: [null, [Validators.required]],
    type: [null, [Validators.required]],
    name: [null, [Validators.required]],
    defunct: [null, [Validators.required]],
    creaton: [null, [Validators.required]],
    creatby: [null, [Validators.required]],
    modifyon: [],
    modifyby: [],
    patient: [],
    patient: [],
    patient: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected generalMstService: GeneralMstService,
    protected patientService: PatientService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ generalMst }) => {
      this.updateForm(generalMst);
      this.generalMst = generalMst;
    });
    this.patientService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IPatient[]>) => mayBeOk.ok),
        map((response: HttpResponse<IPatient[]>) => response.body)
      )
      .subscribe((res: IPatient[]) => (this.patients = res), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(generalMst: IGeneralMst) {
    this.editForm.patchValue({
      id: generalMst.id,
      orgid: generalMst.orgid,
      genid: generalMst.genid,
      type: generalMst.type,
      name: generalMst.name,
      defunct: generalMst.defunct,
      creaton: generalMst.creaton != null ? generalMst.creaton.format(DATE_TIME_FORMAT) : null,
      creatby: generalMst.creatby,
      modifyon: generalMst.modifyon != null ? generalMst.modifyon.format(DATE_TIME_FORMAT) : null,
      modifyby: generalMst.modifyby,
      patient: generalMst.patient,
      patient: generalMst.patient,
      patient: generalMst.patient
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const generalMst = this.createFromForm();
    if (generalMst.id !== undefined) {
      this.subscribeToSaveResponse(this.generalMstService.update(generalMst));
    } else {
      this.subscribeToSaveResponse(this.generalMstService.create(generalMst));
    }
  }

  private createFromForm(): IGeneralMst {
    const entity = {
      ...new GeneralMst(),
      id: this.editForm.get(['id']).value,
      orgid: this.editForm.get(['orgid']).value,
      genid: this.editForm.get(['genid']).value,
      type: this.editForm.get(['type']).value,
      name: this.editForm.get(['name']).value,
      defunct: this.editForm.get(['defunct']).value,
      creaton: this.editForm.get(['creaton']).value != null ? moment(this.editForm.get(['creaton']).value, DATE_TIME_FORMAT) : undefined,
      creatby: this.editForm.get(['creatby']).value,
      modifyon: this.editForm.get(['modifyon']).value != null ? moment(this.editForm.get(['modifyon']).value, DATE_TIME_FORMAT) : undefined,
      modifyby: this.editForm.get(['modifyby']).value,
      patient: this.editForm.get(['patient']).value,
      patient: this.editForm.get(['patient']).value,
      patient: this.editForm.get(['patient']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IGeneralMst>>) {
    result.subscribe((res: HttpResponse<IGeneralMst>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }

  trackPatientById(index: number, item: IPatient) {
    return item.id;
  }
}
