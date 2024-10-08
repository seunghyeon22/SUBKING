package kr.co.subking.user;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import subking.config.AppContextListener;

public class UserServiceImple implements UserService {
	private static final UserServiceImple instance = new UserServiceImple();

	private UserServiceImple() {
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
//	@Override
//	public int delete(int userId) {
//		try (SqlSession sqlSession = AppContextListener.getSqlSession()) {
//			UserMapper mapper = sqlSession.getMapper(UserMapper.class);
//			int rows = mapper.delete(userId);
//
//			if (rows == 1) {
//				sqlSession.commit();
//				return rows;
//			}
//		}
//		return 0;
//	}

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
}
