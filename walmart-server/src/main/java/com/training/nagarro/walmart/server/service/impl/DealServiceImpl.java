package com.training.nagarro.walmart.server.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.training.nagarro.walmart.server.exception.NotFoundException;
import com.training.nagarro.walmart.server.model.DealItem;
import com.training.nagarro.walmart.server.repo.DealRepository;
import com.training.nagarro.walmart.server.service.DealService;

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
