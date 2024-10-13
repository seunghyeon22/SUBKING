package kr.co.subking.ingredients;

import java.util.List;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface IngredientsMapper {

    @Select("SELECT ig_no, ig_name, ig_category, ig_price, ig_kcal, ig_image FROM ingredients")
    List<Ingredients> selectAll();
    
    @Select("SELECT I.ig_name, I.ig_category, C.custom_count FROM custom AS C " +
            "JOIN ingredients AS I ON C.custom_ig_id = I.ig_no AND custom_menu_id = #{custom_menu_id}")
    List<IngredientsList> selectIgnameAndCount(@Param("custom_menu_id") int custom_menu_id);

    @Insert("INSERT INTO ingredients (ig_name, ig_category, ig_price, ig_kcal, ig_image) " +
            "VALUES (#{ig_name}, #{ig_category}, #{ig_price}, #{ig_kcal}, #{ig_image})")
    void insertIg(Ingredients ingredient);
    
    
    
    @Select("select c.ig_name, b.custom_count from menu as a \r\n"
    		+ "left outer join custom as b on a.menu_id = b.custom_menu_id \r\n"
    		+ "left outer join ingredients as c on b.custom_ig_id = c.ig_no \r\n"
    		+ "where a.menu_id = #{menu_id}")
    List<CustomNameCount> selectMenubyIg(@Param("menu_id")int menuId);
}
