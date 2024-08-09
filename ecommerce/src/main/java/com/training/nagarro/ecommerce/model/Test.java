package com.training.nagarro.ecommerce.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Test {

	@JsonProperty("x")
	private int x;
}
