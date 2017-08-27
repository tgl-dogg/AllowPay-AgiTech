package br.com.allowpay.integrator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import br.com.allowpay.canonical.Balance;
import br.com.allowpay.integrator.converters.BalanceDtoToBalanceConverter;
import br.com.allowpay.integrator.dtos.BalanceDto;

@Service
public class AgilitasCardRestClient {

	private static final Logger LOGGER = LoggerFactory.getLogger(AgilitasRestClient.class);

	private static final String RESOURCE = "/cartoes";
	private static final String RESOURCE_BALANCE = "/saldo";

	@Autowired
	private AgilitasRestClient agilitasRestClient;

	@Autowired
	private BalanceDtoToBalanceConverter balanceConverter;

	public Balance getBalance(final String cardId) {
		try {
			final HttpEntity<Void> httpEntity = new HttpEntity<>(agilitasRestClient.getHeaders());
			final ResponseEntity<BalanceDto> responseEntity = agilitasRestClient.getRestTemplate()
					.exchange(getUrl(cardId), HttpMethod.GET, httpEntity, BalanceDto.class);

			final BalanceDto balanceDto = responseEntity.getBody();
			balanceDto.setCardId(cardId);
			
			return balanceConverter.convert(responseEntity.getBody());
		} catch (HttpClientErrorException e) {
			LOGGER.error(e.getMessage());
			throw new RuntimeException(e.getMessage(), e.getCause());
		}
	}

	private String getUrl(final String cardId) {
		final StringBuilder builder = new StringBuilder();
		builder.append(agilitasRestClient.getUrl(RESOURCE));
		builder.append("/");
		builder.append(cardId);
		builder.append(RESOURCE_BALANCE);

		return builder.toString();
	}
}
