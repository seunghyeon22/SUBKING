package kr.co.subking.user;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import subking.config.AppContextListener;

public class UserServiceImple implements UserService {
	private static final UserServiceImple instance = new UserServiceImple();

	public UserServiceImple() {
	}

	public static UserService getInstance() {
		return instance;
	}

	@Override
	public List<User> selectAll() {
//		try (SqlSession sqlSession = AppContextListener.getSqlSession()) {
//			UserMapper mapper = sqlSession.getMapper(UserMapper.class);
//			List<User> list = mapper.selectAll();
//
//			return list;
//		}
		return null;
	}
//
//	@Override
//	public int insert(User user) {
//		try (SqlSession sqlSession = AppContextListener.getSqlSession()) {
//			UserMapper mapper = sqlSession.getMapper(UserMapper.class);
//			int pk = mapper.insert(user);
//
//			sqlSession.commit();
//
//			return pk;
//		}
//	}
//
//	@Override
//	public User selectByPk(int pk) {
//		try (SqlSession sqlSession = AppContextListener.getSqlSession()) {
//			UserMapper mapper = sqlSession.getMapper(UserMapper.class);
//			User user = mapper.selectByPk(pk);
//
//			return user;
//		}
//	}
//
//	@Override
//	public User update(User user) {
//		try (SqlSession sqlSession = AppContextListener.getSqlSession()) {
//			UserMapper mapper = sqlSession.getMapper(UserMapper.class);
//			int rows = mapper.update(user);
//
//			if (rows == 1) {
//				sqlSession.commit();
//				return user;
//			}
//		}
//		return null;
//	}
//
	@Override
	public int delete(String user_id) {
		try (SqlSession sqlSession = AppContextListener.getSqlSession()) {
			UserMapper mapper = sqlSession.getMapper(UserMapper.class);
			int rows = mapper.delete(user_id);

			if (rows == 1) {
				sqlSession.commit();
				return rows;
			}
		}
		return 0;
	}

	@Override
	public int insertUser(User user) {
		try (SqlSession sqlSession = AppContextListener.getSqlSession()){
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			int result = userMapper.insertUser(user);	
			sqlSession.commit();
			return result;
		}
		
	}

	@Override
	public User selectByPk(int pk) {
		// TODO Auto-generated method stub
		return null;
	}
   @Override
	public User findIdByNameAndPhone(String user_name, String user_phone) {
        try (SqlSession sqlSession = AppContextListener.getSqlSession()) {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            User user = userMapper.findIdByNameAndPhone(user_name, user_phone); 
            sqlSession.commit();
            return user; // 사용자 정보 반환
	}
}
   @Override
	public User findIdByPw(String user_id, String user_name, String user_phone) {
        try (SqlSession sqlSession = AppContextListener.getSqlSession()) {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            User user = userMapper.findIdByPw(user_id, user_name, user_phone); 
            sqlSession.commit();
            return user; // 사용자 정보 반환
	}
}
	@Override
	public int update2(String user_id, String user_pw) {
        try (SqlSession sqlSession = AppContextListener.getSqlSession()) {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            int user = userMapper.update2(user_id, user_pw); 
            sqlSession.commit();
            return user; // 사용자 정보 반환
	}
}
}


