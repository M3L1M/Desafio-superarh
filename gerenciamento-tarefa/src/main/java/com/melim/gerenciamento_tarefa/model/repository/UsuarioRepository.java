package com.melim.gerenciamento_tarefa.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.melim.gerenciamento_tarefa.model.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

}
