package br.com.allowpay.integrator.dtos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ExtractDto implements Serializable {

	private static final long serialVersionUID = -3631697842455852779L;

	private List<ExtractValuesDto> extrato = new ArrayList<>();

	public ExtractDto() {
	}

	public ExtractDto(List<ExtractValuesDto> extrato) {
		super();
		this.extrato = extrato;
	}

	public List<ExtractValuesDto> getExtrato() {
		return extrato;
	}

	public void setExtrato(List<ExtractValuesDto> extrato) {
		this.extrato = extrato;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((extrato == null) ? 0 : extrato.hashCode());
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
		ExtractDto other = (ExtractDto) obj;
		if (extrato == null) {
			if (other.extrato != null)
				return false;
		} else if (!extrato.equals(other.extrato))
			return false;
		return true;
	}
}
