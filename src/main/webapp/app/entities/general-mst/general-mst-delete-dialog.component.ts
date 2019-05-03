import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IGeneralMst } from 'app/shared/model/general-mst.model';
import { GeneralMstService } from './general-mst.service';

@Component({
  selector: 'jhi-general-mst-delete-dialog',
  templateUrl: './general-mst-delete-dialog.component.html'
})
export class GeneralMstDeleteDialogComponent {
  generalMst: IGeneralMst;

  constructor(
    protected generalMstService: GeneralMstService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.generalMstService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'generalMstListModification',
        content: 'Deleted an generalMst'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-general-mst-delete-popup',
  template: ''
})
export class GeneralMstDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ generalMst }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(GeneralMstDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.generalMst = generalMst;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/general-mst', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/general-mst', { outlets: { popup: null } }]);
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
