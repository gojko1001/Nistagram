import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AgentShopComponent } from './agent-shop.component';

describe('AgentShopComponent', () => {
  let component: AgentShopComponent;
  let fixture: ComponentFixture<AgentShopComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AgentShopComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AgentShopComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
