import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { IGeneralMst, GeneralMst } from 'app/shared/model/general-mst.model';
import { GeneralMstService } from './general-mst.service';

@Component({
  selector: 'jhi-general-mst-update',
  templateUrl: './general-mst-update.component.html'
})
export class GeneralMstUpdateComponent implements OnInit {
  generalMst: IGeneralMst;
  isSaving: boolean;

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
    modifyby: []
  });

  constructor(protected generalMstService: GeneralMstService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ generalMst }) => {
      this.updateForm(generalMst);
      this.generalMst = generalMst;
    });
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
      modifyby: generalMst.modifyby
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
      modifyby: this.editForm.get(['modifyby']).value
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
}
