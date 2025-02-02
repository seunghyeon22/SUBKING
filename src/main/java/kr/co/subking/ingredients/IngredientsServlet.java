package kr.co.subking.ingredients;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/custom/ingredient")
public class IngredientsServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String user_role = (String) req.getSession().getAttribute("user_role");

		if (user_role.equals("user_admin")) {
			req.getRequestDispatcher("../static/html/ingredient_list.html").forward(req, resp);
		} else {
			resp.sendRedirect("http://localhost:8080/240930subKingProject/api/v1/tempLogIn");
		}

	}
}
