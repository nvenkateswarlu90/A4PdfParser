<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page session="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en" >
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
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


<body>
<h2>UPLOAD INVOICE / PO</h2>
<div class="container">
<form:form name="uploadBean"  enctype="multipart/form-data"
								modelAttribute="uploadBean" action="parseFile">
  
   <div class="form-group">

									<%-- <c:if test="${empty environemtType}"> --%>
									<!-- <label for="sel1">Select Type Of Enviornmet</label> -->
									<form:select class="form-control" path="fileType" id="environmentTypeId">
										<form:option value="NONE" label="Select Type Of File"></form:option>
										<form:option value="PO">Purchase Order</form:option>
										<form:option value="Invoice">Invoice</form:option>
									</form:select>
									<%-- </c:if> --%>
								</div>
<div class="form-group">
    <!-- <input type="file" name="img[]" class="file"> -->
    <div class="input-group col-xs-12"> <span class="input-group-addon"><i class="glyphicon glyphicon-picture"></i></span>
      <input type="file" name="file" class="form-control input-md"/>
      <span class="input-group-btn">
      <!-- <button class="browse btn btn-dark input-md" type="button"><i class="glyphicon glyphicon-search"></i> Browse</button> -->
      </span> </div>
    <br>
       <button type="submit" class="btn btn-primary col-md-12"> Upload File </button></a>
</div>
</form:form>
	</div>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script> 
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script> 
<!-- <script  src="resources/js/index.js"></script> -->

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

