<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page session="false" %>
<html>
<head>
<title>Home</title>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
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
	<h2>Upload Invoice / PO</h2>

<form action="parseFile" method="post" enctype="multipart/form-data">  
<!-- Select File: <input type="file" name="file"/>  
<input type="submit" value="Upload File"/>   -->

<div class="container">
  <div class="form-group">
    <input type="file" name="file" class="file">
    <div class="input-group col-xs-12"> <span class="input-group-addon"><i class="glyphicon glyphicon-picture"></i></span>
      <input type="text" class="form-control input-md" disabled placeholder="Chose File">
      <span class="input-group-btn">
      <button class="browse btn btn-dark input-md" type="button"><i class="glyphicon glyphicon-search"></i> Browse</button>
      </span> </div>
    <br>
      <!-- <a href="table.html"><button type="button" class="btn btn-primary col-md-12"> Upload File </button></a> -->
      <input type="submit" value="Upload File" class="btn btn-primary col-md-12"/>   
</div>
	</div>

</form>  
</body>
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
</html>