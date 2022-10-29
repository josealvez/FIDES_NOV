package com.negocio;
import java.util.List;

import javax.ejb.Local;

import com.dto.FormularioDTO;
import com.exception.ServiciosException;

@Local
public interface IGestionFormularioBean {

	public long agregarFormulario(FormularioDTO formularioDTO) throws ServiciosException;

	public List<FormularioDTO> obtenerFormularios() throws ServiciosException;

	FormularioDTO obtenerFormularioPorId(Long id) throws ServiciosException;

	//void actualizarFormulario(FormularioDTO formularioDTO) throws ServiciosException;

	//List<FormularioDTO> obtenerTodosLosFormularios() throws ServiciosException;
}
