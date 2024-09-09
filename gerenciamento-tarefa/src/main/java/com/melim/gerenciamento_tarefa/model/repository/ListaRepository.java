package com.melim.gerenciamento_tarefa.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.melim.gerenciamento_tarefa.model.entity.Lista;

public interface ListaRepository extends JpaRepository<Lista, Integer> {

}
