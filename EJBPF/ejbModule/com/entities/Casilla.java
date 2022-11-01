package com.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

import com.entities.Contiene;
import com.enumerados.EnumTipoDato;


/**
 * The persistent class for the CASILLAS database table.
 * 
 */
@Entity
@Table(name="CASILLAS")

public class Casilla implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="seqCasilla", initialValue=1, sequenceName="SEQ_ID_CASILLAS")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CASILLAS_IDCASILLA_GENERATOR")
	private long id_casilla;

	@Column(length = 100)
	private String descripcion;

	@Column(nullable = false)
	private String nombre;

	@Column(nullable = false)
	@Enumerated(value = EnumType.STRING)
	private EnumTipoDato tipoDato;

	@Column(name="UNIDAD_MEDIDA")
	private String unidadMedida;

	//bi-directional many-to-one association to Contiene
	@OneToMany(mappedBy="casilla",  cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
	private List<Contiene> contienes;
	
	@Column(nullable = false)
	private String usuario;

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public List<Contiene> getContienes() {
		return contienes;
	}

	public void setContienes(List<Contiene> contienes) {
		this.contienes = contienes;
	}

	public long getId_casilla() {
		return id_casilla;
	}

	public void setId_casilla(long id_casilla) {
		this.id_casilla = id_casilla;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public EnumTipoDato getTipoDato() {
		return tipoDato;
	}

	public void setTipoDato(EnumTipoDato tipoDato) {
		this.tipoDato = tipoDato;
	}

	public String getUnidadMedida() {
		return unidadMedida;
	}

	public void setUnidadMedida(String unidadMedida) {
		this.unidadMedida = unidadMedida;
	}


}