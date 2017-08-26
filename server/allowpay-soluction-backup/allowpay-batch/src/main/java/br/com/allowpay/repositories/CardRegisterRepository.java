package br.com.allowpay.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.allowpay.entities.CardRegister;

@Repository
public interface CardRegisterRepository extends JpaRepository<CardRegister, String> {

}
