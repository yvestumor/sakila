<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "java.util.*"%>
<%@ page import ="dao.*"%>
<%@ page import ="vo.*"%>
<% 
	StaffListDao staffListDao = new StaffListDao();
	List<StaffList> list = staffListDao.selectStaffViewList();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>StaffList</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
</head>
<body>
<div class="container">
	<a class="btn btn-light" href="<%=request.getContextPath()%>/index.jsp">index</a>
	<h1 class="display-1">StaffList(view)</h1>
	<table class="table table-striped">
		<thead>
			<tr>
				<th>ID</th>
				<th>Name</th>
				<th>Address</th>
				<th>zipcode</th>
				<th>Phone</th>
				<th>City</th>
				<th>Country</th>
				<th>SID</th>
			</tr>
		</thead>
		<tbody>
		<% 
			for (StaffList s : list) {
		%>
			<tr>
				<td><%=s.getId()%></td>
				<td><%=s.getName()%></td>
				<td><%=s.getAddress()%></td>
				<td><%=s.getZipCode()%></td>
				<td><%=s.getPhone()%></td>
				<td><%=s.getCity()%></td>
				<td><%=s.getCountry()%></td>
				<td><%=s.getSid()%></td>
			</tr>
		<%
			}
		%>
		</tbody>
	</table>
</div>
</body>
</html>