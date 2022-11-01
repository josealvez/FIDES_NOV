package com.controlador;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import com.dto.CasillaDTO;
import com.dto.FormularioDTO;
import com.dto.UsuarioDTO;
import com.entities.Casilla;
import com.entities.Contiene;
import com.entities.Formulario;
import com.exception.ServiciosException;
import com.negocio.GestionCasillaBean;
import com.negocio.GestionFormularioBean;
import com.negocio.GestionRegistroBean;
import com.enumerados.EnumTipoDato;

@Named("crearregistro")
@SessionScoped
public class CrearRegistro implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	private GestionFormularioBean servicioFormulario;
	
	@EJB
	private GestionCasillaBean servicioCasilla;
	
	@EJB
	private GestionRegistroBean servicioRegistro;
	
	private List<Contiene> listacontienes = new ArrayList<Contiene>();
	
	private String nombre;
	
	private Date fechahora;
	
	private String descripcion;	
	
	public GestionFormularioBean getServicioFormulario() {
		return servicioFormulario;
	}

	public void setServicioFormulario(GestionFormularioBean servicioFormulario) {
		this.servicioFormulario = servicioFormulario;
	}

	public GestionRegistroBean getServicioRegistro() {
		return servicioRegistro;
	}

	public void setServicioRegistro(GestionRegistroBean servicioRegistro) {
		this.servicioRegistro = servicioRegistro;
	}

	public List<Contiene> getListacontienes() {
		this.initConversation();
		return listacontienes;
	}

	public void setListacontienes(List<Contiene> listacontienes) {
		this.listacontienes = listacontienes;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public Date getFechahora() {
		return fechahora;
	}

	public void setFechahora(Date fechahora) {
		this.fechahora = fechahora;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public List<String> getCasillasValues() throws ServiciosException {
			List<String> CasillasNombre = new ArrayList<String>();
			for(CasillaDTO cas : servicioCasilla.obtenerCasillas()) {
				CasillasNombre.add(cas.getNombre());
			}
			return CasillasNombre;
		}

	@PostConstruct
	public void initConversation(){
	    if (!FacesContext.getCurrentInstance().isPostback() ) {
	    	FacesContext fc = FacesContext.getCurrentInstance();	
			if(!fc.getExternalContext().getRequestParameterMap().isEmpty()) {
				try {	
				String idform = fc.getExternalContext().getRequestParameterMap().get("formID");
				FormularioDTO formDTO = servicioFormulario.obtenerFormularioPorId(Long.parseLong(idform));
				Formulario form = new Formulario();
				form.setId_formulario(formDTO.getId_formulario());
				List<Contiene>listaSinFiltro = servicioRegistro.ListarRegistrosPorFormulario(form);
				listacontienes.clear();
				for (Contiene con : listaSinFiltro) {
					if (!con.getIsRegistro()) {
						listacontienes.add(con);
					}
				}
				nombre = formDTO.getNombre();
				descripcion = formDTO.getDescripcion();
				} catch (ServiciosException e) {
					FacesContext context = FacesContext.getCurrentInstance();
					FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), "ERROR");
					context.addMessage("", message);
				}
			}
				
	    }
	}
	
	public String crearRegistro() {
			FacesContext context = FacesContext.getCurrentInstance();
		try {

			UsuarioDTO usr = (UsuarioDTO) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
			
			//cargo y subo los registros
			Long formID = listacontienes.get(0).getFormulario().getId_formulario();
			Formulario form = new Formulario();
			form.setId_formulario(formID);
			List<CasillaDTO> casList = servicioCasilla.obtenerCasillasPorFormulario(form);
			for (Contiene con : listacontienes) {
				for (CasillaDTO casDTO : casList) {
					Casilla cas = new Casilla();
					if (con.getCasilla().getId_casilla() == casDTO.getId_casilla().longValue()) {
						Contiene conNuevo = new Contiene();
						cas.setId_casilla(casDTO.getId_casilla());
						conNuevo.setFormulario(form);
						conNuevo.setCasilla(cas);
						conNuevo.setFecharegistro(this.fechahora);
						conNuevo.setRegistro(con.getRegistro());
						conNuevo.setUsuario(usr.getUsername());
						conNuevo.setIsRegistro(true);
						servicioRegistro.AltaRegistro(conNuevo);
					}
				}
			}
			
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "El registro fue ingresado", "OK");
			context.addMessage("", message);
			context.getExternalContext().getFlash().setKeepMessages(true);
		} catch (ServiciosException e) {
			
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), "ERROR");
			context.addMessage("", message);
			context.getExternalContext().getFlash().setKeepMessages(true);
	        return " ";
		}
		//POST-Redirect-GET 
				return "/pages/altaCasilla.xhtml?faces-redirect=true?i=1";
	}
}
