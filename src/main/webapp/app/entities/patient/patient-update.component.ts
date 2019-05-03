import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { IPatient, Patient } from 'app/shared/model/patient.model';
import { PatientService } from './patient.service';

@Component({
  selector: 'jhi-patient-update',
  templateUrl: './patient-update.component.html'
})
export class PatientUpdateComponent implements OnInit {
  patient: IPatient;
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    orgid: [null, [Validators.required]],
    patid: [null, [Validators.required]],
    fname: [null, [Validators.required]],
    mname: [],
    lname: [null, [Validators.required]],
    dob: [],
    ageY: [],
    ageM: [],
    ageD: [],
    gender: [null, [Validators.required]],
    add1: [],
    add2: [],
    cityid: [null, [Validators.required]],
    stateid: [null, [Validators.required]],
    country: [null, [Validators.required]],
    postalcode: [null, [Validators.required]],
    natid: [null, [Validators.required]],
    email: [],
    mobile: [null, [Validators.required]],
    mobile2: [],
    married: [],
    occupationid: [],
    idtype: [],
    idnumber: [],
    defunct: [null, [Validators.required]],
    creaton: [null, [Validators.required]],
    creatby: [null, [Validators.required]],
    modifyon: [],
    modifyby: []
  });

  constructor(protected patientService: PatientService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ patient }) => {
      this.updateForm(patient);
      this.patient = patient;
    });
  }

  updateForm(patient: IPatient) {
    this.editForm.patchValue({
      id: patient.id,
      orgid: patient.orgid,
      patid: patient.patid,
      fname: patient.fname,
      mname: patient.mname,
      lname: patient.lname,
      dob: patient.dob != null ? patient.dob.format(DATE_TIME_FORMAT) : null,
      ageY: patient.ageY,
      ageM: patient.ageM,
      ageD: patient.ageD,
      gender: patient.gender,
      add1: patient.add1,
      add2: patient.add2,
      cityid: patient.cityid,
      stateid: patient.stateid,
      country: patient.country,
      postalcode: patient.postalcode,
      natid: patient.natid,
      email: patient.email,
      mobile: patient.mobile,
      mobile2: patient.mobile2,
      married: patient.married,
      occupationid: patient.occupationid,
      idtype: patient.idtype,
      idnumber: patient.idnumber,
      defunct: patient.defunct,
      creaton: patient.creaton != null ? patient.creaton.format(DATE_TIME_FORMAT) : null,
      creatby: patient.creatby,
      modifyon: patient.modifyon != null ? patient.modifyon.format(DATE_TIME_FORMAT) : null,
      modifyby: patient.modifyby
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const patient = this.createFromForm();
    if (patient.id !== undefined) {
      this.subscribeToSaveResponse(this.patientService.update(patient));
    } else {
      this.subscribeToSaveResponse(this.patientService.create(patient));
    }
  }

  private createFromForm(): IPatient {
    const entity = {
      ...new Patient(),
      id: this.editForm.get(['id']).value,
      orgid: this.editForm.get(['orgid']).value,
      patid: this.editForm.get(['patid']).value,
      fname: this.editForm.get(['fname']).value,
      mname: this.editForm.get(['mname']).value,
      lname: this.editForm.get(['lname']).value,
      dob: this.editForm.get(['dob']).value != null ? moment(this.editForm.get(['dob']).value, DATE_TIME_FORMAT) : undefined,
      ageY: this.editForm.get(['ageY']).value,
      ageM: this.editForm.get(['ageM']).value,
      ageD: this.editForm.get(['ageD']).value,
      gender: this.editForm.get(['gender']).value,
      add1: this.editForm.get(['add1']).value,
      add2: this.editForm.get(['add2']).value,
      cityid: this.editForm.get(['cityid']).value,
      stateid: this.editForm.get(['stateid']).value,
      country: this.editForm.get(['country']).value,
      postalcode: this.editForm.get(['postalcode']).value,
      natid: this.editForm.get(['natid']).value,
      email: this.editForm.get(['email']).value,
      mobile: this.editForm.get(['mobile']).value,
      mobile2: this.editForm.get(['mobile2']).value,
      married: this.editForm.get(['married']).value,
      occupationid: this.editForm.get(['occupationid']).value,
      idtype: this.editForm.get(['idtype']).value,
      idnumber: this.editForm.get(['idnumber']).value,
      defunct: this.editForm.get(['defunct']).value,
      creaton: this.editForm.get(['creaton']).value != null ? moment(this.editForm.get(['creaton']).value, DATE_TIME_FORMAT) : undefined,
      creatby: this.editForm.get(['creatby']).value,
      modifyon: this.editForm.get(['modifyon']).value != null ? moment(this.editForm.get(['modifyon']).value, DATE_TIME_FORMAT) : undefined,
      modifyby: this.editForm.get(['modifyby']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IPatient>>) {
    result.subscribe((res: HttpResponse<IPatient>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
}
