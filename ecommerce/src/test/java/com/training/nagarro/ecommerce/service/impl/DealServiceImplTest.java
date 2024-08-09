package com.training.nagarro.ecommerce.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.training.nagarro.ecommerce.external.service.AmazonServerService;
import com.training.nagarro.ecommerce.external.service.EbayServerService;
import com.training.nagarro.ecommerce.external.service.WalmartServerService;
import com.training.nagarro.ecommerce.model.DealItem;
import com.training.nagarro.ecommerce.model.Image;
import com.training.nagarro.ecommerce.model.Item;
import com.training.nagarro.ecommerce.model.MarketingPrice;
import com.training.nagarro.ecommerce.model.Price;
import com.training.nagarro.ecommerce.service.DealService;

class DealServiceImplTest {

	@Mock private AmazonServerService amazonServerService;
	@Mock private EbayServerService ebayServerService;
	@Mock private WalmartServerService walmartServerService;

	private DealService dealService;
	
	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
		dealService=new DealServiceImpl(amazonServerService, ebayServerService, walmartServerService);
	}
	@Test
	void testGetDealsByCategoryType() {
		Item item = new Item("1234567", "productTitle", 4, "brand", new Image("imageUrl"),
				new MarketingPrice(new Price(56.54, "USD"), 40.0, new Price(22.62, "USD"), "treatment"),
				new Price(33.92, "USD"), 2, LocalDateTime.of(2024, 8, 1, 1, 25), LocalDateTime.of(2024, 12, 1, 1, 25));
		List<Item> amazonItems = Arrays.asList(item);
		String categoryName = "Jeans";
		DealItem amazonDealItem = new DealItem(categoryName, amazonItems);

		Item item1 = new Item("1234568", "productTitle", 4, "brand", new Image("imageUrl"),
				new MarketingPrice(new Price(46.0, "USD"), 20.0, new Price(9.2, "USD"), "treatment"),
				new Price(36.8, "USD"), 0, LocalDateTime.of(2024, 8, 1, 1, 25), LocalDateTime.of(2024, 12, 1, 1, 25));
		List<Item> ebayItems = Arrays.asList(item1);
		DealItem ebayDealItem = new DealItem(categoryName, ebayItems);

		Item item2 = new Item("1234569", "productTitle", 4, "brand", new Image("imageUrl"),
				new MarketingPrice(new Price(66.0, "USD"), 20.0, new Price(13.2, "USD"), "treatment"),
				new Price(52.8, "USD"), 4, LocalDateTime.of(2023, 8, 1, 1, 25), LocalDateTime.of(2023, 12, 1, 1, 25));
		List<Item> walmartItems = Arrays.asList(item2);
		DealItem walmartDealItem = new DealItem(categoryName, walmartItems);
		
		when(this.amazonServerService.fetchDealItemsByCategoryName(categoryName)).thenReturn(amazonDealItem);
		when(this.ebayServerService.fetchDealItemsByCategoryName(categoryName)).thenReturn(ebayDealItem);
		when(this.walmartServerService.fetchDealItemsByCategoryName(categoryName)).thenReturn(walmartDealItem);
		
		DealItem actualResponse=this.dealService.getDealsByCategoryType(categoryName);
		
		assertEquals("Jeans",actualResponse.getCategoryName() );
		assertEquals(amazonDealItem.getDealItems(), actualResponse.getDealItems());

	}

}
