import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-portfoliodashboard',
  templateUrl: './portfoliodashboard.component.html',
  styleUrls: ['./portfoliodashboard.component.scss']
})
export class PortfoliodashboardComponent implements OnInit {

  portfolioName: string;

  constructor() {
    this.portfolioName = sessionStorage.getItem('portfolioName');
  }

  ngOnInit(): void {
  }

}
