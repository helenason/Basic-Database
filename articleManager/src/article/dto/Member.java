package article.dto;

public class Member extends Dto{
	
	public String loginId; // uni 중복 불가 - DB
	public String loginPw;
	public String name;
	
	public Member(int id, String loginId, String loginPw, String name, String regDate) {
		this.id = id;
		this.loginId = loginId;
		this.loginPw = loginPw;
		this.name = name;
		this.regDate = regDate;
	}
}
