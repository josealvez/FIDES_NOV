package com.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Localidad
 *
 */
@Entity
@Table(name = "LOCALIDADES")
public class Localidad implements Serializable {

	
	private static final long serialVersionUID = 1L;

	@Id
	private Long id_localidad;
	
	@Column(nullable = false)
	private String nombre;
	
	
	@ManyToOne
	@JoinColumn(foreignKey=@ForeignKey(name="fk_local_departamento"))
	private Departamento departamento;

	public Long getId_localidad() {
		return id_localidad;
	}

	public void setId_localidad(Long id_localidad) {
		this.id_localidad = id_localidad;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

	
	
   
}