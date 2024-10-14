package kr.co.subking.orders;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface OrdersMapper {

	@Select("SELECT order_id, order_user_id, \r\n"
			+ "	order_state, order_price, order_date, order_arrive_date\r\n" + "    FROM orders;")
	List<Orders> selectAll();

	@Select("SELECT order_id, order_user_id, \r\n"
			+ "	order_state, order_price, order_date, order_arrive_date\r\n"
			+ "    FROM orders WHERE order_user_id = #{user_id};")
	List<Orders> selectByUserId(@Param("user_id") String user_id);

	@Insert("insert into orders (order_user_id, order_state, order_price, order_address) values (#{user_id},#{order_state},#{order_price},#{order_address} )")
	int InsertOrder(@Param("user_id") String user_id, @Param("order_state") String state,
			@Param("order_price") int order_price,@Param("order_address") String order_address);
	
	@Select("select max(order_id) from orders where order_user_id = #{user_id};")
	int SelectLastIdByOreder(@Param("user_id") String user_id);
	
	
	
}
