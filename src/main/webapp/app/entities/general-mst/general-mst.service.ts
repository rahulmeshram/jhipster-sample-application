import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IGeneralMst } from 'app/shared/model/general-mst.model';

type EntityResponseType = HttpResponse<IGeneralMst>;
type EntityArrayResponseType = HttpResponse<IGeneralMst[]>;

@Injectable({ providedIn: 'root' })
export class GeneralMstService {
  public resourceUrl = SERVER_API_URL + 'api/general-msts';

  constructor(protected http: HttpClient) {}

  create(generalMst: IGeneralMst): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(generalMst);
    return this.http
      .post<IGeneralMst>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(generalMst: IGeneralMst): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(generalMst);
    return this.http
      .put<IGeneralMst>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IGeneralMst>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IGeneralMst[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(generalMst: IGeneralMst): IGeneralMst {
    const copy: IGeneralMst = Object.assign({}, generalMst, {
      creaton: generalMst.creaton != null && generalMst.creaton.isValid() ? generalMst.creaton.toJSON() : null,
      modifyon: generalMst.modifyon != null && generalMst.modifyon.isValid() ? generalMst.modifyon.toJSON() : null
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.creaton = res.body.creaton != null ? moment(res.body.creaton) : null;
      res.body.modifyon = res.body.modifyon != null ? moment(res.body.modifyon) : null;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((generalMst: IGeneralMst) => {
        generalMst.creaton = generalMst.creaton != null ? moment(generalMst.creaton) : null;
        generalMst.modifyon = generalMst.modifyon != null ? moment(generalMst.modifyon) : null;
      });
    }
    return res;
  }
}
