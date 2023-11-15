package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BoardDAO;

public class BoardwriteCommand implements BoardCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		BoardDAO dao = new BoardDAO();
		//write.jsp의 값을 넘겨받기
				String writer = request.getParameter("writer");
				String title = request.getParameter("title");
				String content = request.getParameter("content");
				//dao의 insert구문의 파라미터 변수값 넘겨 받기
				dao.write(writer, title, content);;
			
	}

}
