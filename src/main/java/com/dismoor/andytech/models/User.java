package com.dismoor.andytech.models;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Document(collection = "StockifyUsers")
@AllArgsConstructor
@Setter
@Getter
@EqualsAndHashCode
@ToString
public class User {

	@Id
	String username;
	String Password;
	String email;
	List<Portfolio> portfolios;

	public User() {
		portfolios = new ArrayList<>();
	}

}
