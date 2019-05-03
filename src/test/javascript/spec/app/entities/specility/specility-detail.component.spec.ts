/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { SpecilityDetailComponent } from 'app/entities/specility/specility-detail.component';
import { Specility } from 'app/shared/model/specility.model';

describe('Component Tests', () => {
  describe('Specility Management Detail Component', () => {
    let comp: SpecilityDetailComponent;
    let fixture: ComponentFixture<SpecilityDetailComponent>;
    const route = ({ data: of({ specility: new Specility(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationTestModule],
        declarations: [SpecilityDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(SpecilityDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(SpecilityDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.specility).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
