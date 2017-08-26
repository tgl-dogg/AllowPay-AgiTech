package br.com.allowpay.dtos;

import java.util.Date;

public class FullExtract extends Extract {

	private static final long serialVersionUID = -2351200876485273954L;

	private Date date;

	private String merchant;

	private String type;

	public FullExtract() {
		super();
	}

	public FullExtract(final Date date, final String merchant, final String type) {
		super();
		this.date = date;
		this.merchant = merchant;
		this.type = type;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((merchant == null) ? 0 : merchant.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		FullExtract other = (FullExtract) obj;
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