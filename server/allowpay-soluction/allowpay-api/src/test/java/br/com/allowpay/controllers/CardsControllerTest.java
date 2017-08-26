package br.com.allowpay.controllers;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.allowpay.dtos.Extract;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class CardsControllerTest extends ControllerTest {

	private static final String CARD_ID = "testId-0069P";

	@Test
	public void testGetBonusOk() {
		final String resource = "/" + CARD_ID + "/bonus";
		final ResponseEntity<Extract> response = getTestRestTemplate().getForEntity(getUrl(resource), Extract.class);

		Assert.assertNotNull(response.getBody());
	}

	@Test
	public void testGetBalanceOk() {
		final String resource = "/" + CARD_ID + "/balance";
		final ResponseEntity<Extract> response = getTestRestTemplate().getForEntity(getUrl(resource), Extract.class);

		Assert.assertNotNull(response.getBody());
	}

	@Test
	public void testGetExtractOk() {
		final String resource = "/" + CARD_ID + "/extract";
		final ResponseEntity<Extract> response = getTestRestTemplate().getForEntity(getUrl(resource), Extract.class);

		Assert.assertNotNull(response.getBody());
	}
}