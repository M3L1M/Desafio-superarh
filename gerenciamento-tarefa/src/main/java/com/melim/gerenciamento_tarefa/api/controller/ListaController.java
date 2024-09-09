package com.melim.gerenciamento_tarefa.api.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.melim.gerenciamento_tarefa.api.dto.ItemDTO;
import com.melim.gerenciamento_tarefa.api.dto.ListaDTO;
import com.melim.gerenciamento_tarefa.exception.BusinessException;
import com.melim.gerenciamento_tarefa.model.entity.Item;
import com.melim.gerenciamento_tarefa.model.entity.Lista;
import com.melim.gerenciamento_tarefa.model.entity.Usuario;
import com.melim.gerenciamento_tarefa.model.enums.Estado;
import com.melim.gerenciamento_tarefa.service.ItemService;
import com.melim.gerenciamento_tarefa.service.ListaService;
import com.melim.gerenciamento_tarefa.service.UsuarioService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/lista")
public class ListaController {
	private final ListaService service;
	private final UsuarioService usuarioService;
	private final ItemService itemService;
	
	@PostMapping("create")
	public ResponseEntity<?> create(@RequestBody ListaDTO dto){
		try {
			Lista lista = convert(dto);
			
			Optional<Usuario> usuario=usuarioService.getByUsuario(dto.getUsuario());
			lista.setUsuario(usuario.get());
			lista = service.create(lista);
			
			List<Item> items=convert(dto.getItems(),lista);
			items = itemService.saveAll(items);
			
			return new ResponseEntity<Lista>(lista,HttpStatus.CREATED);
		}catch(BusinessException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@PutMapping("update/{id}")
	public ResponseEntity<?> update(@PathVariable Integer id,@RequestBody ListaDTO dto){
		
		return service.getById(id).map(entity -> {
			try {
				entity.setTitulo(dto.getTitulo());
				service.update(entity);
				return new ResponseEntity<Lista>(HttpStatus.NO_CONTENT);
			}catch (BusinessException e) {
				return ResponseEntity.badRequest().body(e.getMessage());
			}
		}).orElseGet( () ->new ResponseEntity<String>("Lista não encontrada na base de Dados.", HttpStatus.BAD_REQUEST) );
	}
	
	@GetMapping
	public ResponseEntity<?> findAll(
			@RequestParam(value = "idUsuario") Integer idUsuario,
			@RequestParam(value = "idLista",required=false) String titulo){
		
		Lista lista = new Lista();
		lista.setTitulo(titulo);
		
		Optional<Usuario> usuario = usuarioService.getByUsuario(idUsuario);
		if(!usuario.isPresent()) {
			return ResponseEntity.badRequest().body("Não foi possivel realizar a consulta");
		}else {
			lista.setUsuario(usuario.get());
		}
		
		List<Lista> listas = service.findAll(lista);
		return ResponseEntity.ok(listas);
	}
	

	private Lista convert(ListaDTO dto) {
		return Lista.builder()
				.titulo(dto.getTitulo()).build();
	}
	
	private List<Item> convert(List<ItemDTO> itemsDTO,Lista lista) {
		List<Item> items=new ArrayList<Item>();
		for(int i=0;i<itemsDTO.size();i++) {
			Item item=new Item();
			item.setNome(itemsDTO.get(i).getNome());
			item.setPrioridade(itemsDTO.get(i).getPrioridade());
			item.setLista(lista);
			item.setSituacao(Estado.valueOf(itemsDTO.get(i).getSituacao()));
			
			items.add(item);
		}
		return items;
	}
}
