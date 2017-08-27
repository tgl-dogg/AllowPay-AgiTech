package br.com.allowpay.integration;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import br.com.allowpay.canonical.Extract;
import br.com.allowpay.exception.IntegrationFailException;

@Component
public class ApiGatewayAllowPayGetAgilitasBalanceRestClient implements AllowPayApiGatewayServiceRetry {

	@Value("${apiGateway.url:http://localhost:8080}")
	private String url;

	@Value("${apiGateway.balancePath:/{cardId}/balance}")
	private String path;

	public BigDecimal saldo(final String cardId) throws IntegrationFailException {
		final RestTemplate restTemplate = new RestTemplate();
		final Map<String, String> uriVariables = new HashMap<>();
		uriVariables.put("{cardId}", cardId);

		final ResponseEntity<Extract> responseEntityBalance = restTemplate.getForEntity(url + path, Extract.class,
				uriVariables);

		if (responseEntityBalance.getStatusCode().is2xxSuccessful()) {
			return BigDecimal.valueOf(responseEntityBalance.getBody().getAmount());
		} else {
			throw new IntegrationFailException(path.replace("{cardId}", cardId), "GET",
					responseEntityBalance.getStatusCode());
		}
	}

	@Override
	public <I, O> O execute(final I input, final Class<O> returnType) throws IntegrationFailException {
		return returnType.cast(saldo((String) input));
	}
}
