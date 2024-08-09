package com.training.nagarro.ebay.server.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DealItem {

	@JsonProperty("categoryName")
	private String categoryName;
	@JsonProperty("dealItems")
	private List<Item> dealItems;
}
