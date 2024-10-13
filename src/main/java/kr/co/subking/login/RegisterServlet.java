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
import org.json.JSONObject;

import com.fasterxml.jackson.databind.json.JsonMapper;

import kr.co.subking.cart.CartService;
import kr.co.subking.cart.CartServiceImpl;
import kr.co.subking.user.User;
import kr.co.subking.user.UserMapper;
import subking.config.AppContextListener;
import subking.config.PasswordUtils;

@WebServlet("/api/v1/register")
public class RegisterServlet extends HttpServlet {
	private static final CartService cartService = CartServiceImpl.getInstance();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// JSON 형식으로 응답할 때 인코딩 설정
		resp.setContentType("application/json; charset=UTF-8");
		resp.setCharacterEncoding("UTF-8");

		// JSON 형식으로 전송된 요청 데이터 파싱
		req.setCharacterEncoding("UTF-8");
		BufferedReader reader = req.getReader();
		StringBuilder sb = new StringBuilder();
		String line;
		while ((line = reader.readLine()) != null) {
			sb.append(line);
		}
		String json = sb.toString();

		JsonMapper jsonMapper = new JsonMapper();
		
		User user = jsonMapper.readValue(json, User.class);
		String password = user.getUser_pw();
		String hashedPassword = PasswordUtils.hashPassword(password);
		user.setUser_pw(hashedPassword);

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
			
			cartService.insertCartByUserId(user.getUser_id());
			
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
		
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Set response type to JSON
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");

        // Parse the request body to extract the user_id
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = req.getReader().readLine()) != null) {
            sb.append(line);
        }
        String requestBody = sb.toString();
        JSONObject jsonRequest = new JSONObject(requestBody);
        String userId = jsonRequest.getString("user_id");

        // Prepare the response JSON
        JSONObject jsonResponse = new JSONObject();
        
        try (SqlSession sqlSession = AppContextListener.getSqlSession()) {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			
			boolean isDuplicate = false;
			String duplicatedId = userMapper.checkUserExists(userId);
			
			if (duplicatedId != null) {
				isDuplicate = true;
			}
			// Respond with the result
            jsonResponse.put("isDuplicate", isDuplicate);
		} catch (Exception e) {
			e.printStackTrace();
	            jsonResponse.put("error", "Server error occurred.");
		}

        // Send the response
        PrintWriter out = resp.getWriter();
        out.print(jsonResponse.toString());
        out.flush();
	}

	
	
}
