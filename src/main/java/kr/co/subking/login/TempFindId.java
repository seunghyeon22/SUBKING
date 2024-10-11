package kr.co.subking.login;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.json.JsonMapper;

import kr.co.subking.user.User;
import kr.co.subking.user.UserServiceImple;
import subking.config.WebUtil;

@WebServlet("/api/v1/tempFindId")
public class TempFindId extends HttpServlet {
	private static final String formURL = "http://localhost:8080/240930subKingProject/static/jsp/findid.jsp";

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 사용자 입력 정보 받기

		req.setCharacterEncoding("UTF-8");
		WebUtil webUtil = new WebUtil();
		String json = webUtil.readBody(req);
		JsonMapper jsonMapper = new JsonMapper();

		System.out.println(json);
		Temp temp = jsonMapper.readValue(json, Temp.class);
		String user_name = temp.getUser_name();
		String user_phone = temp.getUser_phone();
		System.out.println(user_name);
		System.out.println(user_phone);

		UserServiceImple userServiceimple = new UserServiceImple();
		User user = userServiceimple.findIdByNameAndPhone(user_name, user_phone); // 사용자 정보를 찾는 메서드 호출
		System.out.println("User: " + user.getUser_id()); // User 객체 출력

		resp.setContentType("application/json; charset=UTF-8");

//		PrintWriter out = resp.getWriter();
		try (PrintWriter out = resp.getWriter()) {
			if (user.getUser_id() != null) {
				out.println("{\"success\": true, \"message\": \"아이디를 찾았습니다: " + user.getUser_id() + "\"}");
				resp.setStatus(HttpServletResponse.SC_OK);
			} else {
				out.println("{\"success\": false, \"message\": \"아이디를 찾을 수 없습니다. 정보를 확인하세요.\"}");
				resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			}
			out.close();
		}
	}

}
//try (PrintWriter out = resp.getWriter()) {
//out.println("<html>");
//out.println("<head><title>아이디 찾기 결과</title></head>");
//out.println("<body>");
//if (user.getUser_id() != null) {
//// 아이디 찾기에 성공한 경우
//out.println("<script>");
//System.out.println("아이디 찾기전");
//out.println("alert('아이디를 찾았습니다: " + user.getUser_id() + "');");
//System.out.println("아이디 찾은 후");
////out.println("window.location.href = '" + formURL + "';"); // 찾기 결과 페이지로 리다이렉션
//out.println("</script>");
//} else {
//// 아이디 찾기에 실패한 경우
//out.println("<script>");
//out.println("alert('아이디를 찾을 수 없습니다. 정보를 확인하세요.');");
//out.println("window.location.href = '" + formURL + "'	;"); // 다시 아이디 찾기 페이지로 리다이렉션
//out.println("</script>");
//}
//out.println("</body>");
//out.println("</html>");
