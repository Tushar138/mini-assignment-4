package com.training.nagarro.amazon.server.service;

import com.training.nagarro.amazon.server.model.DealItem;

public interface DealService {

	DealItem getDealsByCategoryType(String categoryType);
}
