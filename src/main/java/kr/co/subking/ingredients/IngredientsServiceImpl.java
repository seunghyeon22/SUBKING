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
			List<Ingredients> list = mapper.selectAll();

			return list;
		}
		
	}

	@Override
	public List<ingredientsList> selectIgnameAndCount(Integer menu_id) {
		try (SqlSession sqlSession = AppContextListener.getSqlSession()) {
			IngredientsMapper mapper = sqlSession.getMapper(IngredientsMapper.class);
			
			List<ingredientsList> list = mapper.selectIgnameAndCount(menu_id);
			return list;
		}
	}

}
