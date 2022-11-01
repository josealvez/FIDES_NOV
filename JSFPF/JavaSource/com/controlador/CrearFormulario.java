package com.controlador;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
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

@Named("crearformulario")
@SessionScoped
public class CrearFormulario implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	private GestionFormularioBean gFormulario;
	
	@EJB
	private GestionCasillaBean gCasilla;
	
	@EJB
	private GestionRegistroBean gRegistro;
	
	private String nombre;
	
	private Date fechahora;
	
	private String descripcion;
	
	private List<String> casillas;
	
	private List<String> casillasValues;

	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public GestionFormularioBean getgFormulario() {
		return gFormulario;
	}

	public void setgFormulario(GestionFormularioBean gFormulario) {
		this.gFormulario = gFormulario;
	}

	public Date getFechahora() {
		return fechahora;
	}

	public void setFechahora(Date fechahora) {
		this.fechahora = fechahora;
	}

	public List<String> getCasillas() {
		return casillas;
	}

	public void setCasillas(List<String> casillas) {
		this.casillas = casillas;
	}

	public void setCasillasValues(List<String> casillasValues) {
		this.casillasValues = casillasValues;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public List<String> getCasillasValues() throws ServiciosException {
			List<String> CasillasNombre = new ArrayList<String>();
			for(CasillaDTO cas : gCasilla.obtenerCasillas()) {
				CasillasNombre.add(cas.getNombre());
			}
			return CasillasNombre;
		}

	public String crearFormulario() {
			FacesContext context = FacesContext.getCurrentInstance();
			
		try {
			if(this.descripcion.trim().equals("")) {
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de validación", "Descripción requerida");
				context.addMessage("Descripción requerida", message);
				context.getExternalContext().getFlash().setKeepMessages(true);
				return " ";
			}else if((this.descripcion.length() < 3) || (this.descripcion.length() > 50)){
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de validación", "Descripción tiene que ser mayor a 2 y menor a 50 caracteres");
				context.addMessage("Descripción tiene que ser mayor a 2 y menor a 50 caracteres", message);
				context.getExternalContext().getFlash().setKeepMessages(true);
				return " ";
			}
			
			//obtengo casillas que coinciden con las seleccionadas
			List<Casilla> casillasIncluidas = new ArrayList<Casilla>();
			for(CasillaDTO cas : gCasilla.obtenerCasillas()) {
				for(String nom : this.casillas) {
					if(nom.equalsIgnoreCase(cas.getNombre())) {
						Casilla casilla = new Casilla();
						casilla.setId_casilla(cas.getId_casilla());
						casilla.setDescripcion(cas.getDescripcion());
						casilla.setId_casilla(cas.getId_casilla());
						casilla.setNombre(cas.getNombre());
						casilla.setTipoDato(Enum.valueOf(EnumTipoDato.class, cas.getTipoDato()));
						casilla.setUnidadMedida(cas.getUnidadMedida());
						casillasIncluidas.add(casilla);
					}
				}
			}
			//subo el formulario
			UsuarioDTO usr = (UsuarioDTO) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
			FormularioDTO form = new FormularioDTO();
			form.setUsuario(usr.getUsername());
			form.setNombre(this.nombre);
			form.setDescripcion(this.descripcion);
			form.setFechahora(this.fechahora);
			long id = gFormulario.agregarFormulario(form); //se sube
			
			//subo los contenedores que conectan
			//obtengo el recien agregado
			form = gFormulario.obtenerFormularioPorId(id); 
			//convierto DTO a entity
			Formulario formE = new Formulario();
			formE.setDescripcion(form.getDescripcion());
			formE.setFechaHora(form.getFechahora());
			formE.setId_formulario(form.getId_formulario());
			formE.setNombre(form.getNombre());
			formE.setUsuario(form.getUsuario());
			
			//cargo y subo los registros
			for (Casilla casi : casillasIncluidas) {
				Contiene con = new Contiene();
				con.setFormulario(formE);
				con.setCasilla(casi);
				con.setFecharegistro(null);
				con.setIsRegistro(false);
				gRegistro.AltaRegistro(con);
			}
			
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "El formulario fue creado", "OK");
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
