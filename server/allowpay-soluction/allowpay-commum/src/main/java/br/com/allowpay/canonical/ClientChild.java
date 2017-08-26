package br.com.allowpay.canonical;

import java.io.Serializable;
import java.util.List;

public class ClientChild extends Client implements Serializable{

	private static final long serialVersionUID = 6360712728705071699L;
	
	private final Long id;
	private final List<Register> registers;
	private final ClientDad clientDad;
	
	public ClientChild(final String name, final Long id, final List<Register> registers, final ClientDad clientDad) {
		super(name);
		this.id = id;
		this.registers = registers;
		this.clientDad = clientDad;
	}

	public Long getId() {
		return id;
	}
	
	public List<Register> getRegisters() {
		return registers;
	}

	public ClientDad getClientDad() {
		return clientDad;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((clientDad == null) ? 0 : clientDad.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		if (clientDad == null) {
			if (other.clientDad != null)
				return false;
		} else if (!clientDad.equals(other.clientDad))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (registers == null) {
			if (other.registers != null)
				return false;
		} else if (!registers.equals(other.registers))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ClientChild [id=" + id + ", registers=" + registers + ", clientDad=" + clientDad + "]";
	}
}
