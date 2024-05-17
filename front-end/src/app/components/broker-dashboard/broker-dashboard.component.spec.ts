import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BrokerDashboardComponent } from './broker-dashboard.component';

describe('BrokerDashboardComponent', () => {
  let component: BrokerDashboardComponent;
  let fixture: ComponentFixture<BrokerDashboardComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [BrokerDashboardComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(BrokerDashboardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
