package com.daos;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import com.entities.Fenomeno;
import com.entities.Observacion;
import com.entities.Usuario;
import com.enumerados.EnumZonasCategoria;
import com.exception.ServiciosException;

/**
 * Session Bean implementation class ObservacionDAO
 */
@LocalBean
@Stateless
public class ObservacionDAO implements IObservacionDAO {

    
	@PersistenceContext
	private EntityManager em;
	
    public ObservacionDAO() {
        // TODO Auto-generated constructor stub
    }
    
    @Override
	public void altaObservacion(Observacion obs) throws ServiciosException {
    	
    	
		try {
			em.persist(obs);
			em.flush();
		} catch (PersistenceException e) {
		    
			throw new ServiciosException(e.getMessage());
		}
		
	}

	@Override
	public void bajaObservacion(Long pk) throws ServiciosException {
		try {
			Fenomeno f = em.find(Fenomeno.class, pk);
			em.remove(f);
			em.flush();
		} catch (PersistenceException e) {
			throw new ServiciosException("No se pudo dar de baja la observacion");
		}
		
	}

	@Override
	public void modificarObservacion(Observacion obs) throws ServiciosException {
		try {
			obs = em.find(Observacion.class, obs.getId_observacion());
			em.merge(obs);
			em.flush();
		} catch (PersistenceException e) {
			throw new ServiciosException("No se pudo modificar la observacio");
		}
		
		
	}

	@Override
	public List<Observacion> obtenerPorUsuario(Usuario usuario) throws ServiciosException {
		try {
			TypedQuery<Observacion> query = em.createQuery("SELECT o FROM Observacion o WHERE usr_aficionado = :usu ORDER BY ID_OBSERVACION DESC", Observacion.class)
					.setParameter("usu", usuario);
			return query.getResultList();
			} catch (PersistenceException e) {
				throw new ServiciosException(e.getMessage());
			}
	}

	@Override
	public List<Observacion> obtenerTodos() throws ServiciosException {
		try {
			TypedQuery<Observacion> query = em.createQuery("SELECT o FROM Observacion o", Observacion.class);
			return query.getResultList();
			} catch (PersistenceException e) {
				throw new ServiciosException(e.getMessage());
			}
	}

	@Override
	public Observacion findForMerge(Long pk) throws ServiciosException {
		try {
			Observacion obs = em.find(Observacion.class, pk);
			return obs;
			} catch(PersistenceException e) {
				throw new ServiciosException("No hay ninguna observacion asociado a esa PK en la tabla" + pk);
			}
	}

	@Override
	public List<Observacion> obtenerPorZona(EnumZonasCategoria zona) throws ServiciosException {
		try {
			TypedQuery<Observacion> query = em.createQuery("SELECT o FROM Observacion o JOIN o.localidad l JOIN l.departamento d JOIN d.zona z WHERE z.categoria = :nZona ORDER BY d.nombre", Observacion.class)
					.setParameter("nZona", zona);;
			return query.getResultList();
			} catch (PersistenceException e) {
				throw new ServiciosException("No se pudo encontrar las localidades: " + e.getMessage());
			}
	
	}

	@Override
	public List<Observacion> obtenerSinValidar() throws ServiciosException {
		try {
			TypedQuery<Observacion> query = em.createQuery("SELECT o FROM Observacion o WHERE o.validarInvestigador = FALSE", Observacion.class);
			return query.getResultList();
			} catch (PersistenceException e) {
				throw new ServiciosException(e.getMessage());
			}
	}

	@Override
	public List<Observacion> obtenerPorPK(Long pk) throws ServiciosException {

		try {
			TypedQuery<Observacion> query = em.createQuery("SELECT o FROM Observacion o WHERE o.id_observacion = :pk", Observacion.class)
					.setParameter("pk", pk);
			return query.getResultList();
			} catch (PersistenceException e) {
				throw new ServiciosException(e.getMessage());
			}
	}

}
