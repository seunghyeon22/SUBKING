package kr.co.subking.ingredients;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface IngredientsMapper {
	
	@Select("select ig_no, ig_name, ig_category, ig_price, ig_kcal from ingredients")
	List<Ingredients> selectIg();
	
}
