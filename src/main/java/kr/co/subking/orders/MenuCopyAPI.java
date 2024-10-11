package kr.co.subking.orders;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.subking.cart.CartService;
import kr.co.subking.cart.CartServiceImpl;

@WebServlet({"/api/v1/menucopy", "/api/v1/menucopy/*"})
public class MenuCopyAPI extends HttpServlet {
	private static final CartService cartService = CartServiceImpl.getInstance();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uri = req.getRequestURI();
		int cartlist_menu_id = parsingIdPath(uri);
		
		HttpSession session = req.getSession();
		String user_id = (String) session.getAttribute("user_id");
		
		int result = cartService.insertCartAndMenuId(user_id, cartlist_menu_id);
		
		// result가 1이면 성공 아니면 실패
		resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter out = resp.getWriter();
        out.print(result);
	}
	
	private Integer parsingIdPath(String uri) {
		int lastIndexOf = uri.lastIndexOf('/');
		String idPath = uri.substring(lastIndexOf + 1);
		Integer order_id = Integer.valueOf(idPath);

		return order_id;
	}
}
