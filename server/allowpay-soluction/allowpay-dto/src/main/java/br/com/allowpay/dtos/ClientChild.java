package br.com.allowpay.dtos;

import java.util.Collection;

public class ClientChild extends Client {

	private static final long serialVersionUID = 3244122499416827260L;

	public ClientChild(final String name, final Collection<Card> cards) {
		super(name, cards);
	}
}
