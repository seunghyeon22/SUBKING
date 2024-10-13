package subking.config;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

import kr.co.subking.cart.CartMapper;
import kr.co.subking.custom.CustomMapper;
import kr.co.subking.ingredients.IngredientsMapper;
import kr.co.subking.menu.MenuMapper;
import kr.co.subking.orders.OrdersMapper;
import kr.co.subking.sales.SalesMapper;
import kr.co.subking.user.UserMapper;


@WebListener
public class AppContextListener implements ServletContextListener {
	private static DataSource dataSource;
	private static SqlSessionFactory sessionFactory;

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		initDataSource();
		initSqlSessionFactory();
	}

	private void initDataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://192.168.0.188:3306/subking");
		dataSource.setUsername("dc");
		dataSource.setPassword("1234");

		AppContextListener.dataSource = dataSource;
	}

	private void initSqlSessionFactory() {
		TransactionFactory transactionFactory = new JdbcTransactionFactory();
		Environment environment = new Environment("dev", transactionFactory, dataSource);

		Configuration configuration = new Configuration(environment);

		// Mapper 추가하는 곳
		configuration.addMapper(IngredientsMapper.class);
		configuration.addMapper(OrdersMapper.class);
		configuration.addMapper(MenuMapper.class);
		configuration.addMapper(CustomMapper.class);
		configuration.addMapper(CartMapper.class);
		configuration.addMapper(UserMapper.class);
		configuration.addMapper(SalesMapper.class);
		sessionFactory = new SqlSessionFactoryBuilder().build(configuration);
	}

	public static SqlSession getSqlSession() {
		return sessionFactory.openSession();
	}

	// 이건 필요 없는거 같기도 하고?
//	public static Connection getConnection() throws SQLException {
//		return dataSource.getConnection();
//	}
}
