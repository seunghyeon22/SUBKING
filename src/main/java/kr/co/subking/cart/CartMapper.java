package kr.co.subking.cart;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface CartMapper {
	@Select("select * from cart where cart_user_id =#{user_id}")
	List<Cart> userIdCheckbyCart(@Param("user_id") String userId); 
	
	@Insert("insert into cart (cart_user_id) value(#{cart_user_id})")
	int insertCart(@Param("cart_user_id") String userId);
	
	@Select("select cart_id from cart where cart_user_id = #{cart_user_id} ")
	Integer selectCartbyUserId(@Param("cart_user_id") String userId);

	@Select("select  count(last_insert_id()) from cart;")
	int selectlastIdbyCart();

	@Insert("insert into cartlist (cartlist_cart_id, cartlist_menu_id) value(#{cartlist_cart_id},#{cartlist_menu_id})")
	int insertCartList(@Param("cartlist_cart_id") int cartlist_cart_id, @Param("cartlist_menu_id") int menu_id);
}
