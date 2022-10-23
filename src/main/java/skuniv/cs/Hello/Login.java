package skuniv.cs.Hello;

public class Login {
	private MemberDao memberDao;
	public Login(MemberDao memberDao) {
		this.memberDao = memberDao;
	}
	public int loginUser(String loginEmail, String loginPwd) {
		try {
		Member member = memberDao.selectByEmail(loginEmail);
		System.out.print(member.getEmail());
		String pwd = memberDao.selectByEmail(loginEmail).getPassword();
		System.out.print(pwd);
		if (pwd.equals(loginPwd)) {
			return 1;
		} else {
			return 0;
		}
		}catch(Exception e) {
			return 0;
		}
	}


}
