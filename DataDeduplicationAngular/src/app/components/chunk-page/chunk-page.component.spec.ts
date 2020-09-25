import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ChunkPageComponent } from './chunk-page.component';

describe('ChunkPageComponent', () => {
  let component: ChunkPageComponent;
  let fixture: ComponentFixture<ChunkPageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ChunkPageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ChunkPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
