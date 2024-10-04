package kr.co.subking.menu;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

public interface MenuMapper {

	@Insert("insert into menu(menu_name) values (#{menu_name})")
	int insertMenu(@Param("menu_name") String menu_name);
}
