package com.training.nagarro.ebay.server.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Price {

	@JsonProperty("value")
	private double value;
	@JsonProperty("currency")
	private String currency;
}
