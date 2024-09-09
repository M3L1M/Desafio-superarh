package com.melim.gerenciamento_tarefa.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.melim.gerenciamento_tarefa.model.entity.Lista;
import com.melim.gerenciamento_tarefa.model.repository.ListaRepository;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ActiveProfiles("test")
public class ListaRepositoryTest {
	@Autowired
	ListaRepository repository;
	@Autowired
	TestEntityManager entityManager;
	
	@Test
	public void deveSalvarUmaLista() {
		Lista lista = criarUmaLista();
		
		lista = repository.save(lista);
		
		assertThat(lista.getId()).isNotNull();
	}
	
	@Test
	public void deveDeletarUmaLista() {
		Lista lista = criarEPersistirUmaLista();
		lista = entityManager.find(Lista.class, lista.getId());
		
		repository.delete(lista);
		
		Lista listaInexistente = entityManager.find(Lista.class, lista.getId());
		assertThat(listaInexistente).isNull();
	}
	@Test
	public void deveBuscarUmaListaPeloID() {
		Lista lista = criarEPersistirUmaLista();
		
		Optional<Lista> listaEncontrada = repository.findById(lista.getId());
		assertThat(listaEncontrada.isPresent()).isTrue();
	}
	
	public static Lista criarUmaLista() {
		return Lista.builder().titulo("Teste").build();
	}
	
	private Lista criarEPersistirUmaLista() {
		Lista lista = criarUmaLista();
		entityManager.persist(lista);
		return lista;
	}
	
}
