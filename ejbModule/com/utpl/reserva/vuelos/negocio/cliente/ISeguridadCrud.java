package com.utpl.reserva.vuelos.negocio.cliente;

import java.util.List;
import java.util.Map;

import javax.ejb.Local;

/**
 * @author Giovanny Cholca
 *
 */

@Local
public interface ISeguridadCrud {
	
	@SuppressWarnings("rawtypes")
	Object consultarLogin(Class tabla, String user, String password) throws Exception;
	
	List<Object> findByQuery(String jpql, Map<String, Object> parameters) throws Exception;

	Object findByNamedQuery(String namedQuery, Map<String, Object> parameters) throws Exception;
}
