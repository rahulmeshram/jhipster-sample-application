/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { DoctorService } from 'app/entities/doctor/doctor.service';
import { IDoctor, Doctor } from 'app/shared/model/doctor.model';

describe('Service Tests', () => {
  describe('Doctor Service', () => {
    let injector: TestBed;
    let service: DoctorService;
    let httpMock: HttpTestingController;
    let elemDefault: IDoctor;
    let expectedResult;
    let currentDate: moment.Moment;
    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = {};
      injector = getTestBed();
      service = injector.get(DoctorService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new Doctor(0, 0, 0, 'AAAAAAA', 0, 'AAAAAAA', 0, 0, 0, 0, false, false, currentDate, 0, currentDate, 0);
    });

    describe('Service methods', () => {
      it('should find an element', async () => {
        const returnedFromService = Object.assign(
          {
            creaton: currentDate.format(DATE_TIME_FORMAT),
            modifyon: currentDate.format(DATE_TIME_FORMAT)
          },
          elemDefault
        );
        service
          .find(123)
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: elemDefault });
      });

      it('should create a Doctor', async () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            creaton: currentDate.format(DATE_TIME_FORMAT),
            modifyon: currentDate.format(DATE_TIME_FORMAT)
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            creaton: currentDate,
            modifyon: currentDate
          },
          returnedFromService
        );
        service
          .create(new Doctor(null))
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should update a Doctor', async () => {
        const returnedFromService = Object.assign(
          {
            orgid: 1,
            docid: 1,
            docname: 'BBBBBB',
            splid: 1,
            licno: 'BBBBBB',
            natid: 1,
            education: 1,
            consultsrvid: 1,
            followupsrvid: 1,
            external: true,
            defunct: true,
            creaton: currentDate.format(DATE_TIME_FORMAT),
            creatby: 1,
            modifyon: currentDate.format(DATE_TIME_FORMAT),
            modifyby: 1
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            creaton: currentDate,
            modifyon: currentDate
          },
          returnedFromService
        );
        service
          .update(expected)
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should return a list of Doctor', async () => {
        const returnedFromService = Object.assign(
          {
            orgid: 1,
            docid: 1,
            docname: 'BBBBBB',
            splid: 1,
            licno: 'BBBBBB',
            natid: 1,
            education: 1,
            consultsrvid: 1,
            followupsrvid: 1,
            external: true,
            defunct: true,
            creaton: currentDate.format(DATE_TIME_FORMAT),
            creatby: 1,
            modifyon: currentDate.format(DATE_TIME_FORMAT),
            modifyby: 1
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            creaton: currentDate,
            modifyon: currentDate
          },
          returnedFromService
        );
        service
          .query(expected)
          .pipe(
            take(1),
            map(resp => resp.body)
          )
          .subscribe(body => (expectedResult = body));
        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a Doctor', async () => {
        const rxPromise = service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
