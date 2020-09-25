import { Component, OnInit, ElementRef, ViewChild } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { FilepageService } from 'src/app/services/fileService/filepage.service';
import { UploadfileService } from 'src/app/services/uploadService/uploadfile.service';

export class Filedata
{
  constructor(
    public fileid: number,
    public filename:String,
    public filedate:Date,
    public fileSize:any)
    {}
}

@Component({
  selector: 'app-file-page',
  templateUrl: './file-page.component.html',
  styleUrls: ['./file-page.component.css']
})
export class FilePageComponent implements OnInit {

  filedets:Filedata[];
  public userFile:any=File;
  public uploadMsg: string;
  
  constructor(
    private router: Router,
    private route:ActivatedRoute,
    private filepageservice:FilepageService,
    private uploadService: UploadfileService
  ) { }

  public id:string;
  public deleteMsg: string;
  public showAlert: boolean=false;
  public showDelete: boolean=false;
  public showUpload: boolean=false;

  ngOnInit(){
    this.id=this.route.snapshot.paramMap.get("id")
    this.retrivedatafile()  
  } 
  
  closeAlert() {
    this.showAlert=false;
  }

  retrivedatafile()
  {    
    this.filepageservice.retrivefiledetails(this.id).subscribe(
      response=>{              
        this.filedets=response
      }
    )
  }

  deleteFileData(id)
  {
    this.filepageservice.deleteFileData(id).subscribe(
      response=>
      {
        this.deleteMsg="File has been deleted successfully";
        this.showAlert=true;
        this.showDelete=true;
        this.retrivedatafile();
      }
    )
  }  

  fileChuckData(id)
  {    
    this.router.navigate(['fileChunkpage',id]);
  }  

  onSelectFile(event)
  {
    const selectFiles=event.target.files[0];
    this.userFile=selectFiles;
  }

  
  uploadFile()
  {
    const formData=new FormData();
    formData.append('file',this.userFile);
    formData.append('id',this.id);
    this.uploadService.uploadFile(formData).subscribe((response)=>
    {
      this.uploadMsg=response;
      this.showAlert=true;
      this.showUpload=true;
      this.retrivedatafile();
    })
  }

  downloadFileData(id)  
  {
    this.filepageservice.downloadFileDataService(id).subscribe(
      (response)=>
      {
        console.log(response);
    })
  }
  
}
