import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { FilepageService } from 'src/app/services/fileService/filepage.service';

export class ChunkCount
{
  constructor(
    public rollingHash: string,
    public chunkCount: number)
    {}
}

@Component({
  selector: 'app-chunk-count',
  templateUrl: './chunk-count.component.html',
  styleUrls: ['./chunk-count.component.css']
})
export class ChunkCountComponent implements OnInit {

  chunkCount: ChunkCount[]

  constructor(
    private route:ActivatedRoute,
    public fileservice: FilepageService) 
    { }
  public id:string;

  ngOnInit() 
  {
    this.id=this.route.snapshot.paramMap.get("id");
    this.retrivechunkcountdata();
  }

  retrivechunkcountdata()
  {
    this.fileservice.retrieveChunkCount().subscribe(
      data=> {
        this.chunkCount=data;
        console.log(data);
      }
    )
  }
}
