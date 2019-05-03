/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { GeneralMstDeleteDialogComponent } from 'app/entities/general-mst/general-mst-delete-dialog.component';
import { GeneralMstService } from 'app/entities/general-mst/general-mst.service';

describe('Component Tests', () => {
  describe('GeneralMst Management Delete Component', () => {
    let comp: GeneralMstDeleteDialogComponent;
    let fixture: ComponentFixture<GeneralMstDeleteDialogComponent>;
    let service: GeneralMstService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationTestModule],
        declarations: [GeneralMstDeleteDialogComponent]
      })
        .overrideTemplate(GeneralMstDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(GeneralMstDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(GeneralMstService);
      mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
      mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
    });

    describe('confirmDelete', () => {
      it('Should call delete service on confirmDelete', inject(
        [],
        fakeAsync(() => {
          // GIVEN
          spyOn(service, 'delete').and.returnValue(of({}));

          // WHEN
          comp.confirmDelete(123);
          tick();

          // THEN
          expect(service.delete).toHaveBeenCalledWith(123);
          expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
          expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
        })
      ));
    });
  });
});
