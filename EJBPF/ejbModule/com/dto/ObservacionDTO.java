package com.dto;

import java.io.Serializable;
import java.util.Date;



public class ObservacionDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id_observacion;
	
	private String emailAficionado;
	
	private Date fecha;
	
	private String descripcion;
	
	private String categoriaFenomeno;
	
	private String Localidad;
	
	private String emialInvestigador;
	
	private double latitud;
	
	private double longitud;
	
	private byte[] imagen;
	
	private String departamento;
	
	private boolean validarInvestigador;
	
	private String estado;
	
	
	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Long getId_observacion() {
		return id_observacion;
	}

	public void setId_observacion(Long id_observacion) {
		this.id_observacion = id_observacion;
	}

	public String getEmailAficionado() {
		return emailAficionado;
	}

	public void setEmailAficionado(String emailAficionado) {
		this.emailAficionado = emailAficionado;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getCategoriaFenomeno() {
		return categoriaFenomeno;
	}

	public void setCategoriaFenomeno(String categoriaFenomeno) {
		this.categoriaFenomeno = categoriaFenomeno;
	}

	public String getLocalidad() {
		return Localidad;
	}

	public void setLocalidad(String localidad) {
		Localidad = localidad;
	}

	public String getEmialInvestigador() {
		return emialInvestigador;
	}

	public void setEmialInvestigador(String emialInvestigador) {
		this.emialInvestigador = emialInvestigador;
	}

	


	public double getLatitud() {
		return latitud;
	}

	public void setLatitud(double latitud) {
		this.latitud = latitud;
	}

	public double getLongitud() {
		return longitud;
	}

	public void setLongitud(double longitud) {
		this.longitud = longitud;
	}

	public boolean isValidarInvestigador() {
		return validarInvestigador;
	}

	public void setValidarInvestigador(boolean validarInvestigador) {
		this.validarInvestigador = validarInvestigador;
	}

	

	public byte[] getImagen() {
		return imagen;
	}

	public void setImagen(byte[] imagen) {
		this.imagen = imagen;
	}

	public String getDepartamento() {
		return departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

	
	
	

}
