<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<html>
<head>

<title>Google Analytic Reporting</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</head>


<body>
	<nav class="navbar navbar-default navbar-fixed-top">
	<div class="container-fluid" align="center">
	
		<div class="navbar-header">
		<a class="navbar-brand" href="#"><font style="color: brown;">JSON
					FILE UPLOAD</font></a>
		</div>
	</div>
	</nav>
	
<br>
<br>
	<h3 style="color: red">${filesuccess}</h3>
	
	
	<form:form method="post" action="savefile"
		enctype="multipart/form-data">
		<p>
			<label for="JSON File">Choose JSON file</label>
		</p>
		<p>
			<input name="file" id="fileToUpload" type="file" />
		</p>
		<p>
			<input type="submit" value="Upload">
		</p>
	</form:form>
</body>
</html>