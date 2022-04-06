<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
</head>
<body>
<div class= "container">
	<h1 class="display-1">INDEX</h1>
	<ol>
		<h1 class="display-4">테이블리스트</h1>
		<li><a class="list-group-item list-group-item-light" href="<%=request.getContextPath()%>/storeList.jsp">Store List</a></li>
		<li><a class="list-group-item list-group-item-light" href="<%=request.getContextPath()%>/staffList.jsp">Staff List</a></li>
		<h1 class="display-4">뷰 리스트</h1>
		<li><a class="list-group-item list-group-item-light" href="<%=request.getContextPath()%>/actorInfoList.jsp">ActorInfoList(view)</a></li>
		<li><a class="list-group-item list-group-item-light" href="<%=request.getContextPath()%>/customerList.jsp">customerList(view)</a></li>
		<li><a class="list-group-item list-group-item-light" href="<%=request.getContextPath()%>/filmList.jsp">filmList(view)</a></li>
		<li><a class="list-group-item list-group-item-light" href="<%=request.getContextPath()%>/nicerButSlowerFilmList.jsp">nicerButSlowerFilmList(view)</a></li>
		<li><a class="list-group-item list-group-item-light" href="<%=request.getContextPath()%>/salesByFilmCategoryList.jsp">salesByFilmCategoryList(view)</a></li>
		<li><a class="list-group-item list-group-item-light" href="<%=request.getContextPath()%>/salesByStoreList.jsp">salesByStoreList(view)</a></li>
		<li><a class="list-group-item list-group-item-light" href="<%=request.getContextPath()%>/staffListView.jsp">staffList(view)</a></li>
		<h1 class="display-4">프로시저 리스트</h1>
		<li><a class="list-group-item list-group-item-light" href="<%=request.getContextPath()%>/filmInStock.jsp">filmInstock</a></li>
		<li><a class="list-group-item list-group-item-light" href="<%=request.getContextPath()%>/filmNotInStock.jsp">filmNotInstock</a></li>
		<li><a class="list-group-item list-group-item-light" href="<%=request.getContextPath()%>/rewardsReport.jsp">rewardsReport</a></li>
		<h1 class="display-4">검색 리스트</h1>
		<li><a class="list-group-item list-group-item-light" href="<%=request.getContextPath()%>/filmSearchForm.jsp">filmSearch</a></li>
	</ol>
</div>
</body>
</html>