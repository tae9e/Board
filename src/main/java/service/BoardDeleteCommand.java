package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BoardDAO;

public class BoardDeleteCommand implements BoardCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String num = request.getParameter("num");
		System.out.println("num...." + num);
		BoardDAO dao = new BoardDAO();
		dao.delete(num);

	}

}
