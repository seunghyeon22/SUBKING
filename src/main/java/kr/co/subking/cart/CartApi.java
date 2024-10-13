package kr.co.subking.cart;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import com.fasterxml.jackson.databind.json.JsonMapper;

import kr.co.subking.custom.CustomService;
import kr.co.subking.custom.CustomServiceImpl;
import subking.config.WebUtil;

@WebServlet("/api/v1/cart")
public class CartApi extends HttpServlet {
	private CustomService customService = CustomServiceImpl.getInstance();
	private CartService cartService = CartServiceImpl.getInstance();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		req.getSession().setAttribute("user_id", "asdf");
		String userId = (String) req.getSession().getAttribute("user_id");

		List<Cartlist1> list = cartService.selectUserIdbyAllMenu(userId);
		resp.setHeader("Content-Type", "application/json; charset=utf-8");

		JsonMapper jsonMapper = new JsonMapper();
		String json = jsonMapper.writeValueAsString(list);
		PrintWriter pw = resp.getWriter();
		pw.print(json);
		pw.flush();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		WebUtil webUtil = new WebUtil();
		String userId = (String) req.getSession().getAttribute("user_id");
		String json = webUtil.readBody(req);
		JSONArray jsonArr = new JSONArray(json);
		List<Integer> list = new ArrayList<Integer>();

		for (int i = 0; i < jsonArr.length(); i++) {
			list.add(jsonArr.getInt(i));
		}
		int result = customService.insertcart(list);
		cartService.insertCart(userId, result);

		webUtil.setCodeAndMimeType(resp, 201, "json");
		webUtil.writeBodyJson(resp, result);

	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		WebUtil webUtil = new WebUtil();
		String json = webUtil.readBody(req);
		
		String user_id = (String)req.getSession().getAttribute("user_id");

		JSONArray jsonArr = new JSONArray(json);
		List<Integer> list = new ArrayList<Integer>();

		for (int i = 0; i < jsonArr.length(); i++) {
			list.add(jsonArr.getInt(i));
		}

		int result = cartService.DeleteCartbyUserId(user_id, list);
		webUtil.setCodeAndMimeType(resp, 201, "json");
		webUtil.writeBodyJson(resp, result);
	}

}
