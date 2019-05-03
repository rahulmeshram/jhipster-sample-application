import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ISpecility } from 'app/shared/model/specility.model';
import { SpecilityService } from './specility.service';

@Component({
  selector: 'jhi-specility-delete-dialog',
  templateUrl: './specility-delete-dialog.component.html'
})
export class SpecilityDeleteDialogComponent {
  specility: ISpecility;

  constructor(protected specilityService: SpecilityService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.specilityService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'specilityListModification',
        content: 'Deleted an specility'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-specility-delete-popup',
  template: ''
})
export class SpecilityDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ specility }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(SpecilityDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.specility = specility;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/specility', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/specility', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          }
        );
      }, 0);
    });
  }

  ngOnDestroy() {
    this.ngbModalRef = null;
  }
}
