package br.com.allowpay.canonical;

import java.util.Collection;

public class ClientChild extends Client {

	private final Collection<CardRegister> registers;

	public ClientChild(final String name, final Collection<CardRegister> registers) {
		super(name);
		this.registers = registers;
	}

	public Collection<CardRegister> getRegisters() {
		return registers;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((registers == null) ? 0 : registers.hashCode());
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
		ClientChild other = (ClientChild) obj;
		if (registers == null) {
			if (other.registers != null)
				return false;
		} else if (!registers.equals(other.registers))
			return false;
		return true;
	}

}
