package com.a4.pdf.pdfServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;

import com.a4.pdf.ipdfDao.IpdfDao;
import com.a4.pdf.ipdfService.IPdfService;

public class PdfServiceImpl implements IPdfService{
	@Autowired
	private IpdfDao pdfDao;
@Override
public void saveInvoiceDetails() {
	pdfDao.saveInvoiceDetails();
}
@Override
	public void savePoDetails() {
		pdfDao.savePoDetails();
	}
}
