import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ChunkData } from 'src/app/components/chunk-page/chunk-page.component';
import { Filedata } from 'src/app/components/file-page/file-page.component';
import { ChunkCount } from 'src/app/components/chunk-count/chunk-count.component';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class FilepageService {
  constructor(private http: HttpClient) { }
  retrivefiledetails(id)
  {
    return this.http.get<Filedata[]>(`http://localhost:9090/data/${id}/filepage`);
  }

  deleteFileData(id)
  {
    return this.http.get(`http://localhost:9090/data/filepage/${id}/deletefile`);
  }

  deleteUserDate(id)
  {
    return this.http.get(`http://localhost:9090/data/user/${id}/deleteuser`);
  }

  downloadFileDataService(id)
  {
    return this.http.get(`http://localhost:9090/data/user/${id}/download`,{responseType: 'text'});    
  }

  retrieveAllChunks(id)
  {
    return this.http.get<ChunkData[]>(`http://localhost:9090/data/${id}/allChunks`);
  }

  retrieveFileChunks(getid)
  {
    return this.http.get<ChunkData[]>(`http://localhost:9090/data/${getid}/fileChunks`)
  }

  retrieveChunkCount()
  {
    return this.http.get<ChunkCount[]>(`http://localhost:9090/data/ChunkCount`)
  }

  retrivefileVersiondetails(id)
  {
    return this.http.get<Filedata[]>(`http://localhost:9090/data/${id}/fileVersionpage`);
  }
}
