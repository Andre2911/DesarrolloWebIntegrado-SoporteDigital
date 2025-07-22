import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SeguimientoSolictiudComponent } from './seguimiento-solictiud.component';

describe('SeguimientoSolictiudComponent', () => {
  let component: SeguimientoSolictiudComponent;
  let fixture: ComponentFixture<SeguimientoSolictiudComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SeguimientoSolictiudComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SeguimientoSolictiudComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
