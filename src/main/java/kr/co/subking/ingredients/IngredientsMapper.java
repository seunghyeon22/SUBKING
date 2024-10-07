package kr.co.subking.ingredients;

import java.util.List;

import org.apache.ibatis.annotations.Select;

public interface IngredientsMapper {

	@Select("select ig_no, ig_name, ig_category, ig_price, ig_kcal from ingredients")
	List<Ingredients> selectAll();

	@Select("select ig_no, ig_name, ig_category, ig_price, ig_kcal from ingredients where ig_category='빵'")
	List<Ingredients> selectIgBread();

	@Select("select ig_no, ig_name, ig_category, ig_price, ig_kcal from ingredients where ig_category='야채'")
	List<Ingredients> selectIgVegtable();

	@Select("select ig_no, ig_name, ig_category, ig_price, ig_kcal from ingredients where ig_category='패티'")
	List<Ingredients> selectIgPatty();

	@Select("select ig_no, ig_name, ig_category, ig_price, ig_kcal from ingredients where ig_category='치즈'")
	List<Ingredients> selectIgCheese();

	@Select("select ig_no, ig_name, ig_category, ig_price, ig_kcal from ingredients where ig_category='소스'")
	List<Ingredients> selectIgSauce();

	@Select("select ig_no, ig_name, ig_category, ig_price, ig_kcal from ingredients where ig_category='사이드'")
	List<Ingredients> selectIgSide();

	@Select("select ig_no, ig_name, ig_category, ig_price, ig_kcal from ingredients where ig_category='음료'")
	List<Ingredients> selectIgDrink();
}
