package com.entities;

import java.io.Serializable;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * The persistent class for the CONTIENE database table.
 * 
 */
@Entity
@Table(name = "CONTIENE")
public class Contiene implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "seqContiene", initialValue=1, sequenceName="SEQ_ID_CONTIENE")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seqContiene")
	private long idcontiene;

	private String registro;
	
	private String usuario;
	
	private Boolean isRegistro;
	
	private java.util.Date fecharegistro;

	//bi-directional many-to-one association to Casilla
	@ManyToOne
	@JoinColumn(name="IDCASILLA")
	@JsonIgnore
	private Casilla casilla;

	//bi-directional many-to-one association to Formulario
	@ManyToOne
	@JoinColumn(name="IDFORMULARIOS", nullable = true)
	@JsonIgnore
	private Formulario formulario;

	public Contiene() {
	}

	
	public Boolean getIsRegistro() {
		return isRegistro;
	}


	public void setIsRegistro(Boolean isRegistro) {
		this.isRegistro = isRegistro;
	}


	public String getUsuario() {
		return usuario;
	}



	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}



	public String getRegistro() {
		return registro;
	}


	public void setRegistro(String registro) {
		this.registro = registro;
	}


	public java.util.Date getFecharegistro() {
		return fecharegistro;
	}


	public void setFecharegistro(java.util.Date fecharegistro) {
		this.fecharegistro = fecharegistro;
	}


	public long getIdcontiene() {
		return this.idcontiene;
	}

	public void setIdcontiene(long idcontiene) {
		this.idcontiene = idcontiene;
	}

	public java.util.Date getFechaRegistro() {
		return this.fecharegistro;
	}

	public void setFechaRegistro(java.util.Date now) {
		this.fecharegistro = now;
	}

	public Casilla getCasilla() {
		return this.casilla;
	}

	public void setCasilla(Casilla casilla) {
		this.casilla = casilla;
	}

	public Formulario getFormulario() {
		return this.formulario;
	}

	public void setFormulario(Formulario formulario) {
		this.formulario = formulario;
	}

}