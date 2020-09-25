import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { MainpageComponent } from './components/mainpage/mainpage.component';
import { LoginComponent } from './components/login/login.component';
import { SignupComponent } from './components/signup/signup.component';
import { OptionPageComponent } from './components/option-page/option-page.component';
import { FilePageComponent } from './components/file-page/file-page.component';
import { ChunkPageComponent } from './components/chunk-page/chunk-page.component';
import { FileChunkPageComponent } from './components/file-chunk-page/file-chunk-page.component';
import { ChunkCountComponent } from './components/chunk-count/chunk-count.component';
import { UpdateFileComponent } from './components/update-file/update-file.component';


const routes: Routes = [
{path:"mainpage",component:MainpageComponent},
{path:"login",component:LoginComponent},
{path:"signup",component:SignupComponent},
{path:"filepage/:id",component:FilePageComponent},
{path:"optionpage/:id/:name",component:OptionPageComponent},
{path:"option",component:OptionPageComponent},
{path:"allChuckpage/:id",component:ChunkPageComponent},
{path:"fileChunkpage/:id",component:FileChunkPageComponent},
{path:"chunkCount",component:ChunkCountComponent},
{path:"updateFile/:id",component:UpdateFileComponent},
{path:"**",component:MainpageComponent}];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
