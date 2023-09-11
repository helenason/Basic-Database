package artlcle.dao;

import java.util.ArrayList;
import java.util.List;

import article.dto.Article;

public class ArticleDao {
	public List<Article> articles;
	
	public ArticleDao() {
		articles = new ArrayList<>();
	}
}
