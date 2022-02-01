import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { TasksViewComponent } from './components/tasks-view/tasks-view.component';
import { VideosViewComponent } from './components/videos-view/videos-view.component';

const routes: Routes = [
  {path:'', component:TasksViewComponent},
  {path:'videos', component:VideosViewComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
