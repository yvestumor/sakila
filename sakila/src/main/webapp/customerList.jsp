z<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "java.util.*"%>
<%@ page import ="dao.*"%>
<%@ page import ="vo.*"%>
<%
	int currentPage = 1; // 현재페이지
	
	if(request.getParameter("currentPage") != null) { // 현제페이지값이 null이아니면
		currentPage = Integer.parseInt(request.getParameter("currentPage")); // 현재페이지 값 받아오기
	}
	System.out.println(currentPage + " <--currentPage(현재페이지값)"); // 디버깅
	
	int rowPerPage = 5; // 볼수있는개시물 개수
	
	int beginRow = (currentPage -1)*rowPerPage;
	
	CustomerListDao customerListDao = new CustomerListDao();
	int lastPage = 0; //마지막 페이지 변수로두기
	List<CustomerList> list = customerListDao.selectCustomerListByPage(beginRow, rowPerPage);
	int totalCount = customerListDao.selectCustomerListTotalRow();
	System.out.println(totalCount + " <--전체 개수");
	
	if (totalCount % rowPerPage == 0) {
		lastPage = totalCount / rowPerPage;
	} else {
		lastPage = (totalCount/rowPerPage) + 1;
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CustomerList</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
</head>
<body>
<div class="container">
	<a class="btn btn-light" href="<%=request.getContextPath()%>/index.jsp">index</a>
	<h1 class="display-1">CustomerList(view)</h1>
	<table class="table table-striped">
		<thead>
			<tr>
				<th>ID</th>
				<th>Name</th>
				<th>Address</th>
				<th>zipCode</th>
				<th>Phone</th>
				<th>City</th>
				<th>Country</th>
				<th>Notes</th>
				<th>SID</th>
			</tr>
		</thead>
		<tbody>
			<% 
				for (CustomerList c : list) {
			%>
				<tr>
					<td><%=c.getId() %></td>
					<td><%=c.getName() %></td>
					<td><%=c.getAddress() %></td>
					<td><%=c.getZipCode() %></td>
					<td><%=c.getPhone() %></td>
					<td><%=c.getCity() %></td>
					<td><%=c.getCountry() %></td>
					<td><%=c.getNotes() %></td>
					<td><%=c.getSid()%></td>
				</tr>	
			<% 		
				}
			%>
		</tbody>
	</table>
	<div>
		<% 
		if (currentPage > 1) {
		%>
			<a  class="btn btn-light" href="<%=request.getContextPath()%>/customerList.jsp?currentPage=<%=currentPage-1%>">이전</a>
		<%
		}
		%>
		<%
			if(currentPage < lastPage) {
		%>
				<a class="btn btn-light" href="<%=request.getContextPath()%>/customerList.jsp?currentPage=<%=currentPage+1%>">다음</a>
		<%
			}
		%>
	</div>
</div>
</body>
</html>