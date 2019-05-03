import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IDoctor } from 'app/shared/model/doctor.model';

type EntityResponseType = HttpResponse<IDoctor>;
type EntityArrayResponseType = HttpResponse<IDoctor[]>;

@Injectable({ providedIn: 'root' })
export class DoctorService {
  public resourceUrl = SERVER_API_URL + 'api/doctors';

  constructor(protected http: HttpClient) {}

  create(doctor: IDoctor): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(doctor);
    return this.http
      .post<IDoctor>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(doctor: IDoctor): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(doctor);
    return this.http
      .put<IDoctor>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IDoctor>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IDoctor[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(doctor: IDoctor): IDoctor {
    const copy: IDoctor = Object.assign({}, doctor, {
      creaton: doctor.creaton != null && doctor.creaton.isValid() ? doctor.creaton.toJSON() : null,
      modifyon: doctor.modifyon != null && doctor.modifyon.isValid() ? doctor.modifyon.toJSON() : null
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
      res.body.forEach((doctor: IDoctor) => {
        doctor.creaton = doctor.creaton != null ? moment(doctor.creaton) : null;
        doctor.modifyon = doctor.modifyon != null ? moment(doctor.modifyon) : null;
      });
    }
    return res;
  }
}
