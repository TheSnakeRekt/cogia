import { ActionReducerMap } from "@ngrx/store";
import { currentTaskReducer, tasksReducer } from "./app/reducers/task.reducer";
import { channelReducer } from "./app/reducers/channel.reducer";
import { Channel, Task } from "./definitions/task";

export interface AppState {
  readonly currentTask: Task;
  readonly tasks : Array<Task>;
  readonly currentChannel: Channel;
}

export const reducers: ActionReducerMap<AppState, any> = {
    tasks: tasksReducer,
    currentTask: currentTaskReducer,
    currentChannel : channelReducer,
};
