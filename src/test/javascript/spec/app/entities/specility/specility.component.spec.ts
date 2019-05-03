/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { SpecilityComponent } from 'app/entities/specility/specility.component';
import { SpecilityService } from 'app/entities/specility/specility.service';
import { Specility } from 'app/shared/model/specility.model';

describe('Component Tests', () => {
  describe('Specility Management Component', () => {
    let comp: SpecilityComponent;
    let fixture: ComponentFixture<SpecilityComponent>;
    let service: SpecilityService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationTestModule],
        declarations: [SpecilityComponent],
        providers: []
      })
        .overrideTemplate(SpecilityComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(SpecilityComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(SpecilityService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new Specility(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.specilities[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
