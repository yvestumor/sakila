<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import ="java.util.*" %>
<%@ page import ="vo.*" %>
<%@ page import ="dao.*" %>
<%
CategoryDao categoryDao = new CategoryDao();
	List<Category> list = categoryDao.selectCategoryList();
	FilmDao filmListDao = new FilmDao();
	List<Double> priceList =filmListDao.selectFilmPriceList();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>filmSearchForm</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
</head>
<body>
<div class="container">
	<a class="btn btn-light" href="<%=request.getContextPath()%>/index.jsp">index</a>
	<h1 class="display-1">필름 리스트 뷰 검색</h1>
	<form  action="<%=request.getContextPath()%>/filmSearchAction.jsp" method="post">
		<table class="table table-striped">
			<tr>
				<td>카테고리</td>
				<td>
					<select name ="category">
						<option value="">카테고리선택</option>
					<% 
						for(Category c : list) {
					%>
						<option value="<%=c.getName()%>"><%=c.getName()%></option>
					<% 
						}
					%>
					</select>
				</td>
			</tr>
			<tr> <!-- 'G','PG','PG-13','R','NC-17' -->
				<td>rating</td>
				<td>
					<select name="rating">
						<option value="">등급선택</option>
						<option value="G">G</option>
						<option value="PG">PG</option>
						<option value="PG-13">PG-13</option>
						<option value="R">R</option>
						<option value="NC-17">NC-17</option>
					</select>
				</td>
			</tr>
			<tr>
				<td>대여료</td>
				<td>
					<div><input type="radio" name="price" value="" checked="checked">선택안함</div>
					<%
						for(Double p : priceList) {
					%>
							<div><input type="radio" name="price" value="<%=p%>"><%=p%></div>	
					<% 
						}
					%>
				</td>
			</tr>
			<tr>
				<td>영화시간</td>
				<td>
					<div><input type="radio" name="length" value="" checked="checked">선택안함</div>
					<div><input type="radio" name="length" value="0">1시간 미만</div><!--  length <60 -->
					<div><input type="radio" name="length" value="2">1시간 이상</div><!-- length >= 60 -->
				</td>
			</tr>
			<tr>
				<td>영화제목 검색</td>
				<td>
					<input type="text" name="title">
				</td>
			</tr>
			<tr>
				<td>배우 검색</td>
				<td>
					<input type="text" name="actor">
				</td>
			</tr>
			<tr>
				<td colspan="2">
				<button  class="btn btn-light" type="submit">검색</button>
				</td>
			</tr>
		</table>
	</form>
</div>
</body>
</html>