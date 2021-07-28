package br.com.desafio.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.desafio.models.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {
  public List<Client> findByCpf(String cpf);
}
