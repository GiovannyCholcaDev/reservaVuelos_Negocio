package com.utpl.reserva.vuelos.negocio.cliente;

import java.util.List;
import java.util.Map;

import javax.ejb.Local;

/**
 * @author Giovanny Cholca
 *
 */
@Local
public interface IAdminCrud {

	/**
	 * Metodo para guardar un registro
	 * 
	 * @param registro
	 * @return
	 * @throws Exception
	 */
	String guardar(Object registro) throws Exception;

	/**
	 * Metodo para actualizar un registro
	 * 
	 * @param registro
	 * @return
	 * @throws Exception
	 */
	String actualizar(Object registro) throws Exception;

	/**
	 * Metodo para eliminar un registro
	 * 
	 * @param tabla
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	String eliminar(Class tabla, Integer id) throws Exception;

	/**
	 * Metodo para consultar por Id
	 * 
	 * @param tabla
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	Object consultarPorId(Class tabla, Integer id) throws Exception;

	/**
	 * Metodo para consultar todos
	 * 
	 * @param tabla
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	List<Object> consultarTodos(Class tabla) throws Exception;
	
	
	/**
	 * @param namedQuery
	 * @param parameters
	 * @return
	 * @throws Exception
	 */
	List<Object> findByNamedQuery(String namedQuery, Map<String, Object> parameters) throws Exception;


}
