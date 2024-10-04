package kr.co.subking.ingredients;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Ingredients {
	private int ig_no;
	private String ig_name;
	private String ig_category;
	private int ig_price;
	private int ig_kcal;
	private String ig_image;
	private Timestamp ig_date;
}
