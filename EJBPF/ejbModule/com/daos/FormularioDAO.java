package com.daos;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import com.entities.Formulario;
import com.exception.ServiciosException;

/**
 * Session Bean implementation class ObservacionDAO
 */
@LocalBean
@Stateless
public class FormularioDAO implements IFormularioDAO {

    
	@PersistenceContext
	private EntityManager em;
	
    public FormularioDAO() {
        // TODO Auto-generated constructor stub
    }
    
	@Override
	public long altaFormulario(Formulario form) throws ServiciosException {
		try {
			em.persist(form);
			em.flush();
			return form.getId_formulario();
		} catch (PersistenceException e) {
		    
			throw new ServiciosException(e.getMessage());
		}
		
	}

	@Override
	public void bajaFormulario(Long pk) throws ServiciosException {
		try {
			Formulario f = em.find(Formulario.class, pk);
			em.remove(f);
			em.flush();
		} catch (PersistenceException e) {
			throw new ServiciosException("No se pudo dar de baja el formulario");
		}
		
	}

	@Override
	public void modificarFormulario(Formulario form) throws ServiciosException {
		try {
			form = em.find(Formulario.class, form.getId_formulario());
			em.merge(form);
			em.flush();
		} catch (PersistenceException e) {
			throw new ServiciosException("No se pudo modificar la observacio");
		}
		
		
	}
	
	@Override
	public List<Formulario> obtenerTodos() throws ServiciosException {
		try {
			TypedQuery<Formulario> query = em.createQuery("SELECT o FROM Formulario o", Formulario.class);
			return query.getResultList();
			} catch (PersistenceException e) {
				throw new ServiciosException(e.getMessage());
			}
	}
	
	@Override
	public List<Formulario> obtenerPorNombre(String nombre) throws ServiciosException {
		try {
			TypedQuery<Formulario> query = em.createQuery("SELECT o FROM Formulario o WHERE o.nombre = :nom ORDER BY NOMBRE", Formulario.class)
					.setParameter("nom", nombre);;
			return query.getResultList();
			} catch (PersistenceException e) {
				throw new ServiciosException("No se pudo encontrar los nombres: " + e.getMessage());
			}
	
	}

//	@Override
//	public List<Formulario> obtenerPorLocalidad(Localidad localidad) throws ServiciosException {
//		try {
//			TypedQuery<Formulario> query = em.createQuery("SELECT o FROM Formulario o WHERE usr_aficionado = :usu ORDER BY ", Observacion.class)
//					.setParameter("usu", usuario);
//			return query.getResultList();
//			} catch (PersistenceException e) {
//				throw new ServiciosException(e.getMessage());
//			}
//	}

	@Override
	public Formulario findForMerge(Long pk) throws ServiciosException {
		try {
			Formulario form = em.find(Formulario.class, pk);
			return form;
			} catch(PersistenceException e) {
				throw new ServiciosException("No hay ningun Formulario asociado a esa PK en la tabla" + pk);
			}
	}
	
	@Override
	public List<Formulario> obtenerPorPK(Long pk) throws ServiciosException {

		try {
			TypedQuery<Formulario> query = em.createQuery("SELECT o FROM Formulario o WHERE o.id_formulario = :pk", Formulario.class)
					.setParameter("pk", pk);
			return query.getResultList();
			} catch (PersistenceException e) {
				throw new ServiciosException(e.getMessage());
			}
	}
	
}
