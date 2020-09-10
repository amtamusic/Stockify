import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Portfolio } from '../classes/portfolio';
import { Observable } from 'rxjs';
@Injectable({
  providedIn: 'root'
})
export class PortfolioService {

  private readonly PORTFOLIOS_URL_GET = 'http://localhost:8080/portfolio/get';

  private readonly PORTFOLIOS_URL_POST = 'http://localhost:8080/portfolio/add';

  constructor(private http: HttpClient) { }


  getPortfolios()
  {
    return this.http.get<Array<Portfolio>>
    (
      this.PORTFOLIOS_URL_GET, {headers: {
        'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
      }
      });
  }

  addPortfolio(portfolio: Portfolio)
  {
    return this.http.post<Portfolio>
    (
      this.PORTFOLIOS_URL_POST, portfolio);
  }
}
