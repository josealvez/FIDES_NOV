package com.daos;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import com.entities.Fenomeno;
import com.exception.ServiciosException;

/**
 * Session Bean implementation class FenomenoDAO
 */
@LocalBean
@Stateless
public class FenomenoDAO implements IFenomenoDAO {

    
	@PersistenceContext
	private EntityManager em;
	
    public FenomenoDAO() {
        // TODO Auto-generated constructor stub
    }
    
    @Override
	public void altaFenomeno(Fenomeno nombre) throws ServiciosException {
		try {
			em.persist(nombre);
			em.flush();
		} catch (PersistenceException e) {
			throw new ServiciosException("No se pudo dar de alta el fenomeno");
		}
		
	}

	@Override
	public void bajaFenomeno(Long pk) throws ServiciosException {
		try {
			Fenomeno f = em.find(Fenomeno.class, pk);
			em.remove(f);
			em.flush();
		} catch (PersistenceException e) {
			throw new ServiciosException(e.getMessage());
		}
		
	}

	@Override
	public void modificarFenomeno(Fenomeno fenomeno) throws ServiciosException {
		try {
			
			em.merge(fenomeno);
			em.flush();
		} catch (PersistenceException e) {
			throw new ServiciosException("No se pudo modificar el fenomeno");
		}
		
	}

	@Override
	public List<Fenomeno> obtenerPorNombre(String descripcion) throws ServiciosException {
		try {
			TypedQuery<Fenomeno> query = em.createQuery("SELECT f FROM Fenomeno f WHERE f.nombre = :n", Fenomeno.class)
					.setParameter("n", descripcion);
			return query.getResultList();
			} catch (PersistenceException e) {
				throw new ServiciosException("No se pudo encontrar el fenomeno: " + descripcion);
			}
	}

	@Override
	public List<Fenomeno> obtenerTodos() throws ServiciosException {
		try {
			TypedQuery<Fenomeno> query = em.createQuery("SELECT f FROM Fenomeno f", Fenomeno.class);
			return query.getResultList();
			} catch (PersistenceException e) {
				throw new ServiciosException(e.getMessage());
			}
	}

	@Override
	public Fenomeno findForMerge(Long pk) throws ServiciosException {
		try {
			Fenomeno nombre = em.find(Fenomeno.class, pk);
			return nombre;
			} catch(PersistenceException e) {
				throw new ServiciosException("No hay ningun fenomeno asociado a esa PK en la tabla" + pk);
			}
	}

}
