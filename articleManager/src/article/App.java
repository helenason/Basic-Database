package article;

import java.util.Scanner;

import article.controller.ArticleController;
import article.controller.Controller;
import article.controller.MemberController;

public class App {
	public void run() {
		System.out.println("== 프로그램 시작 ==");

		Scanner sc = new Scanner(System.in);

		MemberController memberController = new MemberController(sc);
		ArticleController articleController = new ArticleController(sc);

		articleController.makeTestData();
		memberController.makeTestData();

		while (true) {
			System.out.printf("명령어) ");
			String cmd = sc.nextLine();

			if (cmd.length() == 0) {
				System.out.println("명령어를 입력해주세요.");
				continue;
			}
			if (cmd.equals("exit")) {
				break;
			}

			String[] cmdBits = cmd.split(" ");

			if (cmdBits.length == 1) {
				System.out.println("존재하지 않는 명령어입니다.");
				continue;
			}

			String controllerName = cmdBits[0];
			String actionMethodName = cmdBits[1];

			Controller controller = null;

			if (controllerName.equals("article")) {
				controller = articleController;
			} else if (controllerName.equals("member")) {
				controller = memberController;
			} else {
				System.out.println("존재하지 않는 명령어입니다.");
				continue;
			}

			switch (actionMethodName) {
			case "write":
			case "modify":
			case "delete":
			case "logout":
				// 이 모든 경우에
				if (!Controller.isLogined()) {
					System.out.println("로그인 후 이용해주세요.");
					continue; // while continue
				}
				break; // switch break
			}
			switch (actionMethodName) {
			case "login":
			case "join":
				if (Controller.isLogined()) {
					System.out.println("로그아웃 후 이용해주세요.");
					continue; // while continue
				}
				break; // switch break
			}

			controller.doAction(actionMethodName, cmd);

//			sc.close(); : 있으면 에러 뜸 why?

			System.out.println("== 프로그램 끝 ==");
		}
	}
}
