package br.com.allowpay.converters;

import org.springframework.stereotype.Component;

import br.com.allowpay.canonical.CardRegister;
import br.com.allowpay.dtos.Card;

@Component
public class CardToRegisterConverter {

	public CardRegister convert(final Card card) {
		final String id = card.getId();

		final CardRegister register = new CardRegister(id);
		return register;
	}

}
