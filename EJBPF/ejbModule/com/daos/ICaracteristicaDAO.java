package com.daos;

import java.util.List;
import javax.ejb.Local;
import com.entities.Caracteristica;
import com.exception.ServiciosException;

@Local
public interface ICaracteristicaDAO {
	
	void altaCaracteristica(Caracteristica cra) throws ServiciosException;
	void bajaCaracteristica(Caracteristica cra) throws ServiciosException;
	void modificarCaracteristica(Caracteristica cra) throws ServiciosException;
	Caracteristica findForMerge(int pk_cra) throws ServiciosException;
	List<Caracteristica> obtenerTodasLasCrs() throws ServiciosException;
	List<Caracteristica> obtenerCrsPorFenomeno(String fenomeno) throws ServiciosException;
	List<Caracteristica> obtenerCrsPKPorFenomeno(String fenomeno, String caracteristica) throws ServiciosException;

}
