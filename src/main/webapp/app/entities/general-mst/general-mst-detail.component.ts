import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IGeneralMst } from 'app/shared/model/general-mst.model';

@Component({
  selector: 'jhi-general-mst-detail',
  templateUrl: './general-mst-detail.component.html'
})
export class GeneralMstDetailComponent implements OnInit {
  generalMst: IGeneralMst;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ generalMst }) => {
      this.generalMst = generalMst;
    });
  }

  previousState() {
    window.history.back();
  }
}
