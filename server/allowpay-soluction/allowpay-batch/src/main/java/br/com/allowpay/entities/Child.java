package br.com.allowpay.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Child implements Serializable {

	private static final long serialVersionUID = -6325386154682837061L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private String fullName;

	@ManyToOne(optional = true)
	@JoinColumn
	private Dad dad;

	@OneToMany
	@JoinColumn
	private List<CardRegister> cardRegisters;
	
	public Child(){
		
	}

	public Child(final String fullName, final Dad dad, final List<CardRegister> cardRegisters) {
		super();
		this.fullName = fullName;
		this.dad = dad;
		this.cardRegisters = cardRegisters;
	}
	
	public Child(final Long id, final String fullName, final Dad dad, final List<CardRegister> cardRegisters) {
		super();
		this.id = id;
		this.fullName = fullName;
		this.dad = dad;
		this.cardRegisters = cardRegisters;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public Dad getDad() {
		return dad;
	}

	public void setDad(Dad dad) {
		this.dad = dad;
	}

	public List<CardRegister> getCardRegisters() {
		return cardRegisters;
	}

	public void setCardRegisters(List<CardRegister> cardRegisters) {
		this.cardRegisters = cardRegisters;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Child other = (Child) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Child [id=" + id + ", fullName=" + fullName + ", dad=" + dad + ", cardRegisters=" + cardRegisters + "]";
	}
}
