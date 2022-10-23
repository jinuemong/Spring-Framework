package skuniv.cs.Hello;

import java.time.LocalDateTime;

public class BookRegisterService {
	private BookDao bookDao;

	public BookRegisterService(BookDao bookDao) {
		this.bookDao = bookDao;
	}

	public Long book_regist(BookRegisterRequest breq) throws Exception {
		while (true) {
			int rand = (int) Math.floor(Math.random() * 99999999);
			String randnum = Integer.toString(rand);
			Book book = bookDao.selectByBnumber(randnum);
			if (book == null) {
				Book newBook = new Book(breq.getBname(), breq.getBwriter(), breq.getBgenre(), "대여가능",
						LocalDateTime.now(), randnum);
				bookDao.insert(newBook);
				return newBook.getBid(); 
			}
		}
	}
}
