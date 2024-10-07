package kr.co.subking.ingredients;

import java.util.List;

public interface IngredientsService {
	List<Ingredients> selectAll();

	List<ingredientsList> selectIgnameAndCount(Integer menu_id);


}
