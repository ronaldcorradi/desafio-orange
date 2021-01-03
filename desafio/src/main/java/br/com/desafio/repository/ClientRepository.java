package br.com.desafio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.desafio.model.Client;

public interface ClientRepository extends JpaRepository<Client, Long>{

	List<Client> findByName(String nameClient);

}
