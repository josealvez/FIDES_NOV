package com.entities;

import java.io.Serializable;
import javax.persistence.*;
import com.enumerados.EnumNombreDepartamento;

/**
 * Entity implementation class for Entity: Departamento
 *
 */
@Entity
@Table(name = "DEPARTAMENTOS", uniqueConstraints = {
		@UniqueConstraint(name="uk_codigo_departamento", columnNames= {"codigo"}),
		@UniqueConstraint(name="uk_nombre_departamento", columnNames= {"nombre"})
		})
public class Departamento implements Serializable {

	
	private static final long serialVersionUID = 1L;

	@Id
	private Long id_departamento;
	
	@Column(nullable = false)
	private String codigo;
	
	@Column(length = 100, nullable = false)
	@Enumerated(value = EnumType.STRING)
	private EnumNombreDepartamento nombre;
   
	@ManyToOne
	@JoinColumn(foreignKey=@ForeignKey(name="fk_zona_departamento"))
	private Zona zona;

	public Long getId_departamento() {
		return id_departamento;
	}

	public void setId_departamento(Long id_departamento) {
		this.id_departamento = id_departamento;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public EnumNombreDepartamento getNombre() {
		return nombre;
	}

	public void setNombre(EnumNombreDepartamento nombre) {
		this.nombre = nombre;
	}

	public Zona getZona() {
		return zona;
	}

	public void setZona(Zona zona) {
		this.zona = zona;
	}
	
}