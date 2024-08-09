package com.training.nagarro.amazon.server.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.training.nagarro.amazon.server.model.DealItem;

@RequestMapping("/backendserver1/amazon")
public interface DealController {

	@GetMapping("/deals/{categoryName}")
	ResponseEntity<DealItem> fetchDealsByCategoryName(@PathVariable("categoryName") String categoryName);
}
