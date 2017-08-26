package br.com.allowpay.entities.builder;

import java.util.Date;
import java.util.List;

import br.com.allowpay.entities.Child;
import br.com.allowpay.entities.Dad;

public class DadBuilder {

	public static DadBuilder create() {
		return new DadBuilder();
	}

	private String cpf;
	private String fullName;
	private Date createDate;
	private List<Child> childs;
	
	private DadBuilder(){
		
	}

	public DadBuilder withCpf(final String cpf) {
		this.cpf = cpf;
		return this;
	}

	public DadBuilder withFullName(final String fullName) {
		this.fullName = fullName;
		return this;
	}

	public DadBuilder withCreateDate(final Date createDate) {
		this.createDate = createDate;
		return this;
	}

	public DadBuilder withChilds(final List<Child> childs) {
		this.childs = childs;
		return this;
	}

	public Dad build() {
		return new Dad(cpf, fullName, createDate, childs);
	}
}
