<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "java.util.*"%>
<%@ page import ="dao.*"%>
<%@ page import ="vo.*"%>
<% 
  SalesByFilmCategoryDao salesByFilmCategoryDao = new SalesByFilmCategoryDao();
  List<SalesByFilmCategory> list = salesByFilmCategoryDao.selectSalesFilmList();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SalesByFilmCategoryList</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
</head>
<body>
<div class="container">
	<a class="btn btn-light" href="<%=request.getContextPath()%>/index.jsp">index</a>
	<h1 class="display-1">SalesByFilmCategory(view)</h1>
	<table class="table table-striped">
		<thead>
			<tr>
				<td>Category</td>
				<td>TotalSales</td>
			</tr>
		</thead> 	
		<tbody>
			<% 
			for (SalesByFilmCategory s : list) {
			%>
				<tr>
					<td><%=s.getCategory()%></td>
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