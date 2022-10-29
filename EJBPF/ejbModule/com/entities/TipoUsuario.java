package com.entities;

import java.io.Serializable;
import javax.persistence.*;

import com.enumerados.EnumCategoriaUsuario;

/**
 * Entity implementation class for Entity: TipoUsuario
 *
 */
@Entity

@Table(name = "TIPO_USUARIOS", uniqueConstraints = {
		@UniqueConstraint(name="uk_rol", columnNames= {"rol"})
		})
public class TipoUsuario implements Serializable {

	
	private static final long serialVersionUID = 1L;

	@Id
	private Long id_tipo_usuario;
	
	@Column(length = 40, nullable = false)
	@Enumerated(value = EnumType.STRING)
	private EnumCategoriaUsuario rol;


	public Long getId_tipo_usuario() {
		return id_tipo_usuario;
	}

	public void setId_tipo_usuario(Long id_tipo_usuario) {
		this.id_tipo_usuario = id_tipo_usuario;
	}

	public EnumCategoriaUsuario getRol() {
		return rol;
	}

	public void setRol(EnumCategoriaUsuario rol) {
		this.rol = rol;
	}
	
}