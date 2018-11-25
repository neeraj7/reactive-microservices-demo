package com.microservices.demo.multiplier.handlers;

import java.time.Duration;
import java.util.Random;
import java.util.stream.Stream;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.microservices.demo.multiplier.model.NumberData;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

@Component
public class NumHandler {
	
	public <T> Mono<ServerResponse> get(ServerRequest request) {
		Random r = new Random();
        int low = 0;
        int high = 50;
        
        Flux<Long> interval = Flux.interval(Duration.ofSeconds(2));
        
        Stream<NumberData> stream = Stream.generate(() -> r.nextInt(high-low) + low).limit(20)
        							   .map(NumberData::new);        
        
        Flux<NumberData> f = Flux.fromStream(stream);
		return ServerResponse.ok()
							 .contentType(MediaType.APPLICATION_STREAM_JSON)
							 .body(Flux.zip(f, interval).map(Tuple2::getT1), NumberData.class);
//							 .body(f, NumberData.class);
		
	}

}
