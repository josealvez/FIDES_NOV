package com.negocio;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.daos.EstacionMedicionDAO;
import com.dto.EstacionMedicionDTO;
import com.entities.EstacionMedicion;
import com.exception.ServiciosException;

/**
 * Session Bean implementation class GestionEstacionMedicion
 */
@Stateless
@LocalBean
public class GestionEstacionMedicion implements IGestionEstacionMedicion {

	@EJB
	EstacionMedicionDAO emPersistencia;
	
    public GestionEstacionMedicion() {
        // TODO Auto-generated constructor stub
    }

	private EstacionMedicion prepararEM(EstacionMedicionDTO estacionMedicionDTO) throws ServiciosException{
		
		EstacionMedicion em = new EstacionMedicion();

    	if(estacionMedicionDTO.getId_estacionmedicion() != null ) em.setId_estacionmedicion(estacionMedicionDTO.getId_estacionmedicion());
		em.setCiudad(estacionMedicionDTO.getCiudad());
		em.setDepartamento(estacionMedicionDTO.getDepartamento());
		em.setCiudad(estacionMedicionDTO.getCiudad());
		em.setDescripcion(estacionMedicionDTO.getDescripcion());
		em.setNombre(estacionMedicionDTO.getNombre());
		em.setParametro(estacionMedicionDTO.getParametro());
		em.setTipoDato(estacionMedicionDTO.getTipoDato());
		em.setUnidadMedida(estacionMedicionDTO.getUnidadMedida());
		em.setUsuario(estacionMedicionDTO.getUsuario());

		return em;		
	}
	
	private List<EstacionMedicionDTO> prepararTodasLasEM() throws ServiciosException{
		
		List<EstacionMedicion> emList = emPersistencia.obtenerTodasLasEM();
	
		List<EstacionMedicionDTO> emlDTO = new ArrayList<EstacionMedicionDTO>();
		
		for(EstacionMedicion em : emList) {
			EstacionMedicionDTO emDTO = new EstacionMedicionDTO();
			emDTO.setDescripcion(em.getDescripcion());
			emDTO.setCiudad(em.getCiudad());
			emDTO.setDepartamento(em.getDepartamento());
			emDTO.setNombre(em.getNombre());
			emDTO.setParametro(em.getParametro());
			emDTO.setTipoDato(em.getTipoDato());
			emDTO.setUnidadMedida(em.getUnidadMedida());
			emDTO.setUsuario(em.getUsuario());
			emlDTO.add(emDTO);
		}
		return emlDTO;
	
	}
	
	private EstacionMedicionDTO prepararEstacionNombre(String nombre) throws ServiciosException{
    	
		EstacionMedicionDTO emDTO = new EstacionMedicionDTO();
    	
    	if(!emPersistencia.obtenerPorNombre(nombre).isEmpty()) {
    		EstacionMedicion em = emPersistencia.obtenerPorNombre(nombre).get(0);
    		emDTO.setCiudad(em.getCiudad());
    		emDTO.setDepartamento(em.getDepartamento());
    		emDTO.setDescripcion(em.getDescripcion());
    		emDTO.setId_estacionmedicion(em.getId_estacionmedicion());
    		emDTO.setNombre(em.getNombre());
    		emDTO.setParametro(em.getParametro());
    		emDTO.setTipoDato(em.getTipoDato());
    		emDTO.setUnidadMedida(em.getUnidadMedida());
    		emDTO.setUsuario(em.getUsuario());
    		return emDTO;
    	}
    	return null;
    }
	
	@Override
	public void agregarEM(EstacionMedicionDTO emDTO) throws ServiciosException {
		emPersistencia.altaEM(this.prepararEM(emDTO));
	}

	@Override
	public void actualizarEM(EstacionMedicionDTO emDTO) throws ServiciosException {
		emPersistencia.modificarEM(this.prepararEM(emDTO));
	}
	
	@Override
	public List<EstacionMedicionDTO> obtenerTodasEM() throws ServiciosException {
		List<EstacionMedicionDTO> em = this.prepararTodasLasEM();
		return em;
	}
	
	@Override
	public EstacionMedicionDTO obtenerEstacionNombre(String nombre) throws  ServiciosException{
		return this.prepararEstacionNombre(nombre);
	}
	
	private EstacionMedicion prepararBajaLogicaEM(String nombre) throws ServiciosException {
		EstacionMedicion em = emPersistencia.obtenerPorNombre(nombre).get(0);
		return em;
    }
	
	@Override
	public void bajaLogicaEM(String nombre) throws  ServiciosException {
		emPersistencia.bajaEM(this.prepararBajaLogicaEM(nombre));
	}
}
