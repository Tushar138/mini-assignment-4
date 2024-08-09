package com.training.nagarro.walmart.server.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Item {
	
	@JsonProperty("itemid")
	private String itemId;
	@JsonProperty("productTitle")
	private String productTitle;
	@JsonProperty("size")
	private int size;
	@JsonProperty("Brand")
	private String brand;
	@JsonProperty("image")
	private Image image;
	@JsonProperty("marketingPrice")
	private MarketingPrice marketingPrice;
	@JsonProperty("price")
	private Price price;
	@JsonProperty("stock")
	private int stock;
	@JsonProperty("dealStartDate")
	private LocalDateTime dealStartDate;
	@JsonProperty("dealEndDate")
	private LocalDateTime dealEndDate;
	
}
