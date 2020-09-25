import { Component, OnInit } from '@angular/core';
import { Filedata } from '../file-page/file-page.component';
import { Router, ActivatedRoute } from '@angular/router';
import { FilepageService } from 'src/app/services/fileService/filepage.service';
import { UploadfileService } from 'src/app/services/uploadService/uploadfile.service';
import { formatDate } from '@angular/common';

@Component({
  selector: 'app-update-file',
  templateUrl: './update-file.component.html',
  styleUrls: ['./update-file.component.css']
})
export class UpdateFileComponent implements OnInit {
  
  filedets:Filedata[];
  public userFile:any=File;
  public uploadMsg: string;
  

  constructor(
    private router: Router,
    private route:ActivatedRoute,
    private filepageservice:FilepageService,
    private uploadService: UploadfileService) { }

    public id:string;
    public deleteMsg: string;
    public showAlert: boolean=false;
    public showDelete: boolean=false;
    public showUpload: boolean=false;
  
    ngOnInit(){
      this.id=this.route.snapshot.paramMap.get("id")
      this.retriveVersiondatafile()  
    } 

    closeAlert() {
      this.showAlert=false;
    }

    retriveVersiondatafile()
    {    
      this.filepageservice.retrivefileVersiondetails(this.id).subscribe(
        response=>{              
          this.filedets=response
        }
      )
    }

  uploadVersionFile()
  {
    const formData=new FormData();
    formData.append('file',this.userFile);
    formData.append('id',this.id);
    this.uploadService.updateFile(formData).subscribe((response)=>
    {
      this.uploadMsg=response;
      this.showAlert=true;
      this.showUpload=true;   
      this.retriveVersiondatafile();      
    })
  }

  onSelectFile(event)
  {
    const selectFiles=event.target.files[0];
    this.userFile=selectFiles;
  }

  fileVersionChuckData(id)
  {
    this.router.navigate(['fileChunkpage',id]);
  }

  deleteVersionFileData(id)
  {
    this.filepageservice.deleteFileData(id).subscribe(response=>
    {
      this.deleteMsg="File has been deleted successfully";
      this.showAlert=true;
      this.showDelete=true;
      this.retriveVersiondatafile();
    })
  }

  downloadVersionFileData(id)
  {
    this.filepageservice.downloadFileDataService(id).subscribe
    (
      (response)=>
      {
        console.log(response)
    })
  }

}
