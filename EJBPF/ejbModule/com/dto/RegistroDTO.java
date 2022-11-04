package com.dto;

import java.util.List;

public class RegistroDTO {

	private String nombre;
	private String descripcion;
	private List<String> casillas;
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public List<String> getCasillas() {
		return casillas;
	}
	public void setCasillas(List<String> casillas) {
		this.casillas = casillas;
	}
	
	
}
