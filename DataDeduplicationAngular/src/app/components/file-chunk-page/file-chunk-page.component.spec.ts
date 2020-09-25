import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FileChunkPageComponent } from './file-chunk-page.component';

describe('FileChunkPageComponent', () => {
  let component: FileChunkPageComponent;
  let fixture: ComponentFixture<FileChunkPageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FileChunkPageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FileChunkPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
