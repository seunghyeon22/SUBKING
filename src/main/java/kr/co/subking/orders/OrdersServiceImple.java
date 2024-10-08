package kr.co.subking.orders;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import subking.config.AppContextListener;

public class OrdersServiceImple implements OrdersService {
	private static final OrdersServiceImple instance = new OrdersServiceImple();
	
	private OrdersServiceImple() {}
	
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

}
