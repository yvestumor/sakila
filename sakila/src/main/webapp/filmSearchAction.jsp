<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import ="dao.*"%>
<%@ page import = "vo.*" %>
<%@ page import ="java.util.*" %>
<%
	String category = request.getParameter("category"); //카테고리값 받아오기
	System.out.println(category + " <--category"); //디버깅
	
	String rating = request.getParameter("rating"); //rating 값 받아오기
	System.out.println(rating + " <--rating"); //디버깅
	
	double price = -1; // price 데이터가 입력되지않았을때 -1을 반환
	if(!request.getParameter("price").equals("")) { //공백값이아니면
		price = Double.parseDouble(request.getParameter("price")); // price값 받아오기
	}
	
	int length = -1; // length 데이터가 입력되지 않았을때 -1을 반환 
	if(!request.getParameter("length").equals("")) { //공백값이아니면
		length = Integer.parseInt(request.getParameter("length")); //length값 받아오기
	}
	
	String title = request.getParameter("title"); //title 값 받아오기
	System.out.println(title + " <-- title");
	
	String actor = request.getParameter("actor"); // actor 값 받아오기
	System.out.println(actor + " <--actor");
	// 페이징
	int currentPage = 1;
	if(request.getParameter("currentPage") != null) { //현재페이지가 null이 아니면
	currentPage = Integer.parseInt(request.getParameter("currentPage")); //현제페이지값 받아옴
	}
	
	int rowPerPage =10;
	int beginRow = (currentPage -1) * rowPerPage;
	int lastPage = 0;
	
	FilmDao filmDao = new FilmDao();
	List<FilmList> list = filmDao.selectFilmSearchList(beginRow,rowPerPage,category, rating, price, length, title, actor);
	int totalCount = filmDao.selectFilmSearchTotalRow(category, rating, price, length, title, actor);
	
	
	System.out.println(list.size()); // 0
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
<title>FilmSearchAction</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
</head>
<body>
<div class="container">
	<a class="btn btn-light" href="<%=request.getContextPath()%>/index.jsp">index</a>
	<h1 class="display-1">필름리스트 검색</h1>
	<table class="table table-striped">
		<thead>
			<tr>
				<th>FID</th>
				<th>Title</th>
				<th>Description</th>
				<th>Category</th>
				<th>Price</th>
				<th>Length</th>
				<th>Rating</th>
				<th>Actors</th>
			</tr>
		</thead>
		<tbody>
		<% 
			for(FilmList f : list) {
		%>
			<tr>
				<td><%=f.getFid()%></td>
				<td><%=f.getTitle() %></td>
				<td><%=f.getDescription() %></td>
				<td><%=f.getCategory() %></td>
				<td><%=f.getPrice() %></td>
				<td><%=f.getLength() %></td>
				<td><%=f.getRating() %></td>
				<td><%=f.getActors() %></td>
			</tr>
		<% 
			}
		%>
		</tbody>
	</table>
	<div>
	 <% 
		if(currentPage >1) {
	%>
		<a class="btn btn-light" href="<%=request.getContextPath()%>/filmSearchAction.jsp?currentPage=<%=currentPage-1%>&category=<%=category%>&rating=<%=rating%>&price=<%=price%>&length=<%=length%>&title=<%=title%>&actor=<%=actor%>">이전</a>
	
	<%
		}
	%>
	
	<%
		if(currentPage < lastPage) {
	%>
	
		<a class="btn btn-light" href="<%=request.getContextPath()%>/filmSearchAction.jsp?currentPage=<%=currentPage+1%>&category=<%=category%>&rating=<%=rating%>&price=<%=price%>&length=<%=length%>&title=<%=title%>&actor=<%=actor%>">다음</a>
	<%
		}
	
	%>
	
	</div>
</div>
</body>
</html>