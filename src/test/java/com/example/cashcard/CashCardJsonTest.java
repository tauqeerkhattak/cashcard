package com.example.cashcard;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.json.JsonContent;
import org.springframework.boot.test.json.ObjectContent;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
public class CashCardJsonTest {
    @Autowired
    private JacksonTester<CashCard> json;

    @Test
    public void serializationTest() throws IOException {
        CashCard card = new CashCard(69L, 123.45);
        JsonContent<CashCard> result = json.write(card);
        assertThat(result).isStrictlyEqualToJson("expected.json");
        assertThat(result).hasJsonPathValue("$.id");
        assertThat(result).extractingJsonPathNumberValue("$.id").isEqualTo(69);
        assertThat(result).hasJsonPathValue("$.amount");
        assertThat(result).extractingJsonPathNumberValue("$.amount").isEqualTo(123.45);
    }

    @Test
    public void deserializationTest() throws IOException {
        String data = """
        {
            "id": 99,
            "amount": 123.45
        }""";
        ObjectContent<CashCard> content = json.parse(data);
        CashCard card = content.getObject();
        assertThat(card).isNotNull();
        assertThat(card.id()).isNotNull();
        assertThat(card.id()).isEqualTo(99);
        assertThat(card.amount()).isNotNull();
        assertThat(card.amount()).isEqualTo(123.45);
    }
}
