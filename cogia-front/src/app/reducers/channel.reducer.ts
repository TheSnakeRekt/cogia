import { State } from "@ngrx/store";
import { Channel } from "src/definitions/task"
import * as ChannelActions from '../actions/channel.actions'

let currentChannel: Channel = {

} as Channel;


export const channelReducer = (state: Channel = currentChannel, action: ChannelActions.Actions):Channel => {
  switch(action.type){
    case ChannelActions.SET_CHANNEL:
      currentChannel = action.payload;
      return currentChannel;
    default:
      return state;
  }
}
