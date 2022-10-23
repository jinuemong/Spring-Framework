package skuniv.cs.Hello;

import java.time.LocalDateTime;

public class Book {
	private Long bid;
	public String bname; 
	public String bwriter; 
	public String bgenre;
	public String borrow;
	private LocalDateTime registerDateTime;
	public String bnumber;
	public Book(String bname,String bwriter, String bgenre
			,String borrow , LocalDateTime regDateTime,
			String bnumber) {
		this.bname = bname;
		this.bwriter = bwriter;
		this.bgenre = bgenre;
		this.borrow = borrow;
		this.registerDateTime = regDateTime;
		this.bnumber = bnumber;
	}
	public Long getBid() {
		return bid;
	}
	public void setBid(Long bid) {
		this.bid = bid;
	}
	public String getBname() {
		return bname;
	}
	public void setBname(String bname) {
		this.bname = bname;
	}
	public String getBwriter() {
		return bwriter;
	}
	public void setBwriter(String bwriter) {
		this.bwriter = bwriter;
	}
	public String getBgenre() {
		return bgenre;
	}
	public void setBgenre(String bgenre) {
		this.bgenre = bgenre;
	}
	public String getBorrow() {
		return borrow;
	}
	public void setBorrow(String borrow) {
		this.borrow = borrow;
	}
	public LocalDateTime getRegisterDateTime() {
		return registerDateTime;
	}
	public void setRegisterDateTime(LocalDateTime registerDateTime) {
		this.registerDateTime = registerDateTime;
	}
	public String getBnumber() {
		return bnumber;
	}
	public void setBnumber(String bnumber) {
		this.bnumber = bnumber;
	}

}
