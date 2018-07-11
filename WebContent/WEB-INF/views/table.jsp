<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="en" >
<head>

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

<div class="container">
 <table class="table table-bordered">
  <thead>
    <tr>
     
      <th scope="col">Data Point</th>
      <th scope="col">Value</th>
     
    </tr>
  </thead>
  <tbody>
   <c:forEach items="${contactForm.contactMap}" var="contactMap" varStatus="status">
					<tr id='addr0'>
						<td>
						${contactMap.key}
						</td>
						<td><input  type="text"  name="contactMap['${contactMap.key}']" value="${contactMap.value}" class="form-control"/></td>						
						</tr>
					</c:forEach>
  </tbody>
</table>
	<a href="upload.jsp"><button type="button" class="btn btn-dark col-md-12"> Back </button></a>
	</div>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script> 
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script> 
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
