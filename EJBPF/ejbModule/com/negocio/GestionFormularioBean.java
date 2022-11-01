package com.negocio;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.daos.FormularioDAO;
import com.daos.UsuarioDAO;
import com.dto.FormularioDTO;
//import com.dto.ObservacionDTO;
//import com.entities.Casilla;
import com.entities.Formulario;
//import com.entities.Observacion;
import com.exception.ServiciosException;

@LocalBean
@Stateless
public class GestionFormularioBean implements IGestionFormularioBean {
	
	@EJB
	private FormularioDAO formularioPersistencia;
	
	@EJB
	private UsuarioDAO usuarioPersistencia;
	
	@EJB
	private GestionCasillaBean gCasilla;
	
//	private List<CasillaDTO> prepararCasillas(String nombreCasilla) throws ServiciosException{
//		
//		List<CasillaDTO> casillas = new ArrayList<CasillaDTO>();
//
//		
//		for(Casilla c : casillaPersistencia.obtenerTodos()) {
//			CasillaDTO ca = new CasillaDTO();
//			ca.setNombre(c.getNombre());
//			casillas.add(ca);
//		}
//		
//		return casillas;
//		
//	}
	
	private List<FormularioDTO> prepararTodosLosFormularios() throws ServiciosException{
		
		List<Formulario> fList = formularioPersistencia.obtenerTodos();		
	
		List<FormularioDTO> formsDTO = new ArrayList<FormularioDTO>();
		
		for(Formulario f : fList) {
			FormularioDTO fDTO = new FormularioDTO();

			fDTO.setNombre(f.getNombre());
			fDTO.setDescripcion(f.getDescripcion());
			fDTO.setFechahora(f.getFechaHora());
			fDTO.setId_formulario(f.getId_formulario());
			fDTO.setContienes(f.getContienes());
			fDTO.setUsuario(f.getUsuario());
			formsDTO.add(fDTO);
		}		
		return formsDTO;
	}
	
	private Formulario prepararFormulario(FormularioDTO formularioDTO) throws ServiciosException{
		
		Formulario formularioAdd = new Formulario();
		if(formularioDTO.getId_formulario() != null ) formularioAdd.setId_formulario(formularioDTO.getId_formulario());
		formularioAdd.setNombre(formularioDTO.getNombre());
		formularioAdd.setDescripcion(formularioDTO.getDescripcion());
		System.out.print(formularioDTO.getFechahora());
		formularioAdd.setFechaHora(formularioDTO.getFechahora());
		formularioAdd.setContienes(formularioDTO.getContienes());
		formularioAdd.setUsuario(formularioDTO.getUsuario());
		return formularioAdd;
	}
	
	private FormularioDTO prepararFormularioPorId(Long id) throws ServiciosException{
		Formulario f = formularioPersistencia.obtenerPorPK(id).get(0);
		FormularioDTO fDTO = new FormularioDTO();
		
//		fDTO.setCasillas(f.getCasillas());
		
		fDTO.setDescripcion(f.getDescripcion());
		fDTO.setFechahora(f.getFechaHora());
		fDTO.setId_formulario(f.getId_formulario());
		fDTO.setNombre(f.getNombre());
		fDTO.setContienes(f.getContienes());
		fDTO.setUsuario(f.getUsuario());
		return fDTO;
	}
	
//	private Formulario prepararActualizarFormulario(FormularioDTO formularioDTO)throws ServiciosException {
//		Formulario f = formularioPersistencia.findForMerge(formularioDTO.getId_formulario());
//		f.setValidarInvestigador(formularioDTO.isValidarInvestigador());
//		return f;
//	}
	
	@Override
	public long agregarFormulario(FormularioDTO formularioDTO) throws ServiciosException {
//		System.out.println("ESTE ES LA HORA DEL CONTIENES: "+formularioDTO.getContienes().get(0).getFechaRegistro());
		return formularioPersistencia.altaFormulario(prepararFormulario(formularioDTO));
		
	}
	
	@Override
	public List<FormularioDTO> obtenerFormularios() throws ServiciosException {
		List<FormularioDTO> fs = this.prepararTodosLosFormularios();
		return fs;
	}

//	@Override
//	public void actualizarFormulario(FormularioDTO formularioDTO) throws ServiciosException {
//		formularioPersistencia.modificarFormulario(this.prepararActualizarFormulario(formularioDTO));
//	}

	@Override
	public FormularioDTO obtenerFormularioPorId (Long id) throws ServiciosException{
		return this.prepararFormularioPorId(id);
	}

//	@Override
//	public List<FormularioDTO> obtenerTodosLosFormularios() throws ServiciosException {
//		// TODO Auto-generated method stub
//		return this.prepararTodosLosFormularios();
//	}
	

}
