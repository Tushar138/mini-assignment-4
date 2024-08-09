package com.training.nagarro.walmart.server.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.training.nagarro.walmart.server.model.DealItem;

@RequestMapping("/backendserver3/walmart")
public interface DealController {

	@GetMapping("/deals/{categoryName}")
	ResponseEntity<DealItem> fetchDealsByCategoryName(@PathVariable("categoryName") String categoryName);
}
