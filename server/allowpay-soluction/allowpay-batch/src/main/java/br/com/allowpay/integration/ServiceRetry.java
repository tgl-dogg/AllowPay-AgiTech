package br.com.allowpay.integration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import br.com.allowpay.exception.IntegrationFailException;

@Component
public class ServiceRetry {

	@Value("${apiGateway.retry:3}")
	private Integer retry;

	public <I, O> O callWithRetry(final AllowPayApiGatewayServiceRetry allowPayApiGatewayServiceRetry, final I input,
			final Class<O> returnType) throws Exception {
		return executeAndverifyInternalServerError(allowPayApiGatewayServiceRetry, input, returnType, null, 0);
	}

	private <I, O> O executeAndverifyInternalServerError(
			final AllowPayApiGatewayServiceRetry allowPayApiGatewayServiceRetry, final I input,
			final Class<O> returnType, final IntegrationFailException e, final Integer retry) throws Exception {

		if (retry <= this.retry) {
			if (e == null) {
				return allowPayApiGatewayServiceRetry.execute(input, returnType);
			} else {
				if (!e.getStatusCode().is5xxServerError()) {
					try {
						return allowPayApiGatewayServiceRetry.execute(input, returnType);
					} catch (final IntegrationFailException e1) {
						return executeAndverifyInternalServerError(allowPayApiGatewayServiceRetry, input, returnType, e,
								retry + 1);
					}
				} else {
					throw createInternalServerError(e);
				}
			}
		} else {
			throw new RuntimeException("Amount of attempts exceeded, retry=" + retry);
		}
	}

	private RuntimeException createInternalServerError(final IntegrationFailException e) throws Exception {
		return new RuntimeException(e.getStatusCode().value() + "-" + e.getStatusCode().getReasonPhrase() + "["
				+ e.getOperationMethod() + e.getOperationName() + "]");
	}
}
