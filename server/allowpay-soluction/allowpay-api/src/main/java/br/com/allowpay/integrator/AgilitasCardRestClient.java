package br.com.allowpay.integrator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.allowpay.canonical.Balance;
import br.com.allowpay.canonical.Extract;
import br.com.allowpay.integrator.converters.BalanceDtoToBalanceConverter;
import br.com.allowpay.integrator.converters.ExtractDtoToFullExtractConverter;
import br.com.allowpay.integrator.dtos.BalanceDto;
import br.com.allowpay.integrator.dtos.BalanceValueDto;
import br.com.allowpay.integrator.dtos.ExtractDto;

@Service
public class AgilitasCardRestClient {

	private static final Logger LOGGER = LoggerFactory.getLogger(AgilitasRestClient.class);

	private static final String RESOURCE = "/cartoes";
	private static final String RESOURCE_BALANCE = "/saldo";
	private static final String RESOURCE_EXTRACT = "/extrato";

	// FIXME: Data de filtro definida para o mês corrente, porém decorrer da
	// evolução da aplicação a mesma deve possuir o extrato do cliente desde sua
	// origem e fornecer essa info.
	final Map<String, Object> paramsGetExtractCurrentMonth = new HashMap<String, Object>() {
		private static final long serialVersionUID = -8437497415550790542L;
		{
			put("dataInicial", "2017-07-28");
			put("dataFinal", "2017-08-27");
		}
	};

	@Autowired
	private AgilitasRestClient agilitasRestClient;

	@Autowired
	private BalanceDtoToBalanceConverter balanceConverter;

	@Autowired
	private ExtractDtoToFullExtractConverter extractConverter;

	public Balance getBalance(final String cardId) {
		try {
			final HttpEntity<Void> httpEntity = new HttpEntity<>(agilitasRestClient.getHeaders());
			final ResponseEntity<BalanceDto> responseEntity = agilitasRestClient.getRestTemplate()
					.exchange(getUrl(cardId, RESOURCE_BALANCE), HttpMethod.GET, httpEntity, BalanceDto.class);

			final BalanceDto balanceDto = responseEntity.getBody();
			balanceDto.setCardId(cardId);

			return balanceConverter.convert(responseEntity.getBody());
		} catch (HttpClientErrorException e) {
			LOGGER.error(e.getMessage());
			throw new RuntimeException(e.getMessage(), e.getCause());
		}
	}

	public List<Extract> getExtract(final String cardId) {
		try {

			final HttpEntity<Void> httpEntity = new HttpEntity<>(agilitasRestClient.getHeaders());
			final ResponseEntity<ExtractDto> responseEntity = agilitasRestClient.getRestTemplate().exchange(
					getUrl(cardId, RESOURCE_EXTRACT, paramsGetExtractCurrentMonth), HttpMethod.GET, httpEntity,
					ExtractDto.class);

			final ExtractDto extractDto = responseEntity.getBody();
			final List<Extract> extracts = new ArrayList<>(extractDto.getExtrato().size());
			extractDto.getExtrato().forEach((extract) -> {
				extracts.add(extractConverter.convert(extract));
			});

			return extracts;
		} catch (HttpClientErrorException e) {
			LOGGER.error(e.getMessage());
			throw new RuntimeException(e.getMessage(), e.getCause());
		}
	}
	
	public void recharge(final String cardId, final Double value){
		try {
			final BalanceValueDto balanceValueDto = new BalanceValueDto(value);
			final BalanceDto balanceDto = new BalanceDto(cardId, balanceValueDto);
			
			final HttpEntity<BalanceDto> httpEntity = new HttpEntity<BalanceDto>(balanceDto, agilitasRestClient.getHeaders());
			agilitasRestClient.getRestTemplate().exchange(
					getUrl(cardId, RESOURCE_BALANCE, paramsGetExtractCurrentMonth), HttpMethod.PUT, httpEntity, BalanceDto.class);
			//TODO: Validar retorno da api agilitas
		} catch (HttpClientErrorException e) {
			LOGGER.error(e.getMessage());
			throw new RuntimeException(e.getMessage(), e.getCause());
		}
	}

	private String getUrl(final String cardId, final String resourceService, final Map<String, Object> params) {
		final UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder
				.fromHttpUrl(getUrl(cardId, resourceService));
		params.entrySet().forEach(entry -> addParam(uriComponentsBuilder, entry.getKey(), entry.getValue()));

		return uriComponentsBuilder.build().encode().toUriString();
	}

	private void addParam(final UriComponentsBuilder uriComponentsBuilder, final String name, final Object value) {
		if (value == null) {
			return;
		}

		uriComponentsBuilder.queryParam(name, value);
	}

	private String getUrl(final String cardId, final String resourceService) {
		final StringBuilder builder = new StringBuilder();
		builder.append(agilitasRestClient.getUrl(RESOURCE));
		builder.append("/");
		builder.append(cardId);
		builder.append(resourceService);

		return builder.toString();
	}

}
