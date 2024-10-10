package kr.co.subking.orders;

import java.util.List;

public interface OrdersService {

	List<Orders> selectAll();

	List<Orders> selectByUserId(String user_id);


}
