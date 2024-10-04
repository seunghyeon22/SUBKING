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
		
		// 문제가 있다. custom_ig_id, custom_count인데 필요한건 이름이다.
		// 새로운 객체를 만들어야 하나? custom_ig_id, ig_name, custom_count 3개를 필드로 가지는
		// JOIN해서 select하고
//		List<Ingredients> list = ingredientsService.getIgListByMenuId(menu_id);
	}

	private Integer parsingIdPath(String uri) {
		int lastIndexOf = uri.lastIndexOf('/');
		String idPath = uri.substring(lastIndexOf + 1);
		Integer menu_id = Integer.valueOf(idPath);

		return menu_id;
	}

}
