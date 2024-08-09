package com.training.nagarro.ebay.server.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.training.nagarro.ebay.server.model.DealItem;

@RequestMapping("/backendserver2/ebay")
public interface DealController {

	@GetMapping("/deals/{categoryName}")
	ResponseEntity<DealItem> fetchDealsByCategoryName(@PathVariable("categoryName") String categoryName);
}
