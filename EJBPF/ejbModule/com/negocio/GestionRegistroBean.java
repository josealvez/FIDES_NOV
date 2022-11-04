package com.negocio;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.daos.FormularioDAO;
import com.daos.RegistroDAO;
import com.dto.FormularioDTO;
import com.dto.RegistroDTO;
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
	@EJB
	FormularioDAO formulariodao;
	
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
    
    @Override
    public List<RegistroDTO> ListarRegistrosRest() throws ServiciosException{
    	List<Formulario> listaForm = formulariodao.obtenerTodos();
    	List<Contiene> listaCont = registrodao.ListarRegistros();
    	List<RegistroDTO> listaRegistros = new ArrayList<>();
    	for (Formulario form : listaForm) {
    		RegistroDTO registro = new RegistroDTO();
    		List<String> strings = new ArrayList<>();
    		for (Contiene con : listaCont) {
    			if (con.getIsRegistro()) {
    				if (con.getFormulario().equals(form)) {
    				String ingreso = "{Casilla: "+con.getCasilla().getNombre()+" Parametro: "+con.getRegistro()+"}";
    				registro.setNombre(form.getNombre());
    	    		registro.setDescripcion(form.getDescripcion());
    				strings.add(ingreso);
    				}
    			}
    		}
    		registro.setCasillas(strings);
    		if (registro.getNombre()!=null) listaRegistros.add(registro);
    	}
    	return listaRegistros;
    }

}
