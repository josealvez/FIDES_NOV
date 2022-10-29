package com.controlador;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import org.primefaces.event.map.MarkerDragEvent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;
import com.dto.FenomenoDTO;
import com.dto.LocalidadDTO;
import com.dto.ObservacionDTO;
import com.dto.UsuarioDTO;
import com.enumerados.EnumNombreDepartamento;
import com.exception.ServiciosException;
import com.negocio.GestionFenomenoBean;
import com.negocio.GestionObservacionBean;
import javax.servlet.http.Part;
import org.omnifaces.util.Utils;




@Named("crearobservacion")
@SessionScoped
public class CrearObservacion implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@EJB
	private GestionFenomenoBean gFenomeno;
	
	@EJB
	private GestionObservacionBean gObservacion;
	
	
	
	private String emailVoluntario;
	
	private Date fecha;
	
	private String descripcion;
	
	private String categoriaFenomeno;
	
	private String localidad;
	
	private double latitud;
	
	private double longitud;
	
	private String departamento;
	
	private byte[] imagen;

	private Part fileImage;
	
	private MapModel draggableModel;
    
    private Marker marker;
	

    @PostConstruct
    public void init() {
        draggableModel = new DefaultMapModel();
        LatLng coord1 = new LatLng(-34.754703, -56.326882);
        draggableModel.addOverlay(new Marker(coord1, "Observacion"));
  
        for(Marker premarker : draggableModel.getMarkers()) {
            premarker.setDraggable(true);
        }
    }
    
    public MapModel getDraggableModel() {
        return draggableModel;
    }
      
    public void onMarkerDrag(MarkerDragEvent event) {
        marker = event.getMarker();
         
        this.longitud = marker.getLatlng().getLng();
        this.latitud = marker.getLatlng().getLat();
        
		FacesMessage message = new FacesMessage(
				FacesMessage.SEVERITY_INFO, "Coordenadas creadas en: ", "Lat:" + marker.getLatlng().getLat() + ", Lng:" + marker.getLatlng().getLng());

        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
    
    
	@SuppressWarnings("unused")
	private EnumNombreDepartamento enDpto;
	
	@SuppressWarnings("unused")
	private List<String> localidadesValues;
	
	@SuppressWarnings("unused")
	private List<String> fenomenosValues;
	
	
	private List<String> caracteristicasValues;

	public String getEmailVoluntario() {
		return emailVoluntario;
	}

	public void setEmailVoluntario(String emailVoluntario) {
		this.emailVoluntario = emailVoluntario;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getCategoriaFenomeno() {
		return categoriaFenomeno;
	}

	public void setCategoriaFenomeno(String categoriaFenomeno) {
		this.categoriaFenomeno = categoriaFenomeno;
	}

	public String getLocalidad() {
		return localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	public double getLatitud() {
		return latitud;
	}

	public void setLatitud(double latitud) {
		this.latitud = latitud;
	}

	public double getLongitud() {
		return longitud;
	}

	public void setLongitud(double longitud) {
		this.longitud = longitud;
	}

	public String getDepartamento() {
		return departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

	public byte[] getImagen() {
		return imagen;
	}

	public void setImagen(byte[] imagen) {
		this.imagen = imagen;
	}

	public List<String> getLocalidadesValues() throws ServiciosException {
		List<String> localidadesNombre = new ArrayList<String>();
		for(LocalidadDTO loc : gObservacion.obtenerLocalidadesPorDepartamento(this.departamento)) {
			localidadesNombre.add(loc.getNombre());
		}
		return localidadesNombre;
	}

	public void setLocalidadesValues(List<String> localidadesValues) {
		this.localidadesValues = localidadesValues;
	}

	public List<String> getFenomenosValues() throws ServiciosException {
		List<String> fenomenosNombre = new ArrayList<String>();
		for(FenomenoDTO fem : gFenomeno.obtenerFenomenos()) {
			fenomenosNombre.add(fem.getNombre());
		}
		return fenomenosNombre;
	}

	public void setFenomenosValues(List<String> fenomenosValues) {
		this.fenomenosValues = fenomenosValues;
	}

	public List<String> getCaracteristicasValues() {
		return caracteristicasValues;
	}

	public void setCaracteristicasValues(List<String> caracteristicasValues) {
		this.caracteristicasValues = caracteristicasValues;
	}


	public EnumNombreDepartamento[] getEnDpto() {
		return EnumNombreDepartamento.values();
	}


	public Part getFileImage() {
		return fileImage;
	}

	public void setFileImage(Part fileImage) {
		this.fileImage = fileImage;
	}

	public String crearObservacion() {
		
		UsuarioDTO u = (UsuarioDTO) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
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
			
			
			ObservacionDTO ob = new ObservacionDTO();
			ob.setCategoriaFenomeno(this.categoriaFenomeno);
			ob.setDescripcion(this.descripcion);
			ob.setFecha(this.fecha);
			ob.setLocalidad(this.localidad);
			ob.setDepartamento(this.departamento);
			ob.setEmailAficionado(u.getEmail());
			ob.setLatitud(this.latitud);
			ob.setLongitud(this.longitud);
			
			ob.setImagen(Utils.toByteArray(fileImage.getInputStream()));
			gObservacion.agregarObservacion(ob);
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "La observacion fue creada", "OK");
			context.addMessage("", message);
			context.getExternalContext().getFlash().setKeepMessages(true);
		} catch (ServiciosException | IOException e) {
			
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), "ERROR");
			context.addMessage("", message);
			context.getExternalContext().getFlash().setKeepMessages(true);
	        return " ";
		}
		//POST-Redirect-GET 
				return "/pages/altaFenomeno.xhtml?faces-redirect=true?i=1";
	}
	
}
