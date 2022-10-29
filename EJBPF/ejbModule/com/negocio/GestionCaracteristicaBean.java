package com.negocio;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.daos.CaracteristicaDAO;
import com.daos.FenomenoDAO;
import com.dto.CaracteristicaDTO;
import com.entities.Caracteristica;
import com.enumerados.EnumTipoDatoCaracteristica;
import com.exception.ServiciosException;


@LocalBean
@Stateless
public class GestionCaracteristicaBean implements IGestionCaracteristicaBean {

	@EJB
	private CaracteristicaDAO cPersistencia;
	
	@EJB
	private FenomenoDAO fPersistencia;
	
	private Caracteristica prepararAgregarCaracteristica(CaracteristicaDTO crc) throws ServiciosException {
		Caracteristica c = new Caracteristica();
		
		c.setNombre(crc.getNombre());
		c.setEtiqueta(crc.getEtiqueta());
		
		for(EnumTipoDatoCaracteristica tdc:EnumTipoDatoCaracteristica.values()) {
			if(tdc.toString().equalsIgnoreCase(crc.getTipoDato())) {
				c.setTipoDato(tdc);
				break;
			}
		}
		
		c.setFenomeno(fPersistencia.obtenerPorNombre(crc.getFenomeno()).get(0));
		return c;
	} 
	
	
	private List<CaracteristicaDTO> prepararTodasLasCaracteristicas() throws ServiciosException{
		List<Caracteristica> crc = cPersistencia.obtenerTodasLasCrs();
		List<CaracteristicaDTO> crcDTO = new ArrayList<CaracteristicaDTO>();
		
		for(Caracteristica c : crc) {
			CaracteristicaDTO cDTO = new CaracteristicaDTO();
			cDTO.setEtiqueta(c.getEtiqueta());
			cDTO.setFenomeno(c.getFenomeno().getNombre());
			cDTO.setNombre(c.getNombre());
			cDTO.setTipoDato(c.getTipoDato().name());
			cDTO.setId_caracteristica(c.getId_caracteristica());
			crcDTO.add(cDTO);
		}
		
		return crcDTO;
		
	}
	
	
	@Override
	public void agregarCaracteristica(CaracteristicaDTO caracteristicaDTO) throws ServiciosException {
		cPersistencia.altaCaracteristica(this.prepararAgregarCaracteristica(caracteristicaDTO));
		
	}

	@Override
	public void actualizarCaracteristica(CaracteristicaDTO caracteristicaDTO) throws ServiciosException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<CaracteristicaDTO> obtenerCaracteristicas() throws ServiciosException {
		// TODO Auto-generated method stub
		return this.prepararTodasLasCaracteristicas();
	}

	
}
