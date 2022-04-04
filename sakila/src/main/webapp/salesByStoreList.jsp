<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "java.util.*"%>
<%@ page import ="dao.*"%>
<%@ page import ="vo.*"%>
<% 
	SalesByStoreDao salesByStoreDao = new SalesByStoreDao();
	List<SalesByStore> list = salesByStoreDao.selectSalesByStoreList();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SalesByStore</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
</head>
<body>
<div class="container">
	<a class="btn btn-light" href="<%=request.getContextPath()%>/index.jsp">index</a>
	<h1 class="display-1">SalesByStore(view)</h1>
	<table class="table table-striped">
		<thead>
			<tr>
				<th>Store</th>
				<th>Manager</th>
				<th>TotalSales</th>
			</tr>
		</thead>
		<tbody>
		<% 
			for (SalesByStore s : list) {
		%>
			<tr>
				<td><%=s.getStore()%></td>
				<td><%=s.getManager()%></td>
				<td><%=s.getTotalSales()%></td>
			</tr>
		<% 
			}
		%>
		</tbody> 
	</table>
</div>
</body>
</html>