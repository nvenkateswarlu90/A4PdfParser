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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.a4.pdf.entity.InvoiceDetailsEntity;
import com.a4.pdf.entity.POEntity;
import com.a4.pdf.ipdfDao.IpdfDao;

@Repository
public class PdfDaoImpl implements IpdfDao {
	@Autowired
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
			_LOGGER.error("unable to save Invoice data:" + e.getCause());
			System.err.println("unable to save Invoice data:" + e.getCause());
			if (trx != null) {
				try {
					trx.rollback();
				} catch (Exception exce) {
					_LOGGER.error("Unbale to rollback Invoice details:" + exce.getCause());
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
		_LOGGER.info("Invoice Data has been saved successfully in DB");
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
			_LOGGER.error("unable to save PO data:" + e.getCause());
			if (trx != null) {
				try {
					trx.rollback();
				} catch (Exception exce) {
					_LOGGER.error("Unbale to rollback PO details:" + exce.getCause());
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
		_LOGGER.info("PO Data has been saved successfully in DB");
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
			return poNumbersList;

		} catch (Exception e) {
			_LOGGER.error("Unable to fetch PO numbers:" + e.getCause());
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
			return poNumbersList;

		} catch (Exception e) {
			_LOGGER.error("Unable to fetch PO numbers:" + e.getCause());
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
			criteria.add(Restrictions.eq("invoiceNo", invoiceNo));// InvoiceNumber//invoiceNo
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
			criteria.add(Restrictions.eq("poNumber", poNo));
			poEntity = (POEntity) criteria.uniqueResult();
		} catch (Exception e) {
			_LOGGER.error("Unable to fetch PO numbers:" + e.getCause());
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

}
