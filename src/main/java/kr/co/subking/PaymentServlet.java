package kr.co.subking;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/custom/payment")
public class PaymentServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		req.getSession().setAttribute("userId", "asdf");
		String userId = (String) req.getSession().getAttribute("userId");
		if (userId != null) {
			req.getRequestDispatcher("/WEB-INF/views/custom/payment.jsp").forward(req, resp);
		} else {
			resp.sendRedirect("../static/html/login.html");
		}
	}

}
