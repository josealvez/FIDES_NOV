package com.entities;

import java.io.Serializable;
import javax.persistence.*;

import com.enumerados.EnumCategoriaDocumento;

/**
 * Entity implementation class for Entity: DocumentoCategoria
 *
 */
@Entity

@Table(name = "DOCUMENTO_CATEGORIAS", uniqueConstraints = {
		@UniqueConstraint(name="uk_categoria", columnNames= {"categoria_nombre"})
		})
public class DocumentoCategoria implements Serializable {

	
	private static final long serialVersionUID = 1L;

	@Id
	private Long id_docu_categoria;
	
	@Column(length = 40)
	@Enumerated(value = EnumType.STRING)
	private EnumCategoriaDocumento categoria_nombre;


	public Long getId_docu_categoria() {
		return id_docu_categoria;
	}

	public void setId_docu_categoria(Long id_docu_categoria) {
		this.id_docu_categoria = id_docu_categoria;
	}

	public EnumCategoriaDocumento getCategoria_nombre() {
		return categoria_nombre;
	}

	public void setCategoria_nombre(EnumCategoriaDocumento categoria_nombre) {
		this.categoria_nombre = categoria_nombre;
	}



	
}