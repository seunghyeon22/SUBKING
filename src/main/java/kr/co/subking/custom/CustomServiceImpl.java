package kr.co.subking.custom;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
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
		System.out.println(list);
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
//				System.out.println(i + " : " + Collections.frequency(list, i));
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

	@Override
	public List<List<Ingredients>> customKcal(int kcal) {

		Random rand = new Random();
		try (SqlSession sqlSession = AppContextListener.getSqlSession()) {
			IngredientsMapper igMapper = sqlSession.getMapper(IngredientsMapper.class);
			List<Ingredients> list = igMapper.selectAll();

			int size = list.size();

			List<Ingredients> breads = new ArrayList<Ingredients>();
			List<Ingredients> vegetables = new ArrayList<Ingredients>();
			List<Ingredients> pattys = new ArrayList<Ingredients>();
			List<Ingredients> sauces = new ArrayList<Ingredients>();
			List<Ingredients> cheeses = new ArrayList<Ingredients>();

			for (Ingredients i : list) {
				if (i.getIg_category().equals("빵")) {
					breads.add(i);
				}
				if (i.getIg_category().equals("야채")) {
					vegetables.add(i);
				}
				if (i.getIg_category().equals("패티")) {
					pattys.add(i);
				}
				if (i.getIg_category().equals("소스")) {
					sauces.add(i);
				}
				if (i.getIg_category().equals("치즈")) {
					cheeses.add(i);
				}
			}

			List<List<Ingredients>> lists = new ArrayList<List<Ingredients>>();
			for (int i = 0; i < 10; i++) {
				List<Ingredients> ig_list = new ArrayList<Ingredients>();
				int cal = kcal;
				int a = rand.nextInt(breads.size());
				cal -= breads.get(a).getIg_kcal();
				ig_list.add(breads.get(a));

				int b = rand.nextInt(vegetables.size());
				cal -= vegetables.get(b).getIg_kcal();
				ig_list.add(vegetables.get(b));

				int c = rand.nextInt(pattys.size());
				cal -= pattys.get(c).getIg_kcal();
				ig_list.add(pattys.get(c));

				int d = rand.nextInt(sauces.size());
				cal -= sauces.get(d).getIg_kcal();
				ig_list.add(sauces.get(d));

				int e = rand.nextInt(cheeses.size());
				cal -= cheeses.get(e).getIg_kcal();
				ig_list.add(cheeses.get(e));
				
				
				while (cal <= 50 || cal >= -50) {
					b = rand.nextInt(vegetables.size());
					cal -= vegetables.get(b).getIg_kcal();
					ig_list.add(vegetables.get(b));
					if (cal > 450) {
						c = rand.nextInt(pattys.size());
						cal -= pattys.get(c).getIg_kcal();
						ig_list.add(pattys.get(c));
					}else if (cal > 200) {
						e = rand.nextInt(cheeses.size());
						cal -= cheeses.get(e).getIg_kcal();
						ig_list.add(cheeses.get(e));
					}else if (cal > 100) {
						d = rand.nextInt(sauces.size());
						cal -= sauces.get(d).getIg_kcal();
						ig_list.add(sauces.get(d));
					}else if (cal > 50) {
						b = rand.nextInt(vegetables.size());
						cal -= vegetables.get(b).getIg_kcal();
						ig_list.add(vegetables.get(b));
					}else if(cal<=50) {
						break;
					}
				}
				System.out.println(cal);
				System.out.println(ig_list.toString());
				lists.add(ig_list);
			}

			return lists;
		}

	}

}
