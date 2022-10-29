package com.daos;

import java.util.List;

import javax.ejb.Local;

import com.entities.Usuario;
import com.exception.ServiciosException;

@Local
public interface IUsuarioDAO {
	
	void altaUsuario(Usuario user) throws ServiciosException;
	void bajaUsuario(Usuario usuario) throws ServiciosException;
	void modificarUsuario(Usuario usuario) throws ServiciosException;
	List<Usuario> obtenerPorEmail(String email) throws ServiciosException;
	Usuario findForMerge (Long pk) throws ServiciosException;
	List<Usuario> obtenerTodosLosUsuarios() throws ServiciosException;
	public Usuario validarUsuario(String email, String password) throws ServiciosException;

}
