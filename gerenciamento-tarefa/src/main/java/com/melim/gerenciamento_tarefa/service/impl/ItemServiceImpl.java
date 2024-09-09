package com.melim.gerenciamento_tarefa.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import com.melim.gerenciamento_tarefa.exception.ErroValidacao;
import com.melim.gerenciamento_tarefa.model.entity.Item;
import com.melim.gerenciamento_tarefa.model.repository.ItemRepository;
import com.melim.gerenciamento_tarefa.model.repository.ListaRepository;
import com.melim.gerenciamento_tarefa.model.repository.UsuarioRepository;
import com.melim.gerenciamento_tarefa.service.ItemService;

import lombok.AllArgsConstructor;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@AllArgsConstructor
@Service
public class ItemServiceImpl implements ItemService{
	
	private final ItemRepository repository;
	
	@Override
	@Transactional
	public Item create(Item item) {
		validate(item);
		item=repository.save(item);
		return item;
	}

	@Override
	@Transactional
	public void update(Item item) {
		validate(item);
		repository.save(item);
		
	}

	@Override
	@Transactional
	public void delete(Item item) {
		Objects.requireNonNull(item.getId());
		repository.delete(item);
		
		
	}

	@Override
	@Transactional(readOnly = true)
	public List<Item> findAll(Item item) {
		Example example=Example.of(item,
				ExampleMatcher.matching()
				.withIgnoreCase()
				.withStringMatcher(StringMatcher.CONTAINING));
		
		return repository.findAll(example);
	}

	@Override
	@Transactional
	public List<Item> saveAll(List<Item> items) {
		List<Item> itemsAdd=new ArrayList<Item>();
		for(int i=0;i<items.size();i++) {
			
			Optional<Item> item=validate(items.get(i));
			if(item.isPresent()) {
				itemsAdd.add(item.get());
			}
		}
		
		return repository.saveAll(itemsAdd);
		
	}

	private Optional<Item> validate(Item item) {
		
		if(item.getNome().isEmpty()) {
			return Optional.empty();
		}
		return Optional.ofNullable(item);
	}

	@Override
	public Optional<Item> getById(Integer id) {
		Optional<Item> item=repository.findById(id);
		if(!item.isPresent()) {
			throw new ErroValidacao("Não existe nenhum item com este ID");
		}
		return item;
	}

}
