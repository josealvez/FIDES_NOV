package com.daos;

import java.util.List;

import javax.ejb.Local;

import com.entities.Detalle;
import com.exception.ServiciosException;

@Local
public interface IDetalleDAO {
	
	void altaDetalle (Detalle dt)  throws ServiciosException;
	void bajaCaracteristica(Detalle dt) throws ServiciosException;
	void modificarCaracteristica(Detalle dt) throws ServiciosException;
	Detalle findForMerge(int pkDt) throws ServiciosException;
	List<Detalle> ObtenerObsAndCrcForEmail (String emailUsuario) throws ServiciosException;

}
