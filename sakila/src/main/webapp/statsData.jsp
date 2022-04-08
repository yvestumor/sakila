<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import ="java.util.*" %>
<%@ page import ="dao.*" %>
<%
	StatsDataDao statsDataDao = new StatsDataDao();

	// 1. customer별 총 amount
	List<Map<String, Object>> amountByCoustomer = statsDataDao.amountByCoustomer();
	// 2. 금액별 film 수
	List<Map<String, Object>> filmCountByRentalRate = statsDataDao.filmCountByRentalRate();
	// 3. 상영등급별 film 수
	List<Map<String,Object>> filmCountByRating = statsDataDao.filmCountByRating();
	// 4. language 별 영화수
	List<Map<String,Object>> languageFilmCount = statsDataDao.languageFilmCount(); 
	// 5. length별 영화 개수
	List<Map<String,Object>> lengthFilmCount = statsDataDao.lengthFilmCount();
	// 6. store별 영화 소지 개수
	List<Map<String, Object>> storeInventoryCount = statsDataDao.storeInventoryCount();
	// 7. customer 별 빌린 횟수
	List<Map<String, Object>> customerRentalCount =statsDataDao.customerRentalCount();
	// 8. actor별 영화 출연 횟수
	List<Map<String ,Object>> actorFilmCount = statsDataDao.actorFilmCount();
	// 9. 빌려간 사람이 가장 많은 배우
	List<Map<String, Object>> customerLikeActor = statsDataDao.customerLikeActor();
	// 10. 배우별 총 수입
	List<Map<String, Object>> actorIncome = statsDataDao.actorIncome();
	// 11.store별 이용하는 고객수
	List<Map<String, Object>> storeUseCustomerCount = statsDataDao.storeUseCustomerCount();
	// 12. 나라별 도시 개수
	List<Map<String, Object>> CountrycityCount = statsDataDao.countryCityCount();
	// 13. NC17등급 많이빌린사람 카운트
	List<Map<String,Object>> customerNc17FilmCount = statsDataDao.customerNc17FilmCount();
	// 14. category Horror영화를 많이빌린사람 count
	List<Map<String, Object>> customerRentalCategoryHorrorCount = statsDataDao.customerRentalCategoryHorrorCount();
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>amountByCoutomer(180이상)</h1>
	<table border ="1">
		<tr>
			<th>고객아이디</th>
			<th>고객이름</th>
			<th>총지불액</th>
		</tr>
		<%
			for(Map<String, Object> m : amountByCoustomer) {
		%>
			<tr>
				<td><%=m.get("customerId")%></td>
				<td><%=m.get("name")%></td>
				<td><%=m.get("total")%></td>
			</tr>
		<% 
			}
		%>
	</table>
	<h1>rating 별 film수</h1>
	<table border ="1">
		<tr>
			<th>금액</th>
			<th>film수</th>
		</tr>
		<%
			for(Map<String, Object> m : filmCountByRentalRate) {
		%>
			<tr>
				<td><%=m.get("rentalRate")%></td>
				<td><%=m.get("cnt")%>
			</tr>
		<% 
			}
		%>
	</table>
	<h1>상영 등급별 film수</h1>
	<table border ="1">
	<tr>
		<th>상영 등급</th>
		<th>film수</th>
	</tr>
	<% 
		for(Map<String ,Object> m : filmCountByRating) {
	%>		
		<tr>
			<td><%=m.get("rating")%></td>
			<td><%=m.get("cnt")%></td>
		</tr>
	<% 
		}
	%>
	</table>
	<h1>language별 영화수</h1>
	<table border="1">
		<tr>
			<th>language</th>
			<th>count</th>
		</tr>
		<%
			for (Map<String, Object> m : languageFilmCount) {
		%>
			<tr>
				<td><%=m.get("name")%></td>
				<td><%=m.get("cnt")%></td>
			</tr>
		<% 	
			}
		%>
	</table>
	<h1>길이별 영화 수</h1>
	<table border ="1">
		<tr>
			<th>running time</th>
			<th>count</th>
		</tr>
		<%
			for (Map<String, Object> m : lengthFilmCount) {
		%>
			<tr>
				<td><%=m.get("runningTime")%></td>
				<td><%=m.get("cnt")%></td>
			</tr>
		<% 
			}
		%>
	</table>
	<h1>store별 영화 소지 개수</h1>
	<table border = "1">
		<tr>
			<th>StoreID</th>
			<th>Count</th>
		</tr>
		<%
			for (Map<String, Object> m : storeInventoryCount) {
		%>
			<tr>
				<td><%=m.get("storeId")%></td>
				<td><%=m.get("cnt")%></td>
			</tr>
		<% 
			}
		%>
	</table>
	<h1>customer별 빌린 횟수(상위 10명)</h1>
	<table border ="1">
		<tr>
			<th>NAME</th>
			<th>COUNT</th>
		</tr>
		<%
			for(Map<String, Object> m : customerRentalCount) {
		%>		
			<tr>
				<td><%=m.get("NAME")%></td>
				<td><%=m.get("rentalCount")%></td>
			</tr>
		<%
			}
		%>
	</table>
	<h1>actor별 영화 출연 횟수(상위 10명)</h1>
	<table border="1">
		<tr>
			<th>NAME</th>
			<th>COUNT</th>
		</tr>
		<%
			for(Map<String ,Object> m : actorFilmCount) {
		%>
			<tr>
				<td><%=m.get("NAME")%></td>
				<td><%=m.get("cnt")%></td>
			</tr>
		<% 
			}
		%>
	</table>
	<h1>가장 인기있는 배우 (상위 10명)</h1>
	<table border="1">
		<tr>
			<td>NAME</td>
			<td>rentalCount</td>
		</tr>
		<%
			for(Map<String, Object> m : customerLikeActor) {
		%>
			<tr>
				<td><%=m.get("NAME")%></td>
				<td><%=m.get("cnt")%></td>
			</tr>
				
		<% 
			}
		%>
	</table>
	<h1>배우별 총 수입 (상위 10명)</h1>
	<table border="1">
		<tr>
			<th>배우이름</th>
			<th>수입</th>
		</tr>
		<%
			for (Map<String, Object> m : actorIncome) {
		%>
			<tr>
				<td><%=m.get("NAME")%></td>
				<td><%=m.get("inCome") %></td>
			</tr>
		<% 
			}
		%>
	</table>
	<h1>Store별 이용하는 고객수 </h1>
	<table border="1">
		<tr>
			<th>StoreID</th>
			<th>CustomerCount</th>
		</tr>
		<%
			for(Map<String,Object> m : storeUseCustomerCount) {
		%>		
			<tr>
				<td><%=m.get("storeId")%></td>
				<td><%=m.get("cnt")%></td>
			</tr>
		<% 
			}
		%>
	</table>
	<h1>나라별 도시 개수</h1>
	<table	border="1">
		<tr>
			<th>나라</th>
			<th>cityCount</th>
		</tr>
		<%
			for(Map<String, Object> m : CountrycityCount) {
		%>
			<tr>
				<td><%=m.get("country")%></td>
				<td><%=m.get("cityCount") %></td>
			</tr>
		<% 		
			}
		%>
	</table>
	<h1>영화등급 NC-17을 많이 빌린사람 순(상위10명)</h1>
	<table border="1">
		<tr>
			<th>Name</th>
			<th>Count</th>
		</tr>
		<%
			for(Map<String, Object> m : customerNc17FilmCount) {
		%>
			<tr>
				<td><%=m.get("NAME")%></td>
				<td><%=m.get("cnt")%></td>
			</tr>
		<% 
			}
		%>
	</table>
	<h1>category Horror영화를 많이 빌린사람(상위 10명)</h1>
	<table border="1">
		<tr>
			<th>NAME</th>
			<th>COUNT</th>
		</tr>
		<%
			for(Map<String, Object> m : customerRentalCategoryHorrorCount) {
		%>
			<tr>
				<td><%=m.get("NAME")%></td>
				<td><%=m.get("cnt")%></td>
			</tr>
		<% 		
				
			}
		%>
	</table>
</body>
</html>