package com.training.nagarro.ebay.server.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.training.nagarro.ebay.server.exception.NotFoundException;
import com.training.nagarro.ebay.server.model.DealItem;
import com.training.nagarro.ebay.server.repo.DealRepository;
import com.training.nagarro.ebay.server.service.DealService;

@Service
public class DealServiceImpl implements DealService {

	private DealRepository dealRepository;
	
	@Autowired
	public DealServiceImpl(DealRepository dealRepository) {
		super();
		this.dealRepository = dealRepository;
	}


	@Override
	public DealItem getDealsByCategoryType(String categoryType) {
		if(categoryType.equalsIgnoreCase("Jeans"))
			return this.dealRepository.fetchAllDealsForJeansCategory();
		throw new NotFoundException("No item found for "+categoryType+" categoryType");
	}

}
