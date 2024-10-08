package kr.co.subking.cart;

import java.awt.Menu;

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
			Integer lastCartId=cartMapper.selectCartbyUserId(userId);
			if (lastCartId!=null) {
			
			} else if(lastCartId==null) {
				int insertCartId = cartMapper.insertCart(userId);
				sqlSession.commit();
				lastCartId = cartMapper.selectCartbyUserId(userId);
			}

			int resultCartList = cartMapper.insertCartList(lastCartId, menuId);
			sqlSession.commit();
			return resultCartList;
		}
	}
}
