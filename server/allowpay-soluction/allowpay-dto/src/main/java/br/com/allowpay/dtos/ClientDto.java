package br.com.allowpay.dtos;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;

public abstract class ClientDto implements Serializable {

	private static final long serialVersionUID = -775080252163168280L;

	private String name;
	
	private String deviceId;

	private Collection<CardDto> cards = new HashSet<>();

	public ClientDto() {
		super();
	}

	public ClientDto(final String name, final Collection<CardDto> cards, final String deviceId) {
		super();
		this.name = name;
		this.cards = cards;
		this.deviceId = deviceId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Collection<CardDto> getCards() {
		return cards;
	}

	public void setCards(Collection<CardDto> cards) {
		this.cards = cards;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cards == null) ? 0 : cards.hashCode());
		result = prime * result + ((deviceId == null) ? 0 : deviceId.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ClientDto other = (ClientDto) obj;
		if (cards == null) {
			if (other.cards != null)
				return false;
		} else if (!cards.equals(other.cards))
			return false;
		if (deviceId == null) {
			if (other.deviceId != null)
				return false;
		} else if (!deviceId.equals(other.deviceId))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
}