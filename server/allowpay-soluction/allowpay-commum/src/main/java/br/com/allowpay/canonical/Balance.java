package br.com.allowpay.canonical;

import java.io.Serializable;
import java.math.BigDecimal;

public class Balance implements Serializable{

	private static final long serialVersionUID = 5753924319516788407L;
	private final String cardId;
	private final BigDecimal value;

	public Balance(final String cardId, final BigDecimal value) {
		super();
		this.cardId = cardId;
		this.value = value;
	}
	
	public String getCardId() {
		return cardId;
	}

	public BigDecimal getValue() {
		return value;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cardId == null) ? 0 : cardId.hashCode());
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
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Balance [cardId=" + cardId + ", value=" + value + "]";
	}
}
