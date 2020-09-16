import { Component, OnInit } from '@angular/core';
import { Portfolio } from 'src/app/classes/portfolio';
import { PortfolioService } from 'src/app/services/portfolio.service';
import { Stock } from 'src/app/classes/stock';
import { StockService } from 'src/app/services/stock.service';

@Component({
  selector: 'app-portfoliodashboard',
  templateUrl: './portfoliodashboard.component.html',
  styleUrls: ['./portfoliodashboard.component.scss']
})
export class PortfoliodashboardComponent implements OnInit {

  searchText: string;
  portfolio: Portfolio;
  stocksOwned: Array<Stock>;
  stocksSearch: Array<Stock>;
  stocksSearchResult: Array<Stock>;

  constructor(private portfolioService: PortfolioService, private stockService: StockService) {
    this.portfolio = new Portfolio();
    this.stocksOwned = new Array<Stock>();
    const s = new Stock();
    s.symbol = 'TOPS';
    s.price = 100.00;
    this.stocksOwned.push(s);
    this.stocksOwned.push(s);
    this.stocksOwned.push(s);
    this.stocksOwned.push(s);
    this.stocksOwned.push(s);
    this.stocksSearch = new Array<Stock>();
    this.stocksSearchResult = new Array<Stock>();
    this.portfolio.name = sessionStorage.getItem('selectedPortfolio');
    this.portfolio.total = 100.00;
    this.portfolio.invested = 50.00;
    this.portfolio.available = 50.00;
    this.portfolioService.getPortfolio(this.portfolio.name).subscribe(
      (response) => {
        this.portfolio = response;
        this.stockService.getStocksOwned(this.portfolio.name).subscribe(
          (sOwned) => {
            this.stocksOwned = sOwned;
          }
        );
      }
    );
  }

  ngOnInit(): void {
  }

  search()
  {
    const s = new Stock();
    s.symbol = this.searchText;
    this.stocksSearch.push(s);
    //this.stocksSearchResult.push(s);
    this.stockService.getStocksSearch(this.stocksSearch).subscribe(
      (sOwned) => {
        console.log(sOwned);
        this.stocksSearchResult = sOwned;
      }
    );
  }
}
