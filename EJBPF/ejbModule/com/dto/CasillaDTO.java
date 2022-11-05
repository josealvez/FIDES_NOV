package com.dto;

import java.io.Serializable;

import java.util.List;

import com.entities.Contiene;
public class CasillaDTO implements Serializable{	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1577060491323634320L;
	private Long id_casilla;
	private String descripcion;
	private String nombre;
	private String tipoDato;
	private String unidadMedida;

	private List<Contiene> contienes;	
	
	private String usuario;
	
	public CasillaDTO() {
		
	}

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


	public Long getId_casilla() {
		return id_casilla;
	}

	public void setId_casilla(Long id_casilla) {
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

	public String getTipoDato() {
		return tipoDato;
	}

	public void setTipoDato(String tipoDato) {
		this.tipoDato = tipoDato;
	}

	public String getUnidadMedida() {
		return unidadMedida;
	}

	public void setUnidadMedida(String unidadMedida) {
		this.unidadMedida = unidadMedida;
	}
}
