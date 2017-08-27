package br.com.allowpay.converters;

import org.springframework.stereotype.Component;

import br.com.allowpay.canonical.Register;
import br.com.allowpay.dtos.Card;

@Component
public class CardToRegisterConverter {

	public Register convert(final Card card) {
		final String id = card.getId();

		//TODO: Preencher
		final Register register = new Register(id, null, null, null, null, null);
		return register;
	}

}
