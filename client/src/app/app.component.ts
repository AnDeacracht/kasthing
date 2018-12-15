import { Component, OnInit } from '@angular/core';
import { HttpService } from './http.service';
import { Article } from './article';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  
  title = 'app';
  url = 'http://localhost:8080/api/articles'
  articles: Article[];

  constructor(private http: HttpService) {
  	
  }

  ngOnInit() {
  	this.getArticles(this.url)
  }

  getArticles(url: string) {
  	this.http.getArticles(url)
  		.subscribe((data: Article[]) => 
  			this.articles = [ ...data ])
  }

}
