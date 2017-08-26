package br.com.allowpay.controllers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class CardsControllerTest extends ControllerTest{
	
    
	
	@Test
	public void testGetBonusOk(){
		final String cardId = "testId";
		final String resource = "/" + cardId + "/bonus";
		final String response = getTestRestTemplate().getForObject(getUrl(resource), String.class);
		
		//TODO:
		
	}
	
	@Test
	public void testGetBonusNotFound(){
		//TODO:
	}

}
