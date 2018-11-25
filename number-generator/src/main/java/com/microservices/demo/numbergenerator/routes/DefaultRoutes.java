package com.microservices.demo.numbergenerator.routes;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.microservices.demo.numbergenerator.handlers.NumHandler;

@Configuration
public class DefaultRoutes {
	
	@Bean
	public RouterFunction<ServerResponse> route(NumHandler numHandler) {
		return RouterFunctions.route(GET("/num").and(accept(MediaType.APPLICATION_JSON)), numHandler::get);
				}

}
