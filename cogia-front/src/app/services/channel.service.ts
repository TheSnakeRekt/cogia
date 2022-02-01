import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import { Observable } from 'rxjs';
import { Channel } from '../../definitions/task';

@Injectable({
  providedIn: 'root'
})
export class ChannelService {

  baseUrl: string = "http://localhost:8080/api/tasks/";

  constructor(private httpClient: HttpClient) { }

  getChannel(taskId: string): Observable<Channel> {
    return this.httpClient.get<Channel>(`${this.baseUrl+taskId}`);
  }
}
