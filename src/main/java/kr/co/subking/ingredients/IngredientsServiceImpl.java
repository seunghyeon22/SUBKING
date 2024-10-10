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
    public void addIngredient(Ingredients ingredient) {
        try (SqlSession sqlSession = AppContextListener.getSqlSession()) {
            IngredientsMapper mapper = sqlSession.getMapper(IngredientsMapper.class);
            mapper.insertIg(ingredient);
            sqlSession.commit();
        }
    }

    @Override
	public List<IngredientsList> selectIgnameAndCount(Integer menu_id) {
		try (SqlSession sqlSession = AppContextListener.getSqlSession()) {
			IngredientsMapper mapper = sqlSession.getMapper(IngredientsMapper.class);
			
			List<IngredientsList> list = mapper.selectIgnameAndCount(menu_id);
			return list;
		}
	}
    @Override
    public List<CustomNameCount> selectMenubyIg(int menuId) {
    	try (SqlSession sqlSession = AppContextListener.getSqlSession()){
    		IngredientsMapper mapper = sqlSession.getMapper(IngredientsMapper.class);
    		List<CustomNameCount> list = mapper.selectMenubyIg(menuId);
    		return list;
		}
    }
}
