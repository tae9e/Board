<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- 한글 깨짐 방지 -->
<% request.setCharacterEncoding("utf-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 내용</title>
</head>
<body>
<h2>글 정보</h2>
<form action = "update.do" method="post">
<!-- 번호를 가져가서 수정한 목록이 화면에 나오도록 설정 -->
<input type="hidden" name = "num" value="${detail.num }">

글번호: ${detail.num } &nbsp;&nbsp; 조회수: ${detail.readcnt } <br>
작성자:  <input type="text" name="writer" value="${detail.title }"><br>
내용: <input type="text" name="content" value="${detail.writer }"><br>
<p>등록일자: ${detail.writeday }</p>


<input type="submit" value="수정">
</form>

<a href="delete.do?num=${detail.num }" onclick="return confirm('삭제하시겠습니까?');"><input type="submit" value="삭제"></a><br>
<a href="list.do"><input type="submit" value="목록보기"></a><br>

</body>
</html>






