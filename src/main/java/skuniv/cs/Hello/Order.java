package skuniv.cs.Hello;

import java.time.LocalDateTime;

public class Order {
	private Long oid;
	private String odbook;
	private String odemail;
	private String bookemail;
	private String odbnumber;
	private LocalDateTime registerDateTime;
	public Order(String odbook,String odemail, String bookemail
		,String odbnumber	,  LocalDateTime regDateTime ) {
		this.odbook = odbook;
		this.odemail = odemail;
		this.bookemail = bookemail;
		this.odbnumber = odbnumber;
		this.registerDateTime = regDateTime;
	}
	public LocalDateTime getRegisterDateTime() {
		return registerDateTime;
	}
	public void setRegisterDateTime(LocalDateTime registerDateTime) {
		this.registerDateTime = registerDateTime;
	}
	public Long getOid() {
		return oid;
	}
	public void setOid(Long oid) {
		this.oid = oid;
	}
	public String getOdbook() {
		return odbook;
	}
	public void setOdbook(String odbook) {
		this.odbook = odbook;
	}
	public String getOdemail() {
		return odemail;
	}
	public void setOdemail(String odemail) {
		this.odemail = odemail;
	}
	public String getBookemail() {
		return bookemail;
	}
	public void setBookemail(String bookemail) {
		this.bookemail = bookemail;
	}
	public String getOdbnumber() {
		return odbnumber;
	}
	public void setOdbnumber(String odbnumber) {
		this.odbnumber = odbnumber;
	}
}
