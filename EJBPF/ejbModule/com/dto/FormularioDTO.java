package com.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import com.entities.Contiene;

public class FormularioDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String nombre;
	
	private Long id_formulario;

	private Date fechahora;
	
	private String descripcion;
	
	private List<Contiene> contienes;	
	
	private String usuario;

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Long getId_formulario() {
		return id_formulario;
	}

	public void setId_formulario(Long id_formulario) {
		this.id_formulario = id_formulario;
	}

	public Date getFechahora() {
		return fechahora;
	}

	public void setFechahora(Date fechahora) {
		this.fechahora = fechahora;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	public List<Contiene> getContienes() {
		return contienes;
	}

	public void setContienes(List<Contiene> contienes) {
		this.contienes = contienes;
	}

}
