<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글쓰기</title>
</head>
<body>
<h2>게시판 글쓰기 화면</h2>
<form action="write.do" method="post">
<input type="text" name="title" placeholder="제목"><br>
<input type="text" name="writer" placeholder="작성자"><br>
<textarea name="content" rows="8" placeholder="내용"></textarea>
<input type="submit" value="저장">
</form>
<a href="list.do">목록보기</a>
</body>
</html>