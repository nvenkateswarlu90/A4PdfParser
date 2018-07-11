<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
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
<div class="container">
    <div class="row clearfix">
		<div class="col-md-12 column">
			<table class="table table-bordered table-hover" id="tab_logic">
				<thead>
					<tr >
						<th class="text-center">
							Data Point
						</th>
						<th class="text-center col-md-12">
							Value
						</th>
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
                    <tr id='addr1'></tr>
				</tbody>
			</table>
		</div>
	</div>
	<!-- <a id="add_row" class="btn btn-default pull-left">Add Row</a><a id='delete_row' class="pull-right btn btn-default">Delete Row</a> -->
</div>
<a class="btn btn-home btn-lg btn1" style="margin: 0px 41% auto;" href="<c:url value='/uploadFile.htm' />"><i class="fa fa-home" aria-hidden="true"></i> Home</a>

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
