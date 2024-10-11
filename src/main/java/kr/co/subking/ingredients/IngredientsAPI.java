package kr.co.subking.ingredients;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet({ "/api/v1/ingredients", "/api/v1/ingredients/*" })
public class IngredientsAPI extends HttpServlet {
    private IngredientsService ingredientsService = IngredientsServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("IngredientsAPI: doGet 호출됨");  // 디버그 로그 추가
        List<Ingredients> ingredientsList = ingredientsService.selectAll();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(resp.getOutputStream(), ingredientsList);
    }

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    req.setCharacterEncoding("UTF-8");
	    resp.setContentType("application/json; charset=UTF-8");

	    String ig_name = req.getParameter("ig_name");
	    String ig_category = req.getParameter("ig_category");
	    String ig_priceStr = req.getParameter("ig_price");
	    String ig_kcalStr = req.getParameter("ig_kcal");
	    String ig_image = req.getParameter("ig_image");

	    if (ig_name == null || ig_category == null || ig_image == null || ig_priceStr == null || ig_kcalStr == null) {
	        resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
	        resp.getWriter().write("Missing required parameters");
	        return;
	    }

	    try {
	        int ig_price = Integer.parseInt(ig_priceStr);
	        int ig_kcal = Integer.parseInt(ig_kcalStr);

	        Ingredients ingredient = Ingredients.builder()
	            .ig_name(ig_name)
	            .ig_category(ig_category)
	            .ig_price(ig_price)
	            .ig_kcal(ig_kcal)
	            .ig_image(ig_image)
	            .build();

	        IngredientsServiceImpl.getInstance().addIngredient(ingredient);
	    } catch (NumberFormatException e) {
	        resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
	        resp.getWriter().write("Invalid number format");
	    } catch (Exception e) {
	        resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
	        resp.getWriter().write("Server error");
	    }
	}


	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uri = req.getRequestURI();
		Integer menu_id = parsingIdPath(uri);

		List<IngredientsList> igList = ingredientsService.selectIgnameAndCount(menu_id);

		resp.setHeader("Content-Type", "application/json; charset=utf-8");
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(igList);
		resp.getWriter().print(json);
	}

	private Integer parsingIdPath(String uri) {
		int lastIndexOf = uri.lastIndexOf('/');
		String idPath = uri.substring(lastIndexOf + 1);
		return Integer.valueOf(idPath);
	}
}
