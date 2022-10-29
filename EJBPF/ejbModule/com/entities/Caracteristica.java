package com.entities;

import java.io.Serializable;


import javax.persistence.*;

import com.enumerados.EnumTipoDatoCaracteristica;

/**
 * Entity implementation class for Entity: Caracteristica
 *
 */
@Entity
@Table(name = "CARACTERISTICAS")
public class Caracteristica implements Serializable {

	private static final long serialVersionUID = 1L;

	
	@Id
	@SequenceGenerator(name = "seqCaracteristica", initialValue=1, sequenceName="SEQ_ID_CARACTERISTICA")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seqCaracteristica")
	private Long id_caracteristica;
   
	@Column(length = 80, nullable = false)
	private  String nombre;
	
	@Column(length = 80, nullable = false)
	private  String etiqueta;
	
	@Column(nullable = false)
	@Enumerated(value = EnumType.STRING)
	private EnumTipoDatoCaracteristica tipoDato;
	
	@ManyToOne()
	@JoinColumn(foreignKey=@ForeignKey(name="fk_fenomeno_caracteristica"))
	private Fenomeno fenomeno;
	

	
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

	public EnumTipoDatoCaracteristica getTipoDato() {
		return tipoDato;
	}

	public void setTipoDato(EnumTipoDatoCaracteristica tipoDato) {
		this.tipoDato = tipoDato;
	}
	
	public Fenomeno getFenomeno() {
		return fenomeno;
	}

	public void setFenomeno(Fenomeno fenomeno) {
		this.fenomeno = fenomeno;
	}
	
	
}