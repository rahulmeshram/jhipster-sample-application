import { Moment } from 'moment';
import { IPatient } from 'app/shared/model/patient.model';

export interface IGeneralMst {
  id?: number;
  orgid?: number;
  genid?: string;
  type?: string;
  name?: string;
  defunct?: boolean;
  creaton?: Moment;
  creatby?: number;
  modifyon?: Moment;
  modifyby?: number;
  genids?: IPatient[];
  genids?: IPatient[];
  genids?: IPatient[];
}

export class GeneralMst implements IGeneralMst {
  constructor(
    public id?: number,
    public orgid?: number,
    public genid?: string,
    public type?: string,
    public name?: string,
    public defunct?: boolean,
    public creaton?: Moment,
    public creatby?: number,
    public modifyon?: Moment,
    public modifyby?: number,
    public genids?: IPatient[],
    public genids?: IPatient[],
    public genids?: IPatient[]
  ) {
    this.defunct = this.defunct || false;
  }
}
