package br.com.allowpay.controllers;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.allowpay.dtos.ClientDto;

@RestController
@RequestMapping("${api.version}/clients")
public class ClientsController {

	@PostMapping
	ResponseEntity<Void> setClient(@RequestBody final ClientDto client) {
		// TODO: Registra o cliente
		
		//FIXME: Ativação não é realizada na agilitas neste momento
		return ResponseEntity.created(URI.create("http://allowpay.me")).build();
	}

}
