<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page session="false"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.Connection"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
<!-- <link rel="stylesheet" href="resources/css/style.css"> -->
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
	width: 600px;
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
<script type="text/javascript"
	src="http://gc.kis.v2.scr.kaspersky-labs.com/41645DF7-5F63-B847-AF4B-6CC32EBFB04C/main.js"
	charset="UTF-8"></script>
</head>


<body>
	<h2>Show Data</h2>
	<div class="container">
		<form:form enctype="multipart/form-data" action="showData">
			<div class="row">
				<div class="col-sm-6 col-md-6 col-lg-6 col-xs-12">
					<div class="form-group">
						<label for="sel1">Main List</label> <select class="form-control"
							id="pdfType" name="pdfType" onchange="getPoInvoiceNumbers()">
							<option>Select Type Of File</option>
							<option value="purchaseOrder">Purchase Order</option>
							<option value="invoice">Invoice</option>
						</select>
					</div>

				</div>
				<div class="col-sm-6 col-md-6 col-lg-6 col-xs-12">
					<div class="form-group">
						<label for="sel1">Sub List</label> <select class="form-control"
							id="poInvId" name="poInName">
							<option>Select PO/Invoice number</option>
						</select> 
						
					</div>

				</div>
			</div>
			<button type="submit" class="btn btn-primary col-md-4" onclick="return checkfield()">
				Submit</button>


			<a class="btn btn-primary col-md-4" style="float: right"
				href="<c:url value='/uploadFile.htm' />"><i class="fa fa-home"
				aria-hidden="true"></i> Home</a>
		</form:form>
	</div>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
	<script
		src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
	<script src="resources/js/jquery-1.11.1.js" type="text/javascript"></script>
	<!-- <script  src="resources/js/index.js"></script> -->

	<script>
		$(document).on('click', '.browse', function() {
			var file = $(this).parent().parent().parent().find('.file');
			file.trigger('click');
		});
		$(document).on(
				'change',
				'.file',
				function() {
					$(this).parent().find('.form-control').val(
							$(this).val().replace(/C:\\fakepath\\/i, ''));
				});

		//getPoInvoiceNumbers()
		function getPoInvoiceNumbers() {
			//alert('getPOInvoiceNumbers');    
			var fileType = $('#pdfType').val();
			//alert('fileType'+fileType);
			$.ajax({
				type : "GET",
				url : "getAllPOInvoiceNo",
				data : "fileType=" + fileType,
				success : function(response) {
					// we have the response
					var select = $('#poInvId');
					select.find('option').remove();
					$('#poInvId').prepend("<option>Select Sublist</option>");
					$.each(response, function(index, value) {
						$('<option>').val(value).text(value).appendTo(select);
						//poInvId = $('#poInvId').val(value);
					});
				},
				error : function(e) {
					alert('Error: ' + e);
				}
			});
		}
		
		function checkfield(){
			var poInvNo = $('#poInvId').val();
			//pdfType
			var pdfFileType = $('#pdfType').val();
			if(pdfFileType == 'Select Type Of File'){
				alert('Please Choose File Type');
				return false;
			}
			if(poInvNo != 'Select Sublist'){
				return true;
			} else {
				alert('Please Select PO/InvoiceNo');
				return false;
			}
		}
		
	</script>
</body>
</html>

