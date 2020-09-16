import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Portfolio } from '../classes/portfolio';
import { Stock } from '../classes/stock';

@Injectable({
  providedIn: 'root'
})
export class StockService {

  constructor(private http: HttpClient) { }

  private readonly STOCKS_URL_GET = 'http://localhost:8080/stock/get';
  private readonly STOCKS_URL_BUY = 'http://localhost:8080/stock/buy';
  private readonly STOCKS_URL_SELL = 'http://localhost:8080/stock/sell';

  getStocksSearch(stocks: Array<Stock>)
  {
    return this.http.post<Array<Stock>>
    (
      this.STOCKS_URL_GET, stocks);
  }

  getStocksOwned(portfolio: string)
  {
    return this.http.get<Array<Stock>>
    (
      this.STOCKS_URL_GET + '/' + portfolio, {headers: {
        'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
      }} );
  }

  buyStock(portfolio: string, stock: string, quantity: number)
  {
    return this.http.post<Portfolio>
    (
      this.STOCKS_URL_BUY + '/' + portfolio + '/' + stock + '/' + quantity, {}, {headers: {
        'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
      }} );
  }

  sellStock(portfolio: string, stock: string, quantity: number)
  {
    return this.http.post<Portfolio>
    (
      this.STOCKS_URL_SELL + '/' + portfolio + '/' + stock + '/' + quantity, {}, {headers: {
        'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
      }} );
  }

}
