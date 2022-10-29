package com.daos;

import java.util.List;

import javax.ejb.Local;

import com.entities.Fenomeno;
import com.exception.ServiciosException;

@Local
public interface IFenomenoDAO {
	
	void altaFenomeno(Fenomeno nombre) throws ServiciosException;
	void bajaFenomeno(Long pk) throws ServiciosException;
	void modificarFenomeno(Fenomeno nombre) throws ServiciosException;
	List<Fenomeno> obtenerPorNombre(String descripcion) throws ServiciosException;
	List<Fenomeno> obtenerTodos() throws ServiciosException;
	Fenomeno findForMerge(Long pk) throws ServiciosException;

}
