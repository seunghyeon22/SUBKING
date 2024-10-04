package kr.co.subking.menu;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface MenuMapper {

	@Insert("insert into menu(menu_name) values (#{menu_name})")
	int insertMenu(@Param("menu_name") String menu_name);
	
	@Select("SELECT menu_id, menu_name, menu_price, menu_all_kcal FROM menu \n"
			+ "WHERE menu_id = #{menu_id}")
	Menu getMenuByPk(@Param("menu_id") int menu_id);
	
	@Select("SELECT menu_id FROM menulist WHERE order_menu_id = #{order_id}")
	List<Integer> getMenuIdList(@Param("order_id") int order_id);
	
	@Select("SELECT menu_id, menu_name, menu_price, menu_all_kcal FROM menu")
	List<Menu> getAllMenu();
	
}
