package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import service.BoardCommand;
import service.BoardDeleteCommand;
import service.BoardDetailCommand;
import service.BoardSearchCommand;
import service.BoardUpdateCommand;
import service.BoardlistCommand;
import service.BoardwriteCommand;

@WebServlet("*.do")
public class BoardFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String requestURI = request.getRequestURI();
		String contextpath = request.getContextPath();
		String com = requestURI.substring(contextpath.length());

		BoardCommand command = null;
		String nextPage = null;
		//목록 보기
		if (com.equals("/list.do")) {
			command = new BoardlistCommand();
			command.execute(request, response);
			nextPage = "list.jsp";
			System.out.println("nextPage: " + nextPage);
		}
		//글쓰기 화면으로 이동(write.do와 중복 방지 위해 writeui.do로 작성)
		//화면에 보여줄 게 따로 없음으로 인터페이스 연결은 필요없다.  
		if (com.equals("/writeui.do"))
			nextPage = "write.jsp";
		
		//글쓰기 저장
		if (com.equals("/write.do")) {
			command = new BoardwriteCommand();
		command.execute(request, response);
		nextPage = "list.do";
		}
		
		//글, 제목 검색하기
		if(com.equals("/search.do")) {
			command=new BoardSearchCommand();
			command.execute(request, response);
			nextPage="list.jsp";
		}
		
		//글 자세히 보기
		if(com.equals("/detail.do")) {
			command = new BoardDetailCommand();
			command.execute(request, response);
			nextPage="Detail.jsp";
		}
		
		//수정하기
		if(com.equals("/update.do")) {
			command = new BoardUpdateCommand();
			command.execute(request, response);
			nextPage = "list.do";
		}
		
		
		//삭제하기
		if(com.equals("/delete.do")) {
			command = new BoardDeleteCommand();
			command.execute(request, response);
			nextPage = "list.do";
		}
		
		

		// 인터페이스의 요청과 응답을 받아서 nextPage로 해당 값을 넘기기
		if (nextPage != null) {
			RequestDispatcher dis = request.getRequestDispatcher(nextPage);
			if (dis != null) {
				dis.forward(request, response);
			} else
				System.out.println("찾아갈 페이지가 없습니다.");
		}
	}

}
