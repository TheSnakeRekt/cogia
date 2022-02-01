import { Action } from '@ngrx/store'
import { Channel } from 'src/definitions/task';

export const SET_CHANNEL = '[Channel] Set';


export class SetChannel implements Action{
  readonly type = SET_CHANNEL;

  constructor(public payload: Channel){
  }
}

export type Actions = SetChannel;
