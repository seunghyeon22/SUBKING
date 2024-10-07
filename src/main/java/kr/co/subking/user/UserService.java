package kr.co.subking.user;

import java.util.List;

public interface UserService {

	List<User> selectAll();

	int insert(User user);

	User selectByPk(int pk);

	User update(User user);
	
	int delete(int UserId);
}
