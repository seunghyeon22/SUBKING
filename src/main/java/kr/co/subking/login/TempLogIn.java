package kr.co.subking.login;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;

import kr.co.subking.user.UserMapper;
import subking.config.AppContextListener;
import subking.config.PasswordUtils;

// 임시로 
//@WebServlet("/api/v1/tempLogIn")
public class TempLogIn extends HttpServlet {
	private static final String loginURL = "/static/jsp/login.jsp";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher(loginURL).forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String user_id = req.getParameter("username");
		String user_pw = req.getParameter("password");
		
		int result = 0;
		try (SqlSession sqlSession = AppContextListener.getSqlSession()) {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			
			String userId = userMapper.checkUserExists(user_id);
			String hashedPassword = userMapper.getHashedPassword(user_id);
			if (userId != null && PasswordUtils.checkPassword(user_pw, hashedPassword)) {
				result = 1;
			} else {
				result = 0;
			}
		} catch ( IllegalArgumentException e) {
			result = 0;
		}
		
		resp.setContentType("text/html; charset=UTF-8");
	    PrintWriter out = resp.getWriter();
		
		// db에 아이디나 비밀번호가 없는 경우 
		if (result == 0) {
			out.println("<script>");
		    out.println("alert('비밀번호를 확인해주세요');");
		    out.println("window.location.href = 'http://localhost:8080/240930subKingProject/api/v1/tempLogIn';");
		    out.println("</script>");
		    out.close();
			return;
		}
		
		// 로그인 정보를 세션에 기록
		HttpSession session = req.getSession();
		session.setAttribute("user_id", user_id);
		
		// 로그인 성공 시 자바스크립트를 포함한 HTML 응답
	    out.println("<script>");
	    out.println("alert('로그인에 성공하셨습니다.');");
	    out.println("window.location.href = 'http://localhost:8080/240930subKingProject/custom/home';");
	    out.println("</script>");
	    out.close();
		
//		session.setAttribute("message", "로그인에 성공하셨습니다.");
//		resp.sendRedirect("http://localhost:8080/240930subKingProject/static/jsp/subking.jsp");
	}
}
