import { Component, OnDestroy, OnInit } from '@angular/core';
import { ReadService } from './services/read.service';
import { TaskService } from './services/task.service';
import { WriteService } from './services/write.service';
import {map, tap} from 'rxjs/operators'
import { Task } from 'src/definitions/task';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'cogia-front';

  constructor(private taskService:TaskService, private readComponent: ReadService, private writeComponent: WriteService){

  }

  ngOnInit(): void {
    let obs$ = this.readComponent.tasks?.pipe(
      tap<Array<Task>>(tasks=> {
        if(tasks.length < 1){
          let request = this.taskService.getTasks().pipe(
            tap<Array<Task>>(resp =>{
              resp.forEach(task=>{
               this.writeComponent.addTask(task)
              })
            })
          );

          request.subscribe();
        }
      })
    )

    let sub = obs$?.subscribe();

    setTimeout(async ()=>{
      let request = this.taskService.getTasks().pipe(
        tap<Array<Task>>(resp =>{
          resp.forEach(task=>{
           this.writeComponent.updateTask(task)
          })
        })
      );

     await request.toPromise();
     this.ngOnInit()
    }, 2500)
  }
}

