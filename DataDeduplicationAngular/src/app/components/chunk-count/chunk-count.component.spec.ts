import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ChunkCountComponent } from './chunk-count.component';

describe('ChunkCountComponent', () => {
  let component: ChunkCountComponent;
  let fixture: ComponentFixture<ChunkCountComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ChunkCountComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ChunkCountComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
