package kr.co.subking.custom;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.ibatis.session.SqlSession;

import kr.co.subking.ingredients.Ingredients;
import kr.co.subking.ingredients.IngredientsMapper;
import kr.co.subking.ingredients.IngredientsService;
import kr.co.subking.ingredients.IngredientsServiceImpl;
import kr.co.subking.menu.MenuMapper;
import subking.config.AppContextListener;

public class CustomServiceImpl implements CustomService {

	private static final CustomServiceImpl instance = new CustomServiceImpl();

	private CustomServiceImpl() {
	}

	public static CustomService getInstance() {
		return instance;
	}

	@Override
	public int insertcart(List<Integer> list) {
		try (SqlSession sqlSession = AppContextListener.getSqlSession()) {
			MenuMapper menuMapper = sqlSession.getMapper(MenuMapper.class);
			CustomMapper customMapper = sqlSession.getMapper(CustomMapper.class);
			int menuResult = menuMapper.insertMenu("커스텀 버거");
			sqlSession.commit();
			int lastId = menuMapper.selectLastIdbyMenu();
			List<Custom> custom = new ArrayList<Custom>();
			int result = 0;
			Set<Integer> set = new HashSet<Integer>(list);
			for (Integer i : set) {
				System.out.println(i + " : " + Collections.frequency(list, i));
				custom.add(new Custom(lastId, i, Collections.frequency(list, i)));
			}
			System.out.println(custom.toString());

			for (int i = 0; i < custom.size(); i++) {
				result = customMapper.insertCustom(custom.get(i));
				sqlSession.commit();
			}

			int updateResult = menuMapper.upadetMenu(lastId);
			sqlSession.commit();
			return lastId;
		}
	}

}
