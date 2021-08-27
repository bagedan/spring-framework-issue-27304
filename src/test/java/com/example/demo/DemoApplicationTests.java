package com.example.demo;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

//@SpringBootTest
//@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@SpringBootTest(
		webEnvironment = SpringBootTest.WebEnvironment.MOCK,
		classes = DemoApplication.class)
@AutoConfigureMockMvc
public class DemoApplicationTests {

	@Autowired
	private MockMvc mockMvc;

//	WebTestClient client;
//
//	@BeforeEach
//	void setUp(ApplicationContext context) {
//		client =
//				WebTestClient.bindToController(new Controller()).build();
//	}

	@Test
	public void callingEndpointWithoutReqBody() throws Exception {
//		client.get().uri("/itemWithoutReqBody")
//				.accept(MediaType.APPLICATION_JSON)
//				.exchange()
//				.expectStatus().isOk()
//				.expectHeader().contentType(MediaType.APPLICATION_JSON);
	}

}
