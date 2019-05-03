import { Moment } from 'moment';
import { IDoctor } from 'app/shared/model/doctor.model';

export interface ISpecility {
  id?: number;
  orgid?: number;
  splid?: number;
  splname?: string;
  defunct?: boolean;
  creaton?: Moment;
  creatby?: number;
  modifyon?: Moment;
  modifyby?: number;
  doctor?: IDoctor;
}

export class Specility implements ISpecility {
  constructor(
    public id?: number,
    public orgid?: number,
    public splid?: number,
    public splname?: string,
    public defunct?: boolean,
    public creaton?: Moment,
    public creatby?: number,
    public modifyon?: Moment,
    public modifyby?: number,
    public doctor?: IDoctor
  ) {
    this.defunct = this.defunct || false;
  }
}
