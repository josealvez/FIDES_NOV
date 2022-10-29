package com.negocio;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import com.daos.FenomenoDAO;
import com.dto.FenomenoDTO;
import com.entities.Fenomeno;
import com.exception.ServiciosException;

@LocalBean
@Stateless
public class GestionFenomenoBean implements IGestionFenomenoBean {

	@EJB
	FenomenoDAO fPersistencia;
	
	public GestionFenomenoBean() {
    }

   
    private Fenomeno prepararFenomeno(FenomenoDTO fenomenoDTO) throws ServiciosException {
    	
    	Fenomeno fenomenoAdd = new Fenomeno();
    	
    	if(fenomenoDTO.getId_fenomeno() != null ) fenomenoAdd.setId_fenomeno(fenomenoDTO.getId_fenomeno());
		fenomenoAdd.setCodigo(fenomenoDTO.getCodigo());
		fenomenoAdd.setNombre(fenomenoDTO.getNombre());
    	fenomenoAdd.setDescripcion(fenomenoDTO.getDescripcion());
    	fenomenoAdd.setTelefono(fenomenoDTO.getTelefono());
			
		
		return fenomenoAdd;
    }
    
    
    private List<FenomenoDTO> prepararTodosLosFenomenos() throws ServiciosException{
    	
    	List<Fenomeno> feno = fPersistencia.obtenerTodos();
		List<FenomenoDTO> fenoDTO= new ArrayList<FenomenoDTO>();
		

		for(Fenomeno f : feno) {			
			FenomenoDTO fenDTO = new FenomenoDTO();
			fenDTO.setCodigo(f.getCodigo());
			fenDTO.setNombre(f.getNombre());
			fenDTO.setDescripcion(f.getDescripcion());
			fenDTO.setTelefono(f.getTelefono());
			
			fenoDTO.add(fenDTO);
		}
		return fenoDTO;	
    }

    
    private FenomenoDTO prepararFenomenoNombre(String nombre) throws ServiciosException{
    	
    	FenomenoDTO fdto = new FenomenoDTO();
    	
    	if(!fPersistencia.obtenerPorNombre(nombre).isEmpty()) {
    		Fenomeno f = fPersistencia.obtenerPorNombre(nombre).get(0);
    		fdto.setId_fenomeno(f.getId_fenomeno());
    		fdto.setCodigo(f.getCodigo());
    		fdto.setDescripcion(f.getDescripcion());
    		fdto.setNombre(f.getNombre());
    		fdto.setTelefono(f.getTelefono());
    		return fdto;
    	}
    	return null;
    }
    
    
    /*
     * Servicios para la REST y JSF
     */
    
	@Override
	public void agregarFenomeno(FenomenoDTO fenomenoDTO) throws  ServiciosException  {		
		fPersistencia.altaFenomeno(this.prepararFenomeno(fenomenoDTO));
	}

	
	
	@Override
	public void modificarFenomeno(FenomenoDTO fenomenoDTO) throws  ServiciosException  {
		fPersistencia.modificarFenomeno(this.prepararFenomeno(fenomenoDTO));
	}

	@Override
	public FenomenoDTO obtenerFenomenoNombre(String nombre) throws  ServiciosException{
		return this.prepararFenomenoNombre(nombre);
	};
	
	@Override
	public List<FenomenoDTO> obtenerFenomenos() throws ServiciosException {
		List<FenomenoDTO> fs = this.prepararTodosLosFenomenos();
		return fs;
	}





	
}
