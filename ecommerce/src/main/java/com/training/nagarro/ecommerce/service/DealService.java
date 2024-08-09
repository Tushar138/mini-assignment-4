package com.training.nagarro.ecommerce.service;

import com.training.nagarro.ecommerce.model.DealItem;

public interface DealService {

	DealItem getDealsByCategoryType(String categoryType);
}
