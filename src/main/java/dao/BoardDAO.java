package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import dto.BoardDTO;

public class BoardDAO {
	Connection conn = null;
	PreparedStatement pstmt = null;
	DataSource ds = null;
	ResultSet rs = null;

	// static이나 instance변수가 없으면 가장 먼저 실행되는 메소드, 다른 곳에서 객체 생성해서 dao를 쓰기 위해서는 연결이 가장
	// 먼저 돼야 하기 때문에
	public BoardDAO() {
		try {
			Context init = new InitialContext();
			ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");
		} catch (NamingException e) {
		}
	}

	
	// 번호 역순으로 나오도록 order by num desc
	public ArrayList<BoardDTO> list() {
		ArrayList<BoardDTO> list = new ArrayList<BoardDTO>();
		try {
			conn = ds.getConnection();
			String sql = "select num,writer,title, "
					+ "content,to_char(writeday, 'YYYY/MM/DD') writeday, "
					+ "Readcnt,repRoot,repStep,repIndent "
					+ " from boardT order by num desc";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			System.out.println("rs의 값은?" + rs);
			System.out.println("Before while loop");
			while (rs.next()) {
				System.out.println("while: " + rs.getString("num"));

				int num = rs.getInt("num");
				String writer = rs.getString("writer");
				String title = rs.getString("title");
				String content = rs.getString("content");
				String writeday = rs.getString("writeday");
				int Readcnt = rs.getInt("Readcnt");
				int repRoot = rs.getInt("repRoot");
				int repStep = rs.getInt("repStep");
				int repIndent = rs.getInt("repIndent");

				BoardDTO data = new BoardDTO();
				data.setNum(num);
				data.setWriter(writer);
				data.setTitle(title);
				data.setContent(content);
				data.setWriteday(writeday);
				data.setReadcnt(Readcnt);
				data.setRepRoot(repRoot);
				data.setRepStep(repStep);
				data.setRepIndent(repIndent);

				list.add(data);

			}

			System.out.println("After while loop");

		} catch (SQLException e) {

			e.printStackTrace();

		}
		return list;
	}

	// 글쓰기
	public void write(String writer, String title, String content) {
		String sql = "insert into boardT(num,writer,title,content,repRoot,repStep,repIndent ) "
				+ "values ( boardT_seq.nextval,?,?,?,boardT_seq.CURRVAL,0,0)";

		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, writer);
			pstmt.setString(2, title);
			pstmt.setString(3, content);
			// insert문은 update로
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();

		}
	}

	
	// 글, 제목 검색(LIKE)
	public ArrayList<BoardDTO> search(String searchName, String searchValue) {
		ArrayList<BoardDTO> list = new ArrayList<BoardDTO>();
		try {
			conn = ds.getConnection();
			String sql = "select num,writer,title,content,to_char(writeday, 'YYYY/MM/DD') writeday,Readcnt"
					+ " from boardT ";
			
			System.out.println("sql의 값은? " + sql);
			if (searchName.equals("title")) {
				sql += " where title LIKE ?";
			} else {
				sql += " where writer LIKE ?";
			}
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + searchValue + "%");

			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				System.out.println("rs의 값은?" + rs.getString("writer"));

				int num = rs.getInt("num");
				String writer = rs.getString("writer");
				String title = rs.getString("title");
				String content = rs.getString("content");
				String writeday = rs.getString("writeday");
				int Readcnt = rs.getInt("Readcnt");

				BoardDTO data = new BoardDTO();
				data.setNum(num);
				data.setWriter(writer);
				data.setTitle(title);
				data.setContent(content);
				data.setWriteday(writeday);
				data.setReadcnt(Readcnt);

				list.add(data);
				System.out.println("data의 값은? " + data);
			}

		} catch (SQLException e) {

			e.printStackTrace();

		}
		return list;
	}

	// 한 명만 조회
	public BoardDTO select(String num) {
		BoardDTO data = new BoardDTO();
		readCount(num); // 조회수 증가
		try {
			conn = ds.getConnection();
			String sql = "select num,writer,title,content,to_char(writeday, 'YYYY/MM/DD') writeday,Readcnt,repRoot,repStep,repIndent "
					+ " from boardT where num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(num));

			rs = pstmt.executeQuery();
			if (rs != null && rs.next()) {

				int num2 = rs.getInt("num");
				String writer = rs.getString("writer");
				String title = rs.getString("title");
				String content = rs.getString("content");
				String writeday = rs.getString("writeday");
				int Readcnt = rs.getInt("Readcnt");

				data = new BoardDTO();
				data.setNum(num2);
				data.setWriter(writer);
				data.setTitle(title);
				data.setContent(content);
				data.setWriteday(writeday);
				data.setReadcnt(Readcnt);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return data;
	}

	// 수정
	public void update(int num, String writer, String title, String content) {
		try {
			conn = ds.getConnection();
			String sql = "update boardT set title=?, writer=?, content=? where num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, title);
			pstmt.setString(2, writer);
			pstmt.setString(3, content);
			pstmt.setInt(4, num);
			pstmt.executeUpdate();

			conn.commit();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void readCount(String num) {

		try {
			conn = ds.getConnection();
			String query = "update boardT set Readcnt=Readcnt+1 where num=?";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, Integer.parseInt(num));
			pstmt.executeUpdate();
		} catch (SQLException e) {
		}
	}

	public void delete(String num) {

		try {
			conn = ds.getConnection();
			String query = "delete from boardT  where num=?";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, Integer.parseInt(num));
			pstmt.executeUpdate();
		} catch (SQLException e) {

		}
	}
}