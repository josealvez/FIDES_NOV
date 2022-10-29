package com.daos;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import com.entities.DocumentoCategoria;
import com.enumerados.EnumCategoriaDocumento;
import com.exception.ServiciosException;

/**
 * Session Bean implementation class DocumentoCategoriaDAO
 */
@LocalBean
@Stateless
public class DocumentoCategoriaDAO implements IDocumentoCategoriaDAO {

	@PersistenceContext
	private EntityManager em;
	
    public DocumentoCategoriaDAO() {
    	
    }
    
    @Override
	public void altaDocumentoCategoria(DocumentoCategoria categoria) throws ServiciosException {
		try {
			em.persist(categoria);
			System.out.print("ACA ESTA  EL ID DE DOCUMENTO = " + categoria.getId_docu_categoria());
			em.flush();
		} catch (PersistenceException e) {
			throw new ServiciosException("No se pudo dar de alta la categoria");
		}
		
	}

	@Override
	public void bajaDocumentoCategoria(DocumentoCategoria categoria) throws ServiciosException {
		try {
			em.remove(categoria);
			em.flush();
		} catch (PersistenceException e) {
			throw new ServiciosException("No se pudo dar de baja la categoria");
		}
		
	}

	@Override
	public void modificarDocumentoCategoria(DocumentoCategoria categoria) throws ServiciosException {
		try {
			em.merge(categoria);
			em.flush();
		} catch (PersistenceException e) {
			throw new ServiciosException("No se puedo modificar la categoria");
		}
		
	}

	@Override
	public List<DocumentoCategoria> obtenerPorCategoria(EnumCategoriaDocumento categoriaEnum) throws ServiciosException {
		try {
			TypedQuery<DocumentoCategoria> query = em.createQuery("SELECT d FROM DocumentoCategoria d WHERE d.categoria_nombre = :d", DocumentoCategoria.class)
					.setParameter("d", categoriaEnum);
			return query.getResultList();
			} catch (PersistenceException e) {
				throw new ServiciosException("No se pudo encontrar la categoria: " + categoriaEnum);
			}
	}

	@Override
	public List<DocumentoCategoria> obtenerTodos() throws ServiciosException {
		try {
			TypedQuery<DocumentoCategoria> query = em.createQuery("SELECT d FROM DocumentoCategoria d", DocumentoCategoria.class);
			return query.getResultList();
			} catch (PersistenceException e) {
				throw new ServiciosException(e.getMessage());
			}
	}

	@Override
	public DocumentoCategoria findForMerge(int pk) throws ServiciosException {
		try {
			DocumentoCategoria categoria = em.find(DocumentoCategoria.class, pk);
			return categoria;
			} catch(PersistenceException e) {
				throw new ServiciosException("No hay ninguna categoria asociado a esa PK en la tabla" + pk);
			}
	}

}
