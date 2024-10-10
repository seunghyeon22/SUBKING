package kr.co.subking.user;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.json.JsonMapper;

import lombok.extern.slf4j.Slf4j;
import subking.config.AppContextListener;
import subking.config.WebUtil;


@WebServlet("/api/v1/user")
@Slf4j
public class UserAPI extends HttpServlet {
	private UserService service = UserServiceImple.getInstance();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<User> list = service.selectAll();

		resp.setHeader("Content-Type", "application/json; charset=utf-8");
		JsonMapper jsonMapper = new JsonMapper();
		String json = jsonMapper.writeValueAsString(list);

		PrintWriter pw = resp.getWriter();
		pw.print(json);
		pw.flush();
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		WebUtil webUtil = new WebUtil();
		// JSON 데이터를 User 객체로 변환
		String json = webUtil.readBody(request);
		JsonMapper jsonMapper = new JsonMapper();
		System.out.println(json);
		User user = jsonMapper.readValue(json, User.class);

		System.out.println(user.toString());

		try (SqlSession sqlSession = AppContextListener.getSqlSession()) {
			UserMapper mapper = sqlSession.getMapper(UserMapper.class);
			User select = mapper.selectByUserId(user.getUser_id());

			if (select != null) {
				Map<String, String> duplicateError = new HashMap<>();
				duplicateError.put("아이디", "이미 존재하는 아이디입니다");
				errorForwarding(request, response, user, duplicateError);
				return;
			}

			int result = service.insertUser(user);
			response.setStatus(200);
			sqlSession.commit();
		}

		HttpSession session = request.getSession();
		session.setAttribute("message", "회원가입이 완료되었습니다.");
		response.sendRedirect("http://localhost:8080/240930subKingProject/static/jsp/subking.jsp");
	}

	private void errorForwarding(HttpServletRequest request, HttpServletResponse response, User user,
			Map<String, String> error) throws ServletException, IOException {
		response.setStatus(400);
		request.setAttribute("error", error);
		request.setAttribute("user", user);
		request.getRequestDispatcher("http://localhost:8080/240930subKingProject/static/html/join.html").forward(request, response);
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		WebUtil webUtil = new WebUtil();
		String json = webUtil.readBody(req);
		JsonMapper jsonMapper = new JsonMapper();

		JsonNode rootNode = jsonMapper.readTree(json);
		String user_id = rootNode.path("user_id").asText();
		
		int rows = service.delete(user_id);
		
		if (rows == 1) {
			HttpSession session = req.getSession();
			session.invalidate();
			resp.setStatus(204);
		} else {
			resp.setStatus(404);
		}
	}

//	@Override
//	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		WebUtil webUtil = new WebUtil();
//		String json = webUtil.readBody(req);
//		JsonMapper jsonMapper = new JsonMapper();
//		User user = jsonMapper.readValue(json, User.class);
//
//		User updateduser = service.update(user);
//
//		if (updateduser == null) {
//			resp.setStatus(404);
//			return;
//		}
//
//		webUtil.setCodeAndMimeType(resp, 201, "json");
//		webUtil.writeBodyJson(resp, updateduser);
//	}
//
//	@Override
//	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		WebUtil webUtil = new WebUtil();
//		String json = webUtil.readBody(req);
//		JsonMapper jsonMapper = new JsonMapper();
//
//		JsonNode rootNode = jsonMapper.readTree(json);
//		int user_id = rootNode.path("user_id").asInt();
//
//		int rows = service.delete(user_id);
//
//		if (rows == 1) {
//			resp.setStatus(204);
//		} else {
//			resp.setStatus(404);
//		}
//	}
	
}
