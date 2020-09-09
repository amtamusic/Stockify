package com.dismoor.andytech.controllers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.dismoor.andytech.models.Stock;
import com.dismoor.andytech.models.StockJSON;
import com.dismoor.andytech.services.StockService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@CrossOrigin
public class StockController {

	@Autowired
	private StockService stockService;

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

}
