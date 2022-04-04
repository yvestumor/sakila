<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "java.util.*" %>
<%@ page import = "dao.* "%>
<%@ page import = "vo.*" %>
<% 
	
	int currentPage = 1; // 현재페이지
	
	if(request.getParameter("currentPage") != null) { //현재페이지가 null이 아니면
		currentPage = Integer.parseInt(request.getParameter("currentPage")); //현제페이지값 받아옴
	}
	System.out.println(currentPage +"<-- 현재페이지");
	
	
	int rowPerPage = 5; // 페이지당 볼수있는개수 
	
	int beginRow = (currentPage - 1)*rowPerPage; 
	
		
	ActorInfoDao actorInfoDao = new ActorInfoDao(); //dao호출
	
	int lastPage = 0; //마지막 페이지
	List<ActorInfo> list = actorInfoDao.selectActorInfoListByPage(beginRow, rowPerPage);
	int totalCount = actorInfoDao.selectActorInfoTotalRow();
	System.out.println(totalCount + " <--totalCount"); //디버깅
	
	if(totalCount % rowPerPage == 0) { //전체개수 / 페이지당 볼수있는개수 나머지가 0이면
		lastPage =totalCount / rowPerPage;
		
	} else {
		lastPage = (totalCount / rowPerPage) + 1;
		
	}
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>actorInfoList</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
</head>
<body>
<div class="container">
<a class="btn btn-light" href="<%=request.getContextPath()%>/index.jsp">index</a>
	<h1 class="display-1">ActorInfo List</h1>
	<table class="table table-striped">
		<thead>
			<tr>
				<th>ActorId</th>
				<th>actorFirstName</th>
				<th>actorLastName</th>
				<th>filmInfo</th>
			</tr>
		</thead>
		<tbody>
			<% 
				for (ActorInfo a : list) {
			%>
				<tr>
					<td><%=a.getActorId()%></td>
					<td><%=a.getFirstName()%></td>
					<td><%=a.getLastName()%></td>
					<td><%=a.getFilmInfo()%></td>
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
				<a class="btn btn-light" href="<%=request.getContextPath()%>/actorInfoList.jsp?currentPage=<%=currentPage-1%>">이전</a>
		<%		
			}
		%>
		<%
			if(currentPage < lastPage) {
		%>
				<a class="btn btn-light" href="<%=request.getContextPath()%>/actorInfoList.jsp?currentPage=<%=currentPage+1%>">다음</a>
		<%
			}
		%>
	</div>
</div>
</body>
</html>