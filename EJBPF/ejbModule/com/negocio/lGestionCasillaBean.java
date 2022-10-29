package com.negocio;

import java.util.List;
import javax.ejb.Local;
import com.dto.CasillaDTO;
import com.exception.ServiciosException;

@Local
public interface lGestionCasillaBean {

	public void agregarCasilla(CasillaDTO CasillaDTO) throws ServiciosException;
	public void modificarCasilla(CasillaDTO casillaDTO) throws ServiciosException;
	public CasillaDTO obtenerCasillaNombre(String nombre) throws ServiciosException;
	public List<CasillaDTO> obtenerCasillas() throws ServiciosException;
	List<CasillaDTO> obtenerCasillasPorFormulario(long pk) throws ServiciosException;
	void bajaLogicaCasilla(Long pk) throws ServiciosException;

}
