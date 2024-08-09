package com.training.nagarro.ecommerce.external.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.training.nagarro.ecommerce.external.service.AmazonServerService;
import com.training.nagarro.ecommerce.model.DealItem;

@Service
public class AmazonServerServiceImpl implements AmazonServerService {

	private WebClient amazonServerWebClient;
	
	@Autowired
	public AmazonServerServiceImpl(WebClient amazonServerWebClient) {
		super();
		this.amazonServerWebClient = amazonServerWebClient;
	}

	@Override
	public DealItem fetchDealItemsByCategoryName(String categoryName) {
		return (this.amazonServerWebClient.get()
				.uri(uriBuilder -> uriBuilder.path("/backendserver1/amazon//deals/{categoryName}").build(categoryName))
				.retrieve().bodyToMono(DealItem.class).block());
	}

}
