package com.negocio;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.daos.CasillaDAO;
import com.daos.FormularioDAO;
import com.dto.CasillaDTO;
import com.entities.Casilla;
import com.enumerados.EnumTipoDato;
import com.exception.ServiciosException;

/**
 * Session Bean implementation class GestionCasillaBean
 */
@LocalBean
@Stateless
public class GestionCasillaBean implements lGestionCasillaBean {

	@EJB
	CasillaDAO fPersistencia;
	
	@EJB
	FormularioDAO forPersistencia;
	
    public GestionCasillaBean() {
    }

private Casilla prepararCasilla(CasillaDTO casillaDTO) throws ServiciosException{
    	
    	Casilla casillaAdd = new Casilla();
    	
    	if(casillaDTO.getId_casilla() != null ) casillaAdd.setId_casilla(casillaDTO.getId_casilla());
		casillaAdd.setParametro(casillaDTO.getParametro());
		
		for(EnumTipoDato td:EnumTipoDato.values()) {
			if(td.toString().equalsIgnoreCase(casillaDTO.getTipoDato())) {
				casillaAdd.setTipoDato(td);
				break;
			}
		}
		casillaAdd.setUnidadMedida(casillaDTO.getUnidadMedida());
		casillaAdd.setNombre(casillaDTO.getNombre());
    	casillaAdd.setDescripcion(casillaDTO.getDescripcion());
    	casillaAdd.setLugarubicacion(casillaDTO.getLugarubicacion());
    	casillaAdd.setContienes(casillaDTO.getContienes());
		
		return casillaAdd;
    }
    
    
    private List<CasillaDTO> prepararTodasLasCasillas() throws ServiciosException{
    	
    	List<Casilla> casi = fPersistencia.obtenerTodos();
		List<CasillaDTO> casillasDTO = new ArrayList<CasillaDTO>();
		

		for(Casilla f : casi) {			
			CasillaDTO casDTO = new CasillaDTO();

			casDTO.setId_casilla(f.getId_casilla());
			casDTO.setParametro(f.getParametro());
			casDTO.setTipoDato(f.getTipoDato().name());
			casDTO.setUnidadMedida(f.getUnidadMedida());
			casDTO.setNombre(f.getNombre());
			casDTO.setDescripcion(f.getDescripcion());
			casDTO.setLugarubicacion(f.getLugarubicacion());
			casDTO.setContienes(f.getContienes());
	    	
			casillasDTO.add(casDTO);
		}
		return casillasDTO;	
    }
    
	private List<CasillaDTO> prepararCasillasPorFormulario(long pk) throws ServiciosException{
	    	
	    	List<Casilla> casi = fPersistencia.obtenerCasillasPorFormulario(pk);
			List<CasillaDTO> casillasDTO = new ArrayList<CasillaDTO>();
			
	
			for(Casilla f : casi) {			
				CasillaDTO casDTO = new CasillaDTO();
	
				casDTO.setId_casilla(f.getId_casilla());
				casDTO.setParametro(f.getParametro());
				casDTO.setTipoDato(f.getTipoDato().name());
				casDTO.setUnidadMedida(f.getUnidadMedida());
				casDTO.setNombre(f.getNombre());
				casDTO.setDescripcion(f.getDescripcion());
				casDTO.setLugarubicacion(f.getLugarubicacion());
				casDTO.setContienes(f.getContienes());
		    	
				casillasDTO.add(casDTO);
			}
			return casillasDTO;	
	    }

    
    private CasillaDTO prepararCasillaNombre(String nombre) throws ServiciosException{
    	
    	CasillaDTO cdto = new CasillaDTO();
    	
    	if(!fPersistencia.obtenerPorNombre(nombre).isEmpty()) {
    		Casilla f = fPersistencia.obtenerPorNombre(nombre).get(0);
    		cdto.setId_casilla(f.getId_casilla());
    		cdto.setParametro(f.getParametro());
    		cdto.setTipoDato(f.getTipoDato().name());
    		cdto.setUnidadMedida(f.getUnidadMedida());
    		cdto.setNombre(f.getNombre());
    		cdto.setDescripcion(f.getDescripcion());
    		cdto.setLugarubicacion(f.getLugarubicacion());
    		cdto.setContienes(f.getContienes());
			
    		return cdto;
    	}
    	return null;
    }
    
    
    /*
     * Servicios para la REST y JSF
     */
    
	@Override
	public void agregarCasilla(CasillaDTO casillaDTO) throws  ServiciosException  {		
		fPersistencia.altaCasilla(this.prepararCasilla(casillaDTO));
	}

	
	@Override
	public void modificarCasilla(CasillaDTO casillaDTO) throws  ServiciosException  {
		fPersistencia.modificarCasilla(this.prepararCasilla(casillaDTO));
	}

	@Override
	public CasillaDTO obtenerCasillaNombre(String nombre) throws  ServiciosException{
		return this.prepararCasillaNombre(nombre);
	};

	@Override
	public List<CasillaDTO> obtenerCasillas() throws ServiciosException {
		List<CasillaDTO> fs = this.prepararTodasLasCasillas();
		return fs;
	}
	
	@Override
	public List<CasillaDTO> obtenerCasillasPorFormulario(long pk) throws ServiciosException {
		List<CasillaDTO> fs = this.prepararCasillasPorFormulario(pk);
		return fs;
	}

}
