package kr.co.subking.orders;

import java.util.List;

import org.apache.ibatis.annotations.Select;

public interface OrdersMapper {

	@Select("SELECT order_id, order_user_id, order_menu_id, \r\n"
			+ "	order_state, order_price, order_date, order_arrive_date\r\n"
			+ "    FROM orders;")
	List<Orders> selectAll();

}
