package com.training.nagarro.ebay.server.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.training.nagarro.ebay.server.controller.DealController;
import com.training.nagarro.ebay.server.model.DealItem;
import com.training.nagarro.ebay.server.service.DealService;

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
