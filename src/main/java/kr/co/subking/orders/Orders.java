package kr.co.subking.orders;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Orders {
	private int order_id;
	private String order_user_id;
//	private int order_menu_id;
	private String order_state;
	private int order_price;
	private Date order_date;
//	private Date order_arrive_date;
	
}
