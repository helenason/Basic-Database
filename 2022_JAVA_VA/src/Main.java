
public class Main {
	public static void main(String[] args) {
		
		Article[] articles = new Article[100];
		
		Article article1 = new Article();
		System.out.println(article1.id);

		Article article2 = new Article();
		System.out.println(article2.id);
	}
}

class Article {
	static int lastId; // 객체끼리 공유 가능한 변수 (전역변수 개념)
	int id;
	String regDate;
	
	static { // static 생성자: 프로그램 실행 시 실행
		lastId = 0;
	}
	
	Article() {
		this(lastId + 1, "2022"); // 다른 생성자 실행
		
		lastId++;
	}

	 Article(int id, String regDate) {
		 this.id = id;
		 this.regDate = regDate;
	}
}