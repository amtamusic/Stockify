package com.dismoor.andytech.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dismoor.andytech.models.Portfolio;
import com.dismoor.andytech.models.Stock;
import com.dismoor.andytech.models.StockJSON;
import com.dismoor.andytech.services.StockService;
import com.dismoor.andytech.services.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@CrossOrigin
public class StockController {

	@Autowired
	private StockService stockService;

	@Autowired
	private UserService userService;

	@GetMapping("/stock/{stock}")
	public String loginPage(@PathVariable(name = "stock") String stock) throws IOException {
		System.out.println("Stock: " + stock);
		String stockJson = stockService.getStock(stock);
		ObjectMapper objectMapper = new ObjectMapper();
		StockJSON response = objectMapper.readValue(stockJson, StockJSON.class);
		try {
			response.getProfile().getPrice();
		} catch (NullPointerException e) {
			return null;
		}
		Stock sto = stockService.convertJSONToStock(response);
		return stockService.saveStock(sto).toString();
	}

	@GetMapping("/stock/get/{portfolio}")
	public List<Stock> getOwnedStocks(HttpServletRequest request, @PathVariable(name = "portfolio") String portfolio)
			throws IOException {
		System.out.println("Getting stocks for: " + portfolio);

		List<Portfolio> portfolios = userService.getUser(request.getUserPrincipal().getName()).getPortfolios();
		Portfolio currentPortfolio = null;
		for (int i = 0; i < portfolios.size(); i++) {
			if (portfolios.get(i).getName().equals(portfolio)) {
				currentPortfolio = portfolios.get(i);
			}
		}
		if (currentPortfolio == null) {
			return null;
		}
		List<Stock> stocks = new ArrayList<>();
		for (String key : currentPortfolio.getStocksOwned().keySet()) {
			stocks.add(stockService.getStockBySymbol(key));
		}
		return stocks;
	}

	@PostMapping("/stock/get")
	public List<Stock> getStocks(HttpServletRequest request, @RequestBody List<Stock> stocks) throws IOException {
		System.out.println("Getting stock info for requested stocks");

		List<Stock> stocksInfo = new ArrayList<>();
		for (Stock s : stocks) {
			stocksInfo.add(stockService.getStockBySymbol(s.getSymbol()));
			System.out.println(stockService.getStockBySymbol(s.getSymbol()).toString());
		}
		return stocksInfo;
	}

	@PostMapping("/stock/buy/{portfolio}/{stock}/{quantity}")
	public Portfolio buyStocks(HttpServletRequest request, @PathVariable(name = "portfolio") String portfolio,
			@PathVariable(name = "stock") String stock, @PathVariable(name = "quantity") int quantity)
			throws IOException {
		System.out.println("Getting stocks for: " + portfolio);

		List<Portfolio> portfolios = userService.getUser(request.getUserPrincipal().getName()).getPortfolios();
		Portfolio currentPortfolio = null;
		for (int i = 0; i < portfolios.size(); i++) {
			if (portfolios.get(i).getName().equals(portfolio)) {
				currentPortfolio = portfolios.get(i);
			}
		}
		Stock s = stockService.getStockBySymbol(stock);
		for (int i = 0; i < quantity; i++) {
			double tempPrice = s.getPrice();
			currentPortfolio.setAvailable(currentPortfolio.getAvailable() - tempPrice);
			currentPortfolio.setInvested(currentPortfolio.getInvested() + tempPrice);
			currentPortfolio.setTotal(currentPortfolio.getInvested() + currentPortfolio.getAvailable());
			if (currentPortfolio.getStocksOwned().get(stock) == null) {
				currentPortfolio.getStocksOwned().put(stock, new ArrayList<>());
			}
			List<Double> tempStocks = currentPortfolio.getStocksOwned().get(stock);
			tempStocks.add(tempPrice);
			currentPortfolio.getStocksOwned().put(stock, tempStocks);
		}
		return currentPortfolio;
	}

	@PostMapping("/stock/sell/{portfolio}/{stock}/{quantity}")
	public Portfolio sellStocks(HttpServletRequest request, @PathVariable(name = "portfolio") String portfolio,
			@PathVariable(name = "stock") String stock, @PathVariable(name = "quantity") int quantity)
			throws IOException {
		System.out.println("Getting stocks for: " + portfolio);

		List<Portfolio> portfolios = userService.getUser(request.getUserPrincipal().getName()).getPortfolios();
		Portfolio currentPortfolio = null;
		for (int i = 0; i < portfolios.size(); i++) {
			if (portfolios.get(i).getName().equals(portfolio)) {
				currentPortfolio = portfolios.get(i);
			}
		}
		Stock s = stockService.getStockBySymbol(stock);
		for (int i = 0; i < quantity; i++) {
			double tempPrice = s.getPrice();
			currentPortfolio.setAvailable(currentPortfolio.getAvailable() + tempPrice);
			currentPortfolio.setInvested(currentPortfolio.getInvested() - tempPrice);
			currentPortfolio.setTotal(currentPortfolio.getInvested() + currentPortfolio.getAvailable());
			if (currentPortfolio.getStocksOwned().get(stock) == null) {
				currentPortfolio.getStocksOwned().put(stock, new ArrayList<>());
			}
			if (currentPortfolio.getStocksOwned().get(stock) == null) {
				currentPortfolio.getStocksSold().put(stock, new ArrayList<>());
			}
			List<Double> tempStocksOwned = currentPortfolio.getStocksOwned().get(stock);
			List<Double> tempStocksSold = currentPortfolio.getStocksSold().get(stock);
			tempStocksSold.add(tempPrice);
			tempStocksOwned.remove(0);
			currentPortfolio.getStocksSold().put(stock, tempStocksSold);
			currentPortfolio.getStocksOwned().put(stock, tempStocksOwned);
		}
		return currentPortfolio;
	}

}
