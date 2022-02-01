import { Component, Inject, } from '@angular/core';
import { MatSnackBarRef, MAT_SNACK_BAR_DATA } from '@angular/material/snack-bar';
import { TaskService } from '../../services/task.service';
import { WriteService } from '../../services/write.service';

@Component({
  selector: 'app-snackbarinput',
  templateUrl: './snackbarinput.component.html',
  styleUrls: ['./snackbarinput.component.css']
})
export class SnackbarinputComponent {


  constructor(
    public snackBarRef: MatSnackBarRef<SnackbarinputComponent>,
    @Inject(MAT_SNACK_BAR_DATA) public data: any,
    private taskService: TaskService,
    private writeService: WriteService
  ) { }

  createTask(youtubeId: string): void{
    this.taskService.newTask(youtubeId).subscribe(taskId=>{
      this.writeService.addTask({
        taskId: taskId,
        channelId: youtubeId,
        channelName: '',
        status: 'PENDING'
      })

      this.snackBarRef.dismiss();
    })
  }
}
