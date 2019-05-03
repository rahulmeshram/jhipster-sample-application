import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';
import { IDoctor, Doctor } from 'app/shared/model/doctor.model';
import { DoctorService } from './doctor.service';
import { ISpecility } from 'app/shared/model/specility.model';
import { SpecilityService } from 'app/entities/specility';

@Component({
  selector: 'jhi-doctor-update',
  templateUrl: './doctor-update.component.html'
})
export class DoctorUpdateComponent implements OnInit {
  doctor: IDoctor;
  isSaving: boolean;

  specilities: ISpecility[];

  editForm = this.fb.group({
    id: [],
    orgid: [null, [Validators.required]],
    docid: [null, [Validators.required]],
    docname: [null, [Validators.required]],
    splid: [null, [Validators.required]],
    licno: [null, [Validators.required]],
    natid: [],
    education: [],
    consultsrvid: [],
    followupsrvid: [],
    external: [null, [Validators.required]],
    defunct: [null, [Validators.required]],
    creaton: [null, [Validators.required]],
    creatby: [null, [Validators.required]],
    modifyon: [],
    modifyby: [],
    docid: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected doctorService: DoctorService,
    protected specilityService: SpecilityService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ doctor }) => {
      this.updateForm(doctor);
      this.doctor = doctor;
    });
    this.specilityService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<ISpecility[]>) => mayBeOk.ok),
        map((response: HttpResponse<ISpecility[]>) => response.body)
      )
      .subscribe((res: ISpecility[]) => (this.specilities = res), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(doctor: IDoctor) {
    this.editForm.patchValue({
      id: doctor.id,
      orgid: doctor.orgid,
      docid: doctor.docid,
      docname: doctor.docname,
      splid: doctor.splid,
      licno: doctor.licno,
      natid: doctor.natid,
      education: doctor.education,
      consultsrvid: doctor.consultsrvid,
      followupsrvid: doctor.followupsrvid,
      external: doctor.external,
      defunct: doctor.defunct,
      creaton: doctor.creaton != null ? doctor.creaton.format(DATE_TIME_FORMAT) : null,
      creatby: doctor.creatby,
      modifyon: doctor.modifyon != null ? doctor.modifyon.format(DATE_TIME_FORMAT) : null,
      modifyby: doctor.modifyby,
      docid: doctor.docid
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const doctor = this.createFromForm();
    if (doctor.id !== undefined) {
      this.subscribeToSaveResponse(this.doctorService.update(doctor));
    } else {
      this.subscribeToSaveResponse(this.doctorService.create(doctor));
    }
  }

  private createFromForm(): IDoctor {
    const entity = {
      ...new Doctor(),
      id: this.editForm.get(['id']).value,
      orgid: this.editForm.get(['orgid']).value,
      docid: this.editForm.get(['docid']).value,
      docname: this.editForm.get(['docname']).value,
      splid: this.editForm.get(['splid']).value,
      licno: this.editForm.get(['licno']).value,
      natid: this.editForm.get(['natid']).value,
      education: this.editForm.get(['education']).value,
      consultsrvid: this.editForm.get(['consultsrvid']).value,
      followupsrvid: this.editForm.get(['followupsrvid']).value,
      external: this.editForm.get(['external']).value,
      defunct: this.editForm.get(['defunct']).value,
      creaton: this.editForm.get(['creaton']).value != null ? moment(this.editForm.get(['creaton']).value, DATE_TIME_FORMAT) : undefined,
      creatby: this.editForm.get(['creatby']).value,
      modifyon: this.editForm.get(['modifyon']).value != null ? moment(this.editForm.get(['modifyon']).value, DATE_TIME_FORMAT) : undefined,
      modifyby: this.editForm.get(['modifyby']).value,
      docid: this.editForm.get(['docid']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IDoctor>>) {
    result.subscribe((res: HttpResponse<IDoctor>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

  trackSpecilityById(index: number, item: ISpecility) {
    return item.id;
  }
}
