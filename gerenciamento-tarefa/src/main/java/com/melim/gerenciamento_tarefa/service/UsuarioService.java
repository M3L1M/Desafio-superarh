package com.melim.gerenciamento_tarefa.service;

import java.util.Optional;

import com.melim.gerenciamento_tarefa.model.entity.Usuario;

public interface UsuarioService {
	Usuario create(Usuario usuario);
	Optional<Usuario> getByUsuario(Integer id);
}
