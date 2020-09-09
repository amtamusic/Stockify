package com.dismoor.andytech.models;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Document(collection = "Stocks")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@EqualsAndHashCode
@ToString
public class Stock implements Serializable {

	private static final long serialVersionUID = 6770060364203057751L;

	@Id
	String symbol;
	String beta;
	String ceo;
	double changes;
	double percentage;
	String companyName;
	String description;
	String exchange;
	String image;
	String industry;
	double lastDiv;
	double marketCap;
	double price;
	String range;
	String sector;
	int avgVol;
	String website;
	List<Stock> history;
	String timestamp = LocalDateTime.now().toString();
}
