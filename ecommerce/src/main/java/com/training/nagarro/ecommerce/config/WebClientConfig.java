package com.training.nagarro.ecommerce.config;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import reactor.netty.http.client.HttpClient;

@Configuration
public class WebClientConfig {

	@Bean(name = "amazonServerWebClient")
	public WebClient getAmazonServerWebClient() {
		return WebClient.builder().baseUrl("http://localhost:9091")
				.clientConnector(new ReactorClientHttpConnector(getHttpClient(5000, 5000, 5000))).build();
	}

	@Bean(name = "ebayServerWebClient")
	public WebClient getEbayServerWebClient() {
		return WebClient.builder().baseUrl("http://localhost:9092")
				.clientConnector(new ReactorClientHttpConnector(getHttpClient(5000, 5000, 5000))).build();
	}

	@Bean(name = "walmartServerWebClient")
	public WebClient getWalmartServerWebClient() {
		return WebClient.builder().baseUrl("http://localhost:9093")
				.clientConnector(new ReactorClientHttpConnector(getHttpClient(5000, 5000, 5000))).build();
	}

	private HttpClient getHttpClient(int connectionTimeout, int readTimeout, int writeTimeout) {
		HttpClient httpClient = HttpClient.create().option(ChannelOption.CONNECT_TIMEOUT_MILLIS, connectionTimeout)
				.responseTimeout(Duration.ofMillis(connectionTimeout))
				.doOnConnected(conn -> conn.addHandlerLast(new ReadTimeoutHandler(readTimeout, TimeUnit.MILLISECONDS))
						.addHandlerLast(new WriteTimeoutHandler(writeTimeout, TimeUnit.MILLISECONDS)));
		return httpClient;
	}
}
