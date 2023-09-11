package artlcle.container;

import artlcle.dao.ArticleDao;
import artlcle.dao.MemberDao;

public class Container {
	public static ArticleDao articleDao; // 공유재 (static)
	public static MemberDao memberDao;
	
	static {
		articleDao = new ArticleDao();
		memberDao = new MemberDao();
	}
}
