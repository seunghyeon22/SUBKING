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

import subking.config.WebUtil;

@WebServlet("/api/v1/orders")
public class OrdersAPI extends HttpServlet {
	private OrdersService ordersService = OrdersServiceImple.getInstance();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		String user_id = (String) session.getAttribute("user_id");
		System.out.println(user_id);
		List<Orders> ordersList = ordersService.selectByUserId(user_id);

		resp.setHeader("Content-Type", "application/json; charset=utf-8");
		JsonMapper jsonMapper = new JsonMapper();
		String json = jsonMapper.writeValueAsString(ordersList);

		PrintWriter pw = resp.getWriter();
		pw.print(json);
		pw.flush();

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		WebUtil webUtil = new WebUtil();

		String json = webUtil.readBody(req);
		String user_id = (String) req.getSession().getAttribute("user_id");
		JsonMapper jsonMapper = new JsonMapper();
		Menulist menulist = jsonMapper.readValue(json, Menulist.class);

		List<Integer> menu_ids = menulist.getMenu_ids();
		int price = menulist.getPrice();
		String address = menulist.getAddress();
		
		int result = ordersService.InsertMenulist(user_id, menu_ids, price,address);
		webUtil.setCodeAndMimeType(resp, 201, "json");
		webUtil.writeBodyJson(resp, result);
	}
}
