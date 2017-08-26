package br.com.allowpay.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.allowpay.canonical.ClientChild;
import br.com.allowpay.canonical.ClientDad;
import br.com.allowpay.canonical.Register;
import br.com.allowpay.entities.CardRegister;
import br.com.allowpay.entities.Child;
import br.com.allowpay.entities.Dad;
import br.com.allowpay.entities.builder.ChildBuilder;

@Component
public class ClientChildToChildConverter {

	@Autowired
	private RegisterToCardRegisterConverter registerToCardRegisterConverter;
	
	@Autowired
	private ClientDadToDadConverter clientDadToDadConverter;

	public Child convert(final ClientChild clientChild) {

		final Long id = clientChild.getId();
		final String fullName = clientChild.getName();
		final List<Register> registers = clientChild.getRegisters();
		final ClientDad clientDad = clientChild.getClientDad();


		final List<CardRegister> cardRegisters = registers.parallelStream()
				.map(register -> registerToCardRegisterConverter.convert(register)).collect(Collectors.toList());

		final Dad dad = clientDadToDadConverter.convert(clientDad);
		
		return ChildBuilder.create().withId(id).withFullName(fullName).withCardRegisters(cardRegisters).withDad(dad)
				.build();
	}
}