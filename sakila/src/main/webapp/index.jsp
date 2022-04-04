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
		<li><a class="list-group-item list-group-item-light" href="<%=request.getContextPath()%>/storeList.jsp">Store List</a></li>
		<li><a class="list-group-item list-group-item-light" href="<%=request.getContextPath()%>/staffList.jsp">Staff List</a></li>
		<!-- viewList -->
		<li><a class="list-group-item list-group-item-light" href="<%=request.getContextPath()%>/actorInfoList.jsp">ActorInfoList(view)</a></li>
		<li><a class="list-group-item list-group-item-light" href="<%=request.getContextPath()%>/customerList.jsp">customerList(view)</a></li>
		<li><a class="list-group-item list-group-item-light" href="<%=request.getContextPath()%>/filmList.jsp">filmList(view)</a></li>
		<li><a class="list-group-item list-group-item-light" href="<%=request.getContextPath()%>/nicerButSlowerFilmList.jsp">nicerButSlowerFilmList(view)</a></li>
		<li><a class="list-group-item list-group-item-light" href="<%=request.getContextPath()%>/salesByFilmCategoryList.jsp">salesByFilmCategoryList(view)</a></li>
		<li><a class="list-group-item list-group-item-light" href="<%=request.getContextPath()%>/salesByStoreList.jsp">salesByStoreList(view)</a></li>
		<li><a class="list-group-item list-group-item-light" href="<%=request.getContextPath()%>/staffListView.jsp">staffList(view)</a></li>
	</ol>
</div>
</body>
</html>