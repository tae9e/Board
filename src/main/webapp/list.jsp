<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>

<body>
	<h3>게시판 목록 보기</h3>
 	

		<table border="1">
			<tr>
				<td colspan="5">
			<form action="search.do" method="post">


					<select name="searchName" size="1">
						<option value="writer">작성자</option>
						<option value="title">글제목</option>
					</select> <input type="text" name="searchValue"> <input
						type="submit" value="찾기">
				</form>
			</td>
			</tr>
			<tr>
				<td>번호</td>
				<td>제목</td>
				<td>작성자</td>
				<td>날짜</td>
				<td>조회수</td>
			</tr>
			<c:forEach items="${data }" var="dto">
				<tr>
					<td>${dto.num }</td>
					<td><a href="detail.do?num=${dto.num }">${dto.title}</a></td>
					<td>${dto.writer }</td>
					<td>${dto.writeday }</td>
					<td>${dto.readcnt}</td>
				</tr>
			</c:forEach>
		</table>
		<!-- 글쓰기 화면으로 이동 -->
		<a href="writeui.do">글쓰기</a>
	
</body>
</html>
















