package com.daos;

import java.util.List;

import javax.ejb.Remote;

import com.entities.EstadoUsuario;
import com.enumerados.EnumEstadoUsuario;
import com.exception.ServiciosException;
@Remote
public interface IEstadoUsuarioDAO {

	void altaEstado(EstadoUsuario estado) throws ServiciosException;

	void bajaEstado(EstadoUsuario estado) throws ServiciosException;

	void modificarEstado(EstadoUsuario estado) throws ServiciosException;

	List<EstadoUsuario> obtenerPorEstado(EnumEstadoUsuario estado) throws ServiciosException;

	EstadoUsuario findForMerge(int pk) throws ServiciosException;

	List<EstadoUsuario> obtenerTodos() throws ServiciosException;

}
