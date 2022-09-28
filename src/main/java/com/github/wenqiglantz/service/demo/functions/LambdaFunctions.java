package com.github.wenqiglantz.service.demo.functions;

import com.github.wenqiglantz.service.demo.data.DemoRequest;
import com.github.wenqiglantz.service.demo.data.DemoResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.function.Function;

@Configuration
@Slf4j
@RequiredArgsConstructor
public class LambdaFunctions {

    private static final String BASE_URL = "http://########.com/api";

    private final WebClient webClient;

    @Bean
    public Function<DemoRequest, Mono<DemoResponse>> records() {
        return (request) -> {
            return webClient.post()
                    .uri(URI.create(BASE_URL))
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                    .body(BodyInserters.fromValue(request))
                    .exchange()
                    .block()
                    .bodyToMono(DemoResponse.class);
        };
    }
}
