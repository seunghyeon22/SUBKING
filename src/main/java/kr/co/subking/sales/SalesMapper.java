package kr.co.subking.sales;

import java.util.List;

import org.apache.ibatis.annotations.Select;

public interface SalesMapper {

	// DB에 orders Table 에 order_state 가 '배달 완료' 일때만 매출 집계가 됨
    @Select("SELECT DATE(order_date) as sale_date, SUM(order_price) as total_sales " +
            "FROM orders " +
            "WHERE order_state = '배달 완료' " +
            "GROUP BY DATE(order_date) " +
            "ORDER BY sale_date")
    List<Sales> getDailySales();
}
