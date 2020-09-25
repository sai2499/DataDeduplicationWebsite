import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { FilepageService } from 'src/app/services/fileService/filepage.service';

export class ChunkData
{
  constructor(
    public shaId: number,    
    public rollingHash: string,
    public sha256Value: string ,
    public chucksize:any 
  ){}
}

@Component({
  selector: 'app-chunk-page',
  templateUrl: './chunk-page.component.html',
  styleUrls: ['./chunk-page.component.css']
})

 

export class ChunkPageComponent implements OnInit {

  constructor
  (
    public route: ActivatedRoute,
    public fileservice: FilepageService
  ) { }
  


  chunkdets: ChunkData[];
  public id:string;
  ngOnInit() 
  {    
    this.retrieveChunkData();
  }

  retrieveChunkData()
  {
    this.fileservice.retrieveAllChunks(1).subscribe(
      response => {
       this.chunkdets = response;
       console.log(response);
      }
    )
  }
  

}
