import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AppState } from 'src/app.state';
import { Channel, Task } from 'src/definitions/task';
import { Store } from '@ngrx/store'


@Injectable({
  providedIn: 'root'
})
export class ReadService {

  tasks: Observable<Array<Task>>;
  current: Observable<Task>;
  channel: Observable <Channel>;

  constructor(private store: Store<AppState>) {
    this.tasks = store.select('tasks');
    this.current = store.select('currentTask');
    this.channel = store.select('currentChannel');
  }
}
