package kr.co.subking.ingredients;

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

@WebServlet({ "/api/v1/ingredients", "/api/v1/ingredients/*" })
public class IngredientsAPI extends HttpServlet {
	private IngredientsService ingredientsService = IngredientsServiceImpl.getInstance();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}

	// custom_menu_id (버거 1개)의 재료 정보들을 가져오기 위해 설정
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uri = req.getRequestURI();

		Integer menu_id = parsingIdPath(uri);
		
		List<ingredientsList> igList = ingredientsService.selectIgnameAndCount(menu_id);
		
		resp.setHeader("Content-Type", "application/json; charset=utf-8");
		JsonMapper jsonMapper = new JsonMapper();
		String json  = jsonMapper.writeValueAsString(igList);
		
		PrintWriter pw = resp.getWriter();
		pw.print(json);
		pw.flush();
	}

	private Integer parsingIdPath(String uri) {
		int lastIndexOf = uri.lastIndexOf('/');
		String idPath = uri.substring(lastIndexOf + 1);
		Integer menu_id = Integer.valueOf(idPath);

		return menu_id;
	}

}
