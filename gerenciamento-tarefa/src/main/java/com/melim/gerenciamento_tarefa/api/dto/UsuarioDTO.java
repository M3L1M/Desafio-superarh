package com.melim.gerenciamento_tarefa.api.dto;

import com.melim.gerenciamento_tarefa.model.entity.Usuario;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuarioDTO {
	private String nome;
}
