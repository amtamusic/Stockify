import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  portfolioNames: Array<string>;

  constructor() { }

  ngOnInit(): void {
    this.portfolioNames = new Array<string>();
  }

  addPortfolio()
  {
    const name = prompt('Enter new portfolio name.');
    this.portfolioNames.push(name);
    console.log(this.portfolioNames);
  }

}
