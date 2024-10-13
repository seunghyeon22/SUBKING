package kr.co.subking.ingredients;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class IngredientsList {
	private String ig_name;
	private int custom_count;
	private String ig_category;
	
}
