package com.training.nagarro.ecommerce.external.service;

import com.training.nagarro.ecommerce.model.DealItem;

public interface WalmartServerService {

	DealItem fetchDealItemsByCategoryName(String categoryName);
}
