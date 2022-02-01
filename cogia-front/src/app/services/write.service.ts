import { Injectable } from '@angular/core';
import { AppState } from 'src/app.state';
import { Channel, Task } from 'src/definitions/task';
import { Store } from '@ngrx/store'
import * as TaskActions from '../actions/task.actions'
import * as ChannelActions from '../actions/channel.actions'

@Injectable({
  providedIn: 'root'
})
export class WriteService {

  constructor(private store: Store<AppState>) { }

  selectTask(task:Task) {
    this.store.dispatch(new TaskActions.SelectTask(task));
  }

  addTask(task:Task){
    this.store.dispatch(new TaskActions.AddTask(task));
  }


  updateTask(task: Task){
    this.store.dispatch(new TaskActions.UpdateTask(task));
  }

  setChannel(channel:Channel){
    this.store.dispatch(new ChannelActions.SetChannel(channel));
  }
}
