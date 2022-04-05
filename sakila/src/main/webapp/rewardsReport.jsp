<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import= "vo.*" %>
<%@ page import="dao.*"%>
<%@ page import="java.util.*"%>
<% 
	//적용한 값 받기
	int minMonthlyPurchases = 0;
	double minDollarAmountPurchased = 0;
	if(request.getParameter("minMonthlyPurchases") != null) {
		minMonthlyPurchases = Integer.parseInt(request.getParameter("minMonthlyPurchases"));
	}
	if(request.getParameter("minDollarAmountPurchased") != null) {
		minDollarAmountPurchased = Double.parseDouble(request.getParameter("minDollarAmountPurchased"));
	}
	//디버깅
	System.out.println(minMonthlyPurchases+" <--minMonthlyPurchases");
	System.out.println(minDollarAmountPurchased +" <--minDollarAmountPurchased");
	//dao호출
	CustomerListDao customerListDao = new CustomerListDao();
	Map<String, Object> map = customerListDao.CustomerRewardsReportCall(minMonthlyPurchases, minDollarAmountPurchased);
	
	
	int count = (Integer)map.get("count");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>RewordsReport</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
</head>
<body>
<div class="container">
	<a class="btn btn-light" href="<%=request.getContextPath()%>/index.jsp">index</a>
	<form method="post" action="<%=request.getContextPath()%>/rewardsReport.jsp">
	<div>MinMonthlyPurchases</div>
	<input class="form-control" name="filmId" type="number">
	<div>MinDollarAmountPurchased</div>
	<input class="form-control" name="storeId" type="number">
	<div>
	<button class="btn btn-light" type="submit">검색</button>
	</div>
	</form>
		<table class="table table-striped">
			<thead>
				<tr>
					<th>minMonthlyPurchases</th>
					<th>MinDollarAmountPurchased</th>
					<th>Count</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td><%=minMonthlyPurchases%></td>
					<td><%=minDollarAmountPurchased%></td>
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
			
			</tbody>
		</table>
	</div>
</body>
</html>