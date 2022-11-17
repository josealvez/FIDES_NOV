package com.negocio;
import java.io.IOException;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.naming.NamingException;

import com.daos.DocumentoCategoriaDAO;
import com.daos.EstadoUsuarioDAO;
import com.daos.TipoUsuarioDAO;
import com.daos.UsuarioDAO;
import com.dto.UsuarioDTO;
import com.entities.DocumentoCategoria;
import com.entities.EstadoUsuario;
import com.entities.TipoUsuario;
import com.entities.Usuario;
import com.enumerados.EnumCategoriaDocumento;
import com.enumerados.EnumCategoriaUsuario;
import com.enumerados.EnumEstadoUsuario;
import com.exception.ServiciosException;
import com.ldap.ActiveDirectoryConnect;

@LocalBean
@Stateless
public class GestionUsuarioBean implements IGestionUsuarioBean {

	@EJB
	UsuarioDAO uPersistencia;
	
	@EJB
	DocumentoCategoriaDAO dcPersistencia;
	
	@EJB
	TipoUsuarioDAO tuPersistencia;
	
	@EJB
	EstadoUsuarioDAO euPersistencia;
   
	
    public GestionUsuarioBean() {
    }

   
    private Usuario prepararUsuario(UsuarioDTO usuarioDTO) throws ServiciosException, NoSuchAlgorithmException {
    	
    	Usuario usuarioAdd = new Usuario();
    	
    	if(usuarioDTO.getId_usuario() != null ) usuarioAdd.setId_usuario(usuarioDTO.getId_usuario());
		usuarioAdd.setNombre(usuarioDTO.getNombre());
		usuarioAdd.setApellido(usuarioDTO.getApellido());
		usuarioAdd.setDireccion(usuarioDTO.getDireccion());
		usuarioAdd.setDocumento(usuarioDTO.getDocumento());
		usuarioAdd.setEmail(usuarioDTO.getEmail());
		usuarioAdd.setUsername(usuarioDTO.getUsername());
		usuarioAdd.setPasswd(usuarioDTO.getPasswd());
		
		for (EnumCategoriaDocumento d : EnumCategoriaDocumento.values()) {
			if(d.name().equals(usuarioDTO.getDocumentoCategoria())) {
				List<DocumentoCategoria> dc = dcPersistencia.obtenerPorCategoria(d);
				usuarioAdd.setDoc(dc.get(0));
				break;
			}
		}	
		
		for (EnumCategoriaUsuario c : EnumCategoriaUsuario.values()) {
			if(c.name().equals(usuarioDTO.getRol())) {
				List<TipoUsuario> tu = tuPersistencia.obtenerPorRol(c);
				usuarioAdd.setRol(tu.get(0));
				break;
			}
		}
		
		for(EnumEstadoUsuario e : EnumEstadoUsuario.values()) {
			if(e.name().equals(usuarioDTO.getEstadoUsuario())) {
				List<EstadoUsuario> eu = euPersistencia.obtenerPorEstado(e);
				usuarioAdd.setEstado(eu.get(0));
				break;
			}
		}
		
		return usuarioAdd;
    }
    
    
    private List<UsuarioDTO> prepararTodosLosUsuarios() throws ServiciosException{
    	
    	List<Usuario> us = uPersistencia.obtenerTodosLosUsuarios();
		List<UsuarioDTO> usDTO= new ArrayList<UsuarioDTO>();
		

		for(Usuario u : us) {			
			UsuarioDTO uDTO = new UsuarioDTO();
			uDTO.setNombre(u.getNombre());
			uDTO.setUsername(u.getUsername());
			uDTO.setApellido(u.getApellido());
			uDTO.setDireccion(u.getDireccion());
			uDTO.setDocumento(u.getDocumento());
			uDTO.setEmail(u.getEmail());
			uDTO.setEstadoUsuario(u.getEstado().getEstado_valor().name());
			uDTO.setDocumentoCategoria(u.getDoc().getCategoria_nombre().name());
			uDTO.setRol(u.getRol().getRol().name());
			uDTO.setId_usuario(u.getId_usuario());
			uDTO.setPasswd(u.getPasswd());
			
			usDTO.add(uDTO);
		}
		return usDTO;	
    }
    
    private UsuarioDTO prepararUsuarioEmail(String email) throws ServiciosException {
		List <Usuario> us = uPersistencia.obtenerPorEmail(email);
		UsuarioDTO uDTO = new UsuarioDTO();
		
		if(!us.isEmpty()){
			uDTO.setId_usuario(us.get(0).getId_usuario());
	    	uDTO.setNombre(us.get(0).getNombre());
	    	uDTO.setApellido(us.get(0).getApellido());
	    	uDTO.setUsername(us.get(0).getUsername());
	    	uDTO.setEmail(us.get(0).getEmail());
	    	uDTO.setRol(us.get(0).getRol().getRol().name());
	    	uDTO.setDocumentoCategoria(us.get(0).getDoc().getCategoria_nombre().name());
	    	uDTO.setDocumento(us.get(0).getDocumento());
	    	uDTO.setEstadoUsuario(us.get(0).getEstado().getEstado_valor().name());
	    	uDTO.setDireccion(us.get(0).getDireccion());
	    	uDTO.setPasswd(us.get(0).getPasswd());
		}
    	
    	return uDTO;
    }
    
    private UsuarioDTO prepararUsuarioId(Long id) throws ServiciosException {
		List <Usuario> us = uPersistencia.obtenerPorId(id);
		UsuarioDTO uDTO = new UsuarioDTO();
		
		if(!us.isEmpty()){
			uDTO.setId_usuario(us.get(0).getId_usuario());
	    	uDTO.setNombre(us.get(0).getNombre());
	    	uDTO.setApellido(us.get(0).getApellido());
	    	uDTO.setUsername(us.get(0).getUsername());
	    	uDTO.setEmail(us.get(0).getEmail());
	    	uDTO.setRol(us.get(0).getRol().getRol().name());
	    	uDTO.setDocumentoCategoria(us.get(0).getDoc().getCategoria_nombre().name());
	    	uDTO.setDocumento(us.get(0).getDocumento());
	    	uDTO.setEstadoUsuario(us.get(0).getEstado().getEstado_valor().name());
	    	uDTO.setDireccion(us.get(0).getDireccion());
	    	uDTO.setPasswd(us.get(0).getPasswd());
		}
    	
    	return uDTO;
    }
    
    private Usuario prepararBajaLogicaUsuario(String email) throws ServiciosException {
    	Usuario us = uPersistencia.obtenerPorEmail(email).get(0);
    	EstadoUsuario es = euPersistencia.obtenerPorEstado(EnumEstadoUsuario.DESHABILITADO).get(0);
    	us.setEstado(es);
		return us;
    	
    }
    
    private UsuarioDTO prepararValidarUsuario(String email, String password) throws ServiciosException, NoSuchAlgorithmException {
    	
    	UsuarioDTO udto = new UsuarioDTO();
//    	Md5Encrypt enpass = new Md5Encrypt(password);@Override
//    	Usuario usr = uPersistencia.validarUsuario(email, enpass.getEncryptedPass());
    	Usuario usr = uPersistencia.validarUsuario(email, password);
    	
    	if ( usr != null) {
    		udto.setNombre(usr.getNombre());
    		udto.setUsername(usr.getUsername());
    		udto.setDireccion(usr.getDireccion());
    		udto.setApellido(usr.getApellido());
    		udto.setEmail(usr.getEmail());
    		udto.setRol(usr.getRol().getRol().toString());
    		udto.setEstadoUsuario(usr.getEstado().getEstado_valor().toString());
    		return udto;
    	}else {
    		return null;
    	}
    	
    }
    
    private void prepararValidarLDAP(String user, String token) throws IOException, ServiciosException, NamingException{
    	ActiveDirectoryConnect adc = new ActiveDirectoryConnect();
    	adc.userAutenticar(user, token);
    }
    
    
    /*
     * Servicios para la REST y JSF
     */
    
	@Override
	public void agregarUsuario(UsuarioDTO usuarioDTO) throws  ServiciosException, NoSuchAlgorithmException  {		
		uPersistencia.altaUsuario(this.prepararUsuario(usuarioDTO));
		
	}

	
	
	@Override
	public void actualizarUsuario(UsuarioDTO usuarioDTO) throws NoSuchAlgorithmException, ServiciosException  {
		uPersistencia.modificarUsuario(this.prepararUsuario(usuarioDTO));
	}

	@Override
	public void bajaLogicaUsuario(String email) throws  ServiciosException {
		uPersistencia.bajaUsuario(this.prepararBajaLogicaUsuario(email));
	}
	
	@Override
	public List<UsuarioDTO> obtenerUsuarios() throws ServiciosException {
		List<UsuarioDTO> us = this.prepararTodosLosUsuarios();
		return us;
	}


	@Override
	public UsuarioDTO obtenerUsuarioEmail(String email) throws ServiciosException {
		return prepararUsuarioEmail(email);
		
	}
	@Override 
	public UsuarioDTO obtenerUsuarioId(Long id) throws ServiciosException {
		return prepararUsuarioId(id);
		
	} 
	
	@Override
	public UsuarioDTO validarUsuario(String email, String password) throws ServiciosException, NoSuchAlgorithmException{
		UsuarioDTO udto = prepararValidarUsuario(email, password);
		if(udto != null && udto.getEstadoUsuario().equals(EnumEstadoUsuario.HABILITADO.toString())) {
			return udto;
		}else {
			return null;
		}
	}
	
	@Override
	public void validarLDAP(String user, String token) throws NamingException, IOException, ServiciosException{
		prepararValidarLDAP(user, token);
	}
}
