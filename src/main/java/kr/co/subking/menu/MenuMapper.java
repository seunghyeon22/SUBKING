package kr.co.subking.menu;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface MenuMapper {

	@Insert("insert into menu(menu_name) values (#{menu_name})")
	int insertMenu(@Param("menu_name") String menu_name);

	@Select("select last_insert_id();")
	int selectLastIdbyMenu();
	
	@Update("update menu set menu_price=(select sum(custom_price) from custom where custom_menu_id= 4) where menu_id = 4;")
	int upadetMenu();
}
