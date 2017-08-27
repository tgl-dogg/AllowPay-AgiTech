package br.com.allowpay.integrator.dtos;

import java.io.Serializable;

public class BalanceValueDto implements Serializable {

	private static final long serialVersionUID = -4607441772675019090L;

	private Double valor;

	public BalanceValueDto() {
	}

	public BalanceValueDto(Double valor) {
		super();
		this.valor = valor;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((valor == null) ? 0 : valor.hashCode());
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
		BalanceValueDto other = (BalanceValueDto) obj;
		if (valor == null) {
			if (other.valor != null)
				return false;
		} else if (!valor.equals(other.valor))
			return false;
		return true;
	}
}