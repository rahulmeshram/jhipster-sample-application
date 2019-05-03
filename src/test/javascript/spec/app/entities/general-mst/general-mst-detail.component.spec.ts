/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { GeneralMstDetailComponent } from 'app/entities/general-mst/general-mst-detail.component';
import { GeneralMst } from 'app/shared/model/general-mst.model';

describe('Component Tests', () => {
  describe('GeneralMst Management Detail Component', () => {
    let comp: GeneralMstDetailComponent;
    let fixture: ComponentFixture<GeneralMstDetailComponent>;
    const route = ({ data: of({ generalMst: new GeneralMst(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationTestModule],
        declarations: [GeneralMstDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(GeneralMstDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(GeneralMstDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.generalMst).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
