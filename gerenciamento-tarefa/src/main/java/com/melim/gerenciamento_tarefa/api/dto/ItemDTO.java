package com.melim.gerenciamento_tarefa.api.dto;

import com.melim.gerenciamento_tarefa.model.entity.Item;
import com.melim.gerenciamento_tarefa.model.entity.Lista;
import com.melim.gerenciamento_tarefa.model.enums.Estado;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemDTO {
	private String nome;
	private String situacao;
	private Integer prioridade;
	private Integer lista;
}
