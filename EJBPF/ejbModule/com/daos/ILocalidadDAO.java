package com.daos;

import java.util.List;

import javax.ejb.Local;

import com.entities.Localidad;
import com.enumerados.EnumNombreDepartamento;
import com.exception.ServiciosException;

@Local
public interface ILocalidadDAO {
	
	void altaLocalidad(Localidad nombre) throws ServiciosException;
	void bajaLocalidad(int pk) throws ServiciosException;
	void modificarLocalidadDepartamento(Localidad l, int pkDepartamento) throws ServiciosException;
	List<Localidad> obtenerLocalidadesPorDepartamento(EnumNombreDepartamento departamentoEnum) throws ServiciosException;
	List<Localidad> obtenerLocalidadesIdDosFiltros(EnumNombreDepartamento departamentoEnum, String lc) throws ServiciosException;
	Localidad findForMerge(int pk) throws ServiciosException;

}
