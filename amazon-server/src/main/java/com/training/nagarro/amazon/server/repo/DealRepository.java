package com.training.nagarro.amazon.server.repo;

import com.training.nagarro.amazon.server.model.DealItem;

public interface DealRepository {

	DealItem fetchAllDealsForJeansCategory();
}
