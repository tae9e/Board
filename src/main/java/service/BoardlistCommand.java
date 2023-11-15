package service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BoardDAO;
import dto.BoardDTO;

public class BoardlistCommand implements BoardCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		BoardDAO dao = new BoardDAO();
		ArrayList<BoardDTO> list = dao.list();
		
		//tl태그에 값을 넣기 위해 set으로
		request.setAttribute("data", list);
		
		System.out.println("Data size: " + list.size());

	}

}
