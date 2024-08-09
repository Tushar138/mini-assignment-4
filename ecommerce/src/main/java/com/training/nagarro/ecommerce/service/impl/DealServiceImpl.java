package com.training.nagarro.ecommerce.service.impl;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.training.nagarro.ecommerce.exception.NotFoundException;
import com.training.nagarro.ecommerce.external.service.AmazonServerService;
import com.training.nagarro.ecommerce.external.service.EbayServerService;
import com.training.nagarro.ecommerce.external.service.WalmartServerService;
import com.training.nagarro.ecommerce.model.DealItem;
import com.training.nagarro.ecommerce.model.Item;
import com.training.nagarro.ecommerce.service.DealService;

import jakarta.annotation.PreDestroy;

@Service
public class DealServiceImpl implements DealService {

	private AmazonServerService amazonServerService;
	private EbayServerService ebayServerService;
	private WalmartServerService walmartServerService;
	private ExecutorService executor;

	@Autowired
	public DealServiceImpl(AmazonServerService amazonServerService, EbayServerService ebayServerService,
			WalmartServerService walmartServerService) {
		super();
		this.amazonServerService = amazonServerService;
		this.ebayServerService = ebayServerService;
		this.walmartServerService = walmartServerService;
		this.executor = Executors.newFixedThreadPool(3);
	}

	@Override
	public DealItem getDealsByCategoryType(String categoryType) {
		CompletableFuture<Optional<DealItem>> amazonServerFuture = CompletableFuture
				.supplyAsync(() -> Optional.of(this.amazonServerService.fetchDealItemsByCategoryName(categoryType)),
						executor)
				.exceptionally(ex -> Optional.empty());

		CompletableFuture<Optional<DealItem>> ebayServerFuture = CompletableFuture
				.supplyAsync(() -> Optional.of(this.ebayServerService.fetchDealItemsByCategoryName(categoryType)),
						executor)
				.exceptionally(ex -> Optional.empty());

		CompletableFuture<Optional<DealItem>> walmartServerFuture = CompletableFuture
				.supplyAsync(() -> Optional.of(this.walmartServerService.fetchDealItemsByCategoryName(categoryType)),
						executor)
				.exceptionally(ex -> Optional.empty());

		CompletableFuture<Optional<DealItem>> combinedFuture = CompletableFuture
				.allOf(amazonServerFuture, ebayServerFuture, walmartServerFuture).thenApply(v -> {
					Optional<DealItem> optionalAmazonDealItem = amazonServerFuture.join();
					Optional<DealItem> optionalEbayDealItem = ebayServerFuture.join();
					Optional<DealItem> optionalWalmartDealItem = walmartServerFuture.join();

					if (optionalAmazonDealItem.isEmpty() && optionalEbayDealItem.isEmpty()
							&& optionalWalmartDealItem.isEmpty()) {
						return Optional.empty();
					}
					List<Item> combinedDealItems = Stream
							.of(optionalAmazonDealItem, optionalEbayDealItem, optionalWalmartDealItem)
							.filter(Optional::isPresent).map(Optional::get)
							.flatMap(dealItem -> dealItem.getDealItems().stream())
							.filter(item -> isActiveDeal(item.getDealStartDate(), item.getDealEndDate())
									&& item.getStock() > 0)
							.sorted(Comparator
									.comparingDouble(
											(Item item) -> item.getMarketingPrice().getDiscountAmount().getValue())
									.reversed().thenComparing((Item item) -> item.getPrice().getValue()))
							.collect(Collectors.toList());
					if (combinedDealItems.isEmpty()) {
						return Optional.empty();
					}
					return Optional.of(new DealItem(categoryType, combinedDealItems));
				});

		Optional<DealItem> optionalDealItemResponse = combinedFuture.join();
		if (optionalDealItemResponse.isEmpty()) {
			throw new NotFoundException("No data found");
		}
		return optionalDealItemResponse.get();
	}

	@PreDestroy
	public void shutDownExecutor() {
		this.executor.shutdown();
	}

	private boolean isActiveDeal(LocalDateTime dealStartDate, LocalDateTime dealEndDate) {
		LocalDateTime currentDateTime = LocalDateTime.now();
		return ((currentDateTime.isEqual(dealStartDate) || currentDateTime.isAfter(dealStartDate))
				&& (currentDateTime.isEqual(dealEndDate) || currentDateTime.isBefore(dealEndDate)));
	}

}
