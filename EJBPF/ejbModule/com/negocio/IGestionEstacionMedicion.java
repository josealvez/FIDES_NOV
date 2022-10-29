package com.negocio;

import java.util.List;

import javax.ejb.Local;

import com.dto.EstacionMedicionDTO;
import com.exception.ServiciosException;

@Local
public interface IGestionEstacionMedicion {

	void agregarEM(EstacionMedicionDTO emDTO) throws ServiciosException;

	void actualizarEM(EstacionMedicionDTO emDTO) throws ServiciosException;

	List<EstacionMedicionDTO> obtenerTodasEM() throws ServiciosException;

	EstacionMedicionDTO obtenerEstacionNombre(String nombre) throws ServiciosException;

	void bajaLogicaEM(String nombre) throws ServiciosException;

}
