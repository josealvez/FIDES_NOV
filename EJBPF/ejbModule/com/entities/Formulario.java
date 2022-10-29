package com.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

import com.entities.Contiene;


@Entity
@Table(name = "FORMULARIOS")
public class Formulario implements Serializable {

	
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "seqFormulario", initialValue=1, sequenceName="SEQ_ID_FORMULARIO")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seqFormulario")
	private Long id_formulario;
	
	@Column(length = 50)
	private  String nombre;
	
	@Temporal(TemporalType.DATE)
    private Date fechaHora;
	
	@Column(length = 200)
	private  String descripcion;

//	
//
//	@ManyToMany(cascade = { CascadeType.ALL })
//    @JoinTable(
//        name = "Formulario_Casillas", 
//        joinColumns = { @JoinColumn(name = "id_formulario") }, 
//        inverseJoinColumns = { @JoinColumn(name = "id_casilla") }
//    )
//	Set<Casilla> casillas = new HashSet<>();

	@Column(name = "validar")
	private boolean validarInvestigador;
	
	//bi-directional many-to-one association to Contiene
	@OneToMany(fetch = FetchType.EAGER, mappedBy="formulario")
	private List<Contiene> contienes;	
	//
	
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


	public boolean isValidarInvestigador() {
		return validarInvestigador;
	}


	public void setValidarInvestigador(boolean validarInvestigador) {
		this.validarInvestigador = validarInvestigador;
	}


	public Long getId_formulario() {
		return id_formulario;
	}


	public void setId_formulario(Long id_formulario) {
		this.id_formulario = id_formulario;
	}


	public Date getFechaHora() {
		return fechaHora;
	}


	public void setFechaHora(Date fechaHora) {
		this.fechaHora = fechaHora;
	}

	public List<Contiene> getContienes() {
		return contienes;
	}

	public void setContienes(List<Contiene> contienes) {
		this.contienes = contienes;
	}
	
	

}