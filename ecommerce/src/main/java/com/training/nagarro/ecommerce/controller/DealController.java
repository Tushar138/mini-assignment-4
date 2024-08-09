package com.training.nagarro.ecommerce.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.training.nagarro.ecommerce.model.DealItem;

@RequestMapping("/deals")
public interface DealController {

	@GetMapping("/{categoryName}")
	ResponseEntity<DealItem> fetchDealsByCategoryName(@PathVariable("categoryName") String categoryName);
}
