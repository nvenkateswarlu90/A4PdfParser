package com.a4.pdf.parser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.util.StringUtils;

import com.a4.pdf.model.InVoiceBean;

public class Invoiceprofitmaker {

	private static final Logger _LOGGER = Logger
			.getLogger(PurOrdParser.class);

	
	public static InVoiceBean readExcel(Workbook workbook ){
		LinkedHashMap <String, String> INVOICE_MAP=new LinkedHashMap<String, String>();
		String productId = null;
		InVoiceBean invoiceBeanobj=new InVoiceBean();
		
		int columnIndex = 0;
		String ProdNo = null;
		String xid = null;
	    List<String> repeatRows = new ArrayList<>();
		Set<String> productXids = new HashSet<String>();
		
		String keyCriteriaTableShipped="Criteria_Table Shipped";
		String keyCriteriaTableShipped2="Criteria2_Table Shipped";

		int mapCount1=1;
		int mapCount2=1;
		
		StringBuilder costingTable=new StringBuilder();
		StringBuilder allOrderdetails=new StringBuilder();


			_LOGGER.info("Total sheets in excel::"+workbook.getNumberOfSheets());
		    Sheet sheet = workbook.getSheetAt(0);
			Iterator<Row> iterator = sheet.iterator();
			_LOGGER.info("Started Processing Product");
		   		   
			Row headerRow = null;
			
			while (iterator.hasNext()) {

				try {
					Row nextRow = iterator.next();
					if (nextRow.getRowNum() < 1)
						continue;
					Iterator<Cell> cellIterator = nextRow.cellIterator();
					if (productId != null) {
						productXids.add(productId);
					}
					boolean checkXid = false;
					//mapCount++;
					//keyCriteriaTableShipped=keyCriteriaTableShipped+"_"+mapCount;
					
					keyCriteriaTableShipped=getMapVar(keyCriteriaTableShipped,mapCount1);
					keyCriteriaTableShipped2=getMapVar(keyCriteriaTableShipped2,mapCount2);
 					while (cellIterator.hasNext()) {
						Cell cell = cellIterator.next();
  						  columnIndex = cell.getColumnIndex();
  						  
 						if (columnIndex + 1 == 1) {
							if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
								xid = getCellValueStrinOrInt(cell);
							} else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
								xid = String.valueOf((int) cell
										.getNumericCellValue());
							} else {
								
								xid = ProdNo;
							}
							checkXid = true;
						} else {
   							checkXid = false;
		
							if(productXids.contains(xid) && repeatRows.size() != 1){
								 if(isRepeateColumn(columnIndex+1)){
									 continue;
								 }
							}
						
      						switch (columnIndex + 1) {
      						
      						case 1://Document ID

      						String  docId=getCellValueStrinOrInt(cell);
      						INVOICE_MAP.put("Document ID", docId);

    							break;
      						case 2://Remote ID
      						String invoiceNumber=getCellValueStrinOrInt(cell);
  							invoiceBeanobj.setInvoiceNumber(invoiceNumber);;

    							break;
      						case 3://Filename
      						String fileName=getCellValueStrinOrInt(cell);
      						INVOICE_MAP.put("filename", fileName);
    							
      						     break;
    							
      						case 4://Received At
      						String receivedAt=getCellValueStrinOrInt(cell);
      						INVOICE_MAP.put("Received At", receivedAt);

      							

    							break;
      						case 5://Processed At
      							String ProcessedAt=getCellValueStrinOrInt(cell);
      							INVOICE_MAP.put("Processed At", ProcessedAt);


    							break;
      						case 6://Ship To Address
      							String OfficeAdress=getCellValueStrinOrInt(cell);
      							INVOICE_MAP.put("Ship To Address", OfficeAdress);
      							invoiceBeanobj.setShippingAddress(OfficeAdress);
      							invoiceBeanobj.setBillAddress(OfficeAdress);
    							break;
    							
      						case 7://FOB Info
      							String fobInfo=getCellValueStrinOrInt(cell);
      							INVOICE_MAP.put("FOB Info", fobInfo);
      							invoiceBeanobj.setLogisticInfo(fobInfo);
    							break;
    					/*		
      						case 8://CUST_Sale Table Data Unit

      							String email=getCellValueStrinOrInt(cell);
      							INVOICE_MAP.put("Email", email);
    							break;*/
    						
      						case 9://CUST_Sale Table Data Customer Po #

      							String customerPo=getCellValueStrinOrInt(cell);
      							INVOICE_MAP.put("CUST_Sale Table Data Customer Po", customerPo);
      							invoiceBeanobj.setCustPO(customerPo);

    							break;
      						case 10://CUST_Sale Table Data Salesperson

      							String salesPerson=getCellValueStrinOrInt(cell);
      							INVOICE_MAP.put("CUST_Sale Table Data Salesperson", salesPerson);
      							invoiceBeanobj.setSalesPerson(salesPerson);

    							break;
      						case 11://CUST_Sale Table Data Order Date

      							String orderDate=getCellValueStrinOrInt(cell);
      							INVOICE_MAP.put("CUST_Sale Table Data Order Date", orderDate);
      							invoiceBeanobj.setOrderDate(orderDate);
      							
    							break;
      						case 12://CUST_Sale Table Data Invoice Date

      							String invoiceDate=getCellValueStrinOrInt(cell);
      							INVOICE_MAP.put("CUST_Sale Table Data Invoice Date", invoiceDate);
      							invoiceBeanobj.setInvoiceDate(invoiceDate);

    							break;
      						case 13://CUST_Sale Table Data Date Shipped

      							String shipDate=getCellValueStrinOrInt(cell);
      							INVOICE_MAP.put("CUST_Sale Table Data Date Shipped", shipDate);
      							invoiceBeanobj.setShipDate(shipDate);

    							break;
      						case 14://CUST_Sale Table Data Invoice #

      							String inhandDate=getCellValueStrinOrInt(cell);
      							INVOICE_MAP.put("CUST_Sale Table Data Invoice #", inhandDate);


    							break;
    							
      						case 15://Ordered Field

      							String orderedField=getCellValueStrinOrInt(cell);
                                allOrderdetails.append(orderedField).append("##");

      							INVOICE_MAP.put("Ordered Field", orderedField);


    							break;
      						case 16://Shipped Field

      							String shippedField=getCellValueStrinOrInt(cell);
                                allOrderdetails.append(shippedField).append("##");

      							INVOICE_MAP.put("Shipped Field", shippedField);


    							break;
      						case 17://Qty BO Field

      							String qtyField=getCellValueStrinOrInt(cell);
                                allOrderdetails.append(qtyField).append("##");
      							INVOICE_MAP.put("Qty BO Field", qtyField);


    							break;
      						case 18://Description Field

      							String DescriptionField=getCellValueStrinOrInt(cell);
                                allOrderdetails.append(DescriptionField).append("##");

      							INVOICE_MAP.put("Description Field", DescriptionField);


    							break;
      						case 19://Price Field

      							String PriceField=getCellValueStrinOrInt(cell);
                                allOrderdetails.append(PriceField).append("##");

      							INVOICE_MAP.put("Price Field", PriceField);

    				

    							break;
      						case 20://Price Per

      							String PricePer=getCellValueStrinOrInt(cell);
                                allOrderdetails.append(PricePer).append("##");

      							INVOICE_MAP.put("Price Per", PricePer);

    							break;
      						case 21://Amount Field

      							String AmountField=getCellValueStrinOrInt(cell);
                                allOrderdetails.append(AmountField).append("##");
      							INVOICE_MAP.put("Amount Field", AmountField);

   
    							break;
    							////////////////////////////
      						case 22://Criteria 1 Table Shipped

      							String ShippedColor=getCellValueStrinOrInt(cell);
      							if(!StringUtils.isEmpty(ShippedColor)){
      							if(INVOICE_MAP.containsKey(keyCriteriaTableShipped)){
      						     String Example=INVOICE_MAP.get(keyCriteriaTableShipped);
      						     Example=Example+"#####";
      						    INVOICE_MAP.put(keyCriteriaTableShipped, Example);
      							}else
      							{
          							INVOICE_MAP.put(keyCriteriaTableShipped, ShippedColor);
	
      							}
      							mapCount1++;
      							}
      							

    							break;
    							
      						case 23://Criteria 1 Table Xtra Small

      							String XtraSmall=getCellValueStrinOrInt(cell);
      							if(!StringUtils.isEmpty(XtraSmall)){
          						if(INVOICE_MAP.containsKey(keyCriteriaTableShipped)){
          							String Example=INVOICE_MAP.get(keyCriteriaTableShipped);
          							XtraSmall=Example+"#####"+"Criteria 1 Table Xtra Small"+":"+XtraSmall;
          							INVOICE_MAP.put(keyCriteriaTableShipped, XtraSmall);
          						}
      							}

    							break;
    						
      						case 24://Criteria 1 Table Small

      							String Small=getCellValueStrinOrInt(cell);
      							if(!StringUtils.isEmpty(Small)){
              						if(INVOICE_MAP.containsKey(keyCriteriaTableShipped)){
              							String Example=INVOICE_MAP.get(keyCriteriaTableShipped);
              							Small=Example+"#####"+"Criteria 1 Table Small"+":"+Small;
              							INVOICE_MAP.put(keyCriteriaTableShipped, Small);
              						}
          							}


    							break;
      						case 25://Criteria 1 Table Medium

      							String Medium=getCellValueStrinOrInt(cell);
      							if(!StringUtils.isEmpty(Medium)){
              						if(INVOICE_MAP.containsKey(keyCriteriaTableShipped)){
              							String Example=INVOICE_MAP.get(keyCriteriaTableShipped);
              							Medium=Example+"#####"+"Criteria 1 Table Medium"+":"+Medium;
              							INVOICE_MAP.put(keyCriteriaTableShipped, Medium);
              						}
          							}


    							break;
      						case 26://Criteria 1 Table Large

      							String  Large=getCellValueStrinOrInt(cell);
      							if(!StringUtils.isEmpty(Large)){
              						if(INVOICE_MAP.containsKey(keyCriteriaTableShipped)){
              							String Example=INVOICE_MAP.get(keyCriteriaTableShipped);
              							Large=Example+"#####"+"Criteria 1 Table Large"+":"+Large;
              							INVOICE_MAP.put(keyCriteriaTableShipped, Large);
              						}
          							}

    							break;
      						case 27://Criteria 1 Table X-Large

      							String XLarge=getCellValueStrinOrInt(cell);
      							if(!StringUtils.isEmpty(XLarge)){
              						if(INVOICE_MAP.containsKey(keyCriteriaTableShipped)){
              							String Example=INVOICE_MAP.get(keyCriteriaTableShipped);
              							XLarge=Example+"#####"+"Criteria 1 Table X-Large"+":"+XLarge;
              							INVOICE_MAP.put(keyCriteriaTableShipped, XLarge);
              						}
          							}

    							break;
      						case 28://Criteria 1 Table XX-Large

      							String XXLarge=getCellValueStrinOrInt(cell);
      							if(!StringUtils.isEmpty(XXLarge)){
              						if(INVOICE_MAP.containsKey(keyCriteriaTableShipped)){
              							String Example=INVOICE_MAP.get(keyCriteriaTableShipped);
              							XXLarge=Example+"#####"+"Criteria 1 Table XX-Large"+":"+XXLarge;
              							INVOICE_MAP.put(keyCriteriaTableShipped, XXLarge);
              						}
          							}

      			
    							break;
    							/////////////////////////////////////////////////////
    								case 29://Criteria 2 Table Shipped
          						String Shipped2Color=getCellValueStrinOrInt(cell);
          							if(!StringUtils.isEmpty(Shipped2Color)){
              							if(INVOICE_MAP.containsKey(keyCriteriaTableShipped2)){
              						     String Example=INVOICE_MAP.get(keyCriteriaTableShipped2);
              						     Example=Example+"#####";
              						    INVOICE_MAP.put(keyCriteriaTableShipped2, Example);
              							}else
              							{
                  							INVOICE_MAP.put(keyCriteriaTableShipped2, Shipped2Color);
        	
              							}
              							mapCount2++;

              							}

          							
          							break;
          							
          						case 30://Criteria 2 Table XXX-Large
          							String XXXLarge=getCellValueStrinOrInt(cell);
          							if(!StringUtils.isEmpty(XXXLarge)){
                  						if(INVOICE_MAP.containsKey(keyCriteriaTableShipped2)){
                  							String Example=INVOICE_MAP.get(keyCriteriaTableShipped2);
                  							XXXLarge=Example+"#####"+"Criteria 2 Table XXX-Large"+":"+XXXLarge;
                  							INVOICE_MAP.put(keyCriteriaTableShipped2, XXXLarge);
                  						}
              							}
          							

          							
          							break;
          							
          						case 31://Criteria 2 Table XXXXL
          							String XXXXL=getCellValueStrinOrInt(cell);
          							if(!StringUtils.isEmpty(XXXXL)){
                  						if(INVOICE_MAP.containsKey(keyCriteriaTableShipped2)){
                  							String Example=INVOICE_MAP.get(keyCriteriaTableShipped2);
                  							XXXXL=Example+"#####"+"Criteria 2 Table XXXXL"+":"+XXXXL;
                  							INVOICE_MAP.put(keyCriteriaTableShipped2, XXXXL);
                  						}
              							}
          							

          							
          							break;
          						case 32://Costing Table Terms
      							String Terms=getCellValueStrinOrInt(cell);
      							costingTable.append(Terms).append("####");
      							INVOICE_MAP.put("Costing Table Terms", Terms);
      							
      							break;
      							
      						case 33://Costing Table Sub-total
      							String Subtotal=getCellValueStrinOrInt(cell);
      							costingTable.append(Subtotal).append("####");
      							INVOICE_MAP.put("Costing Table Sub-total", Subtotal);

      							
      							break;
      							
      						case 34://Costing Table Insurance
      							String Insurance=getCellValueStrinOrInt(cell);
      							costingTable.append(Insurance).append("####");
      							INVOICE_MAP.put("Costing Table Insurance", Insurance);

      							
      							break;
      							
      						case 35://Costing Table Shpg/Hdlg
      							String ShpgHdlg=getCellValueStrinOrInt(cell);
      							costingTable.append(ShpgHdlg).append("####");
      							INVOICE_MAP.put("Costing Table Shpg/Hdlg", ShpgHdlg);

      							
      							break;
      							
      						case 36://Costing Table Sales Tax
      							String SalesTax=getCellValueStrinOrInt(cell);
      							costingTable.append(SalesTax).append("####");
      							INVOICE_MAP.put("Costing Table Sales Tax", SalesTax);

      							
      							break;
      							
      						case 37://Costing Table Total
      							String Total=getCellValueStrinOrInt(cell);
      							costingTable.append(Total);
      							INVOICE_MAP.put("Costing Table Total", Total);
      							break;
      					
      						case 38://INVOICE Address
      							String invoiceAddress=getCellValueStrinOrInt(cell);
      							invoiceBeanobj.setInvoiceAddress(invoiceAddress);
      							

						} // end inner while loop
                      
					} 
 			
			}
			workbook.close();

			
			

			
		} catch (Exception e) {
			_LOGGER.error("Error while Processing excel sheet "
					+ e.getMessage());
			////
			String orderDeatils=getTabledata(INVOICE_MAP, allOrderdetails, costingTable);
			invoiceBeanobj.setOrderDetails(orderDeatils);
			return invoiceBeanobj;
		} finally {
			try {
				workbook.close();
			} catch (IOException e) {
				_LOGGER.error("Error while Processing excel sheet "
						+ e.getMessage());

			}
			_LOGGER.info("Complted processing of excel sheet ");
		
			}
			}
		
		String orderDeatils=getTabledata(INVOICE_MAP, allOrderdetails, costingTable);
			invoiceBeanobj.setOrderDetails(orderDeatils);
			return invoiceBeanobj;	
		
	}
	public static boolean isRepeateColumn(int columnIndex){
		if(columnIndex != 9 && columnIndex != 10 && columnIndex != 11 && columnIndex != 12 &&
		   columnIndex != 13 && columnIndex != 14 && columnIndex != 22 & columnIndex != 23 && 
		   columnIndex != 24 && columnIndex != 25 && columnIndex != 26 && columnIndex != 27 &&
		    columnIndex != 28 && columnIndex != 29 && columnIndex != 30 && columnIndex != 31 ){
			return true;
		}
		return false;
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
	
	
	
	
	public static String getMapVar(String mapStr,int count){
		if(mapStr.contains("#####")){
			String strArr[]=mapStr.split("#####");
			mapStr=strArr[0];
		}
		
			if(mapStr.equals("Criteria_Table Shipped")){
		mapStr="Criteria_Table Shipped"+"#####"+count;
			}else{
		mapStr="Criteria2_Table Shipped"+"#####"+count;
			}
		return mapStr;
		
	}

	
	 public static String getTabledata(LinkedHashMap <String, String> INVOICE_MAP,StringBuilder allOrderdetails,
			 StringBuilder costingTable ){
		 
			StringBuilder table1Data=new StringBuilder();
			StringBuilder table2Data=new StringBuilder();

			table1Data.append(INVOICE_MAP.get("Criteria_Table Shipped#####1"));
			table1Data.append(INVOICE_MAP.get("Criteria_Table Shipped#####2"));
			table1Data.append(INVOICE_MAP.get("Criteria_Table Shipped#####3"));
			table1Data.append(INVOICE_MAP.get("Criteria_Table Shipped#####4"));
			table1Data.append(INVOICE_MAP.get("Criteria_Table Shipped#####5"));
			table1Data.append(INVOICE_MAP.get("Criteria_Table Shipped#####6"));
			
			table2Data.append(INVOICE_MAP.get("Criteria2_Table Shipped#####1"));
			table2Data.append(INVOICE_MAP.get("Criteria2_Table Shipped#####2"));
			table2Data.append(INVOICE_MAP.get("Criteria2_Table Shipped#####3"));
			table2Data.append(INVOICE_MAP.get("Criteria2_Table Shipped#####4"));
			table2Data.append(INVOICE_MAP.get("Criteria2_Table Shipped#####5"));
			table2Data.append(INVOICE_MAP.get("Criteria2_Table Shipped#####6"));
			
			
			allOrderdetails.append("@@").append(table1Data).append("@@").append(table2Data).append("@@")
			.append(costingTable);
			
			System.out.println("length" +allOrderdetails.length());
			
			INVOICE_MAP.put("All_ORDER_DETAILS", allOrderdetails.toString());
			
			INVOICE_MAP.remove("Criteria_Table Shipped#####1");;
			INVOICE_MAP.remove("Criteria_Table Shipped#####2");
			INVOICE_MAP.remove("Criteria_Table Shipped#####3");
			INVOICE_MAP.remove("Criteria_Table Shipped#####4");
			INVOICE_MAP.remove("Criteria_Table Shipped#####5");
			INVOICE_MAP.remove("Criteria_Table Shipped#####6");
			
			INVOICE_MAP.remove("Criteria2_Table Shipped#####1");;
			INVOICE_MAP.remove("Criteria2_Table Shipped#####2");
			INVOICE_MAP.remove("Criteria2_Table Shipped#####3");
			INVOICE_MAP.remove("Criteria2_Table Shipped#####4");
			INVOICE_MAP.remove("Criteria2_Table Shipped#####5");
			INVOICE_MAP.remove("Criteria2_Table Shipped#####6");
			



		return allOrderdetails.toString();
		
	}

	
	
}
