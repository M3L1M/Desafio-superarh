package com.melim.gerenciamento_tarefa.service;

import java.util.List;
import java.util.Optional;

import com.melim.gerenciamento_tarefa.model.entity.Item;

public interface ItemService {
	List<Item> saveAll(List<Item> items);
	Item create(Item item);
	void update(Item item);
	void delete(Item item);
	List<Item> findAll( Item item);
	Optional<Item> getById(Integer id);
}
