import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ISpecility } from 'app/shared/model/specility.model';

@Component({
  selector: 'jhi-specility-detail',
  templateUrl: './specility-detail.component.html'
})
export class SpecilityDetailComponent implements OnInit {
  specility: ISpecility;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ specility }) => {
      this.specility = specility;
    });
  }

  previousState() {
    window.history.back();
  }
}
