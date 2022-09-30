package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.time.Duration;

@RestController
public class DummyController {

    @GetMapping("/get/mono")
    public Mono<String> getTest(Integer groupId) {
        return Mono.fromCallable(() -> {
            Thread.sleep(10000);
            return "Success";
        }).timeout(Duration.ofMillis(50), Mono.fromCallable(() -> {
            System.out.println("timed out");
            return "timeout";
        })).onErrorResume(e -> Mono.just(e.getMessage()));
    }

}
