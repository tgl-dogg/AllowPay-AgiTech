package br.com.allowpay.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.allowpay.entities.CardRegister;

public interface CardRegisterRepository extends JpaRepository<CardRegister, String> {

}
