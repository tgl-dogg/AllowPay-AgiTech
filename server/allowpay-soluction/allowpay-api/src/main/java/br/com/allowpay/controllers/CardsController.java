package br.com.allowpay.controllers;

import java.net.URI;
import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.allowpay.canonical.Balance;
import br.com.allowpay.converters.BalanceToExtractConverter;
import br.com.allowpay.dtos.Coordinate;
import br.com.allowpay.dtos.Extract;
import br.com.allowpay.dtos.FullExtract;
import br.com.allowpay.integrator.AgilitasCardRestClient;

@RestController
@RequestMapping("${api.version}/cards")
public class CardsController {
	
	//TODO: HazelCastConfiguration copiar...
	//TODO: MonitorBalanceAgilitasFasade exemplo, injetar, chamar o método e enviar
	//@Bean(name = "registerNotificationTopic")
	//@Bean(name = "dadNotificationTopic")
	//@Bean(name = "childNotificationTopic")
	
	//BalanceNotificationEventListener, ouvir e notificar os aplicativos...
	
	
	@Autowired
	private AgilitasCardRestClient agilitasCardRestClient;
	
	@Autowired
	private BalanceToExtractConverter balanceToExtractConverter;
	
	@GetMapping("/{cardId}/bonus")
	ResponseEntity<Extract> getBonus(@PathVariable("cardId") String cardId){
		//TODO: Recuperar informações de bônus do cliente informado.

		final Extract extractBonus = getMockExtract(cardId);
		return ResponseEntity.ok(extractBonus);
	}
	
	@GetMapping("/{cardId}/extract")
	ResponseEntity<Collection<FullExtract>> getExtract(@PathVariable("cardId") String cardId){
		//TODO: Recuperar informações de extrato do cliente informado.
		
		final Collection<FullExtract> extracts = getMockFullExtracts(cardId);
		return ResponseEntity.ok(extracts);
	}
	
	
	private Collection<FullExtract> getMockFullExtracts(final String cardId) {
		return Stream.of(getMockFullExtract(cardId)).collect(Collectors.toList());
	}

	private FullExtract getMockFullExtract(final String cardId) {
		final Coordinate coordinate = new Coordinate(-22.8174189, -47.0408599);
		final FullExtract fullExtract = new FullExtract(cardId, 9999, "10/01/2017 12:12:12", "Merchant Test", "Carga", coordinate);
		return fullExtract;
	}

	@GetMapping("/{cardId}/balance")
	ResponseEntity<Extract> getBalance(@PathVariable("cardId") String cardId){
		final Balance balance = agilitasCardRestClient.getBalance(cardId);
		final Extract balanceExtract = balanceToExtractConverter.convert(balance);
		
		return ResponseEntity.ok(balanceExtract);
	}
	
	@PostMapping("/{cardId}/payment")
	ResponseEntity<Void> setPayment(@PathVariable("cardId") String cardId){
		//TODO: Realizar o pagamento
		
		return ResponseEntity.created(URI.create("http://allowpay.me")).build();
	}
	
	private Extract getMockExtract(final String cardId){
		final Integer value = 10000;
		
		final Extract extract = new Extract(cardId, value);
		return extract;
	}
	
}