package br.com.desafio.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.com.desafio.model.Client;

public class ClientDTO {

	private Long id;
	private String name;
	private String cpf;
	
	
	public ClientDTO(Client client) {
		this.id = client.getId();
		this.name = client.getName();
		this.cpf = client.getCpf();
	}
	
	public Long getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getCpf() {
		return cpf;
	}
	
	public static List<ClientDTO> convert(List<Client> clients) {		
		return clients.stream().map(ClientDTO::new).collect(Collectors.toList());
	}	
	
	
}
