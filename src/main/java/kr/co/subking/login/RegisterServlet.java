package kr.co.subking.login;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;

import com.fasterxml.jackson.databind.json.JsonMapper;

import kr.co.subking.user.User;
import kr.co.subking.user.UserMapper;
import subking.config.AppContextListener;

@WebServlet("/api/v1/register")
public class RegisterServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");

		// JSON 형식으로 응답할 때 인코딩 설정
		resp.setContentType("application/json; charset=UTF-8");
		resp.setCharacterEncoding("UTF-8");

		// JSON 형식으로 전송된 요청 데이터 파싱
		BufferedReader reader = req.getReader();
		StringBuilder sb = new StringBuilder();
		String line;
		while ((line = reader.readLine()) != null) {
			sb.append(line);
		}
		String json = sb.toString();

		// JSON 데이터 파싱
//        Gson gson = new Gson();
//        User user = gson.fromJson(requestData, User.class);

		JsonMapper jsonMapper = new JsonMapper();
		System.out.println(json);
		User user = jsonMapper.readValue(json, User.class);

		// 회원가입 처리 (DB에 사용자 정보 저장 로직)
		boolean success = false;
		String message = "";
		try (SqlSession sqlSession = AppContextListener.getSqlSession()) {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

			// ID 중복 체크
//            if (userMapper.checkUserExists(user.getUser_id()) > 0) {
//                message = "이미 존재하는 아이디입니다.";
//            } else {
			// 사용자 정보 저장
			userMapper.insertUser(user);
			sqlSession.commit();
			success = true;
//            }
		} catch (Exception e) {
			e.printStackTrace();
			message = "회원가입 중 오류가 발생했습니다.";
		}

		// JSON 응답 생성
		resp.setContentType("application/json");
		PrintWriter out = resp.getWriter();
		if (success) {
			out.println("{\"success\": true}");
		} else {
			out.println("{\"success\": false, \"message\": \"" + message + "\"}");
		}
		out.close();
		
		resp.sendRedirect("http://localhost:8080/240930subKingProject/api/v1/tempLogIn");
	}
}
