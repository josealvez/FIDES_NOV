package com.daos;
import java.util.List;
import javax.ejb.Local;

import com.entities.Formulario;
import com.exception.ServiciosException;

@Local
public interface IFormularioDAO {

	void bajaFormulario(Long pk) throws ServiciosException;
	List<Formulario> obtenerTodos() throws ServiciosException;
	List<Formulario> obtenerPorNombre(String nombre) throws ServiciosException;
	long altaFormulario(Formulario form) throws ServiciosException;
	Formulario findForMerge(Long pk) throws ServiciosException;
	void modificarFormulario(Formulario form) throws ServiciosException;
	List<Formulario> obtenerPorPK(Long pk) throws ServiciosException;
	


}
