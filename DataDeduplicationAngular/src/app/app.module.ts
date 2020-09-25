import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { MainpageComponent } from './components/mainpage/mainpage.component';
import { LoginComponent } from './components/login/login.component';
import { SignupComponent } from './components/signup/signup.component';
import { OptionPageComponent } from './components/option-page/option-page.component';
import { FilePageComponent } from './components/file-page/file-page.component';
import { ChunkPageComponent } from './components/chunk-page/chunk-page.component';
import { FilepageService } from './services/fileService/filepage.service';
import { LoginService } from './services/loginService/login.service';
import { SignupService } from './services/signupService/signup.service';
import { UploadfileService } from './services/uploadService/uploadfile.service';
import { HttpClientModule} from '@angular/common/http';
import { ReactiveFormsModule } from '@angular/forms';
import { FileChunkPageComponent } from './components/file-chunk-page/file-chunk-page.component';
import { ChunkCountComponent } from './components/chunk-count/chunk-count.component';
import { UpdateFileComponent } from './components/update-file/update-file.component';

@NgModule({
  declarations: [
    AppComponent,
    MainpageComponent,
    LoginComponent,
    SignupComponent,
    OptionPageComponent,
    FilePageComponent,
    ChunkPageComponent,
    FileChunkPageComponent,
    ChunkCountComponent,
    UpdateFileComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    ReactiveFormsModule
  ],
  providers: [FilepageService,LoginService,SignupService,UploadfileService],
  bootstrap: [AppComponent]
})
export class AppModule { }
