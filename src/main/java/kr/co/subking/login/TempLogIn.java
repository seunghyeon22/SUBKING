package kr.co.subking.login;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/api/v1/tempLogIn")
public class TempLogIn extends HttpServlet {
	private static final String formURL = "/static/html/login2.html";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher(formURL).forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String user_id = req.getParameter("username");
		System.out.println(user_id);
		HttpSession session = req.getSession();
		session.setAttribute("user_id", user_id);
		
		session.setAttribute("message", "로그인에 성공하셨습니다.");
		
		resp.sendRedirect("http://localhost:8080/240930subKingProject/static/jsp/subking.jsp");
	}

}
