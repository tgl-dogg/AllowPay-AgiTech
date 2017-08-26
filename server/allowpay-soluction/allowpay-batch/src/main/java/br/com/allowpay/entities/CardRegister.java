package br.com.allowpay.entities;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class CardRegister implements Serializable{
	
	private static final long serialVersionUID = 3850564571790759495L;

	@Id
	private String cardId;
	
	@Column
	private BigDecimal balance;
	
	@Column
	private String deviceNotificationDadId;
	
	@Column
	private String deviceNotificationChildId;
	
	@JoinColumn
	@ManyToOne
	private Child child;
	
	public CardRegister(){
		
	}

	public CardRegister(final String cardId, final BigDecimal balance, final String deviceNotificationDadId,
			final String deviceNotificationChildId, final Child child) {
		super();
		this.cardId = cardId;
		this.balance = balance;
		this.deviceNotificationDadId = deviceNotificationDadId;
		this.deviceNotificationChildId = deviceNotificationChildId;
		this.child = child;
	}

	public String getCardId() {
		return cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public String getDeviceNotificationDadId() {
		return deviceNotificationDadId;
	}

	public void setDeviceNotificationDadId(String deviceNotificationDadId) {
		this.deviceNotificationDadId = deviceNotificationDadId;
	}

	public String getDeviceNotificationChildId() {
		return deviceNotificationChildId;
	}

	public void setDeviceNotificationChildId(String deviceNotificationChildId) {
		this.deviceNotificationChildId = deviceNotificationChildId;
	}

	public Child getChild() {
		return child;
	}

	public void setChild(Child child) {
		this.child = child;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
		CardRegister other = (CardRegister) obj;
		if (cardId == null) {
			if (other.cardId != null)
				return false;
		} else if (!cardId.equals(other.cardId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CardRegister [cardId=" + cardId + ", balance=" + balance + ", deviceNotificationDadId="
				+ deviceNotificationDadId + ", deviceNotificationChildId=" + deviceNotificationChildId + ", child="
				+ child + "]";
	}
}