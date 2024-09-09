package com.melim.gerenciamento_tarefa.service;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.melim.gerenciamento_tarefa.model.entity.Lista;
import com.melim.gerenciamento_tarefa.model.repository.ListaRepository;
import com.melim.gerenciamento_tarefa.service.impl.ListaServiceImpl;

@ExtendWith(SpringExtension.class)
public class ListaServiceTest {
	ListaServiceImpl service;
	
	ListaRepository repository;
	
	public void deveSalvarUmaLista() {
		
	}
}
