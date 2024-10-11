package kr.co.subking.custom;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.json.JsonMapper;

import kr.co.subking.ingredients.Ingredients;
import subking.config.WebUtil;

@WebServlet("/api/v1/kcal")
public class KcalCustomAPI extends HttpServlet {
	private CustomService customService = CustomServiceImpl.getInstance();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		WebUtil webUtil = new WebUtil();
		String json = webUtil.readBody(req);
		JsonMapper jsonMapper = new JsonMapper();

		Integer cal = jsonMapper.readValue(json, Integer.class);
		int kcal = (int) cal;
		List<List<Ingredients>> lists = customService.customKcal(kcal);
		resp.setHeader("Content-Type", "application/json; charset=utf-8");
		String jsons = jsonMapper.writeValueAsString(lists);
		System.out.println(jsons);
		PrintWriter pw = resp.getWriter();
		pw.print(jsons);
		pw.flush();

	}
}
