package kr.co.subking.login;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;

import kr.co.subking.user.User;
import kr.co.subking.user.UserMapper;
import subking.config.AppContextListener;


@WebServlet("/api/v1/tempLogIn")
public class TempLogIn2 extends HttpServlet {
	private static final String loginURL = "/static/jsp/login.jsp";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher(loginURL).forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("application/json; charset=UTF-8");
		resp.setCharacterEncoding("UTF-8");
		req.setCharacterEncoding("UTF-8");
		
		String user_id = req.getParameter("username");
		String user_pw = req.getParameter("password");
		
		int result = 0;
		User user = new User();
		try (SqlSession sqlSession = AppContextListener.getSqlSession()) {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			user = userMapper.login(user_id, user_pw);
		}
		
		// db에 아이디나 비밀번호가 없는 경우 
		if (user==null) {
			req.setAttribute("result", "아이디 비밀번호를 확인해주세요");
			req.getRequestDispatcher(loginURL).forward(req, resp);
			return;
		}
		
		// 로그인 정보를 세션에 기록
		HttpSession session = req.getSession();
		
		session.setAttribute("user_id", user_id);
		session.setAttribute("user_role", user.getUser_role());
		session.setAttribute("user_address", user.getUser_address());
		session.setAttribute("message", "로그인에 성공하셨습니다.");
		
		resp.sendRedirect("http://localhost:8080/240930subKingProject/custom/home");
	}
}
