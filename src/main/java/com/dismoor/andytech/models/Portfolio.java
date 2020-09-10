package com.dismoor.andytech.models;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Setter
@Getter
@EqualsAndHashCode
@ToString
public class Portfolio {
	String name;
	double total;
	double available;
	double invested;
	Map<String, Double> totalHistory;
	Map<String, List<Double>> stocksOwned;
	Map<String, List<Double>> stocksSold;

	public Portfolio() {
		totalHistory = new HashMap<>();
		stocksOwned = new HashMap<String, List<Double>>();
		stocksSold = new HashMap<String, List<Double>>();
	}
}
