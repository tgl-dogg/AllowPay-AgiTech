package br.com.allowpay.canonical;

import java.io.Serializable;
import java.math.BigDecimal;

public class Balance implements Serializable{

	private static final long serialVersionUID = 5753924319516788407L;
	private final String cardId;
	private final BigDecimal value;
	private final String deviceNotificationDadId;
	private final String deviceNotificationChildId;

	public Balance(final String cardId, final BigDecimal value, final String deviceNotificationDadId, final String deviceNotificationChildId) {
		super();
		this.cardId = cardId;
		this.value = value;
		this.deviceNotificationDadId = deviceNotificationDadId;
		this.deviceNotificationChildId = deviceNotificationChildId;
	}
	
	public String getCardId() {
		return cardId;
	}

	public BigDecimal getValue() {
		return value;
	}
	
	public String getDeviceNotificationDadId() {
		return deviceNotificationDadId;
	}

	public String getDeviceNotificationChildId() {
		return deviceNotificationChildId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cardId == null) ? 0 : cardId.hashCode());
		result = prime * result + ((deviceNotificationChildId == null) ? 0 : deviceNotificationChildId.hashCode());
		result = prime * result + ((deviceNotificationDadId == null) ? 0 : deviceNotificationDadId.hashCode());
		result = prime * result + ((value == null) ? 0 : value.hashCode());
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
		Balance other = (Balance) obj;
		if (cardId == null) {
			if (other.cardId != null)
				return false;
		} else if (!cardId.equals(other.cardId))
			return false;
		if (deviceNotificationChildId == null) {
			if (other.deviceNotificationChildId != null)
				return false;
		} else if (!deviceNotificationChildId.equals(other.deviceNotificationChildId))
			return false;
		if (deviceNotificationDadId == null) {
			if (other.deviceNotificationDadId != null)
				return false;
		} else if (!deviceNotificationDadId.equals(other.deviceNotificationDadId))
			return false;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Balance [cardId=" + cardId + ", value=" + value + ", deviceNotificationDadId=" + deviceNotificationDadId
				+ ", deviceNotificationChildId=" + deviceNotificationChildId + "]";
	}
}
