package kr.co.subking.menu;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface MenuMapper {

	@Insert("insert into menu(menu_name) values (#{menu_name})")
	int insertMenu(@Param("menu_name") String menu_name);

	@Select("select max(menu_id) from menu;")
	int selectLastIdbyMenu();

	@Update("update menu set menu_price=(select sum(custom_price) from custom where custom_menu_id= #{menu_id}), menu_all_kcal = (select sum(custom_kcal) from custom where custom_menu_id= #{menu_id}) where menu_id = #{menu_id};")
	int upadetMenu(@Param("menu_id") int munu_id);

	@Select("SELECT menu_id, menu_name, menu_price, menu_all_kcal FROM menu \n" + "WHERE menu_id = #{menu_id}")
	Menu getMenuByPk(@Param("menu_id") int menu_id);

	@Select("SELECT menulist_menu_id FROM menulist WHERE menulist_order_id = #{order_id}")
	List<Integer> getMenuIdList(@Param("order_id") int order_id);

	@Select("SELECT menu_id, menu_name, menu_price, menu_all_kcal FROM menu")
	List<Menu> getAllMenu();

	@Insert("insert into menulist (menulist_order_id, menulist_menu_id) values(#{order_id}, #{menu_id})")
	int InsertMenuList(@Param("order_id") int order_id, @Param("menu_id") int menu_id);

//	menulist_order_id, menulist_menu_id
}
