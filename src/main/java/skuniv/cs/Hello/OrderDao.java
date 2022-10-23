package skuniv.cs.Hello;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class OrderDao {
	private JdbcTemplate jdbcTemplate;
	
	public OrderDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public List<Order> selectAll() {
		List<Order> results = jdbcTemplate.query("select * from ORDERB", (ResultSet rs, int rowNum) -> {
			Order order = new Order(rs.getString("ODBOOK"), rs.getString("ODEMAIL"),
					rs.getString("BOOKEMAIL"),rs.getString("ODBNUMBER")
					, rs.getTimestamp("REGDATE").toLocalDateTime());
			order.setOid(rs.getLong("OID"));
			return order;
		});
		return results;
	}
	public List<Order> search(String odemail) { //대여 목록 검색
		List<Order> results = jdbcTemplate.query("select * from ORDERB where ODEMAIL=?", (ResultSet rs, int rowNum) -> {
			Order order = new Order(rs.getString("ODBOOK"), rs.getString("ODEMAIL"),
					rs.getString("BOOKEMAIL"),rs.getString("ODBNUMBER")
					, rs.getTimestamp("REGDATE").toLocalDateTime());
			order.setOid(rs.getLong("OID"));
			return order;
		}, odemail);
		return results;
	}
	
	public Order selectByOdBnumber(String odbnumber) {
		List<Order> results = jdbcTemplate.query(
				"select * from ORDERB where ODBNUMBER=?",
				new RowMapper<Order>() {
					@Override
					public Order mapRow(ResultSet rs, int rowNum) throws SQLException{
						Order order = new Order(
								rs.getString("ODBOOK"),
								rs.getString("ODEMAIL"),
								rs.getString("BOOKEMAIL"),
								rs.getString("ODBNUMBER"),
								rs.getTimestamp("REGDATE").toLocalDateTime()
								);
						order.setOid(rs.getLong("OID"));
						return order;
					}
				}, odbnumber);
		return results.isEmpty()? null : results.get(0);
	}
	
	public void update(Order order) {
		jdbcTemplate.update("update ORDERB set ODBOOK = ?, ODEMAIL = ? , BOOKEMAIL =?  "
		+ " where ODBNUMBER = ? ",
		order.getOdbook() , order.getOdemail(), order.getBookemail(), order.getOdbnumber());
	}
	public void insert(Order order) {
		jdbcTemplate.update("insert into ORDERB (ODBOOK,ODEMAIL,BOOKEMAIL,ODBNUMBER,REGDATE) " 
	+ " values (?, ?, ?, ?, ?) ", order.getOdbook(), order.getOdemail()
	,order.getBookemail(),order.getOdbnumber(), order.getRegisterDateTime());
	}
	
	public void delete(String odbnumber) {
		jdbcTemplate.update("delete from ORDERB where odbnumber=?",odbnumber);
	}
	
}
