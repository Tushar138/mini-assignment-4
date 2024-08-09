package com.training.nagarro.walmart.server.service;

import com.training.nagarro.walmart.server.model.DealItem;

public interface DealService {

	DealItem getDealsByCategoryType(String categoryType);
}
