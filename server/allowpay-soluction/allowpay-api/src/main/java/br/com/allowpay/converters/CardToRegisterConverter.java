package br.com.allowpay.converters;

import org.springframework.stereotype.Component;

import br.com.allowpay.canonical.Register;
import br.com.allowpay.dtos.CardDto;

@Component
public class CardToRegisterConverter {

	public Register convert(final CardDto card) {
		final String id = card.getId();

		//TODO: Preencher
		final Register register = new Register(id, null, null, null, null, null);
		return register;
	}

}
