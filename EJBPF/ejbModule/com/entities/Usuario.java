package com.entities;

import java.io.Serializable;
import javax.persistence.*;



/**
 * Entity implementation class for Entity: UsuarioPDT
 *
 */
@Entity
@Table(name = "USUARIOS", uniqueConstraints = {
		@UniqueConstraint(name="uk_username_usr", columnNames= {"username"}),
		@UniqueConstraint(name="uk_documento_usr", columnNames= {"documento"}),
		@UniqueConstraint(name="uk_email_usr", columnNames= {"email"})
		})
public class Usuario implements Serializable {

	
	private static final long serialVersionUID = 1L;


	@Id
	@SequenceGenerator(name = "seqUsuario", initialValue=1, sequenceName="SEQ_ID_USUARIO")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seqUsuario")
	private Long id_usuario;
	
	@ManyToOne
	@JoinColumn(foreignKey=@ForeignKey(name="fk_tipo_usuario"))
	private TipoUsuario rol;

	@ManyToOne
	@JoinColumn(foreignKey=@ForeignKey(name="fk_estado_usuario"))
	private EstadoUsuario estado;

	@Column(length = 40, nullable = false)
	private String username;
	
	@Column(length = 40, nullable = false)
	private String nombre;
   
	@Column(length = 40, nullable = false)
	private String apellido;
	
	@Column(length = 40, nullable = false)
	private String direccion;
	
	@ManyToOne
	@JoinColumn(foreignKey=@ForeignKey(name="fk_d_c_usuarios"))
	private DocumentoCategoria doc;
	
	@Column(length = 40, nullable = false)
	private String documento;
	
	@Column(length = 40, nullable = false)
	private String email;
	
	@Column(length = 40, nullable = false)
	private String passwd;


	public Long getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(Long id_usuario) {
		this.id_usuario = id_usuario;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}


	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public TipoUsuario getRol() {
		return rol;
	}

	public void setRol(TipoUsuario rol) {
		this.rol = rol;
	}

	public EstadoUsuario getEstado() {
		return estado;
	}

	public void setEstado(EstadoUsuario estado) {
		this.estado = estado;
	}

	public DocumentoCategoria getDoc() {
		return doc;
	}

	public void setDoc(DocumentoCategoria doc) {
		this.doc = doc;
	}
	
   
}