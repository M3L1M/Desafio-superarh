package com.melim.gerenciamento_tarefa.api.dto;


import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ListaDTO {
	
	 private String titulo;
	 private Integer usuario;
	 private List<ItemDTO> items;
}
