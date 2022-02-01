import { Component, OnDestroy, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { Task } from '../../../definitions/task'
import { ReadService } from '../../services/read.service';
import { WriteService } from '../../services/write.service';
import { SnackbarinputComponent } from '../snackbarinput/snackbarinput.component';

@Component({
  selector: 'app-tasks-view',
  templateUrl: './tasks-view.component.html',
  styleUrls: ['./tasks-view.component.css']
})
export class TasksViewComponent implements OnInit {


  tasks: Observable<Array<Task>> = new Observable();

  displayedColumns: string[] = ['ID', 'Channel Name', 'Channel ID', 'Status'];

  constructor(private router:Router, private readService:ReadService,
     private writeService: WriteService, private snackBar: MatSnackBar) { }


  ngOnInit(): void {
    this.tasks = this.readService.tasks;
  }

  openVideos(task: Task){
    this.writeService.selectTask(task);
    this.router.navigate(['videos']);
  }

  openSnackBar() {
    this.snackBar.openFromComponent(SnackbarinputComponent);
  }
}
