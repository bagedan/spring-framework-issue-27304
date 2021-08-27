package com.example.demo;

import com.example.demo.model.Item;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class Controller {

    @PostMapping(value = "/itemWithoutReqBody")
    public Mono<Item> createWithoutReqBody(Item item) {
        // Unable to read Item, but able to get the request's body
        // ....
        System.out.println("Request: " + item);
        throw new RuntimeException("exception");
//        return Mono.just(new Item("test"));
    }

    @PostMapping(value = "/itemWithRequestBody")
    public Mono<Item> createWithRequestBody(@RequestBody Item item) {
        // Could read Item, but unable to get the request's body
        // ....
        System.out.println("Request: " + item);
        throw new RuntimeException("exception");

//        return Mono.just(new Item("test"));
    }
}
