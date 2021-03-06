<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import ="java.util.*"%>
<%@ page import ="dao.*" %>
<%@ page import ="vo.*" %>
<% 
	int currentPage = 1;
	
	if(request.getParameter("currentPage") != null) { //현재페이지가 null이 아니면
	currentPage = Integer.parseInt(request.getParameter("currentPage")); //현제페이지값 받아옴
	}
	System.out.println(currentPage +"<-- 현재페이지");
	
	int rowPerPage = 5;
	
	int beginRow = (currentPage -1)*rowPerPage;
	
	NicerButSlowerFilmListDao nicerButSlowerFilmListDao = new NicerButSlowerFilmListDao();
	int lastPage = 0;
	List<NicerButSlowerFilmList> list = nicerButSlowerFilmListDao.selectNicerButSlowerFilmListByPage(beginRow, rowPerPage);
	int totalCount = nicerButSlowerFilmListDao.selectFilmListTotalRow();
	System.out.println(totalCount + " <--전체 개수");
	
	if (totalCount % rowPerPage == 0) {
		lastPage = totalCount / rowPerPage;
		
	} else {
		lastPage =(totalCount / rowPerPage) + 1;
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>NicerButSlowerFilmList</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
</head>
<body>
<div class="container">
	<a class="btn btn-light" href="<%=request.getContextPath()%>/index.jsp">index</a>
	<h1  class="display-1">NicerButSlowerFilmList(view)</h1>
	<table class="table table-striped">
		<thead>
			<tr>
				<th>FID</th>
				<th>title</th>
				<th>description</th>
				<th>category</th>
				<th>price</th>
				<th>length</th>
				<th>rating</th>
				<th>actors</th>
			</tr>
		</thead>
		<tbody>
			<% 
				for (NicerButSlowerFilmList n : list) {
			%>
				<tr>
					<td><%=n.getFid()%></td>
					<td><%=n.getTitle()%></td>
					<td><%=n.getDescription()%></td>
					<td><%=n.getCategory()%></td>
					<td><%=n.getPrice()%></td>
					<td><%=n.getLength()%></td>
					<td><%=n.getRating()%></td>
					<td><%=n.getActors()%></td>
				</tr>
			<% 
				}
			%>
		</tbody>
	</table>
	<div>
		<%
			if(currentPage > 1) {
		%>
				<a class="btn btn-light" href="<%=request.getContextPath()%>/nicerButSlowerFilmList.jsp?currentPage=<%=currentPage-1%>">이전</a>
		<%		
			}
		%>
		<%
			if(currentPage < lastPage) {
		%>
				<a class="btn btn-light" href="<%=request.getContextPath()%>/nicerButSlowerFilmList.jsp?currentPage=<%=currentPage+1%>">다음</a>
		<%
			}
		%>
	</div>
</div>
</body>
</html>