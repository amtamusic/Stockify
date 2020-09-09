package com.dismoor.andytech.services;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.dismoor.andytech.models.Stock;
import com.dismoor.andytech.models.StockJSON;
import com.dismoor.andytech.repos.StockRepo;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class TaskService {

	@Autowired
	private StockRepo stockRepo;
	@Autowired
	private StockService stockService;

	@Scheduled(fixedDelay = 10000)
	public void updateStocks() throws IOException {
		List<Stock> stocks = stockRepo.findAll();
		if (stocks.size() > 0) {
			for (Stock stock : stocks) {
				String stockJson = stockService.getStock(stock.getSymbol());
				ObjectMapper objectMapper = new ObjectMapper();
				StockJSON response = objectMapper.readValue(stockJson, StockJSON.class);
				Stock sto = stockService.convertJSONToStock(response);
				stockService.saveStock(sto);
				System.out.println("Updated Stock: " + sto.getSymbol() + " Current Price: " + sto.getPrice());
			}
		} else {
			System.out.println("No Stocks to update");
		}
	}

}
