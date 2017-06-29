package com.utpl.reserva.vuelos.negocio.cliente;

import java.io.Serializable;
import java.rmi.ServerException;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;

public interface Dao<T, PK extends Serializable> {

	T create(T newInstance);

	T read(PK id);

	void update(T transientObject);

	void delete(T persistentObject);

	/**
	 * SOLO para clases que tiene como PK un atributo nombrado i. Elimina el
	 * registr
	 * 
	 * @param id
	 * @param nombreCampoId
	 *            TODO
	 */
	void deleteById(Object id, String nombreCampoId);

	void flush();

	List<T> findByQuery(String jpql, Map<String, Object> parameters);

	List<T> findByQueryPaged(String jpql, Map<String, Object> parameters, int iniPos, int numReg);

	Query createNativeQuery(String sql);

	List<Object> callFunction(String nombre, Map<String, Object> parameters, javax.sql.DataSource ds)
			throws ServerException;

	List<T> findAll(String entity, String orderBy) throws ServerException;

	/**
	 * 
	 * Metodo para obtener datos de un Named Query
	 * 
	 * @author bcholca
	 * @version 10/09/2013 - 10:41:55
	 * @param namedQuery
	 * @param parameters
	 * @return
	 * @throws ServerException
	 */
	List<T> findByNamedQuery(String namedQuery, Map<String, Object> parameters);

}
