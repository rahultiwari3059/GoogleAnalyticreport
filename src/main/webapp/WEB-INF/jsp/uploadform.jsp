<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<html>
<head>
<title>Google Analytic Reporting</title>
</head>
<body>
	<h1>JSON File Upload</h1>

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