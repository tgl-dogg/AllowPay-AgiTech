package br.com.allowpay.canonical;

import java.math.BigDecimal;

public class CardIdentification {

	private final String cardId;
	private final BigDecimal balanceValue;

	public CardIdentification(final String cardId, final BigDecimal balanceValue) {
		super();
		this.cardId = cardId;
		this.balanceValue = balanceValue;
	}

	public String getCardId() {
		return cardId;
	}
	
	public BigDecimal getBalanceValue() {
		return balanceValue;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((balanceValue == null) ? 0 : balanceValue.hashCode());
		result = prime * result + ((cardId == null) ? 0 : cardId.hashCode());
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
		CardIdentification other = (CardIdentification) obj;
		if (balanceValue == null) {
			if (other.balanceValue != null)
				return false;
		} else if (!balanceValue.equals(other.balanceValue))
			return false;
		if (cardId == null) {
			if (other.cardId != null)
				return false;
		} else if (!cardId.equals(other.cardId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CardIdentification [cardId=" + cardId + ", balanceValue=" + balanceValue + "]";
	}
}