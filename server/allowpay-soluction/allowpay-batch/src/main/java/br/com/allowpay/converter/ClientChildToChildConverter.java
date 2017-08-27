package br.com.allowpay.converter;

import java.util.List;
import java.util.Optional;
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

		final ChildBuilder childBuilder = ChildBuilder.create();

		final Long id = clientChild.getId();
		final String fullName = clientChild.getName();
		final List<Register> registers = clientChild.getRegisters();

		Optional.ofNullable(registers).ifPresent(consumer -> {
			final List<CardRegister> cardRegisters = consumer.parallelStream()
					.map(register -> registerToCardRegisterConverter.convert(register)).collect(Collectors.toList());

			childBuilder.withCardRegisters(cardRegisters);
		});

		final ClientDad clientDad = clientChild.getClientDad();

		Optional.ofNullable(clientDad).ifPresent(consumer -> {
			final Dad dad = clientDadToDadConverter.convert(consumer);
			childBuilder.withDad(dad);
		});

		return childBuilder.withId(id).withFullName(fullName).build();
	}
}