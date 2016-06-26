package com.news.cd.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

@Transactional
public abstract class AbstractDAO<PK extends Serializable, E> {
	private final Class<E> persitentClass;
	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	public AbstractDAO() {
		this.persitentClass = (Class<E>) ((ParameterizedType) this.getClass()
				.getGenericSuperclass()).getActualTypeArguments()[1];
	}

	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@SuppressWarnings("unchecked")
	protected E getByKey(PK key) {
		try {
			return (E) getSession().get(persitentClass, key);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	protected boolean persist(E entity) {
		try {
			getSession().persist(entity);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	protected boolean delete(E entity) {
		try {
			getSession().delete(entity);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	protected Criteria createEntityCriteria() {
		try {
			return getSession().createCriteria(persitentClass);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
