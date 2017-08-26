package br.com.allowpay.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.allowpay.entities.Child;

public interface ChildRepository extends JpaRepository<Child, Long> {

}
