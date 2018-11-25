package com.microservices.demo.multiplier.handlers;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.microservices.demo.multiplier.model.NumberData;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class MultiplierHandler {

	WebClient client = WebClient.create("http://localhost:8081");
	
	public Mono<ServerResponse> getNum(ServerRequest request) {
		
		Flux<NumberData> re = client.get()
			  .uri("/num")
			  .retrieve()
			  .bodyToFlux(NumberData.class)
			  .map(s -> {
				  System.out.println("marks: " + s.getMarks());
				  s.setMarks(s.getMarks() + 2);
				  return s;
			  });
//		Flux<NumberData> res = client.get().uri("/num").accept(MediaType.APPLICATION_STREAM_JSON).retrieve()
//				.bodyToFlux(NumberData.class);
		
		return ServerResponse.ok()
							 .contentType(MediaType.APPLICATION_STREAM_JSON)
							 .body(re, NumberData.class);
		
	}
	
	public NumberData multiply(NumberData num) {
		num.setNum(num.getNum()+2);
		return num;
	}
	
}
