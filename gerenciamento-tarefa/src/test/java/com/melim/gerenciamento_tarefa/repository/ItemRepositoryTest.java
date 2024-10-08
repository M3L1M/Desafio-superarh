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

import com.melim.gerenciamento_tarefa.model.entity.Item;
import com.melim.gerenciamento_tarefa.model.enums.Estado;
import com.melim.gerenciamento_tarefa.model.repository.ItemRepository;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ActiveProfiles("test")
public class ItemRepositoryTest {


	@Autowired
	ItemRepository repository;
	@Autowired
	TestEntityManager entityManager;
	
	@Test
	public void deveSalvarUmItem() {
		Item item = criarItem();
		
		item = repository.save(item);
		
		assertThat(item.getId()).isNotNull();
	}

	@Test
	public void deveDeletarUmLancamento() {
		Item item= criarEPersistirUmItem();
		
		item = entityManager.find(Item.class, item.getId());
		
		repository.delete(item);
		
		Item itemInexistente = entityManager.find(Item.class, item.getId());
		assertThat(itemInexistente).isNull();
	}

	
	@Test
	public void deveAtualizarUmItem() {
		Item item = criarEPersistirUmItem();
		
		item.setNome("Teste");
		item.setPrioridade(1);
		item.setSituacao(Estado.ATIVO);
		
		repository.save(item);
		
		Item itemAtualizado = entityManager.find(Item.class, item.getId());
		
		assertThat(itemAtualizado.getNome()).isEqualTo("Teste");
		assertThat(itemAtualizado.getPrioridade()).isEqualTo(1);
		assertThat(itemAtualizado.getSituacao()).isEqualTo(Estado.ATIVO);
		
	}
	
	@Test
	public void deveBuscarUmLancamentoPorId() {
		Item item = criarEPersistirUmItem();
		
		Optional<Item> itemEncontrado = repository.findById(item.getId());
		
		assertThat(itemEncontrado.isPresent()).isTrue();
	}
	
	
	private Item criarEPersistirUmItem() {
		Item item = criarItem();
		entityManager.persist(item);
		return item;
	}
	
	public static Item criarItem() {
		return Item.builder()
				.nome("Teste")
				.prioridade(1)
				.situacao(Estado.ATIVO).build();
	}
}
