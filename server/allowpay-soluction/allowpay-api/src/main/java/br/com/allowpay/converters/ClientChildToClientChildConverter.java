package br.com.allowpay.converters;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.allowpay.canonical.ClientChild;
import br.com.allowpay.canonical.Register;

@Component
public class ClientChildToClientChildConverter {

	@Autowired
	private CardToRegisterConverter cardToRegisterConverter;

	public ClientChild convert(final br.com.allowpay.dtos.ClientChild child) {
		final String name = child.getName();
		final List<Register> registers = new ArrayList<>();
		child.getCards().forEach((card) -> {
			registers.add(cardToRegisterConverter.convert(card));
		});

		//TODO: Preencher
		final ClientChild clientChild = new ClientChild(name, null, registers, null);
		return clientChild;
	}

}
