package br.com.allowpay.integration;

import br.com.allowpay.exception.IntegrationFailException;

public interface AllowPayApiGatewayServiceRetry {
	
	public <I,O> O execute(final I input, final Class<O> returnType) throws IntegrationFailException;

}
