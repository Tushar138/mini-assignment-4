package com.training.nagarro.ecommerce.external.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.training.nagarro.ecommerce.external.service.WalmartServerService;
import com.training.nagarro.ecommerce.model.DealItem;

@Service
public class WalmartServerServiceImpl implements WalmartServerService {

	private WebClient walmartServerWebClient;
	
	@Autowired
	public WalmartServerServiceImpl(WebClient walmartServerWebClient) {
		super();
		this.walmartServerWebClient = walmartServerWebClient;
	}

	@Override
	public DealItem fetchDealItemsByCategoryName(String categoryName) {
		return (this.walmartServerWebClient.get()
				.uri(uriBuilder -> uriBuilder.path("/backendserver3/walmart/deals/{categoryName}").build(categoryName))
				.retrieve().bodyToMono(DealItem.class).block());
	}

}
