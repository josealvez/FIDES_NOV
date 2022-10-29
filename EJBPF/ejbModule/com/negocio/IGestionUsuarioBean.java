package com.negocio;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.ejb.Local;
import javax.naming.NamingException;

import com.dto.UsuarioDTO;
import com.exception.ServiciosException;

@Local
public interface IGestionUsuarioBean {
	
	public void agregarUsuario(UsuarioDTO usuarioDTO) throws ServiciosException, NoSuchAlgorithmException;
	public void actualizarUsuario(UsuarioDTO usuarioDTO) throws  NoSuchAlgorithmException, ServiciosException;
	public List<UsuarioDTO> obtenerUsuarios() throws ServiciosException;
	public UsuarioDTO obtenerUsuarioEmail(String email) throws ServiciosException;
	public void bajaLogicaUsuario(String email) throws  ServiciosException;
	public UsuarioDTO validarUsuario(String email, String password) throws ServiciosException, NoSuchAlgorithmException;
//	public void validarLDAP(String user, String token) throws NamingException, IOException, ServiciosException;
}
