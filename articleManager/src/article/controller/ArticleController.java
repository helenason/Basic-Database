package article.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import article.dto.Article;
import article.dto.Member;
import article.util.Util;
import artlcle.container.Container;

public class ArticleController extends Controller {

	private List<Article> articles;
	private Scanner sc;
	private String cmd;

	public ArticleController(Scanner sc) {
		this.articles = Container.articleDao.articles;
		this.sc = sc;
	}

	public void doAction(String actionMethodName, String cmd) { // override
		this.cmd = cmd;

		switch (actionMethodName) {
		case "write":
			doWrite();
			break;
		case "list":
			showList();
			break;
		case "detail":
			showDetail();
			break;
		case "delete":
			doDelete();
			break;
		case "modify":
			doModify();
			break;
		default:
			System.out.println("존재하지 않는 명령어입니다.");
			break;
		}
	}

	private void doWrite() {
		int id = articles.size() + 1;
		System.out.printf("제목 : ");
		String title = sc.nextLine();
		System.out.printf("내용 : ");
		String body = sc.nextLine();
		String regDate = Util.getDateStr();

		Article article = new Article(id, regDate, loginedMember.id, title, body);
		articles.add(article);

		System.out.println(id + "번 글이 생성되었습니다.");
	}

	private void showList() {
		if (articles.size() == 0) {
			System.out.println("게시글이 없습니다.");
			return;
		}

		String searchKeyword = cmd.substring("article list".length()).trim(); // trim 공백제거

		List<Article> forPrintArticles = articles; //

		if (searchKeyword.length() > 0) { // 검색 기능
			forPrintArticles = new ArrayList<>();
			for (Article article : articles) {
				if (article.title.contains(searchKeyword)) {
					forPrintArticles.add(article);
				}
			}
			if (forPrintArticles.size() == 0) {
				System.out.println("검색 결과가 없습니다.");
			}
		}

		System.out.println("번호	|	제목	|	작성자	|	조회");
		for (int i = forPrintArticles.size() - 1; i >= 0; i--) {
			Article article = forPrintArticles.get(i);
			
			String writerName = null;
			List<Member> members = Container.memberDao.members;
			for(Member member : members) {
				if (article.memberId == member.id ) {
					writerName = member.name;
					break;
				}
			}
			System.out.printf("%d	|	%s	|	%s	|	%d\n", article.id, article.title, writerName, article.count);
		}
	}

	private void showDetail() {
		String[] cmdBits = cmd.split(" ");
		int id = Integer.parseInt(cmdBits[2]);

		Article foundArticle = getArticleById(id);

		if (foundArticle == null) {
			System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
			return;
		}

		foundArticle.increaseCount();
		System.out.printf("번호 : %d\n", foundArticle.id);
		System.out.printf("날짜 : %s\n", foundArticle.regDate);
		System.out.printf("작성자 : %d\n", foundArticle.memberId);
		System.out.printf("제목 : %s\n", foundArticle.title);
		System.out.printf("내용 : %s\n", foundArticle.body);
		System.out.printf("조회수 : %s\n", foundArticle.count);
	}

	private void doDelete() {
		String[] cmdBits = cmd.split(" ");
		int id = Integer.parseInt(cmdBits[2]);

		Article foundArticle = getArticleById(id);

		if (foundArticle == null) {
			System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
			return;
		}
		
		if (foundArticle.memberId != loginedMember.id) {// 작성자 != 로그인회원
			System.out.println("삭제할 권한이 없습니다.");
			return;
		}
		
		articles.remove(foundArticle);
		System.out.printf("%d번 게시물이 삭제되었습니다.\n", id);
	}

	private void doModify() {
		String[] cmdBits = cmd.split(" ");
		int id = Integer.parseInt(cmdBits[2]);

		Article foundArticle = getArticleById(id);

		if (foundArticle == null) {
			System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
			return;
		}

		if (foundArticle.memberId != loginedMember.id) {// 작성자 != 로그인회원
			System.out.println("수정할 권한이 없습니다.");
			return;
		}
		
		System.out.printf("수정할 제목 : ");
		String title = sc.nextLine();
		System.out.printf("수정할 내용 : ");
		String body = sc.nextLine();

		foundArticle.title = title;
		foundArticle.body = body;

		System.out.printf("%d번 글이 수정되었습니다.\n", id);
	}

	private int getIndexById(int id) {
		int i = 0;

		for (Article article : articles) {

			if (article.id == id) {
				return i;
			}
			i++;
		}
		return -1;
	}

	private Article getArticleById(int id) {
		int index = getIndexById(id);

		if (index != -1) {
			return articles.get(index);
		}
		return null;
	}

	public void makeTestData() { // dummy
		System.out.println("테스트를 위한 데이터 생성합니다.");
		articles.add(new Article(1, Util.getDateStr(), 1, "제목1", "내용1", 10));
		articles.add(new Article(2, Util.getDateStr(), 2, "제목2", "내용2", 20));
		articles.add(new Article(3, Util.getDateStr(), 2, "제목3", "내용3", 30));
	}
}
