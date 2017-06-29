package com.utpl.reserva.vuelos.negocio.core;

import java.io.Serializable;
import java.rmi.ServerException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.sql.DataSource;

import com.utpl.reserva.vuelos.negocio.cliente.Dao;

@TransactionManagement(TransactionManagementType.CONTAINER)
public class DaoJpaImpl<T, PK extends Serializable> implements Dao<T, PK> {

	@PersistenceContext(unitName = "reservaVuelosPU")
	protected EntityManager entityManager;

	protected Class<T> type;

	protected void setType(Class<T> type) {
		this.type = type;
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public T create(T newInstance) {
		this.entityManager.persist(newInstance);
		return newInstance;
	}

	@Override
	public T read(PK id) {
		return this.entityManager.find(this.type, id);
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public void update(T transientObject) {
		this.entityManager.merge(transientObject);
	}

	@Override
	public void delete(T persistentObject) {
		this.entityManager.remove(persistentObject);
		flush();
	}

	@Override
	public void deleteById(Object id, String nombreCampoId) {
		String deleteSQL = "DELETE FROM " + this.type.getSimpleName() + " o WHERE o." + nombreCampoId + " = :id";
		this.entityManager.createQuery(deleteSQL).setParameter("id", id).executeUpdate();
		flush();
	}

	@Override
	public void flush() {
		this.entityManager.flush();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findByQuery(String jpql, Map<String, Object> parameters) {
		Query query = this.entityManager.createQuery(jpql);
		if (null != parameters && parameters.size() > 0) {
			for (String name : parameters.keySet()) {
				query.setParameter(name, parameters.get(name));
			}
		}
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findByQueryPaged(String jpql, Map<String, Object> parameters, int iniPos, int numReg) {
		Query query = this.entityManager.createQuery(jpql);
		query.setFirstResult(iniPos);
		query.setMaxResults(numReg);
		if (null != parameters && parameters.size() > 0) {
			for (String name : parameters.keySet()) {
				query.setParameter(name, parameters.get(name));
			}
		}
		return query.getResultList();
	}

	@Override
	public Query createNativeQuery(String sql) {
		return this.entityManager.createNativeQuery(sql);
	}

	@Override
	public List<Object> callFunction(String nombre, Map<String, Object> parameters, DataSource ds)
			throws ServerException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<T> findAll(String entity, String orderBy) throws ServerException {
		StringBuffer jpql = new StringBuffer();
		jpql.append("SELECT o FROM ");
		jpql.append(entity);
		jpql.append(" o");
		jpql.append(" where o.activo=true ");
		if (orderBy != null) {
			jpql.append(" order by o.");
			jpql.append(orderBy);
		}
		return this.findByQuery(jpql.toString(), new HashMap<String, Object>());
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findByNamedQuery(String namedQuery, Map<String, Object> parameters) {
		Query query = this.entityManager.createNamedQuery(namedQuery);
		if (null != parameters && parameters.size() > 0) {
			for (String name : parameters.keySet()) {
				query.setParameter(name, parameters.get(name));
			}
		}
		return query.getResultList();
	}

}
