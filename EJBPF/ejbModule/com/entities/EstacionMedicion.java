package com.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "ESTACIONMEDICION", uniqueConstraints = {
		@UniqueConstraint(name="uk_nombre_estacion", columnNames= {"nombre"})})
public class EstacionMedicion implements Serializable {

	private static final long serialVersionUID = 1L;

	
	@Id
	@SequenceGenerator(name = "seqEstacionMedicion", initialValue=1, sequenceName="SEQ_ID_ESTACIONMEDICION")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seqEstacionMedicion")
	private Long id_estacionmedicion;
   
	@Column(length = 80, nullable = false)
	private  String nombre;
	
	@Column(length = 80, nullable = false)
	private  String parametro;
	
	@Column(length = 80, nullable = false)
	private  String unidadMedida;
	
	@Column(length = 80, nullable = true)
	private  String descripcion;
	
	@Column(length = 80, nullable = false)
	private  String tipoDato;
	
	@Column(length = 80, nullable = false)
	private  String departamento;
	
	@Column(length = 80, nullable = false)
	private  String ciudad;
	
	@Column(length = 80, nullable = false)
	private  String usuario;

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
