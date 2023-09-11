package article.dto;

public class Article extends Dto{ // dto, vo

	public String title;
	public String body;
	public int count; // 조회수
	public int memberId; // p.k(고유키), 무조건 존재해야함, 중복 불가 - DB

	public Article(int id, String regDate, int memberId, String title, String body) { // overloading
		this(id, regDate, memberId, title, body, 0);
	}

	public Article(int id, String regDate, int memberId, String title, String body, int count) {
		this.id = id;
		this.regDate = regDate;
		this.memberId = memberId;
		this.title = title;
		this.body = body;
		this.count = count;
	}

	public void increaseCount() {
		this.count++;
	}
}