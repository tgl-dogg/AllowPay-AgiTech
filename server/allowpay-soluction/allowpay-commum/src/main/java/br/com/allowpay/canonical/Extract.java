package br.com.allowpay.canonical;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Extract implements Serializable{

	private static final long serialVersionUID = 8976751451563561280L;

	private final Date date;

	private final String merchant;

	private final String type;

	private final BigDecimal amount;

	public Extract(final Date date, final String merchant, final String type, final BigDecimal amount) {
		super();
		this.date = date;
		this.merchant = merchant;
		this.type = type;
		this.amount = amount;
	}

	public Date getDate() {
		return date;
	}

	public String getMerchant() {
		return merchant;
	}

	public String getType() {
		return type;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((amount == null) ? 0 : amount.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((merchant == null) ? 0 : merchant.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		Extract other = (Extract) obj;
		if (amount == null) {
			if (other.amount != null)
				return false;
		} else if (!amount.equals(other.amount))
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (merchant == null) {
			if (other.merchant != null)
				return false;
		} else if (!merchant.equals(other.merchant))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}

}
