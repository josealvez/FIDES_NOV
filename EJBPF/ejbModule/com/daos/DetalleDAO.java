package com.daos;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import com.entities.Detalle;
import com.exception.ServiciosException;

/**
 * Session Bean implementation class DetalleDAO
 */
@LocalBean
@Stateless
public class DetalleDAO implements IDetalleDAO {

    
	@PersistenceContext
	private EntityManager em;
	
    public DetalleDAO() {
        // TODO Auto-generated constructor stub
    }
    
    @Override
	public void altaDetalle(Detalle dt) throws ServiciosException {
		try {
			em.persist(dt);
			em.flush();
		} catch (PersistenceException e) {
			throw new ServiciosException(e.getMessage());
		}
		
	}

	@Override
	public void bajaCaracteristica(Detalle dt) throws ServiciosException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modificarCaracteristica(Detalle dt) throws ServiciosException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Detalle findForMerge(int pkDt) throws ServiciosException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Detalle> ObtenerObsAndCrcForEmail(String emailUsuario) throws ServiciosException {
		
		try {
			TypedQuery<Detalle> query = em.createQuery("SELECT d FROM Detalle d JOIN d.obs o JOIN o.usr_voluntario u WHERE u.email = :email ", Detalle.class)
					.setParameter("email", emailUsuario);
			return query.getResultList();
			} catch (PersistenceException e) {
				throw new ServiciosException("No se pudo encontrar las localidades: " + e.getMessage());
			}
	}

}
