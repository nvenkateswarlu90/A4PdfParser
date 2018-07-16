package com.a4.pdf.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.a4.pdf.ipdfService.IPdfService;
import com.a4.pdf.model.PurchaseOrder;
import com.a4.pdf.model.UploadBean;
import com.a4.pdf.parser.ConvertCsvToExcel;
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
	public void upload(@ModelAttribute("uploadBean") UploadBean uploadBean){
		MultipartFile file =  uploadBean.getFile();
		System.out.println(file.getOriginalFilename());
		System.out.println(uploadBean.getFileType());
		if(uploadBean.getFileType().equals("PO")){
			getParsePurchaseOrder(file.getOriginalFilename());
		} else{//Invoice
			
		}
	}
	private ModelAndView getParsePurchaseOrder(String fileName){
		pdfService.savePoDetails(null);
		//List<String> poNumbersList = pdfService.getAllPONumber();
		String filename="";
		LinkedHashMap<String,String> valueMap=new LinkedHashMap<String, String>();
		Mapclas contactForm = null;
		List<PurchaseOrder> purchaseOrderList = null;
		try{  
	        //String path=session.getServletContext().getRealPath("/");  
	       // filename=file.getOriginalFilename();  
	          
	       // System.out.println(path+" "+filename);  
	        Workbook workbook=  ConvertCsvToExcel.getWorkBook(fileName);
	        if(filename.equalsIgnoreCase("Purchase Order_ProfitMaker.pdf") || filename.equalsIgnoreCase("ProfitMaker_po_ext_description.PDF")){
	        	purchaseOrderList = ProfitMakerPoMapper.readExcel(workbook);
	        } else {
	        	valueMap=PurOrdParser.readExcel(workbook);
	        }
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
		
	}
	private void parseInvoice(){
		
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

