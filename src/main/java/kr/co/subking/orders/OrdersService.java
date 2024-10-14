package kr.co.subking.orders;

import java.util.List;

public interface OrdersService {

	List<Orders> selectAll();

	List<Orders> selectByUserId(String user_id);

	int InsertMenulist(String user_id, List<Integer> menu_ids, int price, String address);

}
