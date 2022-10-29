package com.daos;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import com.entities.Caracteristica;
import com.exception.ServiciosException;

@LocalBean
@Stateless
public class CaracteristicaDAO implements ICaracteristicaDAO {

    
	@PersistenceContext
	private EntityManager em;
	
    public CaracteristicaDAO() {
        // TODO Auto-generated constructor stub
    }

    
    @Override
	public void altaCaracteristica(Caracteristica cra) throws ServiciosException {

		try {
			em.persist(cra);
			em.flush();
		} catch (PersistenceException e) {
			throw new ServiciosException(e.getMessage());
		}
	}

	@Override
	public void bajaCaracteristica(Caracteristica cra) throws ServiciosException {
		try {
			em.remove(em.find(Caracteristica.class, cra.getId_caracteristica()));
			em.flush();
		} catch (PersistenceException e) {
			throw new ServiciosException("No se pudo dar de baja el Departamento");
		}
		
	}

	@Override
	public void modificarCaracteristica(Caracteristica cra) throws ServiciosException {
		try {
			em.merge(cra);
			em.flush();
		} catch (PersistenceException e) {
			throw new ServiciosException("No se pudo modificar la Caracteristica");
		}
		
	}

	@Override
	public Caracteristica findForMerge(int pk_cra) throws ServiciosException {
		
		try {
			Caracteristica cra = em.find(Caracteristica.class, pk_cra);
			return cra;
			} catch(PersistenceException e) {
				throw new ServiciosException("No hay ninguna caracteristica asociado a esa PK en la tabla: " + pk_cra);
			}
	}

	@Override
	public List<Caracteristica> obtenerTodasLasCrs() throws ServiciosException {
		
		try {
			TypedQuery<Caracteristica> query = em.createQuery("SELECT c FROM Caracteristica c", Caracteristica.class);
			return query.getResultList();
			} catch (PersistenceException e) {
				throw new ServiciosException("No se pudo realizar la consula");
			}
	}

	@Override
	public List<Caracteristica> obtenerCrsPorFenomeno(String fm) throws ServiciosException {
		try {
			TypedQuery<Caracteristica> query = em.createQuery("SELECT c FROM Caracteristica c WHERE c.fenomeno.nombre = :f", Caracteristica.class)
					.setParameter("f", fm);
			return query.getResultList();
			} catch (PersistenceException e) {
				throw new ServiciosException("No se pudo realizar la consula");
			}
	}

	@Override
	public List<Caracteristica> obtenerCrsPKPorFenomeno(String fenomeno, String caracteristica) throws ServiciosException {
		try {
			TypedQuery<Caracteristica> query = em.createQuery("SELECT c FROM Caracteristica c WHERE c.fenomeno.nombre = :fem AND c.nombre = :crc", Caracteristica.class)
					.setParameter("fem", fenomeno)
					.setParameter("crc", caracteristica);
			return query.getResultList();
			} catch (PersistenceException e) {
				throw new ServiciosException("No se pudo realizar la consula");
			}
	}
    
}
