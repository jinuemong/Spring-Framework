package skuniv.cs.Hello;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JavaConfig {
	@Bean(destroyMethod = "close")
	public DataSource dataSource() {
		DataSource ds = new DataSource();
		ds.setDriverClassName("com.mysql.jdbc.Driver");
		ds.setUrl("jdbc:mysql://localhost:3307/spring5fs?characterEncoding=utf8&serverTimezone=UTC");
		ds.setUsername("spring5");
		ds.setPassword("spring5");
		ds.setInitialSize(8);
		ds.setMaxActive(10);
		ds.setTestWhileIdle(true);
		ds.setMinEvictableIdleTimeMillis(60000 * 3);
		ds.setTimeBetweenEvictionRunsMillis(10 * 1000);
		return ds;
	}

	@Bean  @Qualifier("member")
	public MemberDao memberDao() {
		return new MemberDao(dataSource());
	}
 
	@Bean @Qualifier("book")
	public BookDao bookDao() {
		return new BookDao(dataSource());
	}
	
	@Bean @Qualifier("order")
	public OrderDao orderDao() {
		return new OrderDao(dataSource());
	}
	
	@Bean @Qualifier("memberReg")
	public MemberRegisterService memberRegSvc() {
		return new MemberRegisterService(memberDao());
	}
	
	
	@Bean @Qualifier("memberUp")
	public MemberUpdateService memberUpSvc() {
		return new MemberUpdateService(memberDao());
	}
	
	@Bean @Qualifier("bookReg")
	public BookRegisterService bookRegSvc() {
		return new BookRegisterService(bookDao());
	}
	
	
	@Bean @Qualifier("bookUp")
	public BookUpdateService bookUpSvc() {
		return new BookUpdateService(bookDao());
	}
	
	@Bean @Qualifier("orderReg")
	public OrderRegisterService orderRegSvc() {
		return new OrderRegisterService(orderDao());
	}
	
	
	@Bean @Qualifier("orderUp")
	public OrderUpdateService orderUpSvc() {
		return new OrderUpdateService(orderDao());
	}
}