package com.training.nagarro.ecommerce.external.service;

import com.training.nagarro.ecommerce.model.DealItem;

public interface EbayServerService {

	DealItem fetchDealItemsByCategoryName(String categoryName);
}
