package br.com.allowpay.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.web.client.TestRestTemplate;

class ControllerTest {
	
	private static final String PATH = "http://localhost:";
	
	@Value("${api.version}")
	private String apiVersion;
	
	@LocalServerPort
    private int port;
	
	@Autowired
    private TestRestTemplate restTemplate;
	
	String getUrl(final String resource){
		final StringBuilder builder = new StringBuilder();
		builder.append(PATH);
		builder.append(port);
		builder.append("/");
		builder.append(apiVersion);
		builder.append(resource);
		
		return builder.toString();
	}
	
	TestRestTemplate getTestRestTemplate(){
		return restTemplate;
	}

}