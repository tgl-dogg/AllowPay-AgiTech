package br.com.allowpay.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${api.version}/clients")
public class ClientsController {

	//TODO: Definir o request
	@PostMapping
	ResponseEntity<?> setClient(){
		//TODO:
		return ResponseEntity.created(null).build();
	}
	
}
