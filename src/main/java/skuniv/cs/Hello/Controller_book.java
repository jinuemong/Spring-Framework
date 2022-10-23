package skuniv.cs.Hello;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class Controller_book {
	@Autowired  @Qualifier("book")
	private BookDao bookDao;
	
	@Autowired  @Qualifier("order")
	private OrderDao orderDao;
	
	@Autowired  @Qualifier("bookReg")
	private BookRegisterService bookRegisterService;
	
	@Autowired  @Qualifier("bookUp")
	private BookUpdateService bookUpdateService;
	
	@GetMapping(value = "/b_list")
	public String list_b(Model model) {
		List<Book> bookList = bookDao.selectAll();
		model.addAttribute("books", bookList);
		return "b_list";
	}
	
	@GetMapping(value = "b_admin_O")
	public String b_admin_O(Model model) {
		return "b_admin_O";
	}
	
	@RequestMapping(value = "/b_admin_O2", method = RequestMethod.POST)
	public String b_admin_O2(Model model, @RequestParam(value = "admin_pwd", required = false) String admin_pwd) {
		if(admin_pwd.equals("2016301019")) {
			return "b_admin_O2";
		}else {
			return "b_admin_O";
		}
	}
	@GetMapping(value = "b_admin")
	public String b_admin(Model model) {
		return "b_admin";
	}
	
	@GetMapping(value = "b_search")
	public String b_search(Model model) {
		List<Book> bookList = bookDao.selectAll();
		model.addAttribute("books", bookList);
		return "b_search";
	}
	
	@RequestMapping(value = "/b_search_list", method = RequestMethod.POST)
	public String b_search_list(Model model, @RequestParam(value = "op", required = false) String op,
			@RequestParam(value = "search", required = false) String search) {
		List<Book> bookList = bookDao.search(op, search);
		model.addAttribute("books",bookList);
		return "b_search_list";
	}
	@GetMapping(value = "/b_insert")
	public String b_insert(Model model) {
		model.addAttribute("bookRegisterRequest",new BookRegisterRequest());	
		return "b_insert";
	}
	@GetMapping(value = "/m_insert_usedbook") //중고도서 등록
	public String m_insert_usedbook(Model model) {
		model.addAttribute("bookRegisterRequest",new BookRegisterRequest());	
		return "m_insert_usedbook";
	}
	@PostMapping("b_insert_result")
	public String b_insert_result(BookRegisterRequest bregReq) {
		try {
			bookRegisterService.book_regist(bregReq);
			return "b_insert_result";
		}catch(Exception e) {
			return "b_insert";
		}
	}
	
	@RequestMapping(value = "/b_update", method = RequestMethod.POST)
	public String b_update(Model model,HttpServletRequest request
			, @RequestParam(value = "check", required = false) String check) {
		HttpSession session = request.getSession();
		session.setAttribute("bnumber", check);
		Book book = bookDao.selectByBnumber(check); //세션 등록을 위함
		session.setAttribute("bname", book.getBname()); session.setAttribute("bwriter", book.getBwriter());
		session.setAttribute("bgenre", book.getBgenre()); session.setAttribute("borrow", book.getBorrow());
		model.addAttribute("bookRegisterRequest", new BookRegisterRequest());
		return "b_update";
	}
	@PostMapping("b_update_result")
	public String b_update_result(BookRegisterRequest bregReq ,HttpServletRequest request) {
		try {
			HttpSession session = request.getSession();
			Object Obnumber = session.getAttribute("bnumber"); String bnumber = (String)Obnumber;
			bregReq.setBnumber(bnumber);
			Book book = bookDao.selectByBnumber(bnumber); //빈 입력값이 있을 경우 초기화 용도
			if(bregReq.getBname().equals("")) {
				bregReq.setBname(book.getBname());
			}
			if(bregReq.getBwriter().equals("")) {
				bregReq.setBwriter(book.getBwriter());
			}
			if(bregReq.getBgenre().equals("")) {
				bregReq.setBgenre(book.getBgenre());
			}
			if(bregReq.getBorrow().equals("")) {
				bregReq.setBorrow(book.getBorrow());
			}
			bookUpdateService.book_update(bregReq); //업데이트후 세션 초기화
			return "b_update_result";
		}catch(Exception e) {
			return "b_update";
		}
	}

	@RequestMapping(value = "/b_delete", method = RequestMethod.POST)
	public String b_delete(Model model, @RequestParam(value = "check", required = false) String check) {
		bookDao.delete(check);
		orderDao.delete(check);
		return "b_delete";

	}
	
	
	@GetMapping(value = "m_search_book")
	public String m_search_book(Model model) {
		List<Book> bookList = bookDao.selectAll();
		model.addAttribute("books", bookList);
		return "m_search_book";
	}
	
	@RequestMapping(value = "/m_search_book_result", method = RequestMethod.POST)
	public String m_search_book_result(Model model, @RequestParam(value = "op", required = false) String op,
			@RequestParam(value = "search", required = false) String search) {
		List<Book> bookList = bookDao.search(op, search);
		model.addAttribute("books",bookList);
		return "m_search_book_result";
	}
}