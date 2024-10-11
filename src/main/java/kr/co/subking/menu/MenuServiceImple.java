package kr.co.subking.menu;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import subking.config.AppContextListener;

public class MenuServiceImple implements MenuService {
	private static final MenuServiceImple instance = new MenuServiceImple();

	private MenuServiceImple() {
	}

	public static MenuServiceImple getInstance() {
		return instance;
	}

	@Override
	public List<Integer> getMenuListId(Integer order_id) {
		try (SqlSession sqlSession = AppContextListener.getSqlSession()) {
			MenuMapper menuMapper = sqlSession.getMapper(MenuMapper.class);

			List<Integer> list = menuMapper.getMenuIdList(order_id);

			return list;
		}
	}

	@Override
	public Menu getMenuByPk(Integer i) {
		try (SqlSession sqlSession = AppContextListener.getSqlSession()) {
			MenuMapper menuMapper = sqlSession.getMapper(MenuMapper.class);

			Menu menu = menuMapper.getMenuByPk(i);

			return menu;
		}
	}

}
