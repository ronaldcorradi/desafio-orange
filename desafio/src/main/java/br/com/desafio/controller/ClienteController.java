package br.com.desafio.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.desafio.controller.dto.ClientDTO;
import br.com.desafio.controller.form.ClientForm;
import br.com.desafio.model.Client;
import br.com.desafio.repository.ClientRepository;

@RestController
@RequestMapping("/clients")
public class ClienteController {
	
	@Autowired
	ClientRepository clientRepository;
	
	@GetMapping
	public List<ClientDTO> getClients(String nameClient){
		if(nameClient == null) {
			return ClientDTO.convert(clientRepository.findAll());			
		}else {
			return ClientDTO.convert(clientRepository.findByName(nameClient));
		}
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<ClientDTO> save(@RequestBody @Valid ClientForm clientForm,
										  UriComponentsBuilder uriComponentsBuilder) {
		Client client = clientForm.convert();
		clientRepository.save(client);
		URI uri = uriComponentsBuilder.path("/client/{id}").buildAndExpand(client.getId()).toUri();
		return ResponseEntity.created(uri).body(new ClientDTO(client));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ClientDTO> getClientById(@PathVariable Long id) {
		Optional<Client> client = clientRepository.findById(id);
		if(client.isPresent()) {
			return ResponseEntity.ok(new ClientDTO(client.get()));			
		}
		return ResponseEntity.notFound().build();
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<ClientDTO> update(@PathVariable Long id,
			                                @RequestBody @Valid ClientForm clientForm
			                               ){
		Optional<Client> client = clientRepository.findById(id);
		if(client.isPresent()) {			
			client.get().setBirth(clientForm.getBirth());
			client.get().setCpf(clientForm.getCpf());
			client.get().setName(clientForm.getName());
			client.get().setEmail(clientForm.getEmail());
			clientRepository.save(client.get());		
			return ResponseEntity.ok(new ClientDTO(client.get()));			
		}
		return ResponseEntity.notFound().build();	
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> delete(@PathVariable Long id){		
		Optional<Client> client = clientRepository.findById(id);
		if(client.isPresent()) {
			clientRepository.deleteById(id);
			return ResponseEntity.ok().build();						
		}
		return ResponseEntity.notFound().build();		
	}

}
