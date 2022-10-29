package com.dto;

import java.io.Serializable;
import java.util.List;

import com.entities.Contiene;

public class CasillaDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long id_casilla;
	private String descripcion;
	private String lugarubicacion;
	private String nombre;
	private String parametro;
	private String tipoDato;
	private String unidadMedida;
	private List<Contiene> contienes;	
	
	public CasillaDTO() {
		
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

	public String getLugarubicacion() {
		return lugarubicacion;
	}

	public void setLugarubicacion(String lugarubicacion) {
		this.lugarubicacion = lugarubicacion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getParametro() {
		return parametro;
	}

	public void setParametro(String parametro) {
		this.parametro = parametro;
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
