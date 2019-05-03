/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { PatientService } from 'app/entities/patient/patient.service';
import { IPatient, Patient } from 'app/shared/model/patient.model';

describe('Service Tests', () => {
  describe('Patient Service', () => {
    let injector: TestBed;
    let service: PatientService;
    let httpMock: HttpTestingController;
    let elemDefault: IPatient;
    let expectedResult;
    let currentDate: moment.Moment;
    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = {};
      injector = getTestBed();
      service = injector.get(PatientService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new Patient(
        0,
        0,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        currentDate,
        0,
        0,
        0,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        0,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        0,
        'AAAAAAA',
        'AAAAAAA',
        false,
        currentDate,
        0,
        currentDate,
        0
      );
    });

    describe('Service methods', () => {
      it('should find an element', async () => {
        const returnedFromService = Object.assign(
          {
            dob: currentDate.format(DATE_TIME_FORMAT),
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

      it('should create a Patient', async () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            dob: currentDate.format(DATE_TIME_FORMAT),
            creaton: currentDate.format(DATE_TIME_FORMAT),
            modifyon: currentDate.format(DATE_TIME_FORMAT)
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            dob: currentDate,
            creaton: currentDate,
            modifyon: currentDate
          },
          returnedFromService
        );
        service
          .create(new Patient(null))
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should update a Patient', async () => {
        const returnedFromService = Object.assign(
          {
            orgid: 1,
            patid: 'BBBBBB',
            fname: 'BBBBBB',
            mname: 'BBBBBB',
            lname: 'BBBBBB',
            dob: currentDate.format(DATE_TIME_FORMAT),
            ageY: 1,
            ageM: 1,
            ageD: 1,
            gender: 'BBBBBB',
            add1: 'BBBBBB',
            add2: 'BBBBBB',
            cityid: 'BBBBBB',
            stateid: 'BBBBBB',
            country: 'BBBBBB',
            postalcode: 'BBBBBB',
            natid: 1,
            email: 'BBBBBB',
            mobile: 'BBBBBB',
            mobile2: 'BBBBBB',
            married: 'BBBBBB',
            occupationid: 1,
            idtype: 'BBBBBB',
            idnumber: 'BBBBBB',
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
            dob: currentDate,
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

      it('should return a list of Patient', async () => {
        const returnedFromService = Object.assign(
          {
            orgid: 1,
            patid: 'BBBBBB',
            fname: 'BBBBBB',
            mname: 'BBBBBB',
            lname: 'BBBBBB',
            dob: currentDate.format(DATE_TIME_FORMAT),
            ageY: 1,
            ageM: 1,
            ageD: 1,
            gender: 'BBBBBB',
            add1: 'BBBBBB',
            add2: 'BBBBBB',
            cityid: 'BBBBBB',
            stateid: 'BBBBBB',
            country: 'BBBBBB',
            postalcode: 'BBBBBB',
            natid: 1,
            email: 'BBBBBB',
            mobile: 'BBBBBB',
            mobile2: 'BBBBBB',
            married: 'BBBBBB',
            occupationid: 1,
            idtype: 'BBBBBB',
            idnumber: 'BBBBBB',
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
            dob: currentDate,
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

      it('should delete a Patient', async () => {
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
