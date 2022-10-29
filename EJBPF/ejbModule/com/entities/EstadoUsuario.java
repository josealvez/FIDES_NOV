package com.entities;

import java.io.Serializable;
import javax.persistence.*;

import com.enumerados.EnumEstadoUsuario;

/**
 * Entity implementation class for Entity: EstadoUsuario
 *
 */
@Entity
@Table(name = "ESTADO_USUARIOS", uniqueConstraints = {
		@UniqueConstraint(name = "uk_estado", columnNames = { "estado_valor" }) })

public class EstadoUsuario implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	private Long id_estado_usuario;

	@Column(length = 40, nullable = false)
	@Enumerated(value = EnumType.STRING)
	private EnumEstadoUsuario estado_valor;

	public Long getId_estado_usuario() {
		return id_estado_usuario;
	}

	public void setId_estado_usuario(Long id_estado_usuario) {
		this.id_estado_usuario = id_estado_usuario;
	}

	public EnumEstadoUsuario getEstado_valor() {
		return estado_valor;
	}

	public void setEstado_valor(EnumEstadoUsuario estado_valor) {
		this.estado_valor = estado_valor;
	}

}