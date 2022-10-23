package skuniv.cs.Hello;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class controller_order {	
	@Autowired  @Qualifier("order")
	private OrderDao orderDao;
	
	@Autowired  @Qualifier("orderReg")
	private OrderRegisterService orderRegisterService;
	
	@Autowired  @Qualifier("orderUp")
	private OrderUpdateService orderUpdateService;
	
	@Autowired  @Qualifier("book")
	private BookDao bookDao;
	
	@Autowired  @Qualifier("bookUp")
	private BookUpdateService bookUpdateService;
	
	@Autowired  @Qualifier("member")
	private MemberDao memberDao;
	
	
	
	@RequestMapping(value = "/o_book_order", method = RequestMethod.POST)
	public String o_book_order(@RequestParam(value = "check", required = false) String check, HttpServletRequest request) {
		HttpSession session = request.getSession();
		Book book = bookDao.selectByBnumber(check); //체크용
		if(book.getBorrow().equals("대여불가능")) {
			session.setAttribute("borrow_book", 0);
			session.setAttribute("odbnumber", check);
		} else {
			session.setAttribute("borrow_book", 1);
			try {
				OrderRegisterRequest oregReq = new OrderRegisterRequest();
				oregReq.setBookemail("없음"); // 예약자는 없음 표시
				oregReq.setOdbnumber(check); // 전달받은 인자
				oregReq.setOdbook(book.getBname());
				Object Ouser = session.getAttribute("email");
				String user = (String) Ouser;
				oregReq.setOdemail(user); // 세션의 현재 로그인 사용자
				orderRegisterService.order_regist(oregReq);
				
				// book을 대여 불가능 상태로 만듦
				BookRegisterRequest bregReq = new BookRegisterRequest();
				bregReq.setBname(book.getBname());
				bregReq.setBwriter(book.getBwriter());
				bregReq.setBgenre(book.getBgenre());
				bregReq.setBnumber(book.getBnumber());
				bregReq.setBorrow("대여불가능");
				bookUpdateService.book_update(bregReq);
			} catch (Exception e) {};
		}
		session.setAttribute("bname", book.getBname());
		return "o_book_order";

	}
	
	@GetMapping("/o_book_order_list")
	public String o_book_order_list(Model model,
			HttpServletRequest request) {
		HttpSession session = request.getSession();
		Object Oemail = session.getAttribute("email");
		String email = (String)Oemail;
		List<Order> orderList = orderDao.search(email); //사용자의 이메일로 검색
		model.addAttribute("orders",orderList);
		return "o_book_order_list";
	}
	
	@RequestMapping(value = "/o_book_return", method = RequestMethod.POST)
	public String o_book_return(Model model, @RequestParam(value = "check", required = false) String check) {
		Book book = bookDao.selectByBnumber(check); //체크용
		OrderRegisterRequest oregReq = new OrderRegisterRequest();
		Order order = orderDao.selectByOdBnumber(check);
		try {
		// book을 대여 가능 상태로 만듦
		BookRegisterRequest bregReq = new BookRegisterRequest();
		bregReq.setBname(book.getBname());
		bregReq.setBwriter(book.getBwriter());
		bregReq.setBgenre(book.getBgenre());
		bregReq.setBnumber(book.getBnumber());
		orderDao.delete(check); //대여자 삭제
		
		if(order.getBookemail().equals("없음")) {
			bregReq.setBorrow("대여가능");
		}else {
			bregReq.setBorrow("대여불가능");
			//예약자를 대여자로 변경
			oregReq.setOdemail(order.getBookemail()); // 세션의 현재 로그인 사용자
			oregReq.setBookemail("없음"); // 예약자는 없음 표시
			oregReq.setOdbnumber(check); // 도서번호
			oregReq.setOdbook(order.getOdbook()); //도서 이름
	
			orderRegisterService.order_regist(oregReq);

		}
		bookUpdateService.book_update(bregReq);
		}catch(Exception e) {};
		
		return "o_book_return";

	}
	
	@GetMapping("/o_book_book")
	public String o_book_book(Model model,
			HttpServletRequest request) {
		HttpSession session = request.getSession();
		Object Oodbnum = session.getAttribute("odbnumber");
		String odbnumber = (String)Oodbnum;

		Order order = orderDao.selectByOdBnumber(odbnumber);
		if(order.getBookemail().equals("없음")) { //예약자가 없다
			session.setAttribute("book_book", 1);
			try {
				OrderRegisterRequest oregReq = new OrderRegisterRequest();
				Object Ouser = session.getAttribute("email");
				String user = (String) Ouser;
				oregReq.setBookemail(user); // 현재 로그인 세션을 예약자로 등록
				oregReq.setOdbnumber(odbnumber);
				oregReq.setOdbook(order.getOdbook());
				oregReq.setOdemail(order.getOdemail()); // 현재 대여자
				orderUpdateService.order_update(oregReq);
			}catch(Exception e) {};
		}else {
			session.setAttribute("book_book", 0);
		}
		return "o_book_book";
	}
	
}
