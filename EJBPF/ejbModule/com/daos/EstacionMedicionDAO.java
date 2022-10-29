package com.daos;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import com.entities.EstacionMedicion;
import com.entities.Usuario;
import com.exception.ServiciosException;

/**
 * Session Bean implementation class EstacionMedicion
 */
@LocalBean
@Stateless
public class EstacionMedicionDAO implements IEstacionMedicionDAO {

    
	@PersistenceContext
	private EntityManager em;
	
    public EstacionMedicionDAO() {
        // TODO Auto-generated constructor stub
    }

    
    @Override
	public void altaEM(EstacionMedicion esm) throws ServiciosException {

		try {
			em.persist(esm);
			em.flush();
		} catch (PersistenceException e) {
			throw new ServiciosException(e.getMessage());
		}
	}

	@Override
	public void bajaEM(EstacionMedicion esm) throws ServiciosException {
		try {
			em.remove(em.find(EstacionMedicion.class, esm.getId_estacionmedicion()));
			em.flush();
		} catch (PersistenceException e) {
			throw new ServiciosException("No se pudo dar de baja la Estación");
		}
		
	}

	@Override
	public void modificarEM(EstacionMedicion estacion) throws ServiciosException {
		try {
			EstacionMedicion esm = this.findForMerge(estacion.getId_estacionmedicion()); 
			esm = estacion;
			em.merge(esm);
			em.flush();
		} catch (PersistenceException e) {
			throw new ServiciosException("No se pudo modificar la Estación");
		}
		
	}

	@Override
	public EstacionMedicion findForMerge(Long pk_esm) throws ServiciosException {
		try {
			EstacionMedicion esm = em.find(EstacionMedicion.class, pk_esm);
			return esm;
			} catch(PersistenceException e) {
				throw new ServiciosException("No hay ninguna estacion asociado a esa PK en la tabla: " + pk_esm);
			}
	}

	@Override
	public List<EstacionMedicion> obtenerTodasLasEM() throws ServiciosException {
		
		try {
			TypedQuery<EstacionMedicion> query = em.createQuery("SELECT c FROM EstacionMedicion c", EstacionMedicion.class);
			return query.getResultList();
			} catch (PersistenceException e) {
				throw new ServiciosException(e.getMessage());
			}
	}
	
	@Override
	public List<EstacionMedicion> obtenerPorNombre(String nombre) throws ServiciosException {
		try {
		TypedQuery<EstacionMedicion> query = em.createQuery("SELECT u FROM EstacionMedicion u WHERE u.nombre = :e", EstacionMedicion.class)
				.setParameter("e", nombre);
		return query.getResultList();
		} catch (PersistenceException e) {
			throw new ServiciosException("No se pudo encontrar al usuario por el nombre " + nombre);
		}
	}


}
