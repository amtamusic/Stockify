package com.dismoor.andytech.models;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "price", "beta", "volAvg", "mktCap", "lastDiv", "range", "changes", "changesPercentage",
		"companyName", "exchange", "exchangeShortName", "industry", "website", "description", "ceo", "sector",
		"image" })
public class Profile {

	@JsonProperty("price")
	private Double price;
	@JsonProperty("beta")
	private String beta;
	@JsonProperty("volAvg")
	private String volAvg;
	@JsonProperty("mktCap")
	private String mktCap;
	@JsonProperty("lastDiv")
	private String lastDiv;
	@JsonProperty("range")
	private String range;
	@JsonProperty("changes")
	private Double changes;
	@JsonProperty("changesPercentage")
	private String changesPercentage;
	@JsonProperty("companyName")
	private String companyName;
	@JsonProperty("exchange")
	private String exchange;
	@JsonProperty("exchangeShortName")
	private String exchangeShortName;
	@JsonProperty("industry")
	private String industry;
	@JsonProperty("website")
	private String website;
	@JsonProperty("description")
	private String description;
	@JsonProperty("ceo")
	private String ceo;
	@JsonProperty("sector")
	private String sector;
	@JsonProperty("image")
	private String image;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("price")
	public Double getPrice() {
		return price;
	}

	@JsonProperty("price")
	public void setPrice(Double price) {
		this.price = price;
	}

	@JsonProperty("beta")
	public String getBeta() {
		return beta;
	}

	@JsonProperty("beta")
	public void setBeta(String beta) {
		this.beta = beta;
	}

	@JsonProperty("volAvg")
	public String getVolAvg() {
		return volAvg;
	}

	@JsonProperty("volAvg")
	public void setVolAvg(String volAvg) {
		this.volAvg = volAvg;
	}

	@JsonProperty("mktCap")
	public String getMktCap() {
		return mktCap;
	}

	@JsonProperty("mktCap")
	public void setMktCap(String mktCap) {
		this.mktCap = mktCap;
	}

	@JsonProperty("lastDiv")
	public String getLastDiv() {
		return lastDiv;
	}

	@JsonProperty("lastDiv")
	public void setLastDiv(String lastDiv) {
		this.lastDiv = lastDiv;
	}

	@JsonProperty("range")
	public String getRange() {
		return range;
	}

	@JsonProperty("range")
	public void setRange(String range) {
		this.range = range;
	}

	@JsonProperty("changes")
	public Double getChanges() {
		return changes;
	}

	@JsonProperty("changes")
	public void setChanges(Double changes) {
		this.changes = changes;
	}

	@JsonProperty("changesPercentage")
	public String getChangesPercentage() {
		return changesPercentage;
	}

	@JsonProperty("changesPercentage")
	public void setChangesPercentage(String changesPercentage) {
		this.changesPercentage = changesPercentage;
	}

	@JsonProperty("companyName")
	public String getCompanyName() {
		return companyName;
	}

	@JsonProperty("companyName")
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	@JsonProperty("exchange")
	public String getExchange() {
		return exchange;
	}

	@JsonProperty("exchange")
	public void setExchange(String exchange) {
		this.exchange = exchange;
	}

	@JsonProperty("exchangeShortName")
	public String getExchangeShortName() {
		return exchangeShortName;
	}

	@JsonProperty("exchangeShortName")
	public void setExchangeShortName(String exchangeShortName) {
		this.exchangeShortName = exchangeShortName;
	}

	@JsonProperty("industry")
	public String getIndustry() {
		return industry;
	}

	@JsonProperty("industry")
	public void setIndustry(String industry) {
		this.industry = industry;
	}

	@JsonProperty("website")
	public String getWebsite() {
		return website;
	}

	@JsonProperty("website")
	public void setWebsite(String website) {
		this.website = website;
	}

	@JsonProperty("description")
	public String getDescription() {
		return description;
	}

	@JsonProperty("description")
	public void setDescription(String description) {
		this.description = description;
	}

	@JsonProperty("ceo")
	public String getCeo() {
		return ceo;
	}

	@JsonProperty("ceo")
	public void setCeo(String ceo) {
		this.ceo = ceo;
	}

	@JsonProperty("sector")
	public String getSector() {
		return sector;
	}

	@JsonProperty("sector")
	public void setSector(String sector) {
		this.sector = sector;
	}

	@JsonProperty("image")
	public String getImage() {
		return image;
	}

	@JsonProperty("image")
	public void setImage(String image) {
		this.image = image;
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}
}