package kr.co.subking.cart;

import java.awt.Menu;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.co.subking.custom.CustomService;
import kr.co.subking.custom.CustomServiceImpl;
import kr.co.subking.menu.MenuMapper;
import subking.config.AppContextListener;

public class CartServiceImpl implements CartService {
	private static final CartServiceImpl instance = new CartServiceImpl();

	private CartServiceImpl() {
	}

	public static CartService getInstance() {
		return instance;
	}

	@Override
	public int insertCart(String userId, int menuId) {
		try (SqlSession sqlSession = AppContextListener.getSqlSession()) {
			CartMapper cartMapper = sqlSession.getMapper(CartMapper.class);
			MenuMapper menuMapper = sqlSession.getMapper(MenuMapper.class);
//			Integer lastCartId = cartMapper.selectCartbyUserId(userId);
//			if (lastCartId != null) {
//
//			} else if (lastCartId == null) {
//				int insertCartId = cartMapper.insertCart(userId);
//				sqlSession.commit();
//			}
			Integer lastCartId = cartMapper.selectCartbyUserId(userId);
			int lastId = (int)lastCartId;
			int resultCartList = cartMapper.insertCartList(lastId, menuId);
			sqlSession.commit();
			return resultCartList;
		}
	}

	@Override
	public List<Cartlist1> selectUserIdbyAllMenu(String userid) {
		try (SqlSession sqlSession = AppContextListener.getSqlSession()) {
			CartMapper cartMapper = sqlSession.getMapper(CartMapper.class);
			List<Cartlist1> list = cartMapper.selectUserIdbyAllMenu(userid);
			return list;
		}
	}

	@Override
	public int DeleteCartbyUserId(String user_id, List<Integer> list) {
		try (SqlSession sqlSession = AppContextListener.getSqlSession()) {
			CartMapper cartMapper = sqlSession.getMapper(CartMapper.class);
			int result = 0;
			for (int i : list) {
				result += cartMapper.DeleteCartbyUserId(i);
			}
			sqlSession.commit();
			return result;
		}

	}

	@Override
	public void insertCartByUserId(String user_id) {
		try (SqlSession sqlSession = AppContextListener.getSqlSession()) {
			CartMapper cartMapper = sqlSession.getMapper(CartMapper.class);
			Integer lastCartId = cartMapper.selectCartbyUserId(user_id);
			if (lastCartId != null) {

			} else if (lastCartId == null) {
				int insertCartId = cartMapper.insertCart(user_id);
				sqlSession.commit();
				lastCartId = cartMapper.selectCartbyUserId(user_id);
			}
			sqlSession.commit();
		}
	}

	@Override
	public void deleteCartIdbyUserId(String user_id) {
		int result = 0;
		try (SqlSession sqlSession = AppContextListener.getSqlSession()) {
			CartMapper cartMapper = sqlSession.getMapper(CartMapper.class);

			result = cartMapper.deleteCartIdbyUserId(user_id);
			sqlSession.commit();
		}
	}

	@Override
	public int insertCartAndMenuId(String user_id, int cartlist_menu_id) {
		int result = 0;
		try (SqlSession sqlSession = AppContextListener.getSqlSession()) {
			CartMapper cartMapper = sqlSession.getMapper(CartMapper.class);
			
			int cartlist_cart_id = cartMapper.selectCartbyUserId(user_id);
			
			result = cartMapper.insertCartList(cartlist_cart_id, cartlist_menu_id);
			sqlSession.commit();
		}
		return result;
	}
}
