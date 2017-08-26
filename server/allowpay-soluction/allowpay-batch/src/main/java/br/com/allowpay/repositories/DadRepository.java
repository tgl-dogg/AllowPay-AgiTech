package br.com.allowpay.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.allowpay.entities.Dad;

@Repository
public interface DadRepository extends JpaRepository<Dad, String> {

}
