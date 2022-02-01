import { State } from "@ngrx/store";
import { Task } from "src/definitions/task"
import * as TaskActions from '../actions/task.actions'

let currentTask: Task = {

} as Task;

const updateTaskMapper = (current:Task, newTask:Task) =>{
    if(current.taskId == newTask.taskId){
      return newTask;
    }
    return current;
}

export const tasksReducer = (state: Array<Task> = [], action: TaskActions.Actions):Array<Task> => {
  switch(action.type){
    case TaskActions.ADD_TASK:
      return [...state, action.payload];
    case TaskActions.UPDATE_TASK:
      return state.map(task=>updateTaskMapper(task, action.payload));
    default:
      return state;
  }
}

export const currentTaskReducer = (state: Task = currentTask, action: TaskActions.Actions): Task => {
  switch(action.type){
    case TaskActions.SELECT_TASK:
      currentTask = action.payload;
      return action.payload;
    default:
      return state;
  }
}
