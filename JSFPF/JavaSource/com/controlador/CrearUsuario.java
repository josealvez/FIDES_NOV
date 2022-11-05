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
import java.util.ArrayList;
import java.util.List;
import com.dto.UsuarioDTO;
import com.enumerados.EnumCategoriaDocumento;
import com.enumerados.EnumCategoriaUsuario;
import com.enumerados.EnumEstadoUsuario;
import com.exception.ServiciosException;
import com.negocio.GestionUsuarioBean;



@Named("crearusuario")
@ConversationScoped	
public class CrearUsuario implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@EJB
	GestionUsuarioBean usuarioEJB;
	
	@Size(min=2,max=50, message = "El nombre debe contener entre 2 y 50 caracteres.")
	String nombre;
	
	@Size(min=2,max=50, message = "El apellido debe contener entre 2 y 50 caracteres.")
	String apellido;
	
	@Size(min=2,max=50, message = "El domicilio debe contener entre 2 y 50 caracteres.")
	String direccion;

	String documentoCategoria;
	
	@Size(min=2,max=50, message = "El documento debe contener entre 2 y 50 caracteres.")
	String documento;
	
	@Email(message = "El formato del correo es incorrecto")
	String email;
	
	String estadoUsuario;
	
	String rolUsuairo;
	
	@Size(min=2,max=50, message = "El password debe contener entre 2 y 50 caracteres.")
	String password;
	
	@Size(min=2,max=50, message = "El nombre de usuario debe contener entre 2 y 50 caracteres.")
	String userName;
	
	

	@SuppressWarnings("unused")
	List<String> dcEnum;
	

	@SuppressWarnings("unused")
	EnumCategoriaUsuario tuEnum;
	

	@SuppressWarnings("unused")
	EnumEstadoUsuario euEnum;
	
	
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
	
	public String getDocumentoCategoria() {
		return documentoCategoria;
	}
	public void setDocumentoCategoria(String documentoCategoria) {
		this.documentoCategoria = documentoCategoria;
	}
	

	public List<String> getDcEnum() {
		List<String> dc = new ArrayList<String>();
		for(EnumCategoriaDocumento a:EnumCategoriaDocumento.values()) {
			dc.add(a.toString());
		}
		return dc;
	}
		
	public EnumCategoriaUsuario[] getTuEnum() {
		return EnumCategoriaUsuario.values();
	}
	
	public EnumEstadoUsuario[] getEuEnum() {
		return EnumEstadoUsuario.values();
	}
	
	public String crearUsuario() throws NoSuchAlgorithmException {
		
		FacesContext context = FacesContext.getCurrentInstance();
		
		if(this.password.trim().equals("")) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de validación",  "El password debe contener entre 2 y 50 caracteres." );
			context.addMessage( "El password debe contener entre 2 y 50 caracteres." , message);
			context.getExternalContext().getFlash().setKeepMessages(true);
			return " ";
		}else if( (this.password.trim().length() < 2) || (this.password.trim().length() > 50)){
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de validación",  "El password debe contener entre 2 y 50 caracteres.");
			context.addMessage( "El password debe contener entre 2 y 50 caracteres.", message);
			context.getExternalContext().getFlash().setKeepMessages(true);
			return " ";
		}
		
		
		if(this.nombre.trim().equals("")) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de validación", "El nombre debe contener entre 2 y 50 caracteres.");
			context.addMessage( "El nombre debe contener entre 2 y 50 caracteres." , message);
			context.getExternalContext().getFlash().setKeepMessages(true);
			return " ";
		}else if( (this.nombre.trim().length() < 2) || (this.nombre.trim().length() > 50)){
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de validación",  "El nombre debe contener entre 2 y 50 caracteres.");
			context.addMessage( "El nombre debe contener entre 2 y 50 caracteres.", message);
			context.getExternalContext().getFlash().setKeepMessages(true);
			return " ";
		}
		
		if(this.apellido.trim().equals("")) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de validación", "El apellido debe contener entre 2 y 50 caracteres.");
			context.addMessage( "El apellido debe contener entre 2 y 50 caracteres." , message);
			context.getExternalContext().getFlash().setKeepMessages(true);
			return " ";
		}else if( (this.apellido.trim().length() < 2) || (this.apellido.trim().length() > 50)){
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de validación",  "El apellido debe contener entre 2 y 50 caracteres.");
			context.addMessage( "El apellido debe contener entre 2 y 50 caracteres.", message);
			context.getExternalContext().getFlash().setKeepMessages(true);
			return " ";
		}
		
		if(this.direccion.trim().equals("")) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de validación", "La dirección debe contener entre 2 y 50 caracteres.");
			context.addMessage( "La dirección debe contener entre 2 y 50 caracteres." , message);
			context.getExternalContext().getFlash().setKeepMessages(true);
			return " ";
		}else if( (this.direccion.trim().length() < 2) || (this.direccion.trim().length() > 50)){
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de validación",  "La dirección debe contener entre 2 y 50 caracteres.");
			context.addMessage( "La dirección debe contener entre 2 y 50 caracteres.", message);
			context.getExternalContext().getFlash().setKeepMessages(true);
			return " ";
		}
		
		if(this.userName.trim().equals("")) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de validación", "El userName debe contener entre 2 y 50 caracteres.");
			context.addMessage( "El userName debe contener entre 2 y 50 caracteres." , message);
			context.getExternalContext().getFlash().setKeepMessages(true);
			return " ";
		}else if( (this.userName.trim().length() < 2) || (this.userName.trim().length() > 50)){
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de validación",  "El userName debe contener entre 2 y 50 caracteres.");
			context.addMessage( "El userName debe contener entre 2 y 50 caracteres.", message);
			context.getExternalContext().getFlash().setKeepMessages(true);
			return " ";
		}
		
		if(this.documento.trim().equals("")) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de validación", "El documento debe contener entre 2 y 50 caracteres.");
			context.addMessage( "El documento debe contener entre 2 y 50 caracteres." , message);
			context.getExternalContext().getFlash().setKeepMessages(true);
			return " ";
		}else if( (this.documento.trim().length() < 2) || (this.documento.trim().length() > 50)){
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de validación",  "El documento debe contener entre 2 y 50 caracteres.");
			context.addMessage( "El documento debe contener entre 2 y 50 caracteres.", message);
			context.getExternalContext().getFlash().setKeepMessages(true);
			return " ";
		}
		
		try {
			
			UsuarioDTO usuario = new UsuarioDTO();
			
			usuario.setEmail(this.getEmail());
			usuario.setUsername(this.getUserName());
			usuario.setNombre(this.getNombre());
			usuario.setApellido(this.getApellido());
			usuario.setDireccion(this.getDireccion());
			usuario.setDocumento(this.getDocumento());
			usuario.setDocumentoCategoria(this.getDocumentoCategoria());
			usuario.setEstadoUsuario(this.getEstadoUsuario());
			usuario.setRol(this.getRolUsuairo());
			usuario.setPasswd(this.getPassword());
			
			usuarioEJB.agregarUsuario(usuario);
			
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "EL Usuario fue creado", "OK");
			context.addMessage("", message);
			context.getExternalContext().getFlash().setKeepMessages(true);
			
	      
	        
		} catch (ServiciosException e) {
			
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), "ERROR");
			context.addMessage("", message);
			context.getExternalContext().getFlash().setKeepMessages(true);
	        return " ";
			
			
		}
		
		//POST-Redirect-GET 
		return "/pages/crearUsuario.xhtml?faces-redirect=true?i=1";

	}
	

	
}
