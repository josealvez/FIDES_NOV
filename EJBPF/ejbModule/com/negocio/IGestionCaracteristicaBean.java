package com.negocio;
import java.util.List;

import javax.ejb.Local;
import com.dto.CaracteristicaDTO;
import com.exception.ServiciosException;

@Local
public interface IGestionCaracteristicaBean {

	public void agregarCaracteristica(CaracteristicaDTO caracteristicaDTO) throws ServiciosException;
	public void actualizarCaracteristica(CaracteristicaDTO caracteristicaDTO) throws   ServiciosException;
	public List<CaracteristicaDTO> obtenerCaracteristicas() throws ServiciosException;
	
	
}
