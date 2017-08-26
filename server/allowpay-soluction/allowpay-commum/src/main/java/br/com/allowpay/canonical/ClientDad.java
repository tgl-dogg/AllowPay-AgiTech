package br.com.allowpay.canonical;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class ClientDad extends Client implements Serializable {

	private static final long serialVersionUID = -4512694729143738647L;

	private final String cpf;
	private final Date createDate;
	private final List<ClientChild> clientChilds;

	public ClientDad(final String cpf, final String name, final Date createDate, final List<ClientChild> clientChilds) {
		super(name);
		this.cpf = cpf;
		this.createDate = createDate;
		this.clientChilds = clientChilds;
	}

	public String getCpf() {
		return cpf;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public List<ClientChild> getClientChilds() {
		return clientChilds;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((clientChilds == null) ? 0 : clientChilds.hashCode());
		result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
		result = prime * result + ((createDate == null) ? 0 : createDate.hashCode());
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
		ClientDad other = (ClientDad) obj;
		if (clientChilds == null) {
			if (other.clientChilds != null)
				return false;
		} else if (!clientChilds.equals(other.clientChilds))
			return false;
		if (cpf == null) {
			if (other.cpf != null)
				return false;
		} else if (!cpf.equals(other.cpf))
			return false;
		if (createDate == null) {
			if (other.createDate != null)
				return false;
		} else if (!createDate.equals(other.createDate))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ClientDad [cpf=" + cpf + ", createDate=" + createDate + ", clientChilds=" + clientChilds + "]";
	}
}