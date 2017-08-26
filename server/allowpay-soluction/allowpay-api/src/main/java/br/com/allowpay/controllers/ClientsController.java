package br.com.allowpay.controllers;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.allowpay.dtos.Client;

@RestController
@RequestMapping("${api.version}/clients")
public class ClientsController {

	// TODO: Definir o request
	@PostMapping
	ResponseEntity<Void> setClient(@RequestBody final Client client) {
		// TODO: Registra o cliente
		return ResponseEntity.created(URI.create("http://allowpay.me")).build();
	}

}
