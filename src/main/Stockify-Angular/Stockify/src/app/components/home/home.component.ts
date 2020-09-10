import { Component, OnInit } from '@angular/core';
import { Portfolio } from 'src/app/classes/portfolio';
import { PortfolioService } from 'src/app/services/portfolio.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  portfolioNames: Array<Portfolio>;

  constructor(private portfolioService: PortfolioService) { }

  ngOnInit(): void {
    this.portfolioNames = new Array<Portfolio>();
    this.portfolioService.getPortfolios().subscribe(
      (response) => {
        console.log(response);
        this.portfolioNames = response;
      }
    );
    // const p = new Portfolio();
    // p.name = 'a';
    // p.total = 12.00;
    // p.available = p.total;
    // p.invested = 0.0;
    // this.portfolioNames.push(p);
    // const p1 = new Portfolio();
    // p1.name = 'b';
    // p1.total = 24.00;
    // p1.available = p1.total;
    // p1.invested = 0.0;
    // this.portfolioNames.push(p1);
  }

  addPortfolio()
  {
    const p = new Portfolio();
    const name = prompt('Enter new portfolio name.');
    p.name = name;
    const initAmount = prompt('Enter initial portfolio amount.');
    p.total = parseFloat(initAmount);
    p.available = p.total;
    p.invested = 0.0;
    this.portfolioService.addPortfolio(p).subscribe(
      (response) => {
        console.log(response);
        this.portfolioNames.push(response);
      }
    );
    console.log(this.portfolioNames);
  }

  selectPortfolio(portfolio: Portfolio)
  {
    sessionStorage.setItem('selectedPortfolio', portfolio.name);
  }

}
