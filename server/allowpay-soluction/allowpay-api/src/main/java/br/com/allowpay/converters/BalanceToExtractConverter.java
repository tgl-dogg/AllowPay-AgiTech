package br.com.allowpay.converters;

import org.springframework.stereotype.Component;

import br.com.allowpay.canonical.Balance;
import br.com.allowpay.dtos.ExtractDto;

@Component
public class BalanceToExtractConverter {

	public ExtractDto convert(final Balance balance) {
		final String cardId = balance.getCardId();
		final Integer value = balance.getValue().intValue();

		final ExtractDto extract = new ExtractDto(cardId, value);
		return extract;
	}	
}