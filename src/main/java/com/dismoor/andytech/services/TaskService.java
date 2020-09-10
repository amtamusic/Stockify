package com.dismoor.andytech.services;

import org.springframework.stereotype.Service;

@Service
public class TaskService {

//	@Autowired
//	private StockRepo stockRepo;
//	@Autowired
//	private StockService stockService;
//
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

}
