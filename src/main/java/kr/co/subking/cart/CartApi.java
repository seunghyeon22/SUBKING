package kr.co.subking.cart;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import kr.co.subking.custom.CustomService;
import kr.co.subking.custom.CustomServiceImpl;
import subking.config.WebUtil;

@WebServlet("/api/v1/cart")
public class CartApi extends HttpServlet {
	private CustomService customService = CustomServiceImpl.getInstance();
	private CartService cartService = CartServiceImpl.getInstance();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doGet(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		WebUtil webUtil = new WebUtil();
		String userId = (String) req.getSession().getAttribute("userId");
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

}
