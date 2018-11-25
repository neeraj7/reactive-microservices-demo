package com.microservices.demo.numbergenerator.handlers;

import java.time.Duration;
import java.util.Random;
import java.util.stream.Stream;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

@Component
public class NumHandler {
	
	public Mono<ServerResponse> get(ServerRequest request){
		Random r = new Random();
        int low = 0;
        int high = 50;
        
        Flux<Long> interval = Flux.interval(Duration.ofSeconds(10));
        
        Stream<String> stream = Stream.generate(() -> r.nextInt(high-low) + low)
        							   .map(String::valueOf);        
        
        Flux<String> f = Flux.fromStream(stream);
        							   
       
        
//        return null;
		return ServerResponse.ok()
							 .contentType(MediaType.TEXT_EVENT_STREAM)
							 .body(Flux.zip(f, interval).map(Tuple2::getT1), String.class);
		
	}

}
