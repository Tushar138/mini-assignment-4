package com.training.nagarro.ebay.server.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Test {

	@JsonProperty("x")
	private int x;
}
