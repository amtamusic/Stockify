package com.dismoor.andytech.services;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.dismoor.andytech.models.Stock;
import com.dismoor.andytech.repos.StockRepo;

@Service
public class TaskService {

	@Autowired
	private StockRepo stockRepo;
	@Autowired
	private StockService stockService;

//	@Scheduled(fixedDelay = 10000)
//	public void updateStocks() throws IOException {
//		List<Stock> stocks = stockRepo.findAll();
//		if (stocks.size() > 0) {
//			for (Stock stock : stocks) {
//				String stockJson = stockService.getStock(stock.getSymbol());
//				ObjectMapper objectMapper = new ObjectMapper();
//				StockJSON response = objectMapper.readValue(stockJson, StockJSON.class);
//				Stock sto = stockService.convertJSONToStock(response);
//				stockService.saveStock(sto);
//				System.out.println("Updated Stock: " + sto.getSymbol() + " Current Price: " + sto.getPrice());
//			}
//		} else {
//			System.out.println("No Stocks to update");
//		}
//	}

	@Scheduled(fixedDelay = 1000)
	public void updateStocks() throws IOException {
		List<Stock> stocks = stockRepo.findAll();
		if (stocks.size() > 0) {
			for (Stock stock : stocks) {
				int change = 0;
				if (new java.util.Random().nextInt(100) > 80) {
					change = 1;
				}
				if (new java.util.Random().nextInt(100) % 2 != 0) {
					change *= -1;
				}
				double tempStockPrice = new java.util.Random().nextDouble();
				if (stock.getPrice() == 0) {
					change *= tempStockPrice * new java.util.Random().nextDouble() / 10;
					stock.setPrice(tempStockPrice + change);
				} else {
					change *= stock.getPrice() * new java.util.Random().nextDouble() / 10;
					stock.setPrice(stock.getPrice() + change);
				}
				stockService.saveStock(stock);
				// System.out.println("Updated Stock: " + stock.getSymbol() + " Current Price: "
				// + stock.getPrice());
				// System.out.println("Updated Stock: " + stock.getSymbol());
			}
			System.out.println("Updated Stocks: " + stocks.size());
		} else {
			System.out.println("No Stocks to update");
		}
	}

}
