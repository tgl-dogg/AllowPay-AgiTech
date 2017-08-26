package br.com.allowpay.controllers;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.allowpay.dtos.Extract;

@RestController
@RequestMapping("${api.version}/cards")
public class CardsController {
	
	
	@GetMapping("/{cardId}/bonus")
	ResponseEntity<Extract> getBonus(@PathVariable("cardId") String cardId){
		//TODO: Recuperar informações de bônus do cliente informado.
		
		final Extract extractBonus = getMockExtract(cardId);
		return ResponseEntity.ok(extractBonus);
	}
	
	@GetMapping("/{cardId}/extract")
	ResponseEntity<Collection<Extract>> getExtract(@PathVariable("cardId") String cardId){
		//TODO: Recuperar informações de extrato do cliente informado.
		
		final Collection<Extract> extracts = getMockExtracts(cardId);
		return ResponseEntity.ok(extracts);
	}
	
	
	@GetMapping("/{cardId}/balance")
	ResponseEntity<Extract> getBalance(@PathVariable("cardId") String cardId){
		//TODO: Recuperar informações de extrato do cliente informado.
		
		final Extract extractBalance = getMockExtract(cardId);
		return ResponseEntity.ok(extractBalance);
	}
	
	@PostMapping("/{cardId}/payment")
	ResponseEntity<Void> setPayment(@PathVariable("cardId") String cardId){
		//TODO: Realizar o pagamento
		
		return ResponseEntity.created(null).build();
	}
	
	private Extract getMockExtract(final String cardId){
		final BigDecimal value = BigDecimal.valueOf(1000L);
		
		final Extract extract = new Extract(cardId, value);
		return extract;
	}
	
	private Collection<Extract> getMockExtracts(final String cardId){
		return Stream.of(getMockExtract(cardId)).collect(Collectors.toList()); 
	}
}