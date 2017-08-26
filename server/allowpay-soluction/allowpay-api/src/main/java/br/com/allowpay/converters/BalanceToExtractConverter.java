package br.com.allowpay.converters;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import br.com.allowpay.canonical.Balance;
import br.com.allowpay.dtos.Extract;

@Component
public class BalanceToExtractConverter {

	public Extract convert(final Balance balance) {
		final String cardId = balance.getCardId();
		final Integer value = getBalance(balance.getValue());

		final Extract extract = new Extract(cardId, value);
		return extract;
	}

	private Integer getBalance(final BigDecimal value) {
		return value.toBigInteger().intValue() * 100;
	}
}
