import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Article } from './article'
import { Observable, of } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class HttpService {

  constructor(private http: HttpClient) { }

  getArticles(url: string): Observable<Article[]> {
  	return this.http.get<Article[]>(url);
  }

}
