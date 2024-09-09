package com.melim.gerenciamento_tarefa.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.melim.gerenciamento_tarefa.model.entity.Usuario;
import com.melim.gerenciamento_tarefa.model.repository.UsuarioRepository;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ActiveProfiles("test")
public class UsuarioRepositoryTest {
	@Autowired
	UsuarioRepository repository;
	@Autowired
	TestEntityManager entityManager;
	
	@Test
	public void deveSalvarUmUsuario() {
		Usuario usuario = criarUsuario();
		usuario = repository.save(usuario);
		
		assertThat(usuario.getId()).isNotNull();
	}
	

	private Usuario criarUsuario() {
		return Usuario.builder().id(1).nome("Gabriel").build();
	}
}
