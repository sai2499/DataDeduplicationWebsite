import { TestBed } from '@angular/core/testing';

import { FilepageService } from './filepage.service';

describe('FilepageService', () => {
  let service: FilepageService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(FilepageService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
