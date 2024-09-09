package com.melim.gerenciamento_tarefa.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.melim.gerenciamento_tarefa.api.dto.UsuarioDTO;
import com.melim.gerenciamento_tarefa.exception.BusinessException;
import com.melim.gerenciamento_tarefa.model.entity.Usuario;
import com.melim.gerenciamento_tarefa.service.UsuarioService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {
	private final UsuarioService service;
	
	@PostMapping("create")
	public ResponseEntity<?> create(@RequestBody UsuarioDTO dto){
		try {
			Usuario usuario=convert(dto);
			usuario=service.create(usuario);
			return new ResponseEntity<Usuario>(usuario,HttpStatus.CREATED);
		}catch(BusinessException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	private Usuario convert(UsuarioDTO dto) {
		
		return Usuario.builder().nome(dto.getNome()).build();
	}
}
