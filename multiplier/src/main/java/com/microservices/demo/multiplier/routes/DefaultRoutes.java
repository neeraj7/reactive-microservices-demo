package com.microservices.demo.multiplier.routes;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.microservices.demo.multiplier.handlers.MultiplierHandler;

@Configuration
public class DefaultRoutes {

	@Autowired
	private MultiplierHandler handler;
	
	@Bean
	public RouterFunction<ServerResponse> route() {
		return RouterFunctions.route(GET("/client").and(accept(MediaType.APPLICATION_JSON)), handler::getNum);
				}

}
