package kr.co.subking.orders;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.co.subking.menu.MenuMapper;
import subking.config.AppContextListener;

public class OrdersServiceImple implements OrdersService {
	private static final OrdersServiceImple instance = new OrdersServiceImple();

	private OrdersServiceImple() {
	}

	public static OrdersService getInstance() {
		return instance;
	}

	@Override
	public List<Orders> selectAll() {
		try (SqlSession sqlSession = AppContextListener.getSqlSession()) {
			OrdersMapper ordersMapper = sqlSession.getMapper(OrdersMapper.class);
			List<Orders> list = ordersMapper.selectAll();

			return list;
		}
	}

	@Override
	public List<Orders> selectByUserId(String user_id) {
		try (SqlSession sqlSession = AppContextListener.getSqlSession()) {
			OrdersMapper ordersMapper = sqlSession.getMapper(OrdersMapper.class);

			List<Orders> list = ordersMapper.selectByUserId(user_id);

			return list;
		}
	}

	@Override
	public int InsertMenulist(String user_id, List<Integer> menu_ids, int price, String address) {
		try (SqlSession sqlSession = AppContextListener.getSqlSession()) {
			OrdersMapper orderMapper = sqlSession.getMapper(OrdersMapper.class);
			MenuMapper menuMapper = sqlSession.getMapper(MenuMapper.class);
			int resultOrder = orderMapper.InsertOrder(user_id, "배달 완료", price, address);
			sqlSession.commit();
			int lastOrder_id = orderMapper.SelectLastIdByOreder(user_id);

			for (int i : menu_ids) {
				System.out.println("넘어가는 메뉴 id는: " + i);
				int result = menuMapper.InsertMenuList(lastOrder_id, i);
			}
			sqlSession.commit();
			return resultOrder;
		}
	}
}
