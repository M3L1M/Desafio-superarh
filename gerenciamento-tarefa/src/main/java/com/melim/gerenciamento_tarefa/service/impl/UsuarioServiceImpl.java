package com.melim.gerenciamento_tarefa.service.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.melim.gerenciamento_tarefa.exception.ErroValidacao;
import com.melim.gerenciamento_tarefa.model.entity.Usuario;
import com.melim.gerenciamento_tarefa.model.repository.UsuarioRepository;
import com.melim.gerenciamento_tarefa.service.UsuarioService;

import org.springframework.transaction.annotation.Transactional;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class UsuarioServiceImpl implements UsuarioService {
	
	private final UsuarioRepository repository;
	
	@Override
	@Transactional
	public Usuario create(Usuario usuario) {
		if(usuario.getNome().isEmpty()) {
			throw new ErroValidacao("O nome esta vazio");
		}
		return repository.save(usuario);
	}

	@Override
	public Optional<Usuario> getByUsuario(Integer id) {
		Optional<Usuario> usuario=repository.findById(id);
		if(!usuario.isPresent()) {
			throw new ErroValidacao("Não existe nenhum usuario com este ID");
		}
		
		return usuario;
	}

}
