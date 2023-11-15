package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BoardDAO;
import dto.BoardDTO;

public class BoardDetailCommand implements BoardCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String num = request.getParameter("num");
		BoardDAO dao = new BoardDAO();
		BoardDTO data = dao.select(num);
		request.setAttribute("detail", data);

	}

}
