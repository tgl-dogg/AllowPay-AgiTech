package br.com.allowpay.converters;

import java.util.Collection;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.allowpay.canonical.CardRegister;
import br.com.allowpay.canonical.ClientChild;

@Component
public class ClientChildToClientChildConverter {

	@Autowired
	private CardToRegisterConverter cardToRegisterConverter;

	public ClientChild convert(final br.com.allowpay.dtos.ClientChild child) {
		final String name = child.getName();
		final Collection<CardRegister> registers = new HashSet<>();
		child.getCards().forEach((card) -> {
			registers.add(cardToRegisterConverter.convert(card));
		});

		final ClientChild clientChild = new ClientChild(name, registers);
		return clientChild;
	}

}
