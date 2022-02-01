import {  AfterViewInit, Component, OnInit } from '@angular/core';
import {  Observable, Subject } from 'rxjs';
import { tap } from 'rxjs/internal/operators/tap';
import { Channel, Task, Video } from 'src/definitions/task';
import { ChannelService } from '../../services/channel.service';
import { ReadService } from '../../services/read.service';
import { PageEvent } from '@angular/material/paginator';
import { WriteService } from '../../services/write.service';

@Component({
  selector: 'app-videos-view',
  templateUrl: './videos-view.component.html',
  styleUrls: ['./videos-view.component.css']
})
export class VideosViewComponent implements OnInit, AfterViewInit {


  channel$!: Observable<Channel>;
  video$: Subject<Video[]> = new Subject();

  channel!: Channel;

  allVideos!: Video[];
  videos: Video[] = [];

  displayedColumns: string[] = ['Video ID', 'Video Title', 'URL'];
  length!: number;
  pageSize = 10;
  pageSizeOptions: number[] = [5, 10, 25, 50];

  pageEvent!: PageEvent

  constructor(private readService:ReadService,
    private channelService: ChannelService,
    private writeService: WriteService) {
  }

  ngAfterViewInit(): void {
    this.video$.subscribe(vids=>{
      this.videos = vids;
    });
  }

  ngOnInit(): void {

    let sub = this.readService.current?.pipe<Task>(
      tap(task => this.channel$ = this.channelService.getChannel(task.taskId))
    )

    sub?.subscribe();

    this.channel$ = this.channel$?.pipe(tap(channel=>{
      this.writeService.setChannel(channel);
      this.channel = channel;
      this.allVideos = this.channel.videos;

      this.length = this.allVideos.length;
      let vids  = []
      for(let i = 0; i < this.pageSize; i++){
        if(!this.allVideos[i].id){
          continue;
        }

        vids.push(this.allVideos[i]);
      }

      this.video$.next(vids)
    }));

    this.channel$.subscribe();
  }


  onPaginateChange(event:PageEvent){
    this.videos = [];
    this.pageSize = event.pageSize

    let startIndex = event.pageIndex * event.pageSize + 1;
    let lastItem = event.pageIndex * event.pageSize + event.pageSize + 1;

    for(let i = startIndex; i < lastItem; i++){
      if(i >= this.length){
        break;
      }

      this.videos.push(this.allVideos[i]);
    }
  }

  getVideos(): Video[]{
    return this.videos;
  }
}
