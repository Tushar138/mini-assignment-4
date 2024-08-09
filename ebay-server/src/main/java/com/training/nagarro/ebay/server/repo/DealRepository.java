package com.training.nagarro.ebay.server.repo;

import com.training.nagarro.ebay.server.model.DealItem;

public interface DealRepository {

	DealItem fetchAllDealsForJeansCategory();
}
