package br.com.allowpay.rules;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.allowpay.builder.BalanceBuilder;
import br.com.allowpay.canonical.Balance;
import br.com.allowpay.canonical.CardIdentification;
import br.com.allowpay.integration.AgilitasRestClient;

@Component
public class AgilitasBalanceConsumer {

	@Autowired
	private CompareBalanceValue compareBalanceValue;
	
	@Autowired
	private AgilitasRestClient agilitarRestClient;

	public Balance getBalance(final CardIdentification cardIdentification) {

		final BigDecimal balanceAgilitar = agilitarRestClient.saldo(cardIdentification);
		final BigDecimal balanceAllowPay = cardIdentification.getBalanceValue();
		final BigDecimal balanceDiference = compareBalanceValue.balanceDiference(balanceAgilitar, balanceAllowPay);

		return BalanceBuilder.create().withCardId(cardIdentification.getCardId()).withValue(balanceDiference).build();
	}
}
