package com.training.nagarro.ebay.server.service;

import com.training.nagarro.ebay.server.model.DealItem;

public interface DealService {

	DealItem getDealsByCategoryType(String categoryType);
}
