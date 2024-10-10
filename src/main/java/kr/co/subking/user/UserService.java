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
}
