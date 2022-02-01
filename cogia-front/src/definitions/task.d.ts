export interface Video {
  id:number,
  videoId:string,
  name:string,
  url:string,
  playlistId:string,
}

export interface Channel {
  id: number,
  name: string,
  url: string,
  videos: Array<Video>
}

export interface Task {
  taskId: string,
  channelName: string,
  channelId: string,
  status: string,
}
