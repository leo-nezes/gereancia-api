package br.com.desafio.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.desafio.models.Client;
import br.com.desafio.repositories.ClientRepository;

@RestController
@RequestMapping("/clients")
public class ClientController {
  @Autowired
  private ClientRepository clientRepository;

  @GetMapping("/{id}")
  public Client client(@PathVariable("id") Long id) {
    Optional<Client> clientFound = this.clientRepository.findById(id);

    if(clientFound.isPresent())
      return clientFound.get();

    return null;
  }

  @GetMapping("/list")
  public List<Client> list() {
    return this.clientRepository.findAll();
  }

  @PostMapping("/")
  public Client create(@RequestBody Client client) {
    List<Client> returnedClient = this.clientRepository.findByCpf(client.getCpf());
    
    if(!returnedClient.isEmpty())
      throw new Error("Client with this CPF already exists");

    this.clientRepository.save(client);

    return client;
  }

  @PatchMapping("/update/{id}")
  public void update(@PathVariable Long id, @RequestBody Client client) {
    client.setId(id);

    this.clientRepository.save(client);
  }

  @DeleteMapping("/delete/{id}")
  public void delete(@PathVariable Long id) {
    this.clientRepository.deleteById(id);
  }
  
}