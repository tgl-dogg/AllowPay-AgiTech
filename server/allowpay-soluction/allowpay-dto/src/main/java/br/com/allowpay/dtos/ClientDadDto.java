package br.com.allowpay.dtos;

import java.util.Collection;

public class ClientDadDto extends ClientDto {

	private static final long serialVersionUID = 3262750019261577576L;

	private String cpf;

	public ClientDadDto() {
		super();
	}

	public ClientDadDto(final String cpf, final String name, final String deviceId, final Collection<CardDto> cards) {
		super(name, cards, deviceId);
		this.cpf = cpf;
	}

	public String getCpf() {
		return cpf;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
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
		ClientDadDto other = (ClientDadDto) obj;
		if (cpf == null) {
			if (other.cpf != null)
				return false;
		} else if (!cpf.equals(other.cpf))
			return false;
		return true;
	}
}
