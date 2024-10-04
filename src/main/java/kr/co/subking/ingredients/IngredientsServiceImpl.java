package kr.co.subking.ingredients;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import subking.config.AppContextListener;

public class IngredientsServiceImpl implements IngredientsService {
	private static final IngredientsServiceImpl instance = new IngredientsServiceImpl();

	private IngredientsServiceImpl() {
	}

	public static IngredientsService getInstance() {
		return instance;
	}

	@Override
	public List<Ingredients> selectAll() {
		try (SqlSession sqlSession = AppContextListener.getSqlSession()) {
			IngredientsMapper mapper = sqlSession.getMapper(IngredientsMapper.class);
			List<Ingredients> list = mapper.selectIg();
			return list;
		}
	}
}
