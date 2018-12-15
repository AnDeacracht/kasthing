import { Component, OnInit } from '@angular/core';
import { Article } from '../article';

@Component({
  selector: 'app-article-form',
  templateUrl: './article-form.component.html',
  styleUrls: ['./article-form.component.css']
})
export class ArticleFormComponent implements OnInit {

  model: Article = {
  	title: "test",
  	content: "moar test"
  };

  submitted = false;

  onSubmit() {
  	this.submitted = true;
  }

  get diagnostic() {
  	return JSON.stringify(this.model);
  }

  constructor() { }

  ngOnInit() {
  }

}
