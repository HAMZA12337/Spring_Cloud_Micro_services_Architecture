package org.sid.getwaye;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.ReactiveDiscoveryClient;
import org.springframework.cloud.gateway.discovery.DiscoveryClientRouteDefinitionLocator;
import org.springframework.cloud.gateway.discovery.DiscoveryLocatorProperties;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GetwayeApplication {

	public static void main(String[] args) {
		SpringApplication.run(GetwayeApplication.class, args);
	}


	// use java config instead of using application.yml
//	@Bean
//	RouteLocator routeLocator(RouteLocatorBuilder builder){
//
//		return builder.routes()
//				.route(r1 -> r1.path("/customers/**")
//						.uri("lb://CUSTOMER-SERVICE"))
//
//				.route(r2 -> r2.path("/products/**")
//						.uri("lb://PRODUCT-SERVICE"))
//
//				.build();
//
//	}

	@Bean
	DiscoveryClientRouteDefinitionLocator definitionLocator(ReactiveDiscoveryClient rdc, DiscoveryLocatorProperties properties){

		return new DiscoveryClientRouteDefinitionLocator(rdc,properties);}


















}
