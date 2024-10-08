package com.melim.gerenciamento_tarefa.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.melim.gerenciamento_tarefa.exception.BusinessException;
import com.melim.gerenciamento_tarefa.exception.ErroValidacao;
import com.melim.gerenciamento_tarefa.model.entity.Usuario;
import com.melim.gerenciamento_tarefa.model.repository.UsuarioRepository;
import com.melim.gerenciamento_tarefa.service.impl.UsuarioServiceImpl;

@ExtendWith(SpringExtension.class)
public class UsuarioServiceTest {
	
	@SpyBean
	UsuarioServiceImpl service;

	@MockBean
	UsuarioRepository repository;
	
	@Test
	public void deveCriarUmUsuario() {
		Usuario usuario = createUsuario();
		Mockito.when(repository.save(Mockito.any(Usuario.class))).thenReturn(usuario);
		
		Usuario usuarioSave = service.create(usuario);
		
		Assertions.assertThat(usuarioSave).isNotNull();
		Assertions.assertThat(usuarioSave.getId()).isEqualTo(1);
		Assertions.assertThat(usuarioSave.getNome()).isEqualTo("Gabriel");
	}
	
	@Test
    public void naoDeveCriarUmUsuarioComNomeVazio() {
        Usuario usuario = Usuario.builder().nome("").build(); 
        Exception exception = assertThrows(ErroValidacao.class,
                () -> service.create(usuario));
        assertEquals("O nome esta vazio", exception.getMessage());
        Mockito.verify(repository, Mockito.never()).save(usuario);
    }

	private Usuario createUsuario() {
		
		return Usuario.builder().nome("Gabriel").id(1).build();
	}
}
