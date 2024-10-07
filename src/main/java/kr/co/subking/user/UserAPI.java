package kr.co.subking.user;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.json.JsonMapper;

import kr.co.subking.*;
import lombok.extern.slf4j.Slf4j;
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
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		WebUtil webUtil = new WebUtil();
		String json = webUtil.readBody(req);
		JsonMapper jsonMapper = new JsonMapper();
		User user = jsonMapper.readValue(json, User.class);

		log.info(user.toString());
		
		service.insert(user);
		
		webUtil.setCodeAndMimeType(resp, 201, "json");
		webUtil.writeBodyJson(resp, user);
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		WebUtil webUtil = new WebUtil();
		String json = webUtil.readBody(req);
		JsonMapper jsonMapper = new JsonMapper();
		User user = jsonMapper.readValue(json, User.class);
	
		User updateduser = service.update(user);
		
		if (updateduser == null) {
			resp.setStatus(404);
			return;
		}
		
		webUtil.setCodeAndMimeType(resp, 201, "json");
		webUtil.writeBodyJson(resp, updateduser);
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		WebUtil webUtil = new WebUtil();
		String json = webUtil.readBody(req);
		JsonMapper jsonMapper = new JsonMapper();

		JsonNode rootNode = jsonMapper.readTree(json);
		int user_id = rootNode.path("user_id").asInt();
		
		int rows = service.delete(user_id);	
		
		if (rows == 1) {
			resp.setStatus(204);
		} else {
			resp.setStatus(404);
		}
	}
}






