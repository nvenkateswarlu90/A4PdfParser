package com.a4.pdf.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Workbook;
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
import com.a4.pdf.parser.Mapclas;
import com.a4.pdf.parser.ProfitMakerPoMapper;
import com.a4.pdf.parser.PurOrdParser;



@Controller
@RequestMapping({ "/", "/uploadFile.htm" })
public class FileUpload {
	//@Autowired
	private IPdfService pdfService;
	private static Logger _LOGGER = Logger.getLogger(Class.class);
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView welcomePage(Map<String, Object> model) {
		
		return new ModelAndView("upload", "uploadBean", new UploadBean());
		/*FtpLoginBean ftpLogin = new FtpLoginBean(); //uncommented code while using ftp
		model.put("ftpLoginBean", ftpLogin);
		return "ftpLogin";*/ 
		}
	@RequestMapping(value="/parseFile",method=RequestMethod.POST)  
	public String upload(@ModelAttribute("uploadBean") UploadBean uploadBean){
		MultipartFile file =  uploadBean.getFile();
		System.out.println(file.getOriginalFilename());
		System.out.println(uploadBean.getFileType());
		
		//pdfService.saveInvoiceDetails(new InVoiceBean());
		//pdfService.savePoDetails(new ArrayList<PurchaseOrder>());
		if(uploadBean.getFileType().equals("PO")){
			savePurchaseOrderDetails(file.getOriginalFilename());
		} else{//Invoice
			saveInvoiceDetails(file.getOriginalFilename());
		}
		//pdfService.getPODetails("1201");
		return "dropDown";
	}
	@RequestMapping(value="/getAllPOInvoiceNo")
	@ResponseBody
	public List<String> getAllPoOrInvoiceNumbers(HttpServletRequest req){
		String fileType = req.getParameter("fileType");
		List<String> listOfPoInvoive = null;
		if(fileType.equalsIgnoreCase("po") || fileType.equalsIgnoreCase("purchaseOrder")){
			listOfPoInvoive = pdfService.getAllPONumber();
		} else if(fileType.equals("invoice")){//Invoice
			listOfPoInvoive = pdfService.getAllInvoiceNumber();
		} else {
			listOfPoInvoive = new ArrayList<>();
		}
		//pdfService.
		return listOfPoInvoive;
	}
	@RequestMapping(value="/showData")
	public ModelAndView showData(@RequestParam("poInName") String poInvId,@RequestParam("pdfType") String pdfType,HttpServletRequest req){
		ModelAndView mv = null;
        if(pdfType.equalsIgnoreCase("purchaseOrder")){
        	mv = getPurchaseOrderDetails(poInvId);
        } else if(pdfType.equalsIgnoreCase("invoice")) {
        	mv = getInvoiceDetails(poInvId);
        }
        return mv;
	}	
	private void savePurchaseOrderDetails(String fileName){
		//String filename="";
		LinkedHashMap<String,String> valueMap=new LinkedHashMap<String, String>();
		Mapclas contactForm = null;
		List<PurchaseOrder> purchaseOrderList = new ArrayList<>();
		try{  
	       //String path=session.getServletContext().getRealPath("/");  
	       // filename=file.getOriginalFilename();  
	          
	       // System.out.println(path+" "+filename);  
	        Workbook workbook=  ConvertCsvToExcel.getWorkBook(fileName);
	        if(fileName.equalsIgnoreCase("Purchase Order_ProfitMaker.pdf") || fileName.equalsIgnoreCase("ProfitMaker_po_ext_description.PDF")){
	        	purchaseOrderList = ProfitMakerPoMapper.readExcel(workbook);
	        } else if(fileName.equalsIgnoreCase("Order_-_278501PurchaseOrder_124150611664.pdf")){
	        	purchaseOrderList = PurOrdParser.readExcel(workbook);
	        }
			pdfService.savePoDetails(purchaseOrderList);	
	        }catch(Exception e){
	        	_LOGGER.error("PO details could not be saved: "+e.getMessage());
	        }  
		
		
		/*if(filename.contains("ProfitMaker")){
			return new ModelAndView("profitMakerData","profitMakerPODataList",purchaseOrderList);
		} else {
			return new ModelAndView("user","contactForm",contactForm);
		}*/
	}
	private void saveInvoiceDetails(String invoiceFileName){
		LinkedHashMap<String,String> valueMap=new LinkedHashMap<String, String>();
		Mapclas contactForm = null;
		InVoiceBean invoice  = new InVoiceBean();
		try{    
	        Workbook workbook=  ConvertCsvToExcel.getWorkBook(invoiceFileName);
	        if(invoiceFileName.equalsIgnoreCase("Order279056Invoice124150208343.pdf")){//smartbook invoice
	        	invoice = ExcelMapping.readExcel(workbook);
	        } else if(invoiceFileName.equalsIgnoreCase("INV1022_profitmaker.PDF")){//profitmaker
	        	//valueMap=PurOrdParser.readExcel(workbook);
	        	invoice = Invoiceprofitmaker.readExcel(workbook);
	        }
	        
	        //contactForm = new Mapclas();
			//contactForm.setContactMap(valueMap);
			pdfService.saveInvoiceDetails(invoice);
	        }catch(Exception e){
	        	_LOGGER.error("Invoice details could not be saved: "+e.getMessage());
	        }  
	}
	
	public ModelAndView getPurchaseOrderDetails(String purchaseOrderNo){
		List<PurchaseOrder> pruchaseOrderList = pdfService.getPODetails(purchaseOrderNo);
		return new ModelAndView("profitMakerData","profitMakerPODataList",pruchaseOrderList);
	}
	
	public ModelAndView getInvoiceDetails(String invoiceNo){
	  InVoiceBean invoiceDetails = pdfService.getInvoiceDetails(invoiceNo);
	  List<InVoiceBean> invoiceList = Arrays.asList(invoiceDetails);
	  return new ModelAndView("invoiceDetails","invoiceData",invoiceList);
	}
	/*
	@RequestMapping(value="/parseFile123",method=RequestMethod.POST)  
	public ModelAndView upload1(@RequestParam CommonsMultipartFile file,HttpSession session){ 
		String filename="";
		LinkedHashMap<String,String> valueMap=new LinkedHashMap<String, String>();
		Mapclas contactForm = null;
		List<PurchaseOrder> purchaseOrderList = null;
		try{  
	        String path=session.getServletContext().getRealPath("/");  
	        filename=file.getOriginalFilename();  
	          
	        System.out.println(path+" "+filename);  
	        Workbook workbook=  ConvertCsvToExcel.getWorkBook(filename);
	        if(filename.trim().equals("Order279056Invoice124150208343.pdf")){
	        	valueMap=ExcelMapping.readExcel(workbook);	
	        } else if(filename.equalsIgnoreCase("Purchase Order_ProfitMaker.pdf") || filename.equalsIgnoreCase("ProfitMaker_po_ext_description.PDF")){
	        	purchaseOrderList = ProfitMakerPoMapper.readExcel(workbook);
	        } else{
	        	valueMap=PurOrdParser.readExcel(workbook);
	        }
	        
	        
	        try{  
	        byte barr[]=file.getBytes();  
	          
	        BufferedOutputStream bout=new BufferedOutputStream(  
	                 new FileOutputStream(path+"/"+filename));  
	        bout.write(barr);  
	        bout.flush();  
	        bout.close();  
	          
	         contactForm = new Mapclas();
			contactForm.setContactMap(valueMap);
			
			//return new ModelAndView("add_contact" , "contactForm", contactForm);
	        }catch(Exception e){
	        	System.out.println(e);
	        }  
		if(filename.contains("ProfitMaker")){
			return new ModelAndView("profitMakerData","profitMakerPODataList",purchaseOrderList);
		} else {
			return new ModelAndView("user","contactForm",contactForm);
		}
	          
	    }*/
	public IPdfService getPdfService() {
		return pdfService;
	}
	public void setPdfService(IPdfService pdfService) {
		this.pdfService = pdfService;
	}

}

