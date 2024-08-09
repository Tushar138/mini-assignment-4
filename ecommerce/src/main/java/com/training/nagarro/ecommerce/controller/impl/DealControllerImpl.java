package com.training.nagarro.ecommerce.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.training.nagarro.ecommerce.controller.DealController;
import com.training.nagarro.ecommerce.model.DealItem;
import com.training.nagarro.ecommerce.service.DealService;

@RestController
public class DealControllerImpl implements DealController {

	private DealService dealService;
	
	@Autowired
	public DealControllerImpl(DealService dealService) {
		super();
		this.dealService = dealService;
	}

	@Override
	public ResponseEntity<DealItem> fetchDealsByCategoryName(String categoryName) {
		return ResponseEntity.ok(this.dealService.getDealsByCategoryType(categoryName));
	}
	
}
