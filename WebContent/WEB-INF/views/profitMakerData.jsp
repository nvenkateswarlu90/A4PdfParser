<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<style type="text/css">
.file {
  visibility: hidden;
  position: absolute;
}

/*
 * Styles for demo only
 */
body {
  background-color: #5c4084;
  margin: 50px;
}

.container {
  background-color: #fff;
  padding: 73px 80px;
  border-radius: 8px;
	    width:600px;
}

h2 {
  color: #fff;
  text-align: center;
}

h4 {
  color: #a990cc;
  font-size: 24px;
  font-weight: 400;
  text-align: center;
  margin: 0 0 35px 0;
}

.btn.btn-primary {
  background-color: #5c4084;
  border-color: #5c4084;
  outline: none;
}
.btn.btn-primary:hover {
  background-color: #442f62;
  border-color: #442f62;
}
.btn.btn-primary:active, .btn.btn-primary:focus {
  background-color: #684895;
  border-color: #684895;
}
</style>
<!-- <link href="../css/style.css" rel="stylesheet" type="text/css"> -->
<script type="text/javascript" src="http://gc.kis.v2.scr.kaspersky-labs.com/41645DF7-5F63-B847-AF4B-6CC32EBFB04C/main.js" charset="UTF-8"></script></head>

</head>
<body>

<%-- <h3>Hi ${userName}</h3>
<c:forEach var="country" items="${capitalList}">
	Country: ${country.key}  - Capital: ${country.value}
</c:forEach>
 --%>
 <h2>File Data</h2>  
<%-- <table>
	<tr>
		<th>Key</th>
		<th>Value</th>
	</tr>
	<c:forEach items="${contactForm.contactMap}" var="contactMap" varStatus="status">
		<tr>
			<td>${contactMap.key}</td>
			<td><input name="contactMap['${contactMap.key}']" value="${contactMap.value}"/></td>
		</tr>
	</c:forEach>
</table>	 --%>
<div class="container" style="width: 100%; overflow: scroll;">
    <div class="row clearfix">
		
			<table class="table table-bordered table-hover" id="tab_logic">
					<thead>
						<tr>
							<th scope="col">PoAddress</th>
							<th scope="col">VendorAddress</th>
							<th scope="col">VendorNo</th>
							<th scope="col">ShippingRequest</th>
							<th scope="col">PoNo</th>
							<th scope="col">Job</th>
							<th scope="col">ShippingAddress</th>
							<th scope="col">LogisticInfo</th>
							<th scope="col">CustomerPo</th>
							<th scope="col">OrderDate</th>
							<th scope="col">Terms</th>
							<th scope="col">SalesPerson</th>
							<th scope="col">OrderDetails</th>
							 <th scope="col">Instructions</th>
							<th scope="col">InstructionsToFactory1</th>
							<th scope="col">InstructionsToFactory2</th>
						</tr>
					</thead>
					<tbody>
					<c:forEach items="${profitMakerPODataList}" var="purchaseOrder" varStatus="status">
					<tr>
						<td>${purchaseOrder.poAddress}</td>
						<td>${purchaseOrder.vendorAddress}</td>
						<td>${purchaseOrder.vendorNo}</td>
						<td>${purchaseOrder.shippingRequest}</td>
						<td>${purchaseOrder.poNo}</td>
						<td>${purchaseOrder.job}</td>
						<td>${purchaseOrder.shippingAddress}</td>
						<td>${purchaseOrder.logisticInfo}</td>
						<td>${purchaseOrder.customerPo}</td>
						<td>${purchaseOrder.orderDate}</td>
						<td>${purchaseOrder.terms}</td>
						<td>${purchaseOrder.salesPerson}</td>
						<td>${purchaseOrder.orderDetails}</td>
						<td>${purchaseOrder.productcriteria}</td>
						<td>${purchaseOrder.instructionsToFactory1}</td>
						<td>${purchaseOrder.instructionsToFactory2}</td>
						<%
						session.setAttribute("colValue", "Terms and Conditions");
						%>
						<%-- <td style="width: 50%;">${purchaseOrder.termsAndConditions}</td> --%>						
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
	<div class="modal fade" id="Modal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
		  <h5 class="modal-title" id="exampleModalLabel"><cente>Terms And Conditions</cente></h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body" style="overflow-y: scroll; height: 200px;" >
      
		   <p>1. Please follow all instructions on our order. No deviations are acceptable
                unless expressly approved in writing.</p>
<p>2. You must use our packing list in all drop shipments.</p>
<p>3. Do not show your name on any information going to the recipient.</p>
<p>4. Do not include any manufacturer's literature in shipment unless expressly
      requested.</p>
<p>5. ADVANCE NOTIFICATIONS MUST BE GIVEN ON ANY PRICE DIFFERENCE ON ANY P.O.</p> 
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
       
      </div>
    </div>
  </div>
</div>
	<!-- <a id="add_row" class="btn btn-default pull-left">Add Row</a><a id='delete_row' class="pull-right btn btn-default">Delete Row</a> -->
<a class="btn btn-home btn-lg btn1" style="margin: 0px 41% auto;" href="<c:url value='/uploadFile.htm' />"><i class="fa fa-home" aria-hidden="true"></i> Home</a>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<script>
$(document).on('click', '.browse', function(){
	  var file = $(this).parent().parent().parent().find('.file');
	  file.trigger('click');
	});
	$(document).on('change', '.file', function(){
	  $(this).parent().find('.form-control').val($(this).val().replace(/C:\\fakepath\\/i, ''));
	});
	</script>
</body>
</html>
