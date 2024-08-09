package com.training.nagarro.amazon.server.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.training.nagarro.amazon.server.exception.NotFoundException;
import com.training.nagarro.amazon.server.model.DealItem;
import com.training.nagarro.amazon.server.repo.DealRepository;
import com.training.nagarro.amazon.server.service.DealService;

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
