package com.daos;
import java.util.List;

import javax.ejb.Local;

import com.entities.Zona;
import com.enumerados.EnumZonasCategoria;
import com.exception.ServiciosException;

@Local
public interface IZonaDAO {
	
	void altaZona(Zona categoria) throws ServiciosException;
	void bajaZona(int pk) throws ServiciosException;
	void modificarZona(Zona categoria) throws ServiciosException;
	List<Zona> obtenerPorCategoria(EnumZonasCategoria zonaEnum) throws ServiciosException;
	List<Zona> obtenerTodos() throws ServiciosException;
	Zona findForMerge(int pk) throws ServiciosException;

}
