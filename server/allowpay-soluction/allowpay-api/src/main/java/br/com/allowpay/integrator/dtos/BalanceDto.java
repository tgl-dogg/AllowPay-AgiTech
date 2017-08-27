package br.com.allowpay.integrator.dtos;

import java.io.Serializable;

public class BalanceDto implements Serializable {

	private static final long serialVersionUID = 3806942598537443104L;

	private String cardId;
	private BalanceValueDto saldo;

	public BalanceDto() {
	}

	public BalanceDto(final String cardId, final BalanceValueDto balanceValueDto) {
		super();
		this.cardId = cardId;
		this.saldo = balanceValueDto;
	}

	public String getCardId() {
		return cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

	public BalanceValueDto getSaldo() {
		return saldo;
	}

	public void setSaldo(BalanceValueDto saldo) {
		this.saldo = saldo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cardId == null) ? 0 : cardId.hashCode());
		result = prime * result + ((saldo == null) ? 0 : saldo.hashCode());
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
		BalanceDto other = (BalanceDto) obj;
		if (cardId == null) {
			if (other.cardId != null)
				return false;
		} else if (!cardId.equals(other.cardId))
			return false;
		if (saldo == null) {
			if (other.saldo != null)
				return false;
		} else if (!saldo.equals(other.saldo))
			return false;
		return true;
	}

}