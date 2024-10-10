package kr.co.subking.menu;

import java.util.List;

public interface MenuService {

	List<Integer> getMenuListId(Integer order_id);

	Menu getMenuByPk(Integer i);


}
