package com.a4.pdf.parser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.util.StringUtils;

import com.a4.pdf.model.PurchaseOrder;


public class PurOrdParser  {

	private static final Logger _LOGGER = Logger
			.getLogger(PurOrdParser.class);

	

	
	public static List<PurchaseOrder> readExcel(Workbook workbook ){
		LinkedHashMap <String, String> PDF_MAP=new LinkedHashMap<String, String>();
		List<PurchaseOrder> purchaseOrderList = new ArrayList<>();
		String productId = null;

		int columnIndex = 0;
		String ProdNo = null;
		String xid = null;
	    List<String> repeatRows = new ArrayList<>();
		Set<String> productXids = new HashSet<String>();
		
		String Quantity="";
			
			_LOGGER.info("Total sheets in excel::"+workbook.getNumberOfSheets());
		    Sheet sheet = workbook.getSheetAt(0);
			Iterator<Row> iterator = sheet.iterator();
			_LOGGER.info("Started Processing Product");
		   		   
			Row headerRow = null;
			PurchaseOrder purchaseOrder = new PurchaseOrder();
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
      						
      						case 1:
      						String  docId=getCellValueStrinOrInt(cell);
      						PDF_MAP.put("Document ID", docId);

    							break;
      						case 2://
      						String remoteId=getCellValueStrinOrInt(cell);
      						PDF_MAP.put("Remote ID", remoteId);

    							break;
      						case 3://Filename
      						String filename=getCellValueStrinOrInt(cell);
      						PDF_MAP.put("filename", filename);
    							purchaseOrder.setFileName(filename);
      						     break;
    							
      						case 4://Received At
      						String receivedAt=getCellValueStrinOrInt(cell);
          					PDF_MAP.put("Received At", receivedAt);

      							

    							break;
      						case 5://Processed At
      							String ProcessedAt=getCellValueStrinOrInt(cell);
          						PDF_MAP.put("Processed At", ProcessedAt);
          						purchaseOrder.setProcessedDate(ProcessedAt);

    							break;
      						case 6://OfficeAdress
      							String Officeadress=getCellValueStrinOrInt(cell);
          						PDF_MAP.put("OfficeAdress", Officeadress);
                                purchaseOrder.setPoAddress(Officeadress);

    							break;
      						case 7://OfficeNumber
      							String Officenumber=getCellValueStrinOrInt(cell);
      							Officenumber=Officenumber.toUpperCase();
      							Officenumber=Officenumber.replaceAll("OFFICE: ", "");
          						PDF_MAP.put("OfficeNumber", Officenumber);
    							break;
      						case 8://Email
      							String email=getCellValueStrinOrInt(cell);
      							email=email.replaceAll("Email: ", "");
          						PDF_MAP.put("Email", email);
    							break;
      						case 9://PONumber
      							String poNumber=getCellValueStrinOrInt(cell);
          						PDF_MAP.put("PONumber", poNumber);
          						purchaseOrder.setPoNo(poNumber);

    							break;
      						case 10://Vendor
      							String Vendor=getCellValueStrinOrInt(cell);
      							Vendor=Vendor.replaceAll("Vendor:", "");
          						PDF_MAP.put("Vendor", Vendor);
                                purchaseOrder.setVendorNo(Vendor);

    							break;
      						case 11://SalesPerson
      							String SalesPerson=getCellValueStrinOrInt(cell);
      							SalesPerson=SalesPerson.replaceAll("Salesperson:  ", "");
          						PDF_MAP.put("SalesPerson", SalesPerson);
                                purchaseOrder.setSalesPerson(SalesPerson);

    							break;
      						case 12://OrderDate
      							String OrderDate=getCellValueStrinOrInt(cell);
          						PDF_MAP.put("OrderDate", OrderDate);
                                purchaseOrder.setOrderDate(OrderDate);
                                
    							break;
      						case 13://ShipDate
      							String ShipDate=getCellValueStrinOrInt(cell);
          						PDF_MAP.put("ShipDate", ShipDate);
                                purchaseOrder.setShippingRequest(ShipDate);
 
    							break;
      						case 14://InHandDate
      							String InHandDate=getCellValueStrinOrInt(cell);
          						PDF_MAP.put("InHandDate", InHandDate);
    							break;
      						case 15://VendorAdress
      							String VendorAdress=getCellValueStrinOrInt(cell);
          						PDF_MAP.put("VendorAdress", VendorAdress);
                                 purchaseOrder.setVendorAddress(VendorAdress);

    							break;
      						case 16://ShipAdress
      							String ShipAdress=getCellValueStrinOrInt(cell);
          						PDF_MAP.put("ShipAdress", ShipAdress);
          						purchaseOrder.setShippingAddress(ShipAdress);

    							break;
      						case 17://Account
      							String Account=getCellValueStrinOrInt(cell);
          						PDF_MAP.put("Account", Account);


    							break;
      						case 18://Terms
      							String Terms=getCellValueStrinOrInt(cell);
          						PDF_MAP.put("Terms", Terms);
                                 purchaseOrder.setTerms(Terms);

    							break;
      						case 19://Ship
      							String Ship=getCellValueStrinOrInt(cell);
          						PDF_MAP.put("Ship", Ship);

    				             

    							break;
      						case 20://ShipAccount
      							String ShipAccount=getCellValueStrinOrInt(cell);
      							ShipAccount=ShipAccount.replaceAll("Ship Account:", "");
          						PDF_MAP.put("ShipAccount", ShipAccount);
                                purchaseOrder.setLogisticInfo(ShipAccount);


    							break;
      						case 21://Table Data Quantity
      							 Quantity=getCellValueStrinOrInt(cell);
          						//PDF_MAP.put("Table Data Quantity", Quantity);
      							if(!StringUtils.isEmpty(Quantity)){
          						if(PDF_MAP.containsKey(Quantity)){
          				           String str12=PDF_MAP.get(Quantity);
          				           str12=str12+"#####";
          				         PDF_MAP.put(Quantity, str12);
          				          }else{
          				        	PDF_MAP.put(Quantity, "Quantity"+":"+Quantity);
          				          }
      							}
          						//add this value as key in map && values to be appended by #####
    							break;
    							
      						case 22://Table Data SKU
      							String SKU=getCellValueStrinOrInt(cell);
          						//PDF_MAP.put("Table Data SKU", SKU);
      							if(!StringUtils.isEmpty(SKU)){
          						if(PDF_MAP.containsKey(Quantity)){
           				           String str12=PDF_MAP.get(Quantity);
           				        SKU=str12+"#####"+"SKU"+":"+SKU;
           				         PDF_MAP.put(Quantity, SKU);
           				          }
      							}
    							break;
    							
      						case 23://Table Data Description
      							String Description=getCellValueStrinOrInt(cell);
          						//PDF_MAP.put("Table Data Description", Description);
      							if(!StringUtils.isEmpty(Description)){
      							if(PDF_MAP.containsKey(Quantity)){
            				           String str12=PDF_MAP.get(Quantity);
            				           Description=str12+"#####"+"Description"+":"+Description;
            				         PDF_MAP.put(Quantity, Description);
            				          }
      							}
    							break;
    						
      						case 24://Table Data Unit
      							String Unit=getCellValueStrinOrInt(cell);
      							if(!StringUtils.isEmpty(Unit)){
          						//PDF_MAP.put("Table Data Unit", Unit);
      							if(PDF_MAP.containsKey(Quantity)){
         				           String str12=PDF_MAP.get(Quantity);
         				          Unit=str12+"#####"+"Unit"+":"+Unit;
         				         PDF_MAP.put(Quantity, Unit);
         				          }
      						}

    							break;
      						case 25://Table Data Cost
      							String Cost=getCellValueStrinOrInt(cell);
      							if(!StringUtils.isEmpty(Cost)){
          						//PDF_MAP.put("Table Data Cost", Cost);
      							//String Unit=getCellValueStrinOrInt(cell);
          						//PDF_MAP.put("Table Data Unit", Unit);
      							if(PDF_MAP.containsKey(Quantity)){
         				           String str12=PDF_MAP.get(Quantity);
         				          Cost=str12+"#####"+"Cost"+":"+Cost;
         				         PDF_MAP.put(Quantity, Cost);
         				          }}
        						

    							break;
      						case 26://Table Data Per
      							String  DataPer=getCellValueStrinOrInt(cell);
      							if(!StringUtils.isEmpty(DataPer)){
          						//PDF_MAP.put("Table Data Per", DataPer);
      							if(PDF_MAP.containsKey(Quantity)){
          				           String str12=PDF_MAP.get(Quantity);
          				         DataPer=str12+"#####"+"DataPer"+":"+DataPer;
          				         PDF_MAP.put(Quantity, DataPer);
          				          }
      						}

    							break;
      						case 27://Table Data Total
      							String Total=getCellValueStrinOrInt(cell);
      							if(!StringUtils.isEmpty(Total)){
          						//PDF_MAP.put("Table Data Total", Total);
      							if(PDF_MAP.containsKey(Quantity)){
           				           String str12=PDF_MAP.get(Quantity);
           				        Total=str12+"#####"+"Total"+":"+Total;
           				         PDF_MAP.put(Quantity, Total);
           				          }}
          						//over here append this value by @@@@@
    							break;
      						case 28://Table Data 8
      							String Data8=getCellValueStrinOrInt(cell);
      							if(!StringUtils.isEmpty(Data8)){
          						//PDF_MAP.put("Table Data 8", Data8);
      								purchaseOrder.setProductImprintLocation(Data8);
      								purchaseOrder.setProductcriteria(new StringBuilder(Data8));
      							/*if(PDF_MAP.containsKey(Quantity)){
            				           String str12=PDF_MAP.get(Quantity);
            				           Data8=str12+"#####"+"Data8"+":"+Data8;
            				         PDF_MAP.put(Quantity, Data8);
            				          }*/
      							}
    							break;
      						case 29:
      							String instructions = cell.getStringCellValue();
      							

						} // end inner while loop

					}
					// set product configuration objects

					// end inner while loop
					
				
				
			}
			workbook.close();

			
			

			
		} catch (Exception e) {
			_LOGGER.error("Error while Processing excel sheet "
					+ e.getMessage());
			purchaseOrder.setOrderDetails(new StringBuilder(PDF_MAP.get(Quantity)));
			purchaseOrderList.add(purchaseOrder);
			return purchaseOrderList;
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
			purchaseOrder.setOrderDetails(new StringBuilder(PDF_MAP.get(Quantity)));
			purchaseOrderList.add(purchaseOrder);
			return purchaseOrderList;	
		
	}
	public static boolean isRepeateColumn(int columnIndex){
		if(columnIndex != 2 && columnIndex != 3 && columnIndex != 4 && columnIndex != 5 &&
		   columnIndex != 6 && columnIndex != 7 && columnIndex != 8 & columnIndex != 9 && 
		   columnIndex != 10 && columnIndex != 11&& columnIndex != 12 && columnIndex != 13 &&
		    columnIndex != 14 && columnIndex != 15 && columnIndex != 16 && columnIndex != 17 && columnIndex != 18 &&
		    columnIndex != 19 && columnIndex != 20 && columnIndex != 21 ){
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



