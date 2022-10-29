package com.daos;

import java.util.List;

import javax.ejb.Local;

import com.entities.Contiene;
import com.entities.Formulario;
import com.exception.ServiciosException;

@Local
public interface IRegistroDAO {

	void AltaRegistro(Contiene c) throws ServiciosException;

	void ModificarRegistro(Contiene c) throws ServiciosException;

	void EliminarRegistro(Contiene c) throws ServiciosException;

	Contiene BuscarRegistroPorId(Long id) throws ServiciosException;

	List<Contiene> ListarRegistrosPorFormulario(Formulario f) throws ServiciosException;

	List<Contiene> ListarRegistros() throws ServiciosException;

}
