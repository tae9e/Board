<%@page import="java.sql.Connection"%>
<%@page import="javax.sql.DataSource"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="javax.naming.Context"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	Connection conn = null;

    try{Context init = new InitialContext();
    //java:comp/env/jdbc/OracleDB: JNDI경로, 자원들이 위치
    //DataSource로 변환 후 ds에 담기
	DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");
	conn = ds.getConnection();
		out.println("연결 성공");
    }catch(Exception e){
    	out.println("실패");
    }

%>
</body>
</html>