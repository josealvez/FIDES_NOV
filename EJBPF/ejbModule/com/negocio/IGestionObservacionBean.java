package com.negocio;

import java.util.List;

import javax.ejb.Local;

import com.dto.LocalidadDTO;
import com.dto.ObservacionDTO;
import com.exception.ServiciosException;

@Local
public interface IGestionObservacionBean {
	
	public void agregarObservacion(ObservacionDTO observacionDTO) throws ServiciosException;
	public void actualizarObservacion(ObservacionDTO observacionDTO) throws ServiciosException;
	public List<LocalidadDTO> obtenerLocalidadesPorDepartamento(String nombreDepartamento)throws ServiciosException;
	public List<ObservacionDTO> obtenerObservacionesPorUsuario(String email) throws ServiciosException;
	public ObservacionDTO obtenerObservacionPorId (Long id) throws ServiciosException;
	public List<ObservacionDTO> obtenerTodasObservaciones() throws ServiciosException;
}
