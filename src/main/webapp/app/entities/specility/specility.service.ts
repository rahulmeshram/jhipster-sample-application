import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ISpecility } from 'app/shared/model/specility.model';

type EntityResponseType = HttpResponse<ISpecility>;
type EntityArrayResponseType = HttpResponse<ISpecility[]>;

@Injectable({ providedIn: 'root' })
export class SpecilityService {
  public resourceUrl = SERVER_API_URL + 'api/specilities';

  constructor(protected http: HttpClient) {}

  create(specility: ISpecility): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(specility);
    return this.http
      .post<ISpecility>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(specility: ISpecility): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(specility);
    return this.http
      .put<ISpecility>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<ISpecility>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<ISpecility[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(specility: ISpecility): ISpecility {
    const copy: ISpecility = Object.assign({}, specility, {
      creaton: specility.creaton != null && specility.creaton.isValid() ? specility.creaton.toJSON() : null,
      modifyon: specility.modifyon != null && specility.modifyon.isValid() ? specility.modifyon.toJSON() : null
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
      res.body.forEach((specility: ISpecility) => {
        specility.creaton = specility.creaton != null ? moment(specility.creaton) : null;
        specility.modifyon = specility.modifyon != null ? moment(specility.modifyon) : null;
      });
    }
    return res;
  }
}
