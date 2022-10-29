package com.daos;
import java.util.List;
import javax.ejb.Local;
import com.entities.TipoUsuario;
import com.enumerados.EnumCategoriaUsuario;
import com.exception.ServiciosException;

@Local
public interface ITipoUsuarioDAO {
	
	void altaTipoUsuario(TipoUsuario tipo) throws ServiciosException;
	void bajaTipoUsuario(TipoUsuario tipo) throws ServiciosException;
    void modificarTipoUsuario(TipoUsuario tipo) throws ServiciosException;
    List<TipoUsuario> obtenerPorRol(EnumCategoriaUsuario tipoEnum) throws ServiciosException;
	List<TipoUsuario> obtenerTodos() throws ServiciosException;
	TipoUsuario findForMerge(int pk) throws ServiciosException;

}
