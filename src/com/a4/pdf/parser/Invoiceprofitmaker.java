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

public class Invoiceprofitmaker {

	private static final Logger _LOGGER = Logger
			.getLogger(PurOrdParser_latest.class);

	
	public static LinkedHashMap<String, String> readExcel(Workbook workbook ){
		LinkedHashMap <String, String> INVOICE_MAP=new LinkedHashMap<String, String>();
		String productId = null;

		int columnIndex = 0;
		String ProdNo = null;
		String xid = null;
	    List<String> repeatRows = new ArrayList<>();
		Set<String> productXids = new HashSet<String>();
		
		String ShippedColor="";	
		String Shipped2Color="";
		
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

    							break;
      						case 3://Filename
      						String filename=getCellValueStrinOrInt(cell);
      						INVOICE_MAP.put("filename", filename);
    							
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
      							String Officeadress=getCellValueStrinOrInt(cell);
      							INVOICE_MAP.put("Ship To Address", Officeadress);

    							break;
    							
      						case 7://FOB Info
      							String FOBInfo=getCellValueStrinOrInt(cell);
      							INVOICE_MAP.put("FOB Info", FOBInfo);
    							break;
    					/*		
      						case 8://CUST_Sale Table Data Unit

      							String email=getCellValueStrinOrInt(cell);
      							INVOICE_MAP.put("Email", email);
    							break;*/
    						
      						case 9://CUST_Sale Table Data Customer Po #

      							String CustomerPo=getCellValueStrinOrInt(cell);
      							INVOICE_MAP.put("CUST_Sale Table Data Customer Po", CustomerPo);


    							break;
      						case 10://CUST_Sale Table Data Salesperson

      							String Salesperson=getCellValueStrinOrInt(cell);
      							INVOICE_MAP.put("CUST_Sale Table Data Salesperson", Salesperson);


    							break;
      						case 11://CUST_Sale Table Data Order Date

      							String OrderDate=getCellValueStrinOrInt(cell);
      							INVOICE_MAP.put("CUST_Sale Table Data Order Date", OrderDate);


    							break;
      						case 12://CUST_Sale Table Data Invoice Date

      							String InvoiceDate=getCellValueStrinOrInt(cell);
      							INVOICE_MAP.put("CUST_Sale Table Data Invoice Date", InvoiceDate);


    							break;
      						case 13://CUST_Sale Table Data Date Shipped

      							String ShipDate=getCellValueStrinOrInt(cell);
      							INVOICE_MAP.put("CUST_Sale Table Data Date Shipped", ShipDate);


    							break;
      						case 14://CUST_Sale Table Data Invoice #

      							String InHandDate=getCellValueStrinOrInt(cell);
      							INVOICE_MAP.put("CUST_Sale Table Data Invoice #", InHandDate);


    							break;
    							
      						case 15://Ordered Field

      							String OrderedField=getCellValueStrinOrInt(cell);
      							INVOICE_MAP.put("Ordered Field", OrderedField);


    							break;
      						case 16://Shipped Field

      							String ShippedField=getCellValueStrinOrInt(cell);
      							INVOICE_MAP.put("Shipped Field", ShippedField);


    							break;
      						case 17://Qty BO Field

      							String QtyField=getCellValueStrinOrInt(cell);
      							INVOICE_MAP.put("Qty BO Field", QtyField);


    							break;
      						case 18://Description Field

      							String DescriptionField=getCellValueStrinOrInt(cell);
      							INVOICE_MAP.put("Description Field", DescriptionField);


    							break;
      						case 19://Price Field

      							String PriceField=getCellValueStrinOrInt(cell);
      							INVOICE_MAP.put("Price Field", PriceField);

    				

    							break;
      						case 20://Price Per

      							String PricePer=getCellValueStrinOrInt(cell);
      							INVOICE_MAP.put("Price Per", PricePer);

    							break;
      						case 21://Amount Field

      							String AmountField=getCellValueStrinOrInt(cell);
      							INVOICE_MAP.put("Amount Field", AmountField);

   
    							break;
    							
      						case 22://Criteria 1 Table Shipped

      						    ShippedColor=getCellValueStrinOrInt(cell);
      							if(!StringUtils.isEmpty(ShippedColor)){
      							if(INVOICE_MAP.containsKey(ShippedColor)){
      						     String Example=INVOICE_MAP.get(ShippedColor);
      						     Example=Example+"#####";
      						    INVOICE_MAP.put(ShippedColor, Example);
      							}else
      							{
          							INVOICE_MAP.put("Criteria 1 Table Shipped", ShippedColor);
	
      							}
      							}
      							

    							break;
    							
      						case 23://Criteria 1 Table Xtra Small

      							String XtraSmall=getCellValueStrinOrInt(cell);
      							if(!StringUtils.isEmpty(XtraSmall)){
          						if(INVOICE_MAP.containsKey(ShippedColor)){
          							String Example=INVOICE_MAP.get(ShippedColor);
          							XtraSmall=Example+"#####"+"Criteria 1 Table Xtra Small"+":"+XtraSmall;
          							INVOICE_MAP.put("Criteria 1 Table Xtra Small", XtraSmall);
          						}
      							}

    							break;
    						
      						case 24://Criteria 1 Table Small

      							String Small=getCellValueStrinOrInt(cell);
      							if(!StringUtils.isEmpty(Small)){
              						if(INVOICE_MAP.containsKey(ShippedColor)){
              							String Example=INVOICE_MAP.get(ShippedColor);
              							Small=Example+"#####"+"Criteria 1 Table Small"+":"+Small;
              							INVOICE_MAP.put("Criteria 1 Table Small", Small);
              						}
          							}


    							break;
      						case 25://Criteria 1 Table Medium

      							String Medium=getCellValueStrinOrInt(cell);
      							if(!StringUtils.isEmpty(Medium)){
              						if(INVOICE_MAP.containsKey(ShippedColor)){
              							String Example=INVOICE_MAP.get(ShippedColor);
              							Medium=Example+"#####"+"Criteria 1 Table Small"+":"+Medium;
              							INVOICE_MAP.put("Criteria 1 Table Medium", Medium);
              						}
          							}


    							break;
      						case 26://Criteria 1 Table Large

      							String  Large=getCellValueStrinOrInt(cell);
      							if(!StringUtils.isEmpty(Large)){
              						if(INVOICE_MAP.containsKey(ShippedColor)){
              							String Example=INVOICE_MAP.get(ShippedColor);
              							Large=Example+"#####"+"Criteria 1 Table Large"+":"+Large;
              							INVOICE_MAP.put("Criteria 1 Table Large", Large);
              						}
          							}

    							break;
      						case 27://Criteria 1 Table X-Large

      							String XLarge=getCellValueStrinOrInt(cell);
      							if(!StringUtils.isEmpty(XLarge)){
              						if(INVOICE_MAP.containsKey(ShippedColor)){
              							String Example=INVOICE_MAP.get(ShippedColor);
              							XLarge=Example+"#####"+"Criteria 1 Table X-Large"+":"+XLarge;
              							INVOICE_MAP.put("Criteria 1 Table X-Large", XLarge);
              						}
          							}

    							break;
      						case 28://Criteria 1 Table XX-Large

      							String XXLarge=getCellValueStrinOrInt(cell);
      							if(!StringUtils.isEmpty(XXLarge)){
              						if(INVOICE_MAP.containsKey(ShippedColor)){
              							String Example=INVOICE_MAP.get(ShippedColor);
              							XXLarge=Example+"#####"+"Criteria 1 Table XX-Large"+":"+XXLarge;
              							INVOICE_MAP.put("Criteria 1 Table XX-Large", XXLarge);
              						}
          							}

      			
    							break;
    							
    							
      						case 29://Criteria 2 Table Shipped
      							Shipped2Color=getCellValueStrinOrInt(cell);
      							if(!StringUtils.isEmpty(ShippedColor)){
          							if(INVOICE_MAP.containsKey(Shipped2Color)){
          						     String Example=INVOICE_MAP.get(Shipped2Color);
          						     Example=Example+"#####";
          						    INVOICE_MAP.put(Shipped2Color, Example);
          							}else
          							{
              							INVOICE_MAP.put("Criteria 2 Table Shipped", Shipped2Color);
    	
          							}
          							}

      							
      							break;
      							
      						case 30://Criteria 2 Table XXX-Large
      							String XXXLarge=getCellValueStrinOrInt(cell);
      							if(!StringUtils.isEmpty(XXXLarge)){
              						if(INVOICE_MAP.containsKey(Shipped2Color)){
              							String Example=INVOICE_MAP.get(Shipped2Color);
              							XXXLarge=Example+"#####"+"Criteria 2 Table XXX-Large"+":"+XXXLarge;
              							INVOICE_MAP.put("Criteria 2 Table XXX-Large", XXXLarge);
              						}
          							}
      							

      							
      							break;
      							
      						case 31://Criteria 2 Table XXXXL
      							String XXXXL=getCellValueStrinOrInt(cell);
      							if(!StringUtils.isEmpty(XXXXL)){
              						if(INVOICE_MAP.containsKey(Shipped2Color)){
              							String Example=INVOICE_MAP.get(Shipped2Color);
              							XXXXL=Example+"#####"+"Criteria 2 Table XXXXL"+":"+XXXXL;
              							INVOICE_MAP.put("Criteria 2 Table XXXXL", XXXXL);
              						}
          							}
      							

      							
      							break;
      							
      						case 32://Costing Table Terms
      							String Terms=getCellValueStrinOrInt(cell);
      							INVOICE_MAP.put("Costing Table Terms", Terms);
      							
      							break;
      							
      						case 33://Costing Table Sub-total
      							String Subtotal=getCellValueStrinOrInt(cell);
      							INVOICE_MAP.put("Costing Table Sub-total", Subtotal);

      							
      							break;
      							
      						case 34://Costing Table Insurance
      							String Insurance=getCellValueStrinOrInt(cell);
      							INVOICE_MAP.put("Costing Table Insurance", Insurance);

      							
      							break;
      							
      						case 35://Costing Table Shpg/Hdlg
      							String ShpgHdlg=getCellValueStrinOrInt(cell);
      							INVOICE_MAP.put("Costing Table Shpg/Hdlg", ShpgHdlg);

      							
      							break;
      							
      						case 36://Costing Table Sales Tax
      							String SalesTax=getCellValueStrinOrInt(cell);
      							INVOICE_MAP.put("Costing Table Sales Tax", SalesTax);

      							
      							break;
      							
      						case 37://Costing Table Details
      							String Details=getCellValueStrinOrInt(cell);
      							INVOICE_MAP.put("Costing Table Details", Details);
      							
      							break;
      							
      						case 38://Costing Table Total
      							String Total=getCellValueStrinOrInt(cell);
      							INVOICE_MAP.put("Costing Table Total", Total);
      							
      							break;
      					

						} // end inner while loop

					}
					// set product configuration objects

					// end inner while loop
					
				
				
			}
			workbook.close();

			
			

			
		} catch (Exception e) {
			_LOGGER.error("Error while Processing excel sheet "
					+ e.getMessage());
			return INVOICE_MAP;
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
			return INVOICE_MAP;	
		
	}
	public static boolean isRepeateColumn(int columnIndex){
		if(columnIndex != 9 && columnIndex != 10 && columnIndex != 11 && columnIndex != 12 &&
		   columnIndex != 13 && columnIndex != 14 && columnIndex != 22 & columnIndex != 23 && 
		   columnIndex != 24 && columnIndex != 25 && columnIndex != 26 && columnIndex != 27 &&
		    columnIndex != 28 && columnIndex != 29 && columnIndex != 30 && columnIndex != 31 && columnIndex != 32 &&
		    columnIndex != 33 && columnIndex != 34 && columnIndex != 35 && columnIndex != 36 && columnIndex != 37 
		    && columnIndex != 38 ){
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
	
	
	
	
	
	
	
	
	
}
