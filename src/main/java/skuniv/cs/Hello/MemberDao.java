package skuniv.cs.Hello;

import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.springframework.jdbc.core.*;

public class MemberDao {
	private JdbcTemplate jdbcTemplate;

	public MemberDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	

	public Member selectByEmail(String email) {
		List<Member> results = jdbcTemplate.query(
				"select * from MEMBER where EMAIL=?",
				new RowMapper<Member>() {
					@Override
					public Member mapRow(ResultSet rs, int rowNum) throws SQLException{
						Member member = new Member(
								rs.getString("EMAIL"),
								rs.getString("PASSWORD"),
								rs.getString("NAME"),
								rs.getTimestamp("REGDATE").toLocalDateTime());
						member.setId(rs.getLong("ID"));
						return member;
					}
				}, email);
		return results.isEmpty()? null : results.get(0);
	}
	

	public void update(Member member) {
		jdbcTemplate.update("update MEMBER set name = ?, password = ? "
		+ " where email = ? ",
		member.getName(), member.getPassword(), member.getEmail() );
	}
	public void insert(Member member) {
		jdbcTemplate.update("insert into MEMBER (EMAIL, PASSWORD, NAME, REGDATE) " + " values (?, ?, ?, ?) ",
				member.getEmail(), member.getPassword(), member.getName(), member.getRegisterDateTime());
	}
	public void delete(String email) {
		jdbcTemplate.update("delete from MEMBER where email=?",email);

	}
	
	
	
	public List<Member> selectAll() {
		List<Member> results = jdbcTemplate.query("select * from MEMBER", (ResultSet rs, int rowNum) -> {
			Member member = new Member(rs.getString("EMAIL"), rs.getString("PASSWORD"), rs.getString("NAME"),
					rs.getTimestamp("REGDATE").toLocalDateTime());
			member.setId(rs.getLong("ID"));
			return member;
		});
		return results;
	}
}
	
	