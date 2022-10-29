package com.negocio;

import java.util.List;

import javax.ejb.Local;

import com.dto.FenomenoDTO;
import com.exception.ServiciosException;

@Local
public interface IGestionFenomenoBean {
	
	public void agregarFenomeno(FenomenoDTO fenomenoDTO) throws ServiciosException;
	public void modificarFenomeno(FenomenoDTO fenomenoDTO) throws   ServiciosException;
	public List<FenomenoDTO> obtenerFenomenos() throws ServiciosException;
	public FenomenoDTO obtenerFenomenoNombre(String nombre) throws  ServiciosException;

}
