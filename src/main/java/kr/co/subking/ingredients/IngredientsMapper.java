package kr.co.subking.ingredients;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface IngredientsMapper {

	@Select("select ig_no, ig_name, ig_category, ig_price, ig_kcal from ingredients")
	List<Ingredients> selectAll();

	
	@Select("SELECT I.ig_name, C.custom_count FROM custom AS C \n"
			+ "	JOIN ingredients AS I ON C.custom_ig_id = I.ig_no AND custom_menu_id = #{custom_menu_id}")
	List<ingredientsList> selectIgnameAndCount(@Param("custom_menu_id") int custom_menu_id);
}
