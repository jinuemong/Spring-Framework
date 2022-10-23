package skuniv.cs.Hello;

import java.time.LocalDateTime;

public class BookUpdateService {
	private BookDao bookDao;
	
	public BookUpdateService(BookDao bookDao) {
		this.bookDao = bookDao;
	}
	
	public Long book_update(BookRegisterRequest breq) 
			throws Exception{
		Book book = bookDao.selectByBnumber(breq.getBnumber());
		if(book==null) {
			throw new Exception("DuplicateMemberException");
		}
		Book newBook = new Book(breq.getBname(),breq.getBwriter(),
				breq.getBgenre(), breq.getBorrow(), LocalDateTime.now(),
				breq.getBnumber());
		bookDao.update(newBook);
		return newBook.getBid();
	}
}
