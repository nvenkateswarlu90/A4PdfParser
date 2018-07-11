
<html lang="en" >
<head>
<meta charset="UTF-8">
<title>Bootstrapped Styled File Browse Button</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<link rel="stylesheet" href="css/style.css">
</head>

<body>
<h2>UPLOAD INVOICE / PO</h2>
<div class="container">
  <div class="form-group">
    <input type="file" name="img[]" class="file">
    <div class="input-group col-xs-12"> <span class="input-group-addon"><i class="glyphicon glyphicon-picture"></i></span>
      <input type="text" class="form-control input-md" disabled placeholder="Chose File">
      <span class="input-group-btn">
      <button class="browse btn btn-dark input-md" type="button"><i class="glyphicon glyphicon-search"></i> Browse</button>
      </span> </div>
    <br>
      <a href="table.html"><button type="button" class="btn btn-primary col-md-12"> Upload File </button></a>
</div>
	</div>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script> 
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script> 
<script  src="js/index.js"></script>
</body>
</html>
