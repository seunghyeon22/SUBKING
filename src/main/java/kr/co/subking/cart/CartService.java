package kr.co.subking.cart;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface CartService {

	int insertCart(String userId, int menuId);
	
	List<Cartlist1> selectUserIdbyAllMenu(String userid);
	
	int DeleteCartbyUserId(String user_id, List<Integer> list);
}
