package br.com.allowpay.converter;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import br.com.allowpay.builder.CardIdentificationBuilder;
import br.com.allowpay.canonical.CardIdentification;
import br.com.allowpay.entities.CardRegister;

@Component
public class CardRegisterToCardIdentificationConverter {

	public CardIdentification convert(final CardRegister cardRegister) {
		final String cardId = cardRegister.getCardId();
		final BigDecimal balanceValue = cardRegister.getBalance();

		return CardIdentificationBuilder.create().withCardId(cardId).withBalance(balanceValue).build();
	}
}
