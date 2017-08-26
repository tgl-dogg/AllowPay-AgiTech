package br.com.allowpay.entities.builder;

import java.util.List;

import br.com.allowpay.entities.CardRegister;
import br.com.allowpay.entities.Child;
import br.com.allowpay.entities.Dad;

public class ChildBuilder {

	public static ChildBuilder create() {
		return new ChildBuilder();
	}

	private Long id;
	private String fullName;
	private Dad dad;
	private List<CardRegister> cardRegisters;

	private ChildBuilder() {

	}

	public ChildBuilder withId(final Long id) {
		this.id = id;
		return this;
	}

	public ChildBuilder withFullName(final String fullName) {
		this.fullName = fullName;
		return this;
	}

	public ChildBuilder withDad(final Dad dad) {
		this.dad = dad;
		return this;
	}

	public ChildBuilder withCardRegisters(final List<CardRegister> cardRegisters) {
		this.cardRegisters = cardRegisters;
		return this;
	}

	public Child build() {
		return new Child(id, fullName, dad, cardRegisters);
	}
}