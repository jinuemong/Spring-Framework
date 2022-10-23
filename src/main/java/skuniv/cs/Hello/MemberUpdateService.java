package skuniv.cs.Hello;


public class MemberUpdateService {
	private MemberDao memberDao;
	
	public MemberUpdateService(MemberDao memberDao) {
		this.memberDao = memberDao;
	}
	
	public Long member_update(RegisterRequest req) 
			throws Exception{
		Member member = memberDao.selectByEmail(req.getEmail());
		if(member==null) {
			throw new Exception("DuplicateMemberException");
		}
		Member newMember = new Member(req.getEmail(),req.getPassword(),
				req.getName(), member.getRegisterDateTime());
		memberDao.update(newMember);
		return newMember.getId();
	}
}
