package article.controller;

import article.dto.Member;

public abstract class Controller {
	
	public abstract void doAction(String a, String b);
	
	public abstract void makeTestData();
	
	// 두 객체에서 공유하기 위해 static, static은 객체 만들지 않고 사용
	public static Member loginedMember;
	
	public static boolean isLogined() { // 로그인 상태 반환
		return loginedMember != null;
	}
}

// Ctrl + Shift + R : Open Source
// Ctrl + Shift + T : 제공된 class 정보