package com.melim.gerenciamento_tarefa.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name="lista")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Lista {
	  @Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  private Integer id;

	  @Column(nullable = false)
	  private String titulo;

	  @ManyToOne//(fetch = FetchType.EAGER)
	  @JoinColumn(name = "usuario")
	  private Usuario usuario;
}
