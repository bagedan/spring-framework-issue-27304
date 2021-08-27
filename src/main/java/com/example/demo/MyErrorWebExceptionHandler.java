package com.example.demo;

import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.channels.Channels;
import java.nio.charset.StandardCharsets;

@Configuration
@Order(-2)
public class MyErrorWebExceptionHandler implements ErrorWebExceptionHandler {

    private String capturedBody;

    @Override
    public Mono<Void> handle(ServerWebExchange serverWebExchange, Throwable throwable) {
        Flux<DataBuffer> reqBodyBuff = serverWebExchange.getRequest().getBody();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        reqBodyBuff.collectList().subscribe(r -> {
            r.forEach(buffer -> {
                try {
                    Channels.newChannel(baos).write(buffer.asByteBuffer().asReadOnlyBuffer());
                    capturedBody = new String(baos.toByteArray(), StandardCharsets.UTF_8);
                    System.out.println("Captured body: " + capturedBody);
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

    public String getCapturedBody() {
        return capturedBody;
    }

    public void reset(){
        this.capturedBody = null;
    }
}
