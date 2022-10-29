package com.daos;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import com.entities.Usuario;
import com.exception.ServiciosException;

/**
 * Session Bean implementation class UsuarioDAO
 */
@LocalBean
@Stateless
public class UsuarioDAO implements IUsuarioDAO {

   
	@PersistenceContext
	private EntityManager em;
	
    public UsuarioDAO() {
        // TODO Auto-generated constructor stub
    }
    
    @Override
	public void altaUsuario(Usuario user) throws ServiciosException {
		
    	try {  	
			em.persist(user);
			em.flush();
		} catch (PersistenceException e ) {
			
			
			throw new ServiciosException(e.getMessage());
		}
	}

	@Override
	public void bajaUsuario(Usuario usuario) throws ServiciosException {
		try {
			Usuario u = this.findForMerge(usuario.getId_usuario());
			u = usuario;
			em.merge(u);
			em.flush();
		} catch (PersistenceException e) {
			throw new ServiciosException("No se pudo dar de baja a el usuario");
		}
	}

	
	//Priviamente a una modificacion hay que usar el metodo find
	@Override
	public void modificarUsuario(Usuario usuario) throws ServiciosException {
		try {
			Usuario u = this.findForMerge(usuario.getId_usuario());
			u = usuario;
			em.merge(u);
			em.flush();
		
		} catch (PersistenceException e) {
			throw new ServiciosException("No se pudo modificar el usuario por alguna razon");
		}
	}

	@Override
	public List<Usuario> obtenerPorEmail(String email) throws ServiciosException {
		try {
		TypedQuery<Usuario> query = em.createQuery("SELECT u FROM Usuario u WHERE u.email = :e", Usuario.class)
				.setParameter("e", email);
		return query.getResultList();
		} catch (PersistenceException e) {
			throw new ServiciosException("No se pudo encontrar al usuario por el correo " + email);
		}
	}

	@Override
	public Usuario findForMerge(Long pk) throws ServiciosException {
		try {
		Usuario user = em.find(Usuario.class, pk);
		return user;
		} catch(PersistenceException e) {
			throw new ServiciosException("No hay ningun usuario asociado a esa PK en la tabla" + pk);
		}
	}

	@Override
	public List<Usuario> obtenerTodosLosUsuarios() throws ServiciosException {
		
		try {
			TypedQuery<Usuario> query = em.createQuery("SELECT u FROM Usuario u ORDER BY u.apellido", Usuario.class);
			return query.getResultList();
			} catch (PersistenceException e) {
				throw new ServiciosException("No se pudo realizar la consula");
			}
	}
	
	
	
	@Override
	public Usuario validarUsuario(String email, String password) throws ServiciosException {
		try {
			TypedQuery<Usuario> query = em.createQuery("SELECT u FROM Usuario u WHERE u.email = :e AND u.passwd = :ps", Usuario.class)
					.setParameter("e", email)
					.setParameter("ps", password);
			 
			return (!query.getResultList().isEmpty())?
					query.getResultList().get(0):
						null;
			
			} catch (PersistenceException e)  {
				throw new ServiciosException("No se pudo realizar la consula" );
			}
		
		
	}

}
