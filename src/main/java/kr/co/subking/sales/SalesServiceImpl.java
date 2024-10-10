package kr.co.subking.sales;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import subking.config.AppContextListener;

public class SalesServiceImpl implements SalesService {
    private static final SalesServiceImpl instance = new SalesServiceImpl();
    
    private SalesServiceImpl() {}
    
    public static SalesService getInstance() {
        return instance;
    }

    @Override
    public List<Sales> getDailySales() {
        try (SqlSession sqlSession = AppContextListener.getSqlSession()) {
            SalesMapper salesMapper = sqlSession.getMapper(SalesMapper.class);
            return salesMapper.getDailySales();
        }
    }
}
