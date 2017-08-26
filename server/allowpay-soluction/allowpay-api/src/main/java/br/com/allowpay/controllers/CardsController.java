package br.com.allowpay.controllers;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.allowpay.canonical.Extract;

@RestController
@RequestMapping("${api.version}/cards")
public class CardsController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CardsController.class);
	
	@GetMapping("/{cardId}/bonus")
	ResponseEntity<?> getBonus(@PathVariable("cardId") String cardId){
		LOGGER.info("test id");
		//TODO: Recuperar informações de bônus do cliente informado.
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/{cardId}/extract")
	ResponseEntity<Collection<Extract>> getExtract(@PathVariable("cardId") String cardId){
		//TODO: Recuperar informações de extrato do cliente informado.
		return ResponseEntity.ok().build();
	}
	
	
	@GetMapping("/{cardId}/balance")
	ResponseEntity<?> getBalance(@PathVariable("cardId") String cardId){
		//TODO: Recuperar informações de extrato do cliente informado.
		return ResponseEntity.ok().build();
	}
	
	@PostMapping("/{cardId}/payment")
	ResponseEntity<?> setPayment(@PathVariable("cardId") String cardId){
		//TODO: Realizar o pagamento
		return ResponseEntity.created(null).build();
	}
}