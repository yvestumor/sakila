<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import ="java.util.*" %>
<%@ page import="dao.*" %>
<%
	//Form에서 입력한 값 받아옴
	int storeId = -1;
	if(request.getParameter("storeId") != null ) {
	storeId = Integer.parseInt(request.getParameter("storeId"));
	}
	System.out.println(storeId + "<--받아온 storeId");
	
	String customerName = request.getParameter("customerName");
	System.out.println(customerName + "<--받아온 customerName");
	
	String beginDate = request.getParameter("beginDate");
	System.out.println(beginDate + "<--받아온 beginDate");
	
	String endDate = request.getParameter("endDate");
	System.out.println(endDate + "<--받아온 endDate");
	
	//페이징
	int currentPage =1;
	if(request.getParameter("currentPage")!=null) {
		currentPage=Integer.parseInt(request.getParameter("currentPage"));
	}
	
	int beginRow = 0;
	int rowPerPage = 10;
	beginRow=(currentPage-1) * rowPerPage;
	int lastPage = 0;
	
	//dao호출
	RentalDao rentalDao = new RentalDao();
	List<Map<String, Object>> list = rentalDao.selectRentalSearchList(storeId, customerName, beginDate, endDate, beginRow, rowPerPage);
	int totalCount  = rentalDao.selectRentalTotalRow(storeId, customerName, beginDate, endDate);
	
	// 총페이지?구하기
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
<title>rentalSearchAction</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
</head>
<body>
<div class="container">
	<a class="btn btn-light" href="<%=request.getContextPath()%>/index.jsp">index</a>
	<h1 class="display-1">검색결과 리스트</h1>
	<table class="table table-striped">
			<%
				for(Map<String, Object> m : list) {
			%>		
				<tr>
					<td><%=m.get("rentalId")%></td>
					<td><%=m.get("rentalDate")%></td>
					<td><%=m.get("inventoryId")%></td>
					<td><%=m.get("customerId")%></td>
					<td><%=m.get("returnDate")%></td>
					<td><%=m.get("staffId")%></td>
					<td><%=m.get("lastUpdate")%></td>
					<td><%=m.get("cName")%></td>
					<td><%=m.get("storeId")%></td>
					<td><%=m.get("filmId")%></td>
					<td><%=m.get("title")%></td>
				</tr>
			<% 	
				}
			%>
	</table>
	<div>
		<% 
			if (currentPage>1) {
		%>
			<a class="btn btn-light" href="<%=request.getContextPath() %>/rentalSearchAction.jsp?currentPage=<%=currentPage-1%>&storeId=<%=storeId%>&customerName=<%=customerName%>&beginDate=<%=beginDate%>&endDate=<%=endDate%>">이전</a>
		<% 
			}
		%>
		<% 
			if(currentPage<lastPage) {
		%>
			<a class="btn btn-light" href="<%=request.getContextPath() %>/rentalSearchAction.jsp?currentPage=<%=currentPage+1%>&storeId=<%=storeId%>&customerName=<%=customerName%>&beginDate=<%=beginDate%>&endDate=<%=endDate%>"> 다음</a>
		<% 		
			}
		%>
	</div>
</div>
</body>
</html>