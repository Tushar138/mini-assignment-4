package com.training.nagarro.walmart.server.repo.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.training.nagarro.walmart.server.model.DealItem;
import com.training.nagarro.walmart.server.model.Test;
import com.training.nagarro.walmart.server.repo.DealRepository;

@Component("dealRepository")
public class DealRepositoryImpl implements DealRepository {

	@Value("classpath:data/DealItems.json")
    private Resource jsonResource;
	
	private ObjectMapper objectMapper;
	

	public DealRepositoryImpl(ObjectMapper objectMapper) {
		super();
		this.objectMapper = objectMapper;
	}

	@Override
	public DealItem fetchAllDealsForJeansCategory() {
		DealItem dealItem=this.convertJsonDataToModel();
		return dealItem;
	}

	private DealItem convertJsonDataToModel() {
		DealItem dealItem=null;
		try(InputStream jsonInputStream=this.jsonResource.getInputStream()) {
			dealItem=this.objectMapper.readValue(jsonInputStream, DealItem.class);
		}
		catch (IOException e) {
			throw new RuntimeException(e);
		}
		return dealItem;
	}
	

}
