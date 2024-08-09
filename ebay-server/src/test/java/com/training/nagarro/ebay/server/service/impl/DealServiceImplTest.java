package com.training.nagarro.ebay.server.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.training.nagarro.ebay.server.exception.NotFoundException;
import com.training.nagarro.ebay.server.model.DealItem;
import com.training.nagarro.ebay.server.model.Image;
import com.training.nagarro.ebay.server.model.Item;
import com.training.nagarro.ebay.server.model.MarketingPrice;
import com.training.nagarro.ebay.server.model.Price;
import com.training.nagarro.ebay.server.repo.DealRepository;
import com.training.nagarro.ebay.server.service.DealService;

class DealServiceImplTest {

	@Mock
	private DealRepository dealRepository;
	private DealService dealService;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
		dealService = new DealServiceImpl(dealRepository);
	}

	@Test
	void testGetDealsByCategoryType() {
		Item item = new Item("1234567", "productTitle", 4, "brand", new Image("imageUrl"),
				new MarketingPrice(new Price(56.54, "USD"), 40.0, new Price(22.62, "USD"), "treatment"),
				new Price(33.92, "USD"), 2, LocalDateTime.of(2024, 8, 1, 1, 25), LocalDateTime.of(2024, 12, 1, 1, 25));
		List<Item> items = Arrays.asList(item);
		String categoryName = "Jeans";
		DealItem dealItem = new DealItem(categoryName, items);

		when(this.dealRepository.fetchAllDealsForJeansCategory()).thenReturn(dealItem);

		DealItem actualResponse = this.dealService.getDealsByCategoryType(categoryName);

		assertEquals(dealItem, actualResponse);

	}

	@Test
	void testGetDealsByCategoryTypeWhenTypeIsNotJeans() {
		String categoryName = "Shirt";
		assertThrows(NotFoundException.class, () -> whenGetDealsByCategoryTypeIsCalled(categoryName));
	}

	private void whenGetDealsByCategoryTypeIsCalled(String categoryType) throws NotFoundException {
		when(this.dealRepository.fetchAllDealsForJeansCategory()).thenThrow(new NotFoundException());
		this.dealService.getDealsByCategoryType(categoryType);
	}
}
