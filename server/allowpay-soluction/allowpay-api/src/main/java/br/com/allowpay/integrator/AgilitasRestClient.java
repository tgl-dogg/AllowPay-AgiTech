package br.com.allowpay.integrator;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
class AgilitasRestClient {

	@Value("${api.agilitas.url.path}")
	private String apiPathUrl;

	@Value("${api.agilitas.url.version}")
	private String apiVersion;

	@Value("#{${api.agilitas.headers}}")
	private Map<String, String> headers;

	@Autowired
	private RestTemplate restTemplate;

	protected RestTemplate getRestTemplate() {
		return restTemplate;
	}

	protected MultiValueMap<String, String> getHeaders() {
		final MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
		headers.entrySet().forEach((entry) -> {
			multiValueMap.add(entry.getKey(), entry.getValue());
		});

		return multiValueMap;
	}

	protected String getUrl(final String resource) {
		final StringBuilder builder = new StringBuilder();
		builder.append(apiPathUrl);
		builder.append(apiVersion);
		builder.append(resource);
		return builder.toString();
	}

	protected String getUrlWithParams(final String resource, final Map<String, Object> params) {
		final UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromHttpUrl(getUrl(resource));
		params.entrySet().forEach(entry -> addParam(uriComponentsBuilder, entry.getKey(), entry.getValue()));

		return uriComponentsBuilder.build().encode().toUriString();
	}

	private void addParam(final UriComponentsBuilder uriComponentsBuilder, final String name, final Object value) {
		if (value == null) {
			return;
		}

		uriComponentsBuilder.queryParam(name, value);
	}

}
