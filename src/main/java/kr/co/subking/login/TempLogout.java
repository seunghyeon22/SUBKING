package kr.co.subking.login;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/api/v1/tempLogOut")
public class TempLogout extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		session.invalidate();
		
		session = req.getSession();
		
//		session.setAttribute("message", "로그아웃되었습니다.");
		
		resp.setContentType("text/html; charset=UTF-8");
	    PrintWriter out = resp.getWriter();
		out.println("<script>");
	    out.println("alert('로그아웃하셨습니다.');");
	    out.println("window.location.href = 'http://localhost:8080/240930subKingProject/custom/home';");
	    out.println("</script>");
	    out.close();
		
//		resp.sendRedirect("http://localhost:8080/240930subKingProject/static/jsp/subking.jsp");
	}
	
}
