package br.com.primeiroprojeto.ws.controller;
/*
 * consumes -> consome informações do Json;
 * produces -> produz Json com as informações passadas;
 * ResponseEntity -> leva uma informação para o Browser, neste caso os objetos transformados em Json;
 * @PathVariable -> seta o que estiver no caminho "clientes/{id}" por exemplo e inserir em uma variavel informada.
 * 
 */

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.primeiroprojeto.ws.modulo.Cliente;
import br.com.primeiroprojeto.ws.service.ClienteService;

@RestController
public class ClienteController {
	
	@Autowired
	ClienteService clienteService;

		
	//End points
	@RequestMapping(method=RequestMethod.POST, value = "/clientes", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Cliente> cadastrarCliente(@RequestBody Cliente cliente) {
		
		Cliente clienteCadastrado = this.clienteService.cadastrar(cliente);
		return new ResponseEntity<>(clienteCadastrado, HttpStatus.CREATED); 
	}
	
	@RequestMapping(method=RequestMethod.GET, value = "/clientes", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Cliente>> buscarTodosClientes() {
		
		Collection<Cliente> clientesBuscados = this.clienteService.buscarTodos();
		return new ResponseEntity<>(clientesBuscados, HttpStatus.OK); 
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value = "/clientes/{id}")
	public ResponseEntity<Cliente> excluirCliente(@PathVariable Integer id) {
		
		Cliente clienteDeletado = this.clienteService.buscaPorId(id);
		
		if(clienteDeletado == null) 
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
		this.clienteService.excluirID(id);
		return new ResponseEntity<>(clienteDeletado, HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.PUT, value = "/clientes", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Cliente> alterarCliente(@RequestBody Cliente cliente) {
		this.clienteService.alterar(cliente);
		Cliente clienteAlterado = this.clienteService.buscaPorId(cliente.getId());
		return new ResponseEntity<>(clienteAlterado, HttpStatus.OK); 
	}
	
} 
