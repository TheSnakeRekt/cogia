import { Action } from '@ngrx/store'
import { Task } from 'src/definitions/task';

export const ADD_TASK = '[TASKS] Add';
export const SELECT_TASK = '[TASKS] Select'
export const UPDATE_TASK = '[TASKS] Update'

export class AddTask implements Action{
  readonly type = ADD_TASK;

  constructor(public payload: Task){
  }
}

export class SelectTask implements Action{
  readonly type = SELECT_TASK;

  constructor(public payload: Task){
  }
}


export class UpdateTask implements Action{
  readonly type = UPDATE_TASK;

  constructor(public payload: Task){
  }
}

export type Actions = AddTask | SelectTask | UpdateTask
