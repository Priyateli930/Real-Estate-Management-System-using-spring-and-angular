import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PropertyTransactionsComponent } from './property-transactions.component';

describe('PropertyTransactionsComponent', () => {
  let component: PropertyTransactionsComponent;
  let fixture: ComponentFixture<PropertyTransactionsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [PropertyTransactionsComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(PropertyTransactionsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
