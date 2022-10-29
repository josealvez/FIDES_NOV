package com.daos;
import java.util.List;
import javax.ejb.Local;
import com.entities.Observacion;
import com.entities.Usuario;
import com.enumerados.EnumZonasCategoria;
import com.exception.ServiciosException;

@Local
public interface IObservacionDAO {
	
	void altaObservacion(Observacion obs) throws ServiciosException;
	void bajaObservacion(Long pk) throws ServiciosException;
	void modificarObservacion(Observacion obs) throws ServiciosException;
	List<Observacion> obtenerPorUsuario(Usuario usuario) throws ServiciosException;
	List<Observacion> obtenerPorZona(EnumZonasCategoria zona) throws ServiciosException;
	List<Observacion> obtenerTodos() throws ServiciosException;
	List<Observacion> obtenerSinValidar() throws ServiciosException;
	List<Observacion> obtenerPorPK(Long pk) throws ServiciosException;
	Observacion findForMerge(Long pk) throws ServiciosException;

}
