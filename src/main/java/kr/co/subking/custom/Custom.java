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

	public Custom(int custom_menu_id, int custom_ig_id, int custom_count) {
		super();
		this.custom_menu_id = custom_menu_id;
		this.custom_ig_id = custom_ig_id;
		this.custom_count = custom_count;
	}

}
