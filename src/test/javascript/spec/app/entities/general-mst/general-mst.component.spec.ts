/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { GeneralMstComponent } from 'app/entities/general-mst/general-mst.component';
import { GeneralMstService } from 'app/entities/general-mst/general-mst.service';
import { GeneralMst } from 'app/shared/model/general-mst.model';

describe('Component Tests', () => {
  describe('GeneralMst Management Component', () => {
    let comp: GeneralMstComponent;
    let fixture: ComponentFixture<GeneralMstComponent>;
    let service: GeneralMstService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationTestModule],
        declarations: [GeneralMstComponent],
        providers: []
      })
        .overrideTemplate(GeneralMstComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(GeneralMstComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(GeneralMstService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new GeneralMst(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.generalMsts[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
