package com.dto;

import java.io.Serializable;


public class UsuarioDTO implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
	
	private Long id_usuario;
	private String rol;
	private String estadoUsuario;
	private String username;
	private String nombre;
	private String apellido;
	private String direccion;
	private String documentoCategoria;
	private String documento;
	private String email;
	private String passwd;
	
	public UsuarioDTO() {
		
	}

	public Long getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(Long id_usuario) {
		this.id_usuario = id_usuario;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	public String getEstadoUsuario() {
		return estadoUsuario;
	}

	public void setEstadoUsuario(String estadoUsuario) {
		this.estadoUsuario = estadoUsuario;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getDocumentoCategoria() {
		return documentoCategoria;
	}

	public void setDocumentoCategoria(String documentoCategoria) {
		this.documentoCategoria = documentoCategoria;
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	
}
