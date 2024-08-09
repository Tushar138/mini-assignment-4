package com.training.nagarro.ecommerce.external.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.training.nagarro.ecommerce.external.service.EbayServerService;
import com.training.nagarro.ecommerce.model.DealItem;

@Service
public class EbayServerServiceImpl implements EbayServerService {

	private WebClient ebayServerWebClient;
	
	@Autowired
	public EbayServerServiceImpl(WebClient ebayServerWebClient) {
		super();
		this.ebayServerWebClient = ebayServerWebClient;
	}

	@Override
	public DealItem fetchDealItemsByCategoryName(String categoryName) {
		return (this.ebayServerWebClient.get()
				.uri(uriBuilder -> uriBuilder.path("/backendserver2/ebay/deals/{categoryName}").build(categoryName))
				.retrieve().bodyToMono(DealItem.class).block());
	}

}
