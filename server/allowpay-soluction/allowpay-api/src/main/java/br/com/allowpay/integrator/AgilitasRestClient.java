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
}
