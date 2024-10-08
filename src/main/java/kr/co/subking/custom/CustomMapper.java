package kr.co.subking.custom;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

public interface CustomMapper {
	
	@Insert("insert into custom (custom_menu_id, custom_ig_id, custom_count, custom_price, custom_kcal) values(#{custom.custom_menu_id},#{custom.custom_ig_id},#{custom.custom_count},(select ig_price from ingredients  where ig_no=#{custom.custom_ig_id})*#{custom.custom_count}, (select ig_kcal from ingredients  where ig_no=#{custom.custom_ig_id})*#{custom.custom_count});")
	int insertCustom(@Param("custom") Custom custom);
}