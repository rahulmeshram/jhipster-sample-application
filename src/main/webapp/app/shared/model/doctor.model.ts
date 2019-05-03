import { Moment } from 'moment';
import { ISpecility } from 'app/shared/model/specility.model';

export interface IDoctor {
  id?: number;
  orgid?: number;
  docid?: number;
  docname?: string;
  splid?: number;
  licno?: string;
  natid?: number;
  education?: number;
  consultsrvid?: number;
  followupsrvid?: number;
  external?: boolean;
  defunct?: boolean;
  creaton?: Moment;
  creatby?: number;
  modifyon?: Moment;
  modifyby?: number;
  docid?: ISpecility;
}

export class Doctor implements IDoctor {
  constructor(
    public id?: number,
    public orgid?: number,
    public docid?: number,
    public docname?: string,
    public splid?: number,
    public licno?: string,
    public natid?: number,
    public education?: number,
    public consultsrvid?: number,
    public followupsrvid?: number,
    public external?: boolean,
    public defunct?: boolean,
    public creaton?: Moment,
    public creatby?: number,
    public modifyon?: Moment,
    public modifyby?: number,
    public docid?: ISpecility
  ) {
    this.external = this.external || false;
    this.defunct = this.defunct || false;
  }
}
