package com.daos;

import java.util.List;

import javax.ejb.Local;

import com.entities.DocumentoCategoria;
import com.enumerados.EnumCategoriaDocumento;
import com.exception.ServiciosException;

@Local
public interface IDocumentoCategoriaDAO {
	
	void altaDocumentoCategoria(DocumentoCategoria categoria) throws ServiciosException;
	void bajaDocumentoCategoria(DocumentoCategoria categoria) throws ServiciosException;
	void modificarDocumentoCategoria(DocumentoCategoria categoria) throws ServiciosException;
	List<DocumentoCategoria> obtenerPorCategoria(EnumCategoriaDocumento categoriaEnum) throws ServiciosException;
	List<DocumentoCategoria> obtenerTodos() throws ServiciosException;
	DocumentoCategoria findForMerge(int pk) throws ServiciosException;

}
