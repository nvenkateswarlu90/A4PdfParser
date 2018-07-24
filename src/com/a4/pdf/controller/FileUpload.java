package com.a4.pdf.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.a4.pdf.ipdfService.IPdfService;
import com.a4.pdf.model.InVoiceBean;
import com.a4.pdf.model.PurchaseOrder;
import com.a4.pdf.model.UploadBean;
import com.a4.pdf.parser.ConvertCsvToExcel;
import com.a4.pdf.parser.ExcelMapping;
import com.a4.pdf.parser.Invoiceprofitmaker;
import com.a4.pdf.parser.ProfitMakerPoMapper;
import com.a4.pdf.parser.PurOrdParser;

@Controller
@RequestMapping({ "/", "/uploadFile.htm" })
public class FileUpload {
	@Autowired
	private IPdfService pdfService;
	private static Logger _LOGGER = Logger.getLogger(Class.class);

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView welcomePage(Map<String, Object> model) {
		return new ModelAndView("upload", "uploadBean", new UploadBean());
	}

	@RequestMapping(value = "/parseFile", method = RequestMethod.POST)
	public String upload(@ModelAttribute("uploadBean") UploadBean uploadBean) {
		MultipartFile file = uploadBean.getFile();
		if (uploadBean.getFileType().equals("PO")) {
			savePurchaseOrderDetails(file.getOriginalFilename());
		} else {// Invoice
			saveInvoiceDetails(file.getOriginalFilename());
		}
		return "dropDown";
	}

	@RequestMapping(value = "/getAllPOInvoiceNo")
	@ResponseBody
	public List<String> getAllPoOrInvoiceNumbers(HttpServletRequest req) {
		String fileType = req.getParameter("fileType");
		List<String> listOfPoInvoive = null;
		_LOGGER.info("File Type: " + fileType);
		if (fileType.equalsIgnoreCase("po") || fileType.equalsIgnoreCase("purchaseOrder")) {
			listOfPoInvoive = pdfService.getAllPONumber();
		} else if (fileType.equals("invoice")) {// Invoice
			listOfPoInvoive = pdfService.getAllInvoiceNumber();
		} else {
			listOfPoInvoive = new ArrayList<>();
		}
		return listOfPoInvoive;
	}

	@RequestMapping(value = "/showData")
	public ModelAndView showData(@RequestParam("poInName") String poInvId, @RequestParam("pdfType") String pdfType,
			HttpServletRequest req) {
		ModelAndView mv = null;
		if (pdfType.equalsIgnoreCase("purchaseOrder")) {
			mv = getPurchaseOrderDetails(poInvId);
		} else if (pdfType.equalsIgnoreCase("invoice")) {
			mv = getInvoiceDetails(poInvId);
		}
		return mv;
	}

	private void savePurchaseOrderDetails(String fileName) {
		List<PurchaseOrder> purchaseOrderList = new ArrayList<>();
		try {
			Workbook workbook = ConvertCsvToExcel.getWorkBook(fileName);
			if (fileName.equalsIgnoreCase("Purchase Order_ProfitMaker.pdf")
					|| fileName.equalsIgnoreCase("ProfitMaker_po_ext_description.PDF")) {
				purchaseOrderList = ProfitMakerPoMapper.readExcel(workbook);
			} else if (fileName.equalsIgnoreCase("Order_-_278501PurchaseOrder_124150611664.pdf")) {
				purchaseOrderList = PurOrdParser.readExcel(workbook);
			}
			pdfService.savePoDetails(purchaseOrderList);
		} catch (Exception e) {
			_LOGGER.error("PO details could not be saved: " + e.getMessage());
		}
	}

	private void saveInvoiceDetails(String invoiceFileName) {
		InVoiceBean invoice = new InVoiceBean();
		try {
			Workbook workbook = ConvertCsvToExcel.getWorkBook(invoiceFileName);
			if (invoiceFileName.equalsIgnoreCase("Order279056Invoice124150208343.pdf")) {// smartbook
																							// invoice
				invoice = ExcelMapping.readExcel(workbook);
			} else if (invoiceFileName.equalsIgnoreCase("INV1022_profitmaker.PDF")) {// profitmaker
				invoice = Invoiceprofitmaker.readExcel(workbook);
			}
			pdfService.saveInvoiceDetails(invoice);
		} catch (Exception e) {
			_LOGGER.error("Invoice details could not be saved: " + e.getMessage());
		}
	}

	public ModelAndView getPurchaseOrderDetails(String purchaseOrderNo) {
		List<PurchaseOrder> pruchaseOrderList = pdfService.getPODetails(purchaseOrderNo);
		return new ModelAndView("profitMakerData", "profitMakerPODataList", pruchaseOrderList);
	}

	public ModelAndView getInvoiceDetails(String invoiceNo) {
		InVoiceBean invoiceDetails = pdfService.getInvoiceDetails(invoiceNo);
		List<InVoiceBean> invoiceList = Arrays.asList(invoiceDetails);
		return new ModelAndView("invoiceDetails", "invoiceData", invoiceList);
	}
}
