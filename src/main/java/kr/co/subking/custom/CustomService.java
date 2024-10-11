package kr.co.subking.custom;

import java.util.List;

import kr.co.subking.ingredients.Ingredients;

public interface CustomService {
	
	int insertcart(List<Integer> list);
	
	List<List<Ingredients>> customKcal(int kcal);

}
