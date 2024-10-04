package kr.co.subking.menu;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Menu {
	private int menu_id;
	private String menu_name;
	private int menu_price;
	private int menu_all_kcal;
	private String menu_des;
	private Timestamp menu_date;
	private String menu_img;
}
