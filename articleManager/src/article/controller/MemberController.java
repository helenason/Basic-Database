package article.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import article.dto.Member;
import article.util.Util;
import artlcle.container.Container;

public class MemberController extends Controller {

	private List<Member> members;
	private Scanner sc;

	public MemberController(Scanner sc) {
		this.members = Container.memberDao.members;
		this.sc = sc;
	}

	public void doAction(String actionMethodName, String cmd) {
		switch (actionMethodName) {
		case "join":
			doJoin();
			break;
		case "login":
			doLogin();
			break;
		case "logout":
			doLogout();
			break;
		default:
			System.out.println("존재하지 않는 명령어입니다.");
			break;
		}
	}
	
	private void doLogin() {
		System.out.printf("로그인 아이디 : ");
		String loginId = sc.nextLine();
		System.out.printf("로그인 비밀번호 : ");
		String loginPw = sc.nextLine();
		
		// 해당 아이디 회원 탐색 후 저장
		Member member = getMemberByLoginId(loginId);
		
		if (member == null) {// 일치하는 게 없다면
			System.out.println("존재하지 않는 아이디입니다.");
			return;
		}
		
		// 아이디는 있지만 비번 틀림
		if (member.loginPw.equals(loginPw) == false) {
			System.out.println("비밀번호를 확인해주세요.");
			return;
		}
		
		loginedMember = member;
		System.out.printf("로그인 성공! %s님 환영합니다\n", loginedMember.name);
	}

	private void doLogout() {
		loginedMember = null;
		System.out.println("로그아웃 완료!");
	}

	private void doJoin() {
		int id = members.size() + 1;

		String loginId;
		String loginPw;
		String loginPwChk;

		while (true) { // 아이디 중복 체크
			System.out.printf("아이디 : ");
			loginId = sc.nextLine();

			if (!isJoinableLoginId(loginId)) {
				System.out.printf("%s은(는) 이미 사용 중인 아이디입니다.\n", loginId);
				continue;
			}
			break;
		}

		while (true) { // 비밀번호 확인
			System.out.printf("비밀번호 : ");
			loginPw = sc.nextLine();
			System.out.printf("비밀번호 확인 : ");
			loginPwChk = sc.nextLine();

			if (!loginPw.equals(loginPwChk)) {
				System.out.println("비밀번호 다시 입력해주세요.");
				continue;
			}
			break;
		}

		System.out.printf("이름 : ");
		String name = sc.nextLine();
		String regDate = Util.getDateStr();

		Member member = new Member(id, loginId, loginPw, name, regDate);
		members.add(member);

		System.out.println(id + "번 멤버 등록이 완료되었습니다.");
	}

	private Member getMemberByLoginId(String loginId) {
		int index = getMemberIndexByLoginId(loginId);
		
		if (index == -1) {
			return null;
		}
		return members.get(index);
	}
	
	private int getMemberIndexByLoginId(String loginId) {
		int i = 0;

		for (Member member : members) {

			if (member.loginId.equals(loginId)) {
				return i;
			}
			i++;
		}
		return -1;
	}

	private boolean isJoinableLoginId(String loginId) {

		int index = getMemberIndexByLoginId(loginId);

		if (index == -1) {
			return true;
		}
		return false;
	}
	
	public void makeTestData() { // dummy
		System.out.println("테스트를 위한 회원 데이터를 생성합니다.");
		members.add(new Member(1, "admin", "admin", "관리자", Util.getDateStr()));
		members.add(new Member(2, "test1", "test2", "유저1", Util.getDateStr()));
		members.add(new Member(3, "test2", "test2", "유저2", Util.getDateStr()));
	}
}
