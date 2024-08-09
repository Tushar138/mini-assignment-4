package com.training.nagarro.ecommerce.controller.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.training.nagarro.ecommerce.controller.DealController;
import com.training.nagarro.ecommerce.model.DealItem;
import com.training.nagarro.ecommerce.model.Image;
import com.training.nagarro.ecommerce.model.Item;
import com.training.nagarro.ecommerce.model.MarketingPrice;
import com.training.nagarro.ecommerce.model.Price;
import com.training.nagarro.ecommerce.service.DealService;

class DealControllerImplTest {

	@Mock
	private DealService dealService;
	private DealController dealController;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
		dealController = new DealControllerImpl(dealService);
	}

	@Test
	void testFetchDealsByCategoryName() {
		Item item = new Item("1234567", "productTitle", 4, "brand", new Image("imageUrl"),
				new MarketingPrice(new Price(56.54, "USD"), 40.0, new Price(22.62, "USD"), "treatment"),
				new Price(33.92, "USD"), 2, LocalDateTime.of(2024, 8, 1, 1, 25), LocalDateTime.of(2024, 12, 1, 1, 25));
		List<Item> items = Arrays.asList(item);
		String categoryName = "Jeans";
		DealItem dealItem = new DealItem(categoryName, items);

		when(this.dealService.getDealsByCategoryType(categoryName)).thenReturn(dealItem);

		ResponseEntity<DealItem> actualResponse = this.dealController.fetchDealsByCategoryName(categoryName);

		assertEquals(HttpStatus.OK, actualResponse.getStatusCode());
		assertEquals(dealItem, actualResponse.getBody());
	}

}
