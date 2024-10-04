package kr.co.subking.menu;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.json.JsonMapper;

@WebServlet({ "/api/v1/menu", "/api/v1/menu/*" })
public class ManuAPI extends HttpServlet {
	private MenuService menuService = MenuServiceImple.getInstance();

	// /api/v1/menu/* *에 order_id가 들어간다. order_id에 맞는 햄버거들(메뉴들) list 반환
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uri = req.getRequestURI();
		
		Integer order_id = parsingIdPath(uri);
		
		List<Integer> menuListId = menuService.getMenuListId(order_id);		
		
		List<Menu> menuList = new ArrayList<Menu>();
		
		for (Integer i : menuListId) {
			menuList.add(menuService.getMenuByPk(i));
		}
		
		resp.setHeader("Content-Type", "application/json; charset=utf-8");
		JsonMapper jsonMapper = new JsonMapper();
		String json  = jsonMapper.writeValueAsString(menuList);
		
		PrintWriter pw = resp.getWriter();
		pw.print(json);
		pw.flush();
	}
	
	private Integer parsingIdPath(String uri) {
		int lastIndexOf = uri.lastIndexOf('/');
		String idPath = uri.substring(lastIndexOf + 1);
		Integer order_id = Integer.valueOf(idPath);

		return order_id;
	}
}
