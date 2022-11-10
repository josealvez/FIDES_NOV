package com.dto;

import java.util.HashMap;
import java.util.List;

public class RegistroDTO {

	private String nombre;
	private String descripcion;
	private HashMap<String, String> casillas;
	
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
	public HashMap<String, String> getCasillas() {
		return casillas;
	}
	public void setCasillas(HashMap<String, String> ingreso) {
		this.casillas = ingreso;
	}
	
	
}
