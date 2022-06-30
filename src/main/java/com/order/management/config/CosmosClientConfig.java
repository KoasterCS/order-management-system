package com.order.management.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.azure.cosmos.CosmosClient;
import com.azure.cosmos.CosmosClientBuilder;
import com.azure.spring.data.cosmos.config.AbstractCosmosConfiguration;
import com.azure.spring.data.cosmos.config.CosmosConfig;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class CosmosClientConfig extends AbstractCosmosConfiguration {
	@Value("${azure.cosmos.uri}")
	private String host;

	@Value("${azure.cosmos.key}")
	private String masterKey;

	@Value("${azure.cosmos.database}")
	private String database;
	
	@Bean
	public CosmosClient getCosmosClient() {
		return new CosmosClientBuilder()
				.endpoint(host)
				.key(masterKey)
				.buildClient();
	}

	@Override
    public CosmosConfig cosmosConfig() {
        return CosmosConfig.builder()
                           .enableQueryMetrics(true)
                           .build();
    }
	
	@Override
	protected String getDatabaseName() {
		return database;
	}

}
