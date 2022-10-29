package com.daos;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import com.entities.TipoUsuario;
import com.enumerados.EnumCategoriaUsuario;
import com.exception.ServiciosException;

/**
 * Session Bean implementation class TipoUsuarioDAO
 */
@LocalBean
@Stateless
public class TipoUsuarioDAO implements ITipoUsuarioDAO {

    
	@PersistenceContext
	private EntityManager em;
	
    public TipoUsuarioDAO() {
        // TODO Auto-generated constructor stub
    }
    
    @Override
	public void altaTipoUsuario(TipoUsuario tipo) throws ServiciosException {
		try {
			em.persist(tipo);
			em.flush();
		} catch (PersistenceException e) {
			throw new ServiciosException("No se pudo dar de alta en la tabla esta categoria: " + tipo.getRol());
		}
		
	}

	@Override
	public void bajaTipoUsuario(TipoUsuario tipo) throws ServiciosException {
		try {
			em.merge(tipo);
			em.flush();
		} catch (PersistenceException e) {
			throw new ServiciosException("No se pudo dar de baja la siguiente categoria: " + tipo.getRol());
		}
		
	}

	@Override
	public void modificarTipoUsuario(TipoUsuario tipo) throws ServiciosException {
		try {
			em.merge(tipo);
			em.flush();
		} catch (PersistenceException e) {
			throw new ServiciosException("No se pudo modificar la siguiente categoria: " + tipo.getRol());
		}
		
	}

	@Override
	public List<TipoUsuario> obtenerPorRol(EnumCategoriaUsuario tipoEnum) throws ServiciosException {
		try {
			TypedQuery<TipoUsuario> query = em.createQuery("SELECT r FROM TipoUsuario r WHERE r.rol = :r", TipoUsuario.class)
					.setParameter("r", tipoEnum);
			return query.getResultList();
			} catch (PersistenceException e) {
				throw new ServiciosException("No se pudo encontrar el rol: " + tipoEnum);
			}
	}

	@Override
	public List<TipoUsuario> obtenerTodos() throws ServiciosException {
		try {
			TypedQuery<TipoUsuario> query = em.createQuery("SELECT r FROM TipoUsuario r", TipoUsuario.class);
			return query.getResultList();
			} catch (PersistenceException e) {
				throw new ServiciosException(e.getMessage());
			}

	}

	@Override
	public TipoUsuario findForMerge(int pk) throws ServiciosException {
		try {
			TipoUsuario rol = em.find(TipoUsuario.class, pk);
			return rol;
			} catch(PersistenceException e) {
				throw new ServiciosException("No hay rol asociado a esa PK en la tabla" + pk);
			}
	}

}
