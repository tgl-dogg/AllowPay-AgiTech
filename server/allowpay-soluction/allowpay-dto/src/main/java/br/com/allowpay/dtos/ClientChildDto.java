package br.com.allowpay.dtos;

import java.util.Collection;

public class ClientChildDto extends ClientDto {

	private static final long serialVersionUID = 3244122499416827260L;

	public ClientChildDto(final String name, final String deviceId, final Collection<CardDto> cards) {
		super(name, cards, deviceId);
	}
}
