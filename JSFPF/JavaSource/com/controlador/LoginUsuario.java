package com.controlador;

import java.io.IOException;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.naming.NamingException;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

import com.dto.UsuarioDTO;
import com.enumerados.EnumCategoriaUsuario;
import com.enumerados.EnumEstadoUsuario;
import com.exception.ServiciosException;
import com.negocio.GestionUsuarioBean;

@Named("loginusuario")
@SessionScoped
public class LoginUsuario implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EJB
	private GestionUsuarioBean persistenciaUsuario;

	@Email
	private String email;

	@Size(min = 2, max = 40, message = "La contrase�a debe contener entre 2 y 40 caracteres.")
	private String password;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void validarUsuario() throws NamingException, IOException, NoSuchAlgorithmException {

		UsuarioDTO u = new UsuarioDTO();
		String email = new String(this.email);
		String[] dominio = email.split("@");

		try {
			u = persistenciaUsuario.validarUsuario(this.email, this.password);
		} catch (ServiciosException e) {
			e.printStackTrace();
		}

		if (u != null && u.getEstadoUsuario().equals(EnumEstadoUsuario.HABILITADO.toString())) {

			if (u.getRol().equals(EnumCategoriaUsuario.ADMINISTRADOR.toString()))
				router("Administrador", u);
			if (u.getRol().equals(EnumCategoriaUsuario.INVESTIGADOR.toString()))
				router("Investigador", u);
			if (u.getRol().equals(EnumCategoriaUsuario.AFICIONADO.toString()))
				router("Aficionado", u);

//		} else if (dominio[1].equalsIgnoreCase("pf.edu.uy")) {
//
//			if ( validarLDAP() && u == null) {
//				ldapNewUser();
//			} else {
//				usuarioInvalido();
//			}
//
		} else {

			usuarioInvalido();
		}
			
	}

	public String usuarioInvalido() {
		String redirect = " ";
		FacesContext fc = FacesContext.getCurrentInstance();
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuario o contrase�a incorrecta",
				"ERROR");
		fc.addMessage("", message);
		fc.getExternalContext().getFlash().setKeepMessages(true);
		return redirect;
	}

//	public boolean validarLDAP() throws NamingException {
//
//		try {
//			persistenciaUsuario.validarLDAP(this.email, this.password);
//			return true;
//		}
//
//		catch (ServiciosException | IOException e) {
//			return false;
//		}
//	}

	public void router(String rol, UsuarioDTO u) throws IOException {
		ExternalContext fc = FacesContext.getCurrentInstance().getExternalContext();
		String route = new String();
		switch (rol) {
		case "Administrador":
			route = "/pages/listarUsuario.xhtml?faces-redirect=true";
			break;
		case "Investigador":
			route = "/pages/listarFormulario.xhtml?faces-redirect=true";
			break;
		case "Aficionado":
			route = "/pages/formularioDisponible.xhtml?faces-redirect=true";
			break;
	
		}
		fc.getSessionMap().put("usuario", u);
		fc.redirect(fc.getRequestContextPath() + route);
	}
	
//	public void ldapNewUser() {
//		ExternalContext fc = FacesContext.getCurrentInstance().getExternalContext();
//		String route = "/pages/nuevoEnLDAP.xhtml?faces-redirect=true";
//		UsuarioDTO u = new UsuarioDTO();
//		u.setEmail(this.email);
//		u.setPasswd(this.password);
//		String[] username = this.email.split("@");
//		u.setUsername(username[0]);
//		fc.getSessionMap().put("usuario", u);
//		try {
//			fc.redirect(fc.getRequestContextPath() + route);
//		} catch (IOException e) {
//			System.out.println("Se rompio todo");
//			e.printStackTrace();
//		}
//	}
}
