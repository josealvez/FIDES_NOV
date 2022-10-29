package com.daos;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import com.entities.Zona;
import com.enumerados.EnumZonasCategoria;
import com.exception.ServiciosException;

/**
 * Session Bean implementation class ZonaDAO
 */
@LocalBean
@Stateless
public class ZonaDAO implements IZonaDAO {

   

	@PersistenceContext
	private EntityManager em;
	
    public ZonaDAO() {
        // TODO Auto-generated constructor stub
    }
    
    @Override
	public void altaZona(Zona categoria) throws ServiciosException {
		try {
			em.persist(categoria);
			em.flush();
		} catch (PersistenceException e) {
			throw new ServiciosException("No se pudo dar de alta la categoria");
		}
		
	}

	@Override
	public void bajaZona(int pk) throws ServiciosException {
		try {
			Zona z = em.find(Zona.class, pk);
			em.remove(z);
			em.flush();
		} catch (PersistenceException e) {
			throw new ServiciosException("No se pudo dar de baja la zona");
		}
		
		
	}

	@Override
	public void modificarZona(Zona categoria) throws ServiciosException {
		try {
			em.merge(categoria);
			em.flush();
		} catch (PersistenceException e) {
			throw new ServiciosException("No se pudo modificar la zona");
		}
		
		
	}

	@Override
	public List<Zona> obtenerPorCategoria(EnumZonasCategoria zonaEnum) throws ServiciosException {
		try {
			TypedQuery<Zona> query = em.createQuery("SELECT z FROM Zona z WHERE z.categoria = :c", Zona.class)
					.setParameter("c", zonaEnum);
			return query.getResultList();
			} catch (PersistenceException e) {
				throw new ServiciosException("No se pudo encontrar la categoria: " + zonaEnum);
			}
	}

	@Override
	public List<Zona> obtenerTodos() throws ServiciosException {
		
		try { 
			TypedQuery<Zona> query = em.createQuery("SELECT z FROM Zona z",
		 Zona.class); return query.getResultList(); }
		catch (PersistenceException e) {
		 throw new ServiciosException("No se pudo encontrar ninguna Zona"); 
		 }
		 
	}

	@Override
	public Zona findForMerge(int pk) throws ServiciosException {
		try {
			Zona categoria = em.find(Zona.class, pk);
			return categoria;
			} catch(PersistenceException e) {
				throw new ServiciosException("No hay ninguna zona asociado a esa PK en la tabla" + pk);
			}
	}

}
