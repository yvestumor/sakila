<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="dao.*"%>
<%@ page import="java.util.*"%>
<%	
	// filmInstock에서 넘어온 값 받기
	int filmId = 0;
	int storeId = 0;
	if(request.getParameter("filmId") != null){
	filmId = Integer.parseInt(request.getParameter("filmId"));
	}
	
	if(request.getParameter("storeId") != null){
	storeId= Integer.parseInt(request.getParameter("storeId"));
	}
	System.out.println(filmId + " <--filmId");
	System.out.println(storeId + " <--storeId");
	
	//dao호출
	FilmListDao filmListDao = new FilmListDao();
	Map<String, Object> map = filmListDao.filmInStockCall(filmId, storeId);
	
	List<Integer> list = (List<Integer>)map.get("list");
	int count = (Integer)map.get("count");
	
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>filmInstock</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
</head>
<body>
	<div class="container">
	<a class="btn btn-light" href="<%=request.getContextPath()%>/index.jsp">index</a>
	<h1 class ="display-1">filmInstock</h1>
		<form method="post" action="<%=request.getContextPath()%>/filmInStock.jsp">
			<div>FilmID</div>
			<input class="form-control" name="filmId" type="number">
			<div>StoreID</div>
			<input class="form-control" name="storeId" type="number">
			<div>
				<button  class="btn btn-light" type="submit">검색</button>
			</div>
		</form>
		<table class="table table-striped">
			<thead>
				<tr>
					<th>FilmId</th>
					<th>StoreId</th>
					<th>Count</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td><%=filmId%></td>
					<td><%=storeId%></td>
					<td><%=count%></td>
				</tr>
			</tbody>
		</table>
		<table class="table table-striped">
			<thead>
				<tr>
					<th>List</th>
				</tr>
			</thead>
			<tbody>
			<%
				for(int i : list) {
			%>	
				<tr>
					<td><%=i%></td>
				</tr>
			<% 	
				}
			%>
			</tbody>
		</table>
	</div>
</body>
</html>