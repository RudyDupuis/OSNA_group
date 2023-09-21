package fr.eni.OSNA.dal;

public class DAOFactory {
	public static UserDAO getUserDAO() {
		return new UserDaoJdbcImpl();
	}
	
	public static ArticleDAO getArticleDAO() {
		return new ArticleDaoJdbcImpl();
	}
}
