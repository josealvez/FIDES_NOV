package com.dto;

import java.io.Serializable;

public class CaracteristicaDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id_caracteristica;
	private String fenomeno;
	private String nombre;
	private String etiqueta;
	private String tipoDato;
	
	
	
	
	public Long getId_caracteristica() {
		return id_caracteristica;
	}
	public void setId_caracteristica(Long id_caracteristica) {
		this.id_caracteristica = id_caracteristica;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getEtiqueta() {
		return etiqueta;
	}
	public void setEtiqueta(String etiqueta) {
		this.etiqueta = etiqueta;
	}
	public String getTipoDato() {
		return tipoDato;
	}
	public void setTipoDato(String tipoDato) {
		this.tipoDato = tipoDato;
	}
	public String getFenomeno() {
		return fenomeno;
	}
	public void setFenomeno(String fenomeno) {
		this.fenomeno = fenomeno;
	}
	
	
	
}
