package com.melim.gerenciamento_tarefa.service;

import java.util.List;
import java.util.Optional;

import com.melim.gerenciamento_tarefa.model.entity.Lista;

public interface ListaService {
	Lista create(Lista lista);
	void update(Lista lista);
	Optional<Lista> getById(Integer id);
	List<Lista> findAll( Lista lista);
}
