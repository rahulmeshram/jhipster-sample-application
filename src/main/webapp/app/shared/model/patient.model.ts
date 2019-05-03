import { Moment } from 'moment';
import { IGeneralMst } from 'app/shared/model/general-mst.model';

export interface IPatient {
  id?: number;
  orgid?: number;
  patid?: string;
  fname?: string;
  mname?: string;
  lname?: string;
  dob?: Moment;
  ageY?: number;
  ageM?: number;
  ageD?: number;
  gender?: string;
  add1?: string;
  add2?: string;
  cityid?: string;
  stateid?: string;
  country?: string;
  postalcode?: string;
  natid?: number;
  email?: string;
  mobile?: string;
  mobile2?: string;
  married?: string;
  occupationid?: number;
  idtype?: string;
  idnumber?: string;
  defunct?: boolean;
  creaton?: Moment;
  creatby?: number;
  modifyon?: Moment;
  modifyby?: number;
  cityids?: IGeneralMst[];
  stateids?: IGeneralMst[];
  natids?: IGeneralMst[];
}

export class Patient implements IPatient {
  constructor(
    public id?: number,
    public orgid?: number,
    public patid?: string,
    public fname?: string,
    public mname?: string,
    public lname?: string,
    public dob?: Moment,
    public ageY?: number,
    public ageM?: number,
    public ageD?: number,
    public gender?: string,
    public add1?: string,
    public add2?: string,
    public cityid?: string,
    public stateid?: string,
    public country?: string,
    public postalcode?: string,
    public natid?: number,
    public email?: string,
    public mobile?: string,
    public mobile2?: string,
    public married?: string,
    public occupationid?: number,
    public idtype?: string,
    public idnumber?: string,
    public defunct?: boolean,
    public creaton?: Moment,
    public creatby?: number,
    public modifyon?: Moment,
    public modifyby?: number,
    public cityids?: IGeneralMst[],
    public stateids?: IGeneralMst[],
    public natids?: IGeneralMst[]
  ) {
    this.defunct = this.defunct || false;
  }
}
