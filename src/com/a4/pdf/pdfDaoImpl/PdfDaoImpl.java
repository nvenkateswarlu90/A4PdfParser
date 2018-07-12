package com.a4.pdf.pdfDaoImpl;

import org.hibernate.SessionFactory;

import com.a4.pdf.entity.InvoiceDetailsEntity;
import com.a4.pdf.entity.POEntity;
import com.a4.pdf.ipdfDao.IpdfDao;

public class PdfDaoImpl implements IpdfDao{
	private SessionFactory sessionFactory;
		
@Override
public void saveInvoiceDetails(InvoiceDetailsEntity invoiceDetails) {
	// TODO Auto-generated method stub
	
}
@Override
	public void savePoDetails(POEntity poEntity) {
		// TODO Auto-generated method stub
		
	}
public SessionFactory getSessionFactory() {
	return sessionFactory;
}
public void setSessionFactory(SessionFactory sessionFactory) {
	this.sessionFactory = sessionFactory;
}

}
