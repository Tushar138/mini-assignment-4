package com.training.nagarro.amazon.server.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Test {

	@JsonProperty("x")
	private int x;
}
