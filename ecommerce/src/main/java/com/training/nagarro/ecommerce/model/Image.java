package com.training.nagarro.ecommerce.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Image {
	
	@JsonProperty("imageUrl")
	private String imageUrl;
	
}
