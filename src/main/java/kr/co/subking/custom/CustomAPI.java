package kr.co.subking.custom;

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

import kr.co.subking.ingredients.Ingredients;
import kr.co.subking.ingredients.IngredientsService;
import kr.co.subking.ingredients.IngredientsServiceImpl;

@WebServlet("/api/v1/custom")
public class CustomAPI extends HttpServlet {
	private IngredientsService service = IngredientsServiceImpl.getInstance();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Ingredients> list = service.selectAll();

		List<Ingredients> bread = new ArrayList<Ingredients>();
		List<Ingredients> vegetable = new ArrayList<Ingredients>();
		List<Ingredients> patty = new ArrayList<Ingredients>();
		List<Ingredients> sauce = new ArrayList<Ingredients>();
		List<Ingredients> cheese = new ArrayList<Ingredients>();

		for (Ingredients ig : list) {
			if (ig.getIg_category().equals("빵")) {
				bread.add(ig);
			}
			if (ig.getIg_category().equals("야채")) {
				vegetable.add(ig);
			}
			if (ig.getIg_category().equals("패티")) {
				patty.add(ig);
			}
			if (ig.getIg_category().equals("소스")) {
				sauce.add(ig);
			}
			if (ig.getIg_category().equals("치즈")) {
				cheese.add(ig);
			}
		}
		System.out.println(bread.toString());
		System.out.println(vegetable.toString());
		System.out.println(patty.toString());
		System.out.println(sauce.toString());
		System.out.println(cheese.toString());

		resp.setHeader("Content-Type", "application/json; charset=utf-8");
		JsonMapper jsonMapper = new JsonMapper();
		String json1 = jsonMapper.writeValueAsString(list);
//		String json2 = jsonMapper.writeValueAsString(vegetable);
//		String json3 = jsonMapper.writeValueAsString(patty);
//		String json4 = jsonMapper.writeValueAsString(sauce);
//		String json5 = jsonMapper.writeValueAsString(cheese);

		PrintWriter pw = resp.getWriter();
		pw.print(json1);
//		pw.print(json2);
//		pw.print(json3);
//		pw.print(json4);
//		pw.print(json5);
		pw.flush();
	}
}
