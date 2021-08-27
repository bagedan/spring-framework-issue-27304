package com.example.demo;

import com.example.demo.model.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@SpringBootTest
@AutoConfigureWebTestClient
public class DemoApplicationTests {

	@Autowired
	private WebTestClient webClient;

	@Autowired
	private MyErrorWebExceptionHandler myErrorWebExceptionHandler;

	@BeforeEach
	public void init(){
		myErrorWebExceptionHandler.reset();
	}

	@Test
	public void callingEndpointWithoutReqBody() throws Exception {
		webClient.post().uri("/itemWithoutReqBody")
				.contentType(MediaType.APPLICATION_JSON)
				.bodyValue(new Item("test item"))
				.accept(MediaType.APPLICATION_JSON)
				.exchange()
				.expectStatus().isOk();

		assertThat(myErrorWebExceptionHandler.getCapturedBody(), equalTo("{\"itemName\":\"test item\"}"));
	}

	@Test
	public void callingEndpointWithReqBody() throws Exception {
		webClient.post().uri("/itemWithRequestBody")
				.contentType(MediaType.APPLICATION_JSON)
				.bodyValue(new Item("test item"))
				.accept(MediaType.APPLICATION_JSON)
				.exchange()
				.expectStatus().isOk();

		assertThat(myErrorWebExceptionHandler.getCapturedBody(), equalTo("{\"itemName\":\"test item\"}"));
	}

}
