package br.com.allowpay.converter;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import br.com.allowpay.builder.BalanceBuilder;
import br.com.allowpay.canonical.Balance;
import br.com.allowpay.entities.CardRegister;

@Component
public class CardRegisterToCardIdentificationConverter {

	public Balance convert(final CardRegister cardRegister) {
		final String cardId = cardRegister.getCardId();
		final BigDecimal balanceValue = cardRegister.getBalance();

		return BalanceBuilder.create().withCardId(cardId).withValue(balanceValue).build();
	}
}
