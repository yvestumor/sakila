<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="dao.*" %>
<%@ page import="java.util.*" %>
<%
	//StoreDao 호출
	StoreDao storeDao = new StoreDao();
	List<Integer> storeIdList = storeDao.selectStoreIdList();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>rentalSearchForm</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
</head>
<body>
<div class="container">
<a class="btn btn-light" href="<%=request.getContextPath()%>/index.jsp">index</a>
	<h1 class="display-1">대여 상세 검색</h1>
	<form action="<%=request.getContextPath()%>/rentalSearchAction.jsp" method="post">
		<table class="table table-striped">
		<!-- StoreId검색 -->
			<tr>
				<td>스토어 ID</td>
				<td>
				<% 
					for(int i : storeIdList) {
				%>		
				<div><input type="radio" name="storeId" value="<%=i%>"><%=i%>번 가게</div>
				<% 	
					}
				%>
					
				</td>
			</tr>
			<!-- 고객 이름 검색 -->
			<tr>
				<td>고객 이름</td>
				<td>
					<input type="text" name ="customerName">
				</td>
			</tr>
			<!-- 대여일자 -->
			<tr>
				<td>대여 일자</td>
				<td>
					<input type="date" name ="beginDate"> ~ <input type="date" name ="endDate">
				</td>
			</tr>
			<tr>
				<td colspan="2"><button class="btn btn-light" type="submit">검색</button>
			</tr>
		</table>
	</form>
</div>
</body>
</html>