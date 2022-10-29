package com.entities;

import java.io.Serializable;

import javax.persistence.*;


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

	private String valoringresado;
	
	private java.util.Date fecharegistro;

	//bi-directional many-to-one association to Casilla
	@ManyToOne
	@JoinColumn(name="IDCASILLA")
	private Casilla casilla;

	//bi-directional many-to-one association to Formulario
	@ManyToOne
	@JoinColumn(name="IDFORMULARIOS")
	private Formulario formulario;

	public Contiene() {
	}

	public long getIdcontiene() {
		return this.idcontiene;
	}

	public void setIdcontiene(long idcontiene) {
		this.idcontiene = idcontiene;
	}

	public String getValoringresado() {
		return this.valoringresado;
	}

	public void setValoringresado(String valoringresado) {
		this.valoringresado = valoringresado;
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