package br.com.allowpay.builder;

import br.com.allowpay.canonical.Client;

public class ClientBuild {

	public static ClientBuild create() {
		return new ClientBuild();
	}
	
	private ClientBuild(){
		
	}
	
	private String identify;

	private String name;

	
	public ClientBuild withIdentify(final String identify) {
		this.identify = identify;
		return this;
	}
	
	public ClientBuild withName(final String name) {
		this.name = name;
		return this;
	}
	
	public Client build(){
		return new Client(identify, name);
	}
}
