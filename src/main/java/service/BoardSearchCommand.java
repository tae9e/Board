package service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BoardDAO;
import dto.BoardDTO;

public class BoardSearchCommand implements BoardCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		BoardDAO dao = new BoardDAO();
		String searchName = request.getParameter("searchName");
		String searchValue = request.getParameter("searchValue");
		System.out.println("searchValue는? " + searchValue);
		System.out.println("searchName?" + searchName);
		ArrayList<BoardDTO> list = dao.search(searchName, searchValue);
		System.out.println("list는? " + list);
		request.setAttribute("data", list);
	}

}
