package com.controlador;

import java.io.Serializable;


import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;



import com.dto.ObservacionDTO;
import com.dto.UsuarioDTO;
import com.exception.ServiciosException;
import com.negocio.GestionObservacionBean;

@Named("detallebservacion")
@SessionScoped
public class DetalleObservacion implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	private GestionObservacionBean gObservacion;
	
	
	@Inject
	CrearObservacion obsMBean;
	
	private boolean validar;
	
	private Long id;

    
	public void initConversation(){
		FacesContext fc = FacesContext.getCurrentInstance();
		String obsId= fc.getExternalContext().getRequestParameterMap().get("obsID");
		this.id = Long.parseLong(obsId);
		
		
		try {
			ObservacionDTO oDTO = gObservacion.obtenerObservacionPorId(id);
			obsMBean.setCategoriaFenomeno(oDTO.getCategoriaFenomeno());
			obsMBean.setDescripcion(oDTO.getDescripcion());
			obsMBean.setFecha(oDTO.getFecha());
			obsMBean.setDepartamento(oDTO.getDepartamento());
			obsMBean.setLocalidad(oDTO.getLocalidad());
			obsMBean.setLatitud(oDTO.getLatitud());
			obsMBean.setLongitud(oDTO.getLongitud());
			obsMBean.setImagen(oDTO.getImagen());
			obsMBean.setEmailVoluntario(oDTO.getEmailAficionado());
			this.setValidar(oDTO.isValidarInvestigador());

		} catch (ServiciosException e) {
			
			e.printStackTrace();
		}
	}
	

	public String validarObservacion() {
		UsuarioDTO u = (UsuarioDTO) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
		
		ObservacionDTO ob = new ObservacionDTO();
		System.out.println("ACA ESTA EL ID: " + this.id);
		ob.setId_observacion(this.id);
		ob.setEmailAficionado(u.getEmail());
		ob.setValidarInvestigador(true);
		
		try {
			gObservacion.actualizarObservacion(ob);
		
		} catch (ServiciosException e) {
			
			
	        return " ";
		}
		//POST-Redirect-GET 
		return "/pages/listarValidarObservacion.xhtml?faces-redirect=true?i=1";
	}
	
	
	public CrearObservacion getObsMBean() {
		return obsMBean;
	}


	public void setObsMBean(CrearObservacion obsMBean) {
		this.obsMBean = obsMBean;
	}


	public boolean isValidar() {
		return validar;
	}


	public void setValidar(boolean validar) {
		this.validar = validar;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	
}
