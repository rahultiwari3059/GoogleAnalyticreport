<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Google AnalyticReporting Explorer</title>
</head>
<style>
table {
	border-collapse: collapse;
	width: 100%;
}

th, td {
	text-align: left;
	padding: 8px;
}

tr:nth-child(even) {
	background-color: #ccf5ff
}
</style>
<body >
	<jsp:include page="/UploadJsonFile.jsp"></jsp:include>

	<h2>AppOpenTable</h2>
	<table border="1" align="center">

		<tr>
			<td><b>Id</b></td>
			<td><b>GaId</b></td>
			<td><b>GaDiscription</b></td>
			<td><b>AndroidId</b></td>
			<td><b>Date</b></td>
		</tr>
		<c:forEach items="${appOpenList}" var="list">
			<tr>
				<td>${list.id}</td>
				<td>${list.mGaId}</td>
				<td>${list.mGadiscription}</td>
				<td>${list.mAndroidId}</td>
				<td>${list.mDate}</td>
			</tr>
		</c:forEach>
	</table>
	<br>
	<br>
	<center><h3>App Open csv download</h3><a href="AppOpenDownload"><button>download</button></a></center>
	
	<br>
	<br>
	<h2>AppReOpenTable</h2>

	<table border="1" align="center">

		<tr>
			<td><b>Id</b></td>
			<td><b>GaId</b></td>
			<td><b>GaDiscription</b></td>
			<td><b>AndroidId</b></td>
			<td><b>Date</b></td>
			<!-- <td>Event category</td> -->

		</tr>
		<c:forEach items="${appReopenList}" var="list">
			<tr>
				<td>${list.id}</td>
				<td>${list.mGaId}</td>
				<td>${list.mGadiscription}</td>
				<td>${list.mAndroidId}</td>
				<td>${list.mDate}</td>
				<%-- 	<td>${list.mEventCategory}</td> --%>

			</tr>
		</c:forEach>
	</table>
	<center><h3>App ReOpen csv download</h3><a href="AppReopenDownload"><button>download</button></a></center>
	
	<br>
	<br>

	<center><h3>Download Summary Report</h3><a href="SumaaryReportDownload"><button>download</button></a></center>
	


</body>
</html>