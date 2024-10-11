package kr.co.subking.user;

import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;

public interface UserService {

	List<User> selectAll();
//
//	int insert(User user);
// 
	User selectByPk(int pk);
//
//	User update(User user);
//	
	
	int insertUser(User user);
	
	int delete(String user_id);
	
	User findIdByNameAndPhone(String user_name, String user_phone);
	
	User findIdByPw(String user_id, String user_name, String user_phone);
	
	int update2(String user_pw, String user_id);
}
