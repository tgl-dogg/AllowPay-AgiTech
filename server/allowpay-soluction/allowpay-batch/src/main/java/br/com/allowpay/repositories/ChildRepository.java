package br.com.allowpay.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.allowpay.entities.Child;

@Repository
public interface ChildRepository extends JpaRepository<Child, Long> {

}
