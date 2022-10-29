package com.dto;

import java.io.Serializable;

public class EstacionMedicionDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id_estacionmedicion;
	private  String nombre;
	private  String parametro;
	private  String unidadMedida;
	private  String descripcion;
	private  String tipoDato;
	private  String departamento;
	private  String ciudad;
	private  String usuario;
	
	public EstacionMedicionDTO() {
		
	}

	public Long getId_estacionmedicion() {
		return id_estacionmedicion;
	}

	public void setId_estacionmedicion(Long id_estacionmedicion) {
		this.id_estacionmedicion = id_estacionmedicion;
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

	public String getUnidadMedida() {
		return unidadMedida;
	}

	public void setUnidadMedida(String unidadMedida) {
		this.unidadMedida = unidadMedida;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getTipoDato() {
		return tipoDato;
	}

	public void setTipoDato(String tipoDato) {
		this.tipoDato = tipoDato;
	}

	public String getDepartamento() {
		return departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	
	
}
