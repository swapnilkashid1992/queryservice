package com.cognologix.dao;

import java.io.Serializable;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognologix.config.HibernateUtil;
import com.cognologix.exception.QueryAlreadyExistsException;
import com.cognologix.exception.QueryDoesNotExistsException;
import com.cognologix.model.Query;
import com.cognologix.repository.QueryRepository;

@Repository
public class QueryDao implements IQueryDao {

	Logger log = LogManager.getLogger(this.getClass());

	@Autowired
	private QueryRepository queryRepository;

	@Override
	public Query addQuery(Query query) throws QueryAlreadyExistsException {
		log.info("Adding Query with identifier " + query.getIdentifier());
		Transaction tx = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			tx = session.beginTransaction();
			int id = (int) session.save(query);
			log.debug("Query Added Successful with Id " + id);
			tx.commit();
			return query;
		} catch (Exception e) {
			tx.rollback();
			throw new QueryAlreadyExistsException("Query Already Exists with identifier");
		}
	}

	@Override
	public Query getQueryByIdentifier(String Identifier) throws QueryDoesNotExistsException {
		log.info("Getting Query with identifier " + Identifier);
		Transaction tx = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			tx = session.beginTransaction();
			Criteria c = session.createCriteria(Query.class);
			c.add(Restrictions.eq("identifier", Identifier));
			List<Query> queries = c.list();
			if (!queries.isEmpty()) {
				Query query = (Query) queries.get(0);
				tx.commit();
				return query;
			} else {
				tx.rollback();
				throw new QueryDoesNotExistsException("Query not present for identifier" + Identifier);
			}
		}
	}

	@Override
	public Query putQueryByIdentifier(String Identifier, String inputquery) throws QueryDoesNotExistsException {
		log.info("Updating Query with identifier " + Identifier);
		Transaction tx = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			tx = session.beginTransaction();
			Query q = getQueryByIdentifier(Identifier);
			if (null != q) {
				q.setQuery(inputquery);
				session.save(q);
				tx.commit();
				session.close();
				return q;
			} else {
				tx.rollback();
				throw new QueryDoesNotExistsException("Query not present for identifier" + Identifier);
			}
		}
	}

	@Override
	public void deleteQueryByIdentifier(String Identifier) throws QueryDoesNotExistsException {
		log.info("Updating Query with identifier " + Identifier);
		Transaction tx = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			tx = session.beginTransaction();
			Query q = getQueryByIdentifier(Identifier);
			if (null != q) {
				session.delete(q);
				tx.commit();
			}
		}
	}
}
