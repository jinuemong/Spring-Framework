package skuniv.cs.Hello;

import java.util.*;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class Controller_member {
	@Autowired  @Qualifier("member")
	private MemberDao memberDao;
	
	@Autowired  @Qualifier("book")
	private BookDao bookDao;
	
	@Autowired  @Qualifier("memberReg")
	private MemberRegisterService memberRegisterService;
	
	@Autowired  @Qualifier("memberUp")
	private MemberUpdateService memberUpdateService;
	
	@GetMapping(value = "/")
	public String main(Model model) {
		return "main";
	}
	

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(Model model, @RequestParam(value = "strEmail", required = false) String strEmail,
			@RequestParam(value = "strPwd", required = false) String strPwd,
			HttpServletRequest request, HttpServletResponse response) {
		try {
		Login login = new Login(memberDao);
		int login_check = login.loginUser(strEmail, strPwd);
		HttpSession session = request.getSession();
		if (login_check == 1) {
			session.setAttribute("login",1);
			session.setAttribute("email", strEmail);
			session.setAttribute("name", memberDao.selectByEmail(strEmail).getName());
			session.setAttribute("pwd", memberDao.selectByEmail(strEmail).getPassword());
			model.addAttribute("LOGIN", "로그인 성공 , "+strEmail );
			
		}else {
			session.setAttribute("login",0);
			model.addAttribute("LOGIN", "로그인 실패" );
		}
		return "login";
		}catch(Exception e) {
			return "/";
		}
	}
	
	@GetMapping(value = "/logout")
	public String logout(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.setAttribute("login",0);
		session.invalidate();
		return "logout";
	}
	

	@GetMapping("m_insert")
	public String m_insert(Model model) {
		model.addAttribute("registerRequest",new RegisterRequest());
		return "m_insert";
	}
	@PostMapping("m_insert_result")
	public String m_insert_result(RegisterRequest regReq) {
		try {
			memberRegisterService.member_regist(regReq);
			return "m_insert_result";
		}catch(Exception e) {
			return "m_insert";
		}
	}
	
	@GetMapping("m_update")
	public String m_update(Model model) {
		model.addAttribute("registerRequest",new RegisterRequest());
		
		return "m_update";
	}
	@PostMapping("m_update_result")
	public String m_update_result(RegisterRequest regReq ,HttpServletRequest request) {
		try {
			HttpSession session = request.getSession();
			Object Oemail = session.getAttribute("email");
			String email = (String)Oemail;
			regReq.setEmail(email);
			if(regReq.getPassword().equals("")) {
				Object Opwd = session.getAttribute("pwd"); String pwd = (String)Opwd;
				regReq.setPassword(pwd);
			}
			if(regReq.getName().equals("")) {
				Object Oname = session.getAttribute("name"); String name = (String)Oname;
				regReq.setName(name);
			}
			memberUpdateService.member_update(regReq); //업데이트후 세션 초기화
			session.setAttribute("name", regReq.getName());
			session.setAttribute("pwd", regReq.getPassword());
			return "m_update_result";
		}catch(Exception e) {
			return "m_update";
		}
	}
	
	@GetMapping("m_delete")
	public String m_delete() {
		
		return "m_delete";
	}
	
	@RequestMapping(value = "/m_delete_result", method = RequestMethod.POST)
	public String m_delete_result(Model model, @RequestParam(value = "strPwd", required = false) String strPwd,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			HttpSession session = request.getSession();
			Object Oemail = session.getAttribute("email");
			String email = (String)Oemail;
			Object Opwd = session.getAttribute("pwd");
			String pwd = (String)Opwd;
			
			if(!pwd.equals(strPwd))
				throw new Exception();
			
			memberDao.delete(email);
			session.setAttribute("login",0);
			session.invalidate();
			return "m_delete_result";
		} catch (Exception e) {
			return "m_delete";
		}
	}
	
	
	@GetMapping(value = "/m_list")
	public String list_m(Model model) {
		List<Member> memberList = memberDao.selectAll();
		model.addAttribute("members", memberList);
		return "m_list";
	}
	
}