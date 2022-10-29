package com.controlador;

import java.io.Serializable;


import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import com.dto.FormularioDTO;
import com.exception.ServiciosException;
import com.negocio.GestionFormularioBean;

@Named("detalleformulario")
@SessionScoped
public class DetalleFormulario implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	private GestionFormularioBean gFormulario;
	
	
	@Inject
	CrearFormulario frmBean;
	
	private Long id;

    
	public void initConversation(){
		FacesContext fc = FacesContext.getCurrentInstance();
		String frmId= fc.getExternalContext().getRequestParameterMap().get("frmID");
		this.id = Long.parseLong(frmId);
		try {
			FormularioDTO fDTO = gFormulario.obtenerFormularioPorId(id);
//			frmBean.setCasilla(fDTO.getCasillas());
			frmBean.setDescripcion(fDTO.getDescripcion());
			frmBean.setFechahora(fDTO.getFechahora());
			frmBean.setNombre(fDTO.getNombre());

		} catch (ServiciosException e) {
			e.printStackTrace();
		}
	}


	public GestionFormularioBean getgFormulario() {
		return gFormulario;
	}


	public void setgFormulario(GestionFormularioBean gFormulario) {
		this.gFormulario = gFormulario;
	}


	public CrearFormulario getFrmBean() {
		return frmBean;
	}


	public void setFrmBean(CrearFormulario frmBean) {
		this.frmBean = frmBean;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}
	
	
	
}
