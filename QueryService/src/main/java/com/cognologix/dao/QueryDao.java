package com.cognologix.dao;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cognologix.config.HibernateUtil;
import com.cognologix.exception.QueryAlreadyExistsException;
import com.cognologix.exception.QueryDoesNotExistsException;
import com.cognologix.exception.QueryException;
import com.cognologix.model.Query;

@Repository
public class QueryDao implements IQueryDao {

	Logger log = LogManager.getLogger(this.getClass());

	@Override
	@Transactional
	public Query addQuery(final Query query) throws QueryAlreadyExistsException {
		log.info("Adding Query with identifier {}",query.getIdentifier());
		try (final Session session = HibernateUtil.getSessionFactory().openSession()) {
			final int id = (int) session.save(query);
			log.debug("Query Added Successful with Id {}", id);
			return query;
		} catch (Exception e) {
			throw new QueryAlreadyExistsException("Query Already Exists with identifier");
		}
	}

	@Override
	@Transactional
	public Query getQueryByIdentifier(final String Identifier) throws QueryDoesNotExistsException, QueryException{
		log.info("Getting Query with identifier {}" , Identifier);
		try (final Session session = HibernateUtil.getSessionFactory().openSession()) {
			final Criteria c = session.createCriteria(Query.class);
			c.add(Restrictions.eq("identifier", Identifier));
			final List<Query> queries = c.list();
			if (queries!=null && !queries.isEmpty()) {
				final Query query = (Query) queries.get(0);
				return query;
			} else {
				throw new QueryDoesNotExistsException("Query not present for identifier" + Identifier);
			}
		}catch (Exception e) {
			throw new QueryException(e);
		}
	}

	@Override
	@Transactional
	public Query putQueryByIdentifier(final String Identifier,final  String inputquery) throws QueryDoesNotExistsException,QueryException {
		log.info("Updating Query with identifier {} ", Identifier);
		try (final Session session = HibernateUtil.getSessionFactory().openSession()) {
			final Query q = getQueryByIdentifier(Identifier);
			if (null != q) {
				q.setQuery(inputquery);
				session.saveOrUpdate(q);
				return q;
			} else {
				throw new QueryDoesNotExistsException("Query not present for identifier" + Identifier);
			}
		}catch (Exception e) {
			throw new QueryException(e);
		}
		
	}

	@Override
	@Transactional
	public void deleteQueryByIdentifier(final String Identifier) throws QueryDoesNotExistsException {
		log.info("Updating Query with identifier {}", Identifier);
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			final Query q = getQueryByIdentifier(Identifier);
			if (null != q) {
				session.delete(q);
		}
			}catch (Exception e) {
			throw new QueryException(e);
		}
	}
}
