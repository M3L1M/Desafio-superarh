package com.melim.gerenciamento_tarefa.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.melim.gerenciamento_tarefa.model.entity.Item;

public interface ItemRepository extends JpaRepository<Item, Integer>{

}
