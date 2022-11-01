package com.daos;

import java.util.LinkedList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import com.entities.Casilla;
import com.entities.Contiene;
import com.entities.Formulario;
import com.exception.ServiciosException;

/**
 * Session Bean implementation class CasillaDAO
 */
@Stateless
@LocalBean
public class CasillaDAO implements ICasillaDAO {

	@PersistenceContext
	private EntityManager em;
	
    public CasillaDAO() {
        // TODO Auto-generated constructor stub
    }
    
    @Override
	public void altaCasilla(Casilla nombre) throws ServiciosException {
		try {
			em.persist(nombre);
			em.flush();
		} catch (PersistenceException e) {
			throw new ServiciosException("No se pudo dar de alta el casilla");
		}
		
	}

	@Override
	public void bajaCasilla(Long pk) throws ServiciosException {
		try {
			Casilla f = em.find(Casilla.class, pk);
			em.remove(f);
			em.flush();
		} catch (PersistenceException e) {
			throw new ServiciosException(e.getMessage());
		}
		
	}

	@Override
	public void modificarCasilla(Casilla casilla) throws ServiciosException {
		try {
			
			em.merge(casilla);
			em.flush();
		} catch (PersistenceException e) {
			throw new ServiciosException("No se pudo modificar el casilla");
		}
		
	}

	@Override
	public List<Casilla> obtenerPorNombre(String nombre) throws ServiciosException {
		try {
			TypedQuery<Casilla> query = em.createQuery("SELECT f FROM Casilla f WHERE f.nombre = :n", Casilla.class)
					.setParameter("n", nombre);
			return query.getResultList();
			} catch (PersistenceException e) {
				throw new ServiciosException("No se pudo encontrar la casilla: " + nombre);
			}
	}
	@Override
	public List<Contiene> obtenerCasillasPorFormulario(Formulario f) throws ServiciosException {
		List<Contiene> casillas = new LinkedList<Contiene>();
		try {
			TypedQuery<Contiene> query = em.createQuery(
					"SELECT c FROM Contiene c WHERE c.formulario = :formulario",
					Contiene.class).setParameter("formulario", f);
			casillas = query.getResultList();
		} catch (PersistenceException e) {
			throw new ServiciosException("No se pudieron cargar las casillas");
		}
		return casillas;
	}
	
	@Override
	public List<Casilla> obtenerTodos() throws ServiciosException {
		try {
			TypedQuery<Casilla> query = em.createQuery("SELECT f FROM Casilla f", Casilla.class);
			return query.getResultList();
			} catch (PersistenceException e) {
				throw new ServiciosException(e.getMessage());
			}
	}

	@Override
	public Casilla findForMerge(Long pk) throws ServiciosException {
		try {
			Casilla nombre = em.find(Casilla.class, pk);
			return nombre;
			} catch(PersistenceException e) {
				throw new ServiciosException("No hay ningun Casilla asociado a esa PK en la tabla" + pk);
			}
	}

}
