import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UploadedPropertyComponent } from './uploaded-property.component';

describe('UploadedPropertyComponent', () => {
  let component: UploadedPropertyComponent;
  let fixture: ComponentFixture<UploadedPropertyComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [UploadedPropertyComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(UploadedPropertyComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
