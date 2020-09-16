package com.dismoor.andytech.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dismoor.andytech.models.Stock;
import com.dismoor.andytech.models.StockJSON;
import com.dismoor.andytech.repos.StockRepo;

@Service
public class StockService {

	@Autowired
	private StockRepo stockRepo;

	public String getStock(String stock) throws IOException {

		URL url = new URL("https://financialmodelingprep.com/api/v3/company/profile/" + stock
				+ "?apikey=24e9162ed7fc554729fbcb0ab0f8c74f");
		String json = "";
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
			for (String line; (line = reader.readLine()) != null;) {
				json += line;
			}
			return json;
		} catch (Exception e) {
			return "";
		}

	}

	public Stock convertJSONToStock(StockJSON json) {
		Stock result = new Stock();
		result.setSymbol(json.getSymbol());
		result.setPrice(json.getProfile().getPrice());
		result.setBeta(json.getProfile().getBeta());
		result.setAvgVol(Integer.parseInt(json.getProfile().getVolAvg()));
		result.setMarketCap(Double
				.parseDouble(json.getProfile().getMktCap().substring(0, json.getProfile().getMktCap().length() - 3)));
		result.setLastDiv(Double.parseDouble(json.getProfile().getLastDiv()));
		result.setRange(json.getProfile().getRange());
		result.setChanges(json.getProfile().getChanges());
		result.setPercentage(Double.parseDouble(json.getProfile().getChangesPercentage().replaceAll("\\(", "")
				.replaceAll("\\)", "").replaceAll("%", "")));
		result.setCompanyName(json.getProfile().getCompanyName());
		result.setExchange(json.getProfile().getExchangeShortName());
		result.setIndustry(json.getProfile().getIndustry());
		result.setWebsite(json.getProfile().getWebsite());
		result.setDescription(json.getProfile().getDescription());
		result.setCeo(json.getProfile().getCeo());
		result.setSector(json.getProfile().getSector());
		result.setImage(json.getProfile().getImage());
		return result;
	}

	public Stock saveStock(Stock s) {
		s.setHistory(new ArrayList<>());
		if (stockRepo.existsById(s.getSymbol())) {
			Stock stock = stockRepo.findById(s.getSymbol()).get();
			s.setHistory(stock.getHistory());
			stock.setHistory(new ArrayList<>());
			s.getHistory().add(stock);
			return stockRepo.save(s);
		} else {
			return stockRepo.save(s);
		}
	}

	public Stock getStockBySymbol(String s) {
		return stockRepo.findById(s).get();
	}
}
