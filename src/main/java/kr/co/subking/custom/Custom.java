package kr.co.subking.custom;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Custom {
	private int custom_id;
	private int custom_menu_id;
	private int custom_ig_id;
	private int custom_count;
	private int custom_price;
}
