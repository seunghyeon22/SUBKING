package kr.co.subking.login;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;

import com.fasterxml.jackson.databind.json.JsonMapper;
import com.mysql.cj.Session;

import kr.co.subking.user.User;
import kr.co.subking.user.UserMapper;
import kr.co.subking.user.UserServiceImple;
import subking.config.AppContextListener;
import subking.config.PasswordUtils;
import subking.config.WebUtil;


@WebServlet("/api/v1/tempResetPw")
public class TempResetPw extends HttpServlet {
	private static final String formURL = "/static/jsp/resetpw.jsp";
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 사용자 입력 정보 받기
		resp.setContentType("application/json; charset=UTF-8");
		req.setCharacterEncoding("UTF-8");
		WebUtil webUtil = new WebUtil();
		String json = webUtil.readBody(req);
		
		System.out.println(json); // {qwert}
		
		JsonMapper jsonMapper = new JsonMapper();
		HttpSession session = req.getSession();
		
		Temp temp = jsonMapper.readValue(json, Temp.class);
		String user_id = (String)session.getAttribute("asd");
		String user_pw = temp.getUser_pw();
		try (SqlSession sqlSession = AppContextListener.getSqlSession()) {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			
		UserServiceImple userServiceimple = new UserServiceImple();
		System.out.println(user_id);
		
		String hashedPassword = PasswordUtils.hashPassword(user_pw);
		int user = userServiceimple.update2(user_id, hashedPassword); // 사용자 정보를 찾는 메서드 호출
		
		
//		System.out.println(json);
//		PrintWriter out = resp.getWriter();
//		try (PrintWriter out = resp.getWriter()) {
//			if (user.getUser_pw() != null) {
//				out.println("{\"success\": true, \"message\": \"인증이 완료되었습니다.: " + "\"}");
//				resp.setStatus(HttpServletResponse.SC_OK);
//			} else {
//				out.println("{\"success\": false, \"message\": \"정보를 확인하세요.\"}");
//				resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
//			}
//			out.close();
//		}
	
	}
}
}