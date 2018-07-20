package com.a4.pdf.pdfDaoImpl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.a4.pdf.entity.InvoiceDetailsEntity;
import com.a4.pdf.entity.POEntity;
import com.a4.pdf.ipdfDao.IpdfDao;

public class PdfDaoImpl implements IpdfDao {
	private SessionFactory sessionFactory;
	private Logger _LOGGER = Logger.getLogger(PdfDaoImpl.class);

	@Override
	public void saveInvoiceDetails(InvoiceDetailsEntity invoiceDetails) {
		Session session = null;
		Transaction trx = null;
		try {
			session = sessionFactory.openSession();
			trx = session.beginTransaction();
			session.save(invoiceDetails);
			trx.commit();
		} catch (Exception e) {
			_LOGGER.error("unable to save Invoice data:" + e.getMessage());
			System.err.println("unable to save Invoice data:" + e.getMessage());
			if (trx != null) {
				try {
					trx.rollback();
				} catch (Exception exce) {
					_LOGGER.error("Unbale to rollback Invoice details:" + exce.getMessage());
				}
			}
		} finally {
			if (session != null) {
				try {
					session.close();
				} catch (Exception exce) {
					_LOGGER.error("unable to close session connection at save invoice details: " + exce.getCause());
				}
			}
		}

	}

	@Override
	public void savePoDetails(POEntity poEntity) {
		Session session = null;
		Transaction trx = null;
		try {
			session = sessionFactory.openSession();
			trx = session.beginTransaction();
			session.save(poEntity);
			trx.commit();
		} catch (Exception e) {
			_LOGGER.error("unable to save PO data:" + e.getMessage());
			System.err.println("unable to save PO data:" + e.getMessage());
			if (trx != null) {
				try {
					trx.rollback();
				} catch (Exception exce) {
					_LOGGER.error("Unbale to rollback PO details:" + exce.getMessage());
					System.err.println("Unbale to rollback PO details:" + exce.getMessage());
				}
			}
		} finally {
			if (session != null) {
				try {
					session.close();
				} catch (Exception exce) {
					_LOGGER.error("unable to close session connection at save po details: " + exce.getCause());
				}
			}
		}

	}

	@Override
	@SuppressWarnings("unchecked")
	public List<String> getAllInvoiceNumber() {
		Session session = null;
		try {
			session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(InvoiceDetailsEntity.class);
			criteria.setProjection(Projections.property("invoiceNo"));
			List<String> poNumbersList = criteria.list();
			// String data = (String) criteria.uniqueResult();
			return poNumbersList;

		} catch (Exception e) {
			_LOGGER.error("Unable to fetch PO numbers:" + e.getMessage());
		} finally {
			if (session != null) {
				try {
					session.close();
				} catch (Exception exce) {
					_LOGGER.error("unable to close session connection at getAllInvoiveNumber: " + exce.getCause());
				}
			}
		}
		return new ArrayList<>();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<String> getAllPONumber() {
		Session session = null;
		try {
			session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(POEntity.class);
			criteria.setProjection(Projections.property("poNumber"));
			List<String> poNumbersList = criteria.list();
			// String data = (String) criteria.uniqueResult();
			return poNumbersList;

		} catch (Exception e) {
			_LOGGER.error("Unable to fetch PO numbers:" + e.getMessage());
		} finally {
			if (session != null) {
				try {
					session.close();
				} catch (Exception exce) {
					_LOGGER.error("unable to close session connection at getAllPONumbers: " + exce.getCause());
				}
			}
		}
		return new ArrayList<>();
	}

	@Override
	public InvoiceDetailsEntity getInvoiceDetails(String invoiceNo) {
		Session session = null;
		InvoiceDetailsEntity invoiceDetails = null;
		try {
			session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(InvoiceDetailsEntity.class);
			criteria.add(Restrictions.eq("invoiceNo",invoiceNo));//InvoiceNumber//invoiceNo
			invoiceDetails = (InvoiceDetailsEntity) criteria.uniqueResult();

		} catch (Exception e) {
			_LOGGER.error("Unable to fetch PO numbers:" + e.getMessage());
		} finally {
			if (session != null) {
				try {
					session.close();
				} catch (Exception exce) {
					_LOGGER.error("unable to close session connection at getAllPONumbers: " + exce.getCause());
				}
			}
		}
		return invoiceDetails;
	}

	@Override
	public POEntity getPODetails(String poNo) {
		Session session = null;
		POEntity poEntity = null;
		try {
			session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(POEntity.class);
			criteria.add(Restrictions.eq("poNumber",poNo));
			 poEntity = (POEntity) criteria.uniqueResult();
			//return poNumbersList;

		} catch (Exception e) {
			_LOGGER.error("Unable to fetch PO numbers:" + e.getMessage());
		} finally {
			if (session != null) {
				try {
					session.close();
				} catch (Exception exce) {
					_LOGGER.error("unable to close session connection at getAllPONumbers: " + exce.getCause());
				}
			}
		}
		return poEntity;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

}
