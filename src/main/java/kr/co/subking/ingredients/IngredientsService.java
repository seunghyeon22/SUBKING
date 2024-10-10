package kr.co.subking.ingredients;

import java.util.List;

public interface IngredientsService {
    List<Ingredients> selectAll();
    
    List<IngredientsList> selectIgnameAndCount(Integer menu_id);

	void addIngredient(Ingredients ingredient);
	
	List<CustomNameCount> selectMenubyIg(int menuId);
	
}
