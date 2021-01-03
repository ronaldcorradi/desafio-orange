package br.com.desafio.controller.form;

import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.br.CPF;
import org.springframework.format.annotation.DateTimeFormat;

import br.com.desafio.model.Client;


public class ClientForm {
	
	@NotBlank(message = "Campo obrigatório")
	private String name;

	@NotBlank(message = "Campo obrigatório")
	@CPF(message = "CPF inválido")
	private String cpf;
	
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private LocalDate birth;
	
	@NotBlank(message = "Campo obrigatório")
	@Email(message = "E-mail inválido")
	private String email;
		
	public String getName() {
		return name;
	}
	public String getCpf() {
		return cpf;
	}
	public LocalDate getBirth() {
		return birth;
	}
	public String getEmail() {
		return email;
	}
	
	public Client convert() {
		return new Client(name, cpf, email, birth);
	}
	
}
