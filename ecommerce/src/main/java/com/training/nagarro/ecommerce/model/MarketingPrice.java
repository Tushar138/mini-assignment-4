package com.training.nagarro.ecommerce.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MarketingPrice {
	
	@JsonProperty("originalPrice")
	private Price originalPrice;
	@JsonProperty("discountPercentage")
	private double discountPercentage;
	@JsonProperty("discountAmount")
	private Price discountAmount;
	@JsonProperty("priceTreatment")
	private String priceTreatment;
}
