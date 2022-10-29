package com.daos;

import java.util.List;
import javax.ejb.Local;
import com.entities.EstacionMedicion;
import com.exception.ServiciosException;

@Local
public interface IEstacionMedicionDAO {

	void altaEM(EstacionMedicion esm) throws ServiciosException;
	void bajaEM(EstacionMedicion esm) throws ServiciosException;
	void modificarEM(EstacionMedicion esm) throws ServiciosException;
	List<EstacionMedicion> obtenerTodasLasEM() throws ServiciosException;
	EstacionMedicion findForMerge(Long pk_esm) throws ServiciosException;
	List<EstacionMedicion> obtenerPorNombre(String nombre) throws ServiciosException;


}
