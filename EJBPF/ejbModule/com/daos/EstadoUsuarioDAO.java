package com.daos;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import com.entities.EstadoUsuario;
import com.enumerados.EnumEstadoUsuario;
import com.exception.ServiciosException;

/**
 * Session Bean implementation class EstadoUsuarioDAO
 */
@LocalBean
@Stateless
public class EstadoUsuarioDAO implements IEstadoUsuarioDAO {

	@PersistenceContext
	private EntityManager em;
	
    public EstadoUsuarioDAO() {
        // TODO Auto-generated constructor stub
    }

    
   	@Override
   	public void altaEstado(EstadoUsuario estado) throws ServiciosException {
   		try {
   			em.persist(estado);
   			em.flush();
   			
   		} catch (PersistenceException e) {
   			throw new ServiciosException("No se pudo dar de alta el estado");
   		}
   		
   	}

   	@Override
   	public void bajaEstado(EstadoUsuario estado) throws ServiciosException {
   		try {
   			em.merge(estado);
   			em.flush();
   		} catch (PersistenceException e) {
   			throw new ServiciosException("No se pudo dar de baja a el usuario");
   		}
   		
   	}

   	@Override
   	public void modificarEstado(EstadoUsuario estado) throws ServiciosException {
   		try {
   			em.merge(estado);
   			em.flush();
   		} catch (PersistenceException e) {
   			throw new ServiciosException("No se pudo dar de baja a el usuario");
   		}
   		
   	}

   	/*
   	Cuando enumeramos una entidad y queremos lanzar una query
    con la libreria de hibernate 
   	para SQL si o si tenemos que pasar como paramentro 
    el enumerado
     */
   	
   	@Override
   	public List<EstadoUsuario> obtenerPorEstado(EnumEstadoUsuario estado) throws ServiciosException {
   		try {
   			TypedQuery<EstadoUsuario> query = em.createQuery("SELECT e FROM EstadoUsuario e WHERE e.estado_valor = :e", EstadoUsuario.class)
   					.setParameter("e", estado);
   			return query.getResultList();
   			} catch (PersistenceException e) {
   				throw new ServiciosException("No se pudo encontrar el estado " + estado);
   			}
   	}

   	@Override
   	public EstadoUsuario findForMerge(int pk) throws ServiciosException {
   		try {
   			EstadoUsuario estado = em.find(EstadoUsuario.class, pk);
   			return estado;
   			} catch(PersistenceException e) {
   				throw new ServiciosException("No hay ningun estado asociado a esa PK en la tabla" + pk);
   			}
   	}


   	@Override
   	public List<EstadoUsuario> obtenerTodos() throws ServiciosException {
   		try {
   			TypedQuery<EstadoUsuario> query = em.createQuery("SELECT e FROM EstadoUsuario e", EstadoUsuario.class);
   			return query.getResultList();
   			} catch (PersistenceException e) {
   				throw new ServiciosException(e.getMessage());
   			}
   	}
    
}
