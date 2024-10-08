package kr.co.subking.orders;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.json.JsonMapper;

@WebServlet("/api/v1/orders")
public class OrdersAPI extends HttpServlet{
	private OrdersService ordersService = OrdersServiceImple.getInstance();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		String user_id = (String) session.getAttribute("user_id");
		System.out.println(user_id);
		List<Orders> ordersList = ordersService.selectByUserId(user_id);
		
		resp.setHeader("Content-Type", "application/json; charset=utf-8");
		JsonMapper jsonMapper = new JsonMapper();
		String json  = jsonMapper.writeValueAsString(ordersList);
		
		PrintWriter pw = resp.getWriter();
		pw.print(json);
		pw.flush();
		
	}
}
