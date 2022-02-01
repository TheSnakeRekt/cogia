import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import { Observable } from 'rxjs';
import { Task } from 'src/definitions/task';

@Injectable({
  providedIn: 'root'
})
export class TaskService {

  baseUrl: string = "http://localhost:8080/api/tasks/";

  constructor(private httpClient: HttpClient) {
  }

  getTasks():Observable<Array<Task>> {
    return this.httpClient.get<Array<Task>>(this.baseUrl);
  }

  newTask(channelId: string): Observable<string> {
    return this.httpClient.post<string>(`${this.baseUrl+channelId}`,{});
  }
}
