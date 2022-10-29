package com.negocio;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.daos.RegistroDAO;
import com.entities.Contiene;
import com.entities.Formulario;
import com.exception.ServiciosException;


/**
 * Session Bean implementation class GestionRegistroBean
 */
@Stateless
@LocalBean
public class GestionRegistroBean implements IGestionRegistroBeanLocal {

	@EJB
	RegistroDAO registrodao;
	
    public GestionRegistroBean() {
        // TODO Auto-generated constructor stub
    }
    @Override
    public void AltaRegistro(Contiene c) throws ServiciosException{
    	registrodao.AltaRegistro(c);
    }
    @Override
    public void ModificarRegistro(Contiene c) throws ServiciosException{
    	registrodao.ModificarRegistro(c);
    }
    @Override
    public void EliminarRegistro(Contiene c) throws ServiciosException {
    	registrodao.EliminarRegistro(c);
    }
    @Override
    public Contiene BuscarRegistroPorId(Long id) throws ServiciosException{
    	return registrodao.BuscarRegistroPorId(id);
    }
    @Override
    public List<Contiene> ListarRegistrosPorFormulario(Formulario f) throws ServiciosException{
    	return registrodao.ListarRegistrosPorFormulario(f);
    }
    @Override
    public List<Contiene> ListarRegistros() throws ServiciosException{
    	return registrodao.ListarRegistros();
    }

}
