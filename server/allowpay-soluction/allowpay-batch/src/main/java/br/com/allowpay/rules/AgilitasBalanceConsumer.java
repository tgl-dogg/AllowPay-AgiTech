package br.com.allowpay.rules;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.allowpay.builder.BalanceBuilder;
import br.com.allowpay.canonical.Balance;
import br.com.allowpay.exception.IntegrationFailException;
import br.com.allowpay.integration.ApiGatewayAllowPayGetAgilitasBalanceRestClient;

@Component
public class AgilitasBalanceConsumer {

	@Autowired
	private CompareBalanceValue compareBalanceValue;

	@Autowired
	private ApiGatewayAllowPayGetAgilitasBalanceRestClient agilitarRestClient;

	public Balance getBalanceDiferenceAgilitasAndAllowpay(final Balance balance) throws IntegrationFailException {

		final BigDecimal balanceAgilitar = agilitarRestClient.saldo(balance.getCardId());
		final BigDecimal balanceAllowPay = balance.getValue();
		final BigDecimal balanceDiference = compareBalanceValue.balanceDiference(balanceAgilitar, balanceAllowPay);

		return BalanceBuilder.create().withCardId(balance.getCardId()).withValue(balanceDiference).build();
	}
}
