package com.example.cashcard;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CashcardApplicationTests {

	@Autowired
	private TestRestTemplate testRestTemplate;

	@Test
	public void testGetCashCard() throws IOException {
		Long id = 12304L;
		ResponseEntity<CashCard> response = testRestTemplate.getForEntity("/api/cashcard/" + id, CashCard.class);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		CashCard card = response.getBody();
		assertThat(card).isNotNull();
		assertThat(card.id()).isNotNull();
		assertThat(card.id()).isEqualTo(id);
		assertThat(card.amount()).isNotNull();
		assertThat(card.amount()).isEqualTo(123.45);
	}

}
