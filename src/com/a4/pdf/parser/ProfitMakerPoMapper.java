package com.a4.pdf.parser;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.util.StringUtils;

import com.a4.pdf.model.PurchaseOrder;





public class ProfitMakerPoMapper {
	
	private static final Logger _LOGGER = Logger.getLogger(ProfitMakerPoMapper.class);
	public static List<PurchaseOrder> readExcel(Workbook workbook ){
		LinkedHashMap <String, PurchaseOrder> valuesMap=new LinkedHashMap<>();
		int columnIndex=0;
		List<PurchaseOrder> purchaseOrderList = new ArrayList<>();
		try{
			String headerName = "";
		_LOGGER.info("Total sheets in excel::"+workbook.getNumberOfSheets());
	    Sheet sheet = workbook.getSheetAt(0);
		Iterator<Row> iterator = sheet.iterator();
		_LOGGER.info("Started Processing Product");
	   
	    String xid = null;
	    
	   
		Row headerRow = null;
		StringBuilder orderDetails = new StringBuilder();
		StringBuilder productCriteria = new StringBuilder();
		while (iterator.hasNext()) {
			try{
			Row nextRow = iterator.next();
		PurchaseOrder purchaseOrder = new PurchaseOrder();
			if (nextRow.getRowNum() == 0) {
				headerRow = nextRow;
				continue;
			}
			
			Iterator<Cell> cellIterator = nextRow.cellIterator();
			
			 boolean checkXid  = false; //imp line
			//boolean checkXid  = true; //imp line
			//xid=getProductXid(nextRow);
			while (cellIterator.hasNext()) {
				Cell cell = cellIterator.next();
			    columnIndex = cell.getColumnIndex();
			    /*if (columnIndex == 0) {
					continue;
				}*/
				headerName = getHeaderName(columnIndex, headerRow);
				switch (headerName) {
					    /*case 1://XID
						//productId=xid;//CommonUtility.getCellValueStrinOrInt(cell);
					String str=getCellValueStrinOrInt(cell);
						break;*/
					    case "Filename":
					    	String FILENAME=getCellValueStrinOrInt(cell);
					    	if(!StringUtils.isEmpty(FILENAME)){
					    		purchaseOrder.setFileName(FILENAME);
					    	//valuesMap.put(headerName, FILENAME.trim());
					    	}
							break;
					    case "Received At":
					    	String str3=getCellValueStrinOrInt(cell);
					    	if(!StringUtils.isEmpty(str3)){
					    		purchaseOrder.setReceivedDate(str3);
					    		//valuesMap.put(headerName, str3.trim());
						    	}
							break;
					    case "Processed At":
					    	String str4=getCellValueStrinOrInt(cell);
					    	if(!StringUtils.isEmpty(str4)){
					    		purchaseOrder.setProcessedDate(str4);
						    	//valuesMap.put(headerName, str4.trim());
						    	}
							break;
					    case "PO Adress":
					    	String poAdreess=getCellValueStrinOrInt(cell);
					    	if(!StringUtils.isEmpty(poAdreess)){
					    		poAdreess = poAdreess.replaceAll("\n", ",");
					    		purchaseOrder.setPoAddress(poAdreess);
						    	//valuesMap.put(headerName, poAdreess.trim());
						    	}
							break;	
							
					    case "Vendor Details":
					    	String VendorDetails=getCellValueStrinOrInt(cell);
					    	if(!StringUtils.isEmpty(VendorDetails)){
					    		VendorDetails = VendorDetails.replaceAll("\n", ",");
					    		purchaseOrder.setVendorAddress(VendorDetails);
					    		//valuesMap.put(headerName, VendorDetails.trim());
					    	}
					    	break;
							
					    case "Vend#":
					    	String vendNo=getCellValueStrinOrInt(cell);
					    	if(!StringUtils.isEmpty(vendNo)){
					    		purchaseOrder.setVendorNo(vendNo);
					    	//valuesMap.put(headerName, SHIPADDRESS.trim());
					    	}
					    	break;
					    case "Req Ship":
					    	String requestDate=getCellValueStrinOrInt(cell);
					    	if(!StringUtils.isEmpty(requestDate)){
					    		purchaseOrder.setShippingRequest(requestDate);
					    	//valuesMap.put(headerName, requestDate.trim());
					    	}
					    	break;
					    case "PO #":
					    	String poNo=getCellValueStrinOrInt(cell);
					    	if(!StringUtils.isEmpty(poNo)){
					    		purchaseOrder.setPoNo(poNo);
					    	//valuesMap.put(headerName, poNo.trim());
					    	}
					    	break;
					    case "Job #":
					    	String poJob=getCellValueStrinOrInt(cell);
					    	if(!StringUtils.isEmpty(poJob)){
					    		purchaseOrder.setJob(poJob);
								//METHOD = METHOD.toUpperCase();
								//METHOD = METHOD.replace("D:", "");
								//valuesMap.put(headerName, METHOD.trim());
					    	}
					    	break;
							
					    case "Shipping Address":
					    	String shipAddress=getCellValueStrinOrInt(cell);
					    	if(!StringUtils.isEmpty(shipAddress)){
					    		shipAddress = shipAddress.replaceAll("\n", ",");
					    		purchaseOrder.setShippingAddress(shipAddress);
					    	//valuesMap.put(headerName, SHIPVIA.trim());
					    	}
					    	break;	
					    case "Logistic INfo":
					    	String logisticInfo=getCellValueStrinOrInt(cell);
					    	if(!StringUtils.isEmpty(logisticInfo)){
					    		purchaseOrder.setLogisticInfo(logisticInfo);
					    	//valuesMap.put(headerName, LogisticINfo.trim());
					    	}
					    	break;	
							
					    case "Cust po#":
					    	String custPO=getCellValueStrinOrInt(cell);
					    	if(!StringUtils.isEmpty(custPO)){
					    		purchaseOrder.setCustomerPo(custPO);
					    	//valuesMap.put(headerName, custPO.trim());
					    	}
					    	break;
					    case "Ord date":
					    	Date ordDate=cell.getDateCellValue();
					    	if(!StringUtils.isEmpty(ordDate)){
					    		//String dd= DateFormatUtils.format(ordDate, "yyyy/MM/dd");
					    		 DateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");
					    	        String od = dateFormat.format(ordDate);
					    	        String orderDate= DateFormatUtils.format(ordDate, "dd/MM/yy");
					    		purchaseOrder.setOrderDate(orderDate);
					    	//valuesMap.put(headerName, OrdDate.trim());
					    	}
					    	break;
					    case "Terms":
					    	String terms=getCellValueStrinOrInt(cell);
					    	if(!StringUtils.isEmpty(terms)){
					    		purchaseOrder.setTerms(terms);
					    	//valuesMap.put(headerName, Terms.trim());
					    	}
					    	break;	
					    case "SalesPerson":
					    	String SalesPerson=getCellValueStrinOrInt(cell);
					    	if(!StringUtils.isEmpty(SalesPerson)){
					    		purchaseOrder.setSalesPerson(SalesPerson);
					    	//valuesMap.put(headerName, SalesPerson.trim());
					    	}
					    	break;	
					    case "Order Detail Table Ordered":
					    	String Ordered=getCellValueStrinOrInt(cell);
					    	if(!StringUtils.isEmpty(Ordered)){
					    		orderDetails.append("Ordered:").append(Ordered);
					    	//valuesMap.put(headerName, Ordered.trim());
					    	}
					    	break;
					    case "Order Detail Table Shipped":
					    	String ordered=getCellValueStrinOrInt(cell);
					    	if(!StringUtils.isEmpty(ordered)){
					    		
					    	//valuesMap.put(headerName, Shipped.trim());
					    	}
					    	break;
					    case "Order Detail Table Item#":
					    	String Item=getCellValueStrinOrInt(cell);
					    	if(!StringUtils.isEmpty(Item)){
					    		orderDetails.append("###").append("Items:").append(Item);
					    	//valuesMap.put(headerName, Item.trim());
					    	}
					    	break;
					    case "Order Detail Table Description":
					    	String Description=getCellValueStrinOrInt(cell);
					    	if(!StringUtils.isEmpty(Description)){
					    		orderDetails.append("###").append("Description:").append(Description);
					    	//valuesMap.put(headerName, Description.trim());
					    	}
					    	break;
					    case "Order Detail Table Per":
					    	String PerQuantity=getCellValueStrinOrInt(cell);
					    	if(!StringUtils.isEmpty(PerQuantity)){
					    		orderDetails.append("###").append("Per:").append(PerQuantity);
					    	//valuesMap.put(headerName, PerQuantity.trim());
					    	}
					    	break;
					    case "Order Detail Table Unit Cost":
					    	String Cost=getCellValueDouble(cell);
					    	if(!StringUtils.isEmpty(Cost)){
					    		orderDetails.append("###").append("Cost:").append(Cost);
					    	}		
					    	break;
					    case "Table Data":
					    	String color = cell.getStringCellValue();
					    	if(!StringUtils.isEmpty(color)){
					    		if(!StringUtils.isEmpty(color)){
					    			if(color.contains(":")){
					    				productCriteria.append(color).append("###");
					    				//valuesMap.put("Color", color.split(":")[1]);
					    			} else {
					    				productCriteria.append(color);
					    			}
						    		
						    	}	
					    	}
					    	break;
					    case "Table Data1":
					    case "Table Data2":
					    case "Table Data3":
					    case "Table Data4":
					    case "Table Data5":
					    case "Table Data6":
					    case "Table Data7":
					    	String sizeVal=getCellValueStrinOrInt(cell);
					    	if(!StringUtils.isEmpty(sizeVal)){
					    		productCriteria.append(sizeVal).append("#");
					    		//valuesMap.put(headerName, Data7.trim());
					    	}	
							
					    	break;
					
					    case "Imprinting Instruction":
					    	String ImprintingInstruction=getCellValueStrinOrInt(cell);
					    	if(!StringUtils.isEmpty(ImprintingInstruction)){
					    		purchaseOrder.setProductImprintLocation(ImprintingInstruction);
					    		//valuesMap.put(headerName, ImprintingInstruction.trim());
					    	}
					    	break;
					    case "Terms And Condition":
					    	String termsAndCondition=getCellValueStrinOrInt(cell);
					    	if(!StringUtils.isEmpty(termsAndCondition)){
					    		purchaseOrder.setTermsAndConditions(termsAndCondition);
					    		//valuesMap.put(headerName, TermsAndCondition.trim());
					    	}
					    	break;
					    case "Instruction To Factory1":
					    	String instructionToFactory=getCellValueStrinOrInt(cell);
					    	if(!StringUtils.isEmpty(instructionToFactory)){
					    		purchaseOrder.setInstructionsToFactory1(instructionToFactory);
					    		//valuesMap.put(headerName, InstructionToFactory.trim());
					    	}
					    	break;
					    case "Instruction To Factory2":
					    	String instructionToFactory2=getCellValueStrinOrInt(cell);
					    	if(!StringUtils.isEmpty(instructionToFactory2)){
					    		purchaseOrder.setInstructionsToFactory2(instructionToFactory2);
					    		//valuesMap.put(headerName, InstructionToFactory.trim());
					    	}
					    	break;
				}  // end inner while loop	
			}	
			purchaseOrder.setOrderDetails(orderDetails);
			purchaseOrder.setProductcriteria(productCriteria);
			purchaseOrderList.add(purchaseOrder);
			orderDetails     = new StringBuilder();
			productCriteria  = new StringBuilder();
			purchaseOrder    = new PurchaseOrder();
			}catch(Exception e){
				_LOGGER.error("Error while Processing excel sheet " +e.getMessage()+"at column"+columnIndex);
				}
		}
		workbook.close();
		
		}catch(Exception e){
			_LOGGER.error("Error while Processing excel sheet " +e.getMessage());
		//return finalResult;
		}finally{
			try {
				workbook.close();
			} catch (IOException e) { 
				_LOGGER.error("Error while Processing excel sheet" +e.getMessage());
	
			}
				_LOGGER.info("Complted processing of excel sheet ");
				
		}
		return purchaseOrderList;
		
	}
	private static String getHeaderName(int columnIndex, Row headerRow) {
		Cell cell2 = headerRow.getCell(columnIndex);
		String headerName = getCellValueStrinOrInt(cell2);
		// columnIndex = ProGolfHeaderMapping.getHeaderIndex(headerName);
		return headerName;
	}
	public static String getCellValueStrinOrInt(Cell cell) {
		String value = "";
		try {
			if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
				value = cell.getStringCellValue().trim();
			} else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
				int numericValue = (int) cell.getNumericCellValue();
				value = String.valueOf(numericValue).trim();
			}else if(cell.getCellType() == Cell.CELL_TYPE_ERROR){
				//value = String.valueOf(cell.getErrorCellValue());
				value = Byte.toString(cell.getErrorCellValue()).trim();
				value="";
			}else if(cell.getCellType() == Cell.CELL_TYPE_BOOLEAN){
				//value = String.valueOf(cell.getErrorCellValue());
				boolean val = cell.getBooleanCellValue();
				value=String.valueOf(val);
			}
			
		} catch (Exception e) {
			/*_LOGGER.error("Cell value convert into String/Int format: "
					+ e.getMessage());*/
		}

		return value;
	}
	public static String getCellValueDouble(Cell cell) {
		String value = "";
		try {
			if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
				value = cell.getStringCellValue().trim();
			} else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
				double doubleValue = cell.getNumericCellValue();
				value = String.valueOf(doubleValue).trim();
			}else if(cell.getCellType() == Cell.CELL_TYPE_ERROR){
				//value = String.valueOf(cell.getErrorCellValue());
				value = Byte.toString(cell.getErrorCellValue()).trim();
				value="";
			}
		} catch (Exception e) {
			_LOGGER.error("Cell value convert into Double: " + e.getMessage());
		}

		return value;
	}

}
