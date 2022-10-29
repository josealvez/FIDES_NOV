package com.daos;

import java.util.List;

import javax.ejb.Remote;

import com.entities.Casilla;
import com.exception.ServiciosException;

@Remote
public interface ICasillaDAO {

	void altaCasilla(Casilla nombre) throws ServiciosException;

	void bajaCasilla(Long pk) throws ServiciosException;

	void modificarCasilla(Casilla casilla) throws ServiciosException;

	List<Casilla> obtenerPorNombre(String descripcion) throws ServiciosException;

	List<Casilla> obtenerTodos() throws ServiciosException;

	Casilla findForMerge(Long pk) throws ServiciosException;

	List<Casilla> obtenerCasillasPorFormulario(long id) throws ServiciosException;

}
