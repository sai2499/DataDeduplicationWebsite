import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ChunkData } from '../chunk-page/chunk-page.component';
import { FilepageService } from 'src/app/services/fileService/filepage.service';

@Component({
  selector: 'app-file-chunk-page',
  templateUrl: './file-chunk-page.component.html',
  styleUrls: ['./file-chunk-page.component.css']
})
export class FileChunkPageComponent implements OnInit {

  chunkData: ChunkData[]

  constructor(
    private route:ActivatedRoute,
    public fileservice: FilepageService) 
    { }
  public id:string;

  ngOnInit() 
  {
    this.id=this.route.snapshot.paramMap.get("id");
    this.retrivechunkdata();
  }

  retrivechunkdata()
  {
    this.fileservice.retrieveFileChunks(this.id).subscribe(
      data => {
        this.chunkData=data;
        console.log(this.id);
        console.log(this.chunkData);
      }
    )
  }
}
