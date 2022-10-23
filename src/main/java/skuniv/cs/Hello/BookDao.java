package skuniv.cs.Hello;

import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;
import org.springframework.jdbc.core.*;

public class BookDao {
	private JdbcTemplate jdbcTemplate;

	public BookDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public List<Book> selectAll() {
		List<Book> results = jdbcTemplate.query("select * from BOOK", (ResultSet rs, int rowNum) -> {
			Book book = new Book(rs.getString("BNAME"), rs.getString("BWRITER"), rs.getString("BGENRE"),
					rs.getString("BORROW"),rs.getTimestamp("REGDATE").toLocalDateTime(),rs.getString("BNUMBER"));
			book.setBid(rs.getLong("BID"));
			return book;
		});
		return results;
	}
	
	public List<Book> search(String op , String search) { 
		String search2 = "%" + search + "%";
		List<Book> results = jdbcTemplate.query("select * from BOOK where "+op+" LIKE ?", (ResultSet rs, int rowNum) -> {
			Book book = new Book(rs.getString("BNAME"), rs.getString("BWRITER"), rs.getString("BGENRE"),
					rs.getString("BORROW"),rs.getTimestamp("REGDATE").toLocalDateTime(),rs.getString("BNUMBER"));
			book.setBid(rs.getLong("BID"));
			
			return book;
		}, search2);
		return results;
	}
	
	public Book selectByBnumber(String bnumber) {
		List<Book> results = jdbcTemplate.query(
				"select * from BOOK where BNUMBER=?",
				new RowMapper<Book>() {
					@Override
					public Book mapRow(ResultSet rs, int rowNum) throws SQLException{
						Book book = new Book(
								rs.getString("BNAME"),
								rs.getString("BWRITER"),
								rs.getString("BGENRE"),
								rs.getString("BORROW"),
								rs.getTimestamp("REGDATE").toLocalDateTime(),
								rs.getString("BNUMBER"));
						book.setBid(rs.getLong("BID"));
						return book;
					}
				}, bnumber);
		return results.isEmpty()? null : results.get(0);
	}
	

	public void update(Book book) {
		jdbcTemplate.update("update BOOK set bname = ?, bwriter = ? , bgenre =? , "
		+ " borrow=? where bnumber = ? ",
		book.getBname() , book.getBwriter(), book.getBgenre(), book.getBorrow() , book.getBnumber() );
	}
	public void insert(Book book) {
		jdbcTemplate.update("insert into BOOK (BNAME , BWRITER, BGENRE, BORROW, REGDATE, BNUMBER ) " 
	+ " values (?, ?, ?, ?, ?, ?) ", book.getBname(), book.getBwriter(),book.getBgenre(),
	book.getBorrow(),book.getRegisterDateTime(), book.getBnumber());
	}
	
	public void delete(String bnumber) {
		jdbcTemplate.update("delete from BOOK where bnumber=?",bnumber);

	}
	
}