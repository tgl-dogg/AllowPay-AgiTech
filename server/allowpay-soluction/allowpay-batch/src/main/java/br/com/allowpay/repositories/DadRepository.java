package br.com.allowpay.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.allowpay.entities.Dad;

public interface DadRepository extends JpaRepository<Dad, String> {

}
