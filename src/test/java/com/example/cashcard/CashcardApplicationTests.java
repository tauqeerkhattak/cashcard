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
import java.net.URI;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CashcardApplicationTests {

	@Autowired
	private TestRestTemplate testRestTemplate;

	@Test
	public void testGetCashCard() throws IOException {
		Long id = 99L;
		ResponseEntity<CashCard> response = testRestTemplate.getForEntity("/api/cashcard/" + id, CashCard.class);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		CashCard card = response.getBody();
		assertThat(card).isNotNull();
		assertThat(card.id()).isNotNull();
		assertThat(card.id()).isEqualTo(id);
		assertThat(card.amount()).isNotNull();
		assertThat(card.amount()).isEqualTo(123.45);
	}

	@Test
	public void testNotFoundCashCard() {
		/// Not Found Case.
		long id = 6969L;
		ResponseEntity<CashCard> response = testRestTemplate.getForEntity("/api/cashcard/" + id, CashCard.class);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
		assertThat(response.getBody()).isNull();
	}

	@Test
	public void shouldAddNewCashCard() {
		CashCard card = new CashCard(null, 500.0);
		ResponseEntity<Void> response = testRestTemplate.postForEntity("/api/cashcard/", card, Void.class);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
		URI locationNewCard = response.getHeaders().getLocation();
		ResponseEntity<CashCard> getResponse = testRestTemplate.getForEntity(locationNewCard, CashCard.class);
		assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.OK);

		CashCard getCard = getResponse.getBody();
		assertThat(getCard).isNotNull();
		assertThat(getCard.amount()).isEqualTo(card.amount());

	}

}
