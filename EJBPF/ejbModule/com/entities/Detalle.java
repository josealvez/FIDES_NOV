package com.entities;

import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.*;



/**
 * Entity implementation class for Entity: Detalle
 *
 */
@Entity
@Table(name = "DETALLES", uniqueConstraints = {
		@UniqueConstraint(name="uk_detalles", columnNames= {"obs_id_observacion","crtca_id_caracteristica"})
		})
public class Detalle implements Serializable {

	
	private static final long serialVersionUID = 1L;

	@Id
	private Long id_detalle;

	@Temporal(TemporalType.TIMESTAMP)
    private Calendar fechaHora;
		
	private  int valor_numerico;
	
	@Column(length = 200)
	private  String valor_texto;
	
	@ManyToOne
	@JoinColumn(foreignKey=@ForeignKey(name="fk_observ_detalle"))
	private Observacion obs;
	
	@ManyToOne
	@JoinColumn(foreignKey=@ForeignKey(name="fk_crtca_detalle"))
	private Caracteristica crtca;

	public Long getId_detalle() {
		return id_detalle;
	}

	public void setId_detalle(Long id_detalle) {
		this.id_detalle = id_detalle;
	}

	public Calendar getFechaHora() {
		return fechaHora;
	}

	public void setFechaHora(Calendar fechaHora) {
		this.fechaHora = fechaHora;
	}

	public int getValor_numerico() {
		return valor_numerico;
	}

	public void setValor_numerico(int valor_numerico) {
		this.valor_numerico = valor_numerico;
	}

	public String getValor_texto() {
		return valor_texto;
	}

	public void setValor_texto(String valor_texto) {
		this.valor_texto = valor_texto;
	}

	public Observacion getObs() {
		return obs;
	}

	public void setObs(Observacion obs) {
		this.obs = obs;
	}

	public Caracteristica getCrtca() {
		return crtca;
	}

	public void setCrtca(Caracteristica crtca) {
		this.crtca = crtca;
	}
   
	
   
}
