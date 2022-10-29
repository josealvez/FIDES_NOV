package com.daos;

import java.util.List;

import javax.ejb.Local;

import com.entities.Departamento;
import com.enumerados.EnumNombreDepartamento;
import com.enumerados.EnumZonasCategoria;
import com.exception.ServiciosException;

@Local
public interface IDepartamentoDAO {
	
	void altaDepartamento(Departamento nombre) throws ServiciosException;
	void altaDepartamento(Departamento nombre, int zonaPK) throws ServiciosException;
	void bajaDepartamento(int pk) throws ServiciosException;
	void modificarDepartamentoZona(Departamento d, int pkZona) throws ServiciosException;
	List<Departamento> obtenerPorNombre(EnumNombreDepartamento departamentoEnum) throws ServiciosException;
	List<Departamento> obtenerPoZona(EnumZonasCategoria zona) throws ServiciosException;
	List<Departamento> obtenerTodos() throws ServiciosException;
	Departamento findForMerge(int pk) throws ServiciosException;

}
