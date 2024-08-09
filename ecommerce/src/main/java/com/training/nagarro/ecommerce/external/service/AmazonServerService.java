package com.training.nagarro.ecommerce.external.service;

import com.training.nagarro.ecommerce.model.DealItem;

public interface AmazonServerService {

	DealItem fetchDealItemsByCategoryName(String categoryName);
}
