package kr.co.subking.cart;

import java.util.List;

import kr.co.subking.ingredients.Ingredients;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Cartlist1 {
	private String cart_user_id;
	private int cart_id;
	private int menu_id;
	private String menu_name;
	private int menu_price;
	private int menu_all_kcal;

}
