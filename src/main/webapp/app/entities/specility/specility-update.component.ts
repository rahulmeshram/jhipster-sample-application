import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { ISpecility, Specility } from 'app/shared/model/specility.model';
import { SpecilityService } from './specility.service';

@Component({
  selector: 'jhi-specility-update',
  templateUrl: './specility-update.component.html'
})
export class SpecilityUpdateComponent implements OnInit {
  specility: ISpecility;
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    orgid: [null, [Validators.required]],
    splid: [null, [Validators.required]],
    splname: [null, [Validators.required]],
    defunct: [null, [Validators.required]],
    creaton: [null, [Validators.required]],
    creatby: [null, [Validators.required]],
    modifyon: [],
    modifyby: []
  });

  constructor(protected specilityService: SpecilityService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ specility }) => {
      this.updateForm(specility);
      this.specility = specility;
    });
  }

  updateForm(specility: ISpecility) {
    this.editForm.patchValue({
      id: specility.id,
      orgid: specility.orgid,
      splid: specility.splid,
      splname: specility.splname,
      defunct: specility.defunct,
      creaton: specility.creaton != null ? specility.creaton.format(DATE_TIME_FORMAT) : null,
      creatby: specility.creatby,
      modifyon: specility.modifyon != null ? specility.modifyon.format(DATE_TIME_FORMAT) : null,
      modifyby: specility.modifyby
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const specility = this.createFromForm();
    if (specility.id !== undefined) {
      this.subscribeToSaveResponse(this.specilityService.update(specility));
    } else {
      this.subscribeToSaveResponse(this.specilityService.create(specility));
    }
  }

  private createFromForm(): ISpecility {
    const entity = {
      ...new Specility(),
      id: this.editForm.get(['id']).value,
      orgid: this.editForm.get(['orgid']).value,
      splid: this.editForm.get(['splid']).value,
      splname: this.editForm.get(['splname']).value,
      defunct: this.editForm.get(['defunct']).value,
      creaton: this.editForm.get(['creaton']).value != null ? moment(this.editForm.get(['creaton']).value, DATE_TIME_FORMAT) : undefined,
      creatby: this.editForm.get(['creatby']).value,
      modifyon: this.editForm.get(['modifyon']).value != null ? moment(this.editForm.get(['modifyon']).value, DATE_TIME_FORMAT) : undefined,
      modifyby: this.editForm.get(['modifyby']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ISpecility>>) {
    result.subscribe((res: HttpResponse<ISpecility>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
}
