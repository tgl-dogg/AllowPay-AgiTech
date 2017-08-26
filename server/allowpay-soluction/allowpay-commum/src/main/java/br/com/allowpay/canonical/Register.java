package br.com.allowpay.canonical;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class Register implements Serializable {

	private static final long serialVersionUID = 194818387973751364L;

	private final String id;
	private final BigDecimal balanceValue;
	private final String deviceNotificationDadId;
	private final String deviceNotificationChildId;
	private final ClientChild clientChild;
	private final List<ClientChild> othersChilds;

	public Register(final String id, final BigDecimal balanceValue, final String deviceNotificationDadId,
			final String deviceNotificationChildId, final ClientChild clientChild,
			final List<ClientChild> othersChilds) {
		super();
		this.id = id;
		this.balanceValue = balanceValue;
		this.deviceNotificationDadId = deviceNotificationDadId;
		this.deviceNotificationChildId = deviceNotificationChildId;
		this.clientChild = clientChild;
		this.othersChilds = othersChilds;
	}

	public String getId() {
		return id;
	}

	public BigDecimal getBalanceValue() {
		return balanceValue;
	}

	public String getDeviceNotificationChildId() {
		return deviceNotificationDadId;
	}

	public String getDeviceNotificationDadId() {
		return deviceNotificationChildId;
	}

	public ClientChild getClientChild() {
		return clientChild;
	}

	public List<ClientChild> getOthersChilds() {
		return othersChilds;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((balanceValue == null) ? 0 : balanceValue.hashCode());
		result = prime * result + ((clientChild == null) ? 0 : clientChild.hashCode());
		result = prime * result + ((deviceNotificationChildId == null) ? 0 : deviceNotificationChildId.hashCode());
		result = prime * result + ((deviceNotificationDadId == null) ? 0 : deviceNotificationDadId.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((othersChilds == null) ? 0 : othersChilds.hashCode());
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
		Register other = (Register) obj;
		if (balanceValue == null) {
			if (other.balanceValue != null)
				return false;
		} else if (!balanceValue.equals(other.balanceValue))
			return false;
		if (clientChild == null) {
			if (other.clientChild != null)
				return false;
		} else if (!clientChild.equals(other.clientChild))
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
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (othersChilds == null) {
			if (other.othersChilds != null)
				return false;
		} else if (!othersChilds.equals(other.othersChilds))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Register [id=" + id + ", balanceValue=" + balanceValue + ", deviceNotificationDadId="
				+ deviceNotificationDadId + ", deviceNotificationChildId=" + deviceNotificationChildId
				+ ", clientChild=" + clientChild + ", othersChilds=" + othersChilds + "]";
	}
}