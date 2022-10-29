package com.daos;

import java.util.LinkedList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import com.entities.Contiene;
import com.entities.Formulario;
import com.exception.ServiciosException;

/**
 * Session Bean implementation class RegistroDAO
 */
@Stateless
@LocalBean
public class RegistroDAO implements IRegistroDAO {

	@PersistenceContext
	private EntityManager em;
	
    public RegistroDAO() {
        // TODO Auto-generated constructor stub
    }
    
    @Override
    public void AltaRegistro(Contiene c) throws ServiciosException {

		try {
			em.persist(c);
			em.flush();
		} catch (PersistenceException e) {
			throw new ServiciosException("No se pudo crear el registro");
		}
	}
    @Override
    public void ModificarRegistro(Contiene c) throws ServiciosException {

		try {
			em.merge(c);
			em.flush();
		} catch (PersistenceException e) {
			throw new ServiciosException("No se pudo actualizar el registro");
		}
	}
	@Override
    public void EliminarRegistro(Contiene c) throws ServiciosException {

		try {
			Contiene registro = em.find(Contiene.class, c.getIdcontiene());
			em.remove(registro);
			em.flush();
		} catch (PersistenceException e) {
			throw new ServiciosException("No se pudo borrar el registro");
		}
	}
	@Override
    public Contiene BuscarRegistroPorId(Long id) throws ServiciosException {
		Contiene registro = new Contiene();
		try {
			registro = em.find(Contiene.class, id);
		} catch (PersistenceException e) {
			throw new ServiciosException("No se pudo buscar el registro");
		}
		return registro;
	}
	@Override
    public List<Contiene> ListarRegistrosPorFormulario(Formulario f) throws ServiciosException {
		List<Contiene> registros = new LinkedList<Contiene>();
		try {
			TypedQuery<Contiene> query = em
					.createQuery("SELECT c FROM Contiene c WHERE c.formulario = :formulario", Contiene.class)
					.setParameter("formulario", f);
			registros = query.getResultList();
		} catch (PersistenceException e) {
			throw new ServiciosException("No se pudieron cargar los registros");
		}
		return registros;
	}
	@Override
    public List<Contiene> ListarRegistros() throws ServiciosException {
		List<Contiene> registros = new LinkedList<Contiene>();
		try {
			TypedQuery<Contiene> query = em.createQuery("SELECT c FROM Contiene c", Contiene.class);
			registros = query.getResultList();
		} catch (PersistenceException e) {
			throw new ServiciosException("No se pudieron cargar los registros");
		}
		return registros;
	}
}
