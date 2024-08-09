package com.training.nagarro.walmart.server.repo;

import com.training.nagarro.walmart.server.model.DealItem;

public interface DealRepository {

	DealItem fetchAllDealsForJeansCategory();
}
