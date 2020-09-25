import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { FilepageService } from 'src/app/services/fileService/filepage.service';

@Component({
  selector: 'app-option-page',
  templateUrl: './option-page.component.html',
  styleUrls: ['./option-page.component.css']
})
export class OptionPageComponent implements OnInit {

  constructor(
    private router:Router,
    private activeroute: ActivatedRoute,
    private fileservice: FilepageService
  ) { }

  public id:string;
  public name: string;
  ngOnInit() {
    this.id=this.activeroute.snapshot.paramMap.get("id")
    this.name=this.activeroute.snapshot.paramMap.get("name")
  }
  filepage()
  {
    console.log("clicked");
    this.router.navigate(['filepage',this.id]);
  }

  deleteAccount()
  {
    this.fileservice.deleteUserDate(this.id).subscribe(
      response => {
        this.router.navigate(["mainpage"]);
        console.log("User deleted");
      }
    )
  }
  
  logoutUser()
  {
    this.router.navigate(['mainpage']);
  }

  displayAllChuck()
  {    
    this.fileservice.retrieveAllChunks(this.id).subscribe(
      response => {
        this.router.navigate(['allChuckpage',1]);
      }
    )
    console.log(this.id);
  }

  chuckcount()
  {
    this.router.navigate(['chunkCount']);
  }

  updatepage()
  {
    this.router.navigate(['updateFile',this.id]);
  }
}
