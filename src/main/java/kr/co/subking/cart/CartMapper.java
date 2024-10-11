package kr.co.subking.cart;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
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

	@Insert("insert into cartlist (cartlist_cart_id, cartlist_menu_id) value(#{cartlist_cart_id},#{cartlist_menu_id})")
	int insertCartList(@Param("cartlist_cart_id") int cartlist_cart_id, @Param("cartlist_menu_id") int menu_id);

	@Select("SELECT a.cart_user_id, a.cart_id, c.menu_id, c.menu_name, c.menu_price, c.menu_all_kcal\r\n"
			+ "FROM cart AS a\r\n" + "LEFT OUTER JOIN cartlist AS b ON a.cart_id = b.cartlist_cart_id\r\n"
			+ "LEFT OUTER JOIN menu AS c ON b.cartlist_menu_id = c.menu_id\r\n"
			+ "WHERE a.cart_user_id = '${user_id}';")
	List<Cartlist1> selectUserIdbyAllMenu(@Param("user_id") String userid);

//	@Delete("delete from cart where cart_user_id = ${user_id}")
//	int DeleteCart(@Param("user_id") String user_id);
	
	
	@Delete("delete from cartlist where cartlist_menu_id = ${menu_id}")
	int DeleteCartbyUserId(@Param("menu_id") int menu_id);

	@Delete("DELETE FROM cart WHERE cart_user_id = #{user_id};")
	int deleteCartIdbyUserId(@Param("user_id") String user_id);

}
