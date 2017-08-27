package br.com.allowpay.controllers;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.allowpay.canonical.Balance;
import br.com.allowpay.canonical.Extract;
import br.com.allowpay.converters.BalanceToExtractConverter;
import br.com.allowpay.converters.ExtractToFullExtractDtoConverter;
import br.com.allowpay.dtos.ExtractDto;
import br.com.allowpay.dtos.FullExtractDto;
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
	
	@Autowired
	private ExtractToFullExtractDtoConverter extractDtoToFullExtractConverter;
	
	@GetMapping("/{cardId}/bonus")
	ResponseEntity<ExtractDto> getBonus(@PathVariable("cardId") String cardId){
		//TODO: Recuperar informações de bônus do cliente informado.

		final ExtractDto extractBonus = getMockExtract(cardId);
		return ResponseEntity.ok(extractBonus);
	}
	
	@GetMapping("/{cardId}/extract")
	ResponseEntity<List<FullExtractDto>> getExtract(@PathVariable("cardId") String cardId){
		final List<Extract> extracts = agilitasCardRestClient.getExtract(cardId);
		final List<FullExtractDto> fullExtracts = new ArrayList<>(extracts.size());
		extracts.forEach( (extract)  ->{
			final FullExtractDto fullExtractDto = extractDtoToFullExtractConverter.convert(extract);
			fullExtractDto.setCardId(cardId);
			fullExtracts.add(fullExtractDto);
		});
		
		return ResponseEntity.ok(fullExtracts);
	}


	@GetMapping("/{cardId}/balance")
	ResponseEntity<ExtractDto> getBalance(@PathVariable("cardId") String cardId){
		final Balance balance = agilitasCardRestClient.getBalance(cardId);
		final ExtractDto balanceExtract = balanceToExtractConverter.convert(balance);
		
		return ResponseEntity.ok(balanceExtract);
	}
	
	@PostMapping("/{cardId}/payment")
	ResponseEntity<Void> setPayment(@PathVariable("cardId") String cardId){
		//TODO: Realizar o pagamento
		
		return ResponseEntity.created(URI.create("http://allowpay.me")).build();
	}
	
	private ExtractDto getMockExtract(final String cardId){
		final Integer value = 10000;
		
		final ExtractDto extract = new ExtractDto(cardId, value);
		return extract;
	}
	
}