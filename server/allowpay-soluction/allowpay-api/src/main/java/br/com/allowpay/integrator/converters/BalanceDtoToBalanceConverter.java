package br.com.allowpay.integrator.converters;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import br.com.allowpay.builder.BalanceBuilder;
import br.com.allowpay.canonical.Balance;
import br.com.allowpay.integrator.dtos.BalanceDto;

@Component
public class BalanceDtoToBalanceConverter {

	public Balance convert(final BalanceDto balanceDto) {
		final String cardId = balanceDto.getCardId();
		final BigDecimal value = BigDecimal.valueOf(balanceDto.getSaldo().getValor());

		return BalanceBuilder.create().withCardId(cardId).withValue(value).build();
	}
}