package com.example.demo;

import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.channels.Channels;
import java.nio.charset.StandardCharsets;

public class MyErrorWebExceptionHandler implements ErrorWebExceptionHandler {
    @Override
    public Mono<Void> handle(ServerWebExchange serverWebExchange, Throwable throwable) {
        var reqBodyBuff = serverWebExchange.getRequest().getBody();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        reqBodyBuff.collectList().subscribe(r -> {
            r.forEach(buffer -> {
                try {
                    Channels.newChannel(baos).write(buffer.asByteBuffer().asReadOnlyBuffer());
                    String bodyStr = new String(baos.toByteArray(), StandardCharsets.UTF_8);
                    System.out.println(bodyStr);
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        baos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        });

        return Mono.empty();
    }
}
