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
  stocksOwnedQtY: Array<number>;
  stocksOwnedQtySell: Array<number>;
  stocksSearch: Array<Stock>;
  stocksSearchResult: Array<Stock>;
  stocksSearchResultQtyBuy: Array<number>;

  constructor(private portfolioService: PortfolioService, private stockService: StockService) {
    this.portfolio = new Portfolio();
    this.stocksOwned = new Array<Stock>();
    this.stocksOwnedQtySell = new Array<number>();
    this.stocksSearchResultQtyBuy = new Array<number>();
    this.stocksOwnedQtY = new Array<number>();
    // const s = new Stock();
    // s.symbol = 'TOPS';
    // s.price = 100.00;
    // this.stocksOwned.push(s);
    // this.stocksOwned.push(s);
    // this.stocksOwned.push(s);
    // const s1 = new Stock();
    // s1.symbol = 'TOPSI';
    // s1.price = 100.00;
    // this.stocksOwned.push(s);
    // this.stocksOwned.push(s1);
    this.stocksSearch = new Array<Stock>();
    this.stocksSearchResult = new Array<Stock>();
    this.portfolio.name = sessionStorage.getItem('selectedPortfolio');
    // this.portfolio.total = 100.00;
    // this.portfolio.invested = 50.00;
    // this.portfolio.available = 50.00;
    this.portfolioService.getPortfolio(this.portfolio.name).subscribe(
      (response) => {
        this.portfolio = response;
        console.log('Quantity for stocks');
        let index = 0;
        for (const qty of this.portfolio.stocksOwned.keys())
        {
          console.log(this.portfolio.stocksOwned.get(qty).length);
          this.stocksOwnedQtY[index] = this.portfolio.stocksOwned.get(qty).length;
          index++;
        }
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
    s.price = 100.00;
    this.stocksSearch.push(s);
    //this.stocksSearchResult.push(s);
    this.stockService.getStocksSearch(this.stocksSearch).subscribe(
      (sOwned) => {
        console.log(sOwned);
        this.stocksSearchResult = sOwned;
      }
    );
  }

  sellStock(index: number)
  {
    console.log(this.stocksOwned[index].symbol);
    console.log(this.stocksOwnedQtySell[index]);
    this.stockService.sellStock(this.portfolio.name, this.stocksOwned[index].symbol, this.stocksOwnedQtySell[index]).subscribe(
      (response) => {
        console.log(response);
        this.portfolio = response;
      }
    );
  }

  buyStock(index: number)
  {
    console.log(this.stocksSearchResult[index].symbol);
    console.log(this.stocksSearchResultQtyBuy[index]);
    this.stockService.buyStock(this.portfolio.name, this.stocksSearchResult[index].symbol, this.stocksSearchResultQtyBuy[index]).subscribe(
      (response) => {
        console.log(response);
        this.portfolio = response;
      }
    );
  }
}
