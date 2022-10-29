package com.controlador;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Named;
import com.dto.FenomenoDTO;
import com.exception.ServiciosException;
import com.negocio.GestionFenomenoBean;



@Named("listarfenomenos")
@ConversationScoped	
public class ListarFenomenos implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@EJB
	private GestionFenomenoBean servicioFenomeno;

	
	private List<FenomenoDTO> listaFenomenos = new ArrayList<FenomenoDTO>();
	private List<FenomenoDTO> filteredFenomeno;
	
	
	
	
	@PostConstruct
	public void init()  {
		try {
			listaFenomenos = servicioFenomeno.obtenerFenomenos();
		} catch (ServiciosException e) {
			e.printStackTrace();
		}
		for(FenomenoDTO f : listaFenomenos) {
			System.out.println("Este son los fenomenos: " + f.getNombre());
		}
	}
	
	public boolean globalFilterFunction(Object value, Object filter, Locale locale) {
        String filterText = (filter == null) ? null : filter.toString().trim().toLowerCase();
        if (filterText == null || filterText.equals("")) {
            return true;
        }
        
        FenomenoDTO fen = (FenomenoDTO) value;
        return fen.getCodigo().toLowerCase().contains(filterText)
                || fen.getNombre().toLowerCase().contains(filterText)
                || fen.getDescripcion().toLowerCase().contains(filterText)
                || fen.getTelefono().toLowerCase().contains(filterText);
    }
	

	public List<FenomenoDTO> getListaFenomenos() {
		return listaFenomenos;
	}
	public void setListaFenomenos(List<FenomenoDTO> listaFenomenos) {
		this.listaFenomenos = listaFenomenos;
	}

	public List<FenomenoDTO> getFilteredFenomeno() {
		return filteredFenomeno;
	}

	public void setFilteredFenomeno(List<FenomenoDTO> filteredFenomeno) {
		this.filteredFenomeno = filteredFenomeno;
	}
	
	
}
