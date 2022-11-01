package com.controlador;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import javax.ejb.EJB;
import javax.enterprise.context.ConversationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

import com.dto.UsuarioDTO;
import com.enumerados.EnumCategoriaDocumento;
import com.enumerados.EnumCategoriaUsuario;
import com.enumerados.EnumEstadoUsuario;
import com.exception.ServiciosException;
import com.negocio.GestionUsuarioBean;

@Named("actualizarusuario")
@ConversationScoped
public class ActualizarUsuario implements Serializable{

	private static final long serialVersionUID = 1L;


	@EJB
	private GestionUsuarioBean usuarioEJB;
	
	private Long id_usuario;
	
	@Size(min=2,max=50, message = "El nombre debe contener entre 2 y 50 caracteres.")
	private String nombre;
	
	@Size(min=2,max=50, message = "El apellido debe contener entre 2 y 50 caracteres.")
	private String apellido;
	
	@Size(min=2,max=50, message = "El domicilio debe contener entre 2 y 50 caracteres.")
	private String direccion;

	private String documentoCategoria;
	
	@Size(min=2,max=50, message = "El documento debe contener entre 2 y 50 caracteres.")
	private String documento;
	
	@Email(message = "El formato del correo es incorrecto")
	private String email;

	@Email(message = "El formato del correo es incorrecto")
	private String emailBusqueda;
	
	
	public String getEmailBusqueda() {
		return emailBusqueda;
	}


	public void setEmailBusqueda(String emailBusqueda) {
		this.emailBusqueda = emailBusqueda;
	}

	private String estadoUsuario;
	
	private String rolUsuairo;
	
	@Size(min=2,max=50, message = "El password debe contener entre 2 y 50 caracteres.")
	private String password;
	
	@Size(min=2,max=50, message = "El nombre de usuario debe contener entre 2 y 50 caracteres.")
	private String userName;
	

	@SuppressWarnings("unused")
	private EnumCategoriaDocumento dcEnum;
	

	@SuppressWarnings("unused")
	private EnumCategoriaUsuario tuEnum;
	

	@SuppressWarnings("unused")
	private EnumEstadoUsuario euEnum;
	
	private String oldPasswd;
	
	public void initConversation(){
	    if (!FacesContext.getCurrentInstance().isPostback() ) {
	    	FacesContext fc = FacesContext.getCurrentInstance();	
			if(!fc.getExternalContext().getRequestParameterMap().isEmpty()) {
				String email= fc.getExternalContext().getRequestParameterMap().get("userEmail");
				try {
					UsuarioDTO us = usuarioEJB.obtenerUsuarioEmail(email);
					this.id_usuario = us.getId_usuario();
					this.nombre = us.getNombre();
					this.apellido = us.getApellido();
					this.direccion = us.getDireccion();
					this.userName = us.getUsername();
					this.documentoCategoria = us.getDocumentoCategoria();
					this.documento = us.getDocumento();
					this.email = us.getEmail();
					this.estadoUsuario = us.getEstadoUsuario();
					this.rolUsuairo = us.getRol();
					this.userName = us.getUsername();
					this.direccion = us.getDireccion();
					this.oldPasswd = us.getPasswd();
				} catch (ServiciosException e) {
					FacesContext context = FacesContext.getCurrentInstance();
					FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), "ERROR");
					context.addMessage("", message);
				}
			}
	    }
	}
	
	
	public String getOldPasswd() {
		return oldPasswd;
	}

	public GestionUsuarioBean getUsuarioEJB() {
		return usuarioEJB;
	}

	public void setUsuarioEJB(GestionUsuarioBean usuarioEJB) {
		this.usuarioEJB = usuarioEJB;
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

	public String getEstadoUsuario() {
		return estadoUsuario;
	}

	public void setEstadoUsuario(String estadoUsuario) {
		this.estadoUsuario = estadoUsuario;
	}

	public String getRolUsuairo() {
		return rolUsuairo;
	}

	public void setRolUsuairo(String rolUsuairo) {
		this.rolUsuairo = rolUsuairo;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Long getId_usuario() {
		return id_usuario;
	}


	public void setId_usuario(Long id_usuario) {
		this.id_usuario = id_usuario;
	}

	
	
	public EnumCategoriaUsuario[] getTuEnum() {
		return EnumCategoriaUsuario.values();
	}
	
	public EnumCategoriaDocumento[] getDcEnum() {
		return EnumCategoriaDocumento.values();
	}

	public EnumEstadoUsuario[] getEuEnum() {
		return EnumEstadoUsuario.values();
	}	
	
	public String busquedaPorCorreo() {
		return "/pages/actualizarusuario.xhtml?faces-redirect=true&userEmail=" + this.getEmailBusqueda();
	}
	
	public String actulizarUsuario() {
		
		FacesContext context = FacesContext.getCurrentInstance();		
		
		if(this.nombre.trim().equals("")) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de validacion", "El nombre debe contener entre 2 y 50 caracteres.");
			context.addMessage( "El nombre debe contener entre 2 y 50 caracteres." , message);
			context.getExternalContext().getFlash().setKeepMessages(true);
			return " ";
		}else if( (this.nombre.trim().length() < 2) || (this.nombre.trim().length() > 50)){
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de validacion",  "El nombre debe contener entre 2 y 50 caracteres.");
			context.addMessage( "El nombre debe contener entre 2 y 50 caracteres.", message);
			context.getExternalContext().getFlash().setKeepMessages(true);
			return " ";
		}
		
		if(this.apellido.trim().equals("")) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de validacion", "El apellido debe contener entre 2 y 50 caracteres.");
			context.addMessage( "El apellido debe contener entre 2 y 50 caracteres." , message);
			context.getExternalContext().getFlash().setKeepMessages(true);
			return " ";
		}else if( (this.apellido.trim().length() < 2) || (this.apellido.trim().length() > 50)){
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de validacion",  "El apellido debe contener entre 2 y 50 caracteres.");
			context.addMessage( "El apellido debe contener entre 2 y 50 caracteres.", message);
			context.getExternalContext().getFlash().setKeepMessages(true);
			return " ";
		}
		
		if(this.direccion.trim().equals("")) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de validacion", "La direccion debe contener entre 2 y 50 caracteres.");
			context.addMessage( "La direccion debe contener entre 2 y 50 caracteres." , message);
			context.getExternalContext().getFlash().setKeepMessages(true);
			return " ";
		}else if( (this.direccion.trim().length() < 2) || (this.direccion.trim().length() > 50)){
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de validacion",  "La direccion debe contener entre 2 y 50 caracteres.");
			context.addMessage( "La direccion debe contener entre 2 y 50 caracteres.", message);
			context.getExternalContext().getFlash().setKeepMessages(true);
			return " ";
		}
		
		if(this.documento.trim().equals("")) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de validacion", "El documento debe contener entre 2 y 50 caracteres.");
			context.addMessage( "El documento debe contener entre 2 y 50 caracteres." , message);
			context.getExternalContext().getFlash().setKeepMessages(true);
			return " ";
		}else if( (this.documento.trim().length() < 2) || (this.documento.trim().length() > 50)){
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de validacion",  "El documento debe contener entre 2 y 50 caracteres.");
			context.addMessage( "El documento debe contener entre 2 y 50 caracteres.", message);
			context.getExternalContext().getFlash().setKeepMessages(true);
			return " ";
		}
		
		
		try {
			
			UsuarioDTO usuario = new UsuarioDTO();
			usuario.setId_usuario(this.getId_usuario());
			usuario.setEmail(this.getEmail());
			usuario.setNombre(this.getNombre());
			usuario.setApellido(this.getApellido());
			usuario.setDireccion(this.getDireccion());
			usuario.setDocumento(this.getDocumento());
			usuario.setDocumentoCategoria(this.getDocumentoCategoria());
			usuario.setEstadoUsuario(this.getEstadoUsuario());
			usuario.setRol(this.getRolUsuairo());
			UsuarioDTO us = usuarioEJB.obtenerUsuarioId(this.getId_usuario());
			usuario.setPasswd(us.getPasswd());					
			usuario.setUsername(us.getUsername());
				
			
			
			
			usuarioEJB.actualizarUsuario(usuario);
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "EL Usuario fue actualizado", "OK");
			context.addMessage("", message);
			context.getExternalContext().getFlash().setKeepMessages(true);
			        
		} catch (ServiciosException | NoSuchAlgorithmException e) {
			
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), "ERROR");
			context.addMessage("", message);
			context.getExternalContext().getFlash().setKeepMessages(true);
	        return " ";
		}
		//POST-Redirect-GET 
		return "/pages/actualizarUsuario.xhtml?faces-redirect=true";
		
	}
}
