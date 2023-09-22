package fr.eni.OSNA.bll;

import java.util.List;

import fr.eni.OSNA.bo.Article;
import fr.eni.OSNA.dal.ArticleDAO;
import fr.eni.OSNA.dal.DAOFactory;

public class ArticleManager {
private static ArticleManager instance;
	
	public static ArticleManager getInstance() {
		if(instance == null) {
			instance = new ArticleManager();
		}
		
		return instance;
	}
	
	private ArticleDAO dao = DAOFactory.getArticleDAO();
	
	public Article selectById(int id) throws Exception {
		return dao.selectById(id);
	}
	
	public List<Article> selectAll() throws Exception {
		return dao.selectAll();
	}
	
	public void insert(Article article) throws Exception {
		dao.insert(article);
	}
	
	public void update(Article article) throws Exception {
		dao.update(article);
	}
	
	public void delete(int id) throws Exception {
		dao.deleteById(id);
	}
	
	public void updateOffer(int bestOffer, int idUserOffer, int idArticle) throws Exception {
		dao.updateOffer(bestOffer, idUserOffer, idArticle);
	}
	
	public List<Article> selectByCategorie(String categorie) throws Exception {
		return dao.selectByCategorie(categorie);
	}
	
	public List<Article> selectByKeyword(String keyword) throws Exception {
		return dao.selectByKeyword(keyword);
	}
	
	public List<Article> selectUserCurrentAuction(int idUser) throws Exception {
		return dao.selectUserCurrentAuction(idUser);
	}
	
	public List<Article> selectUserWonAuction(int idUser) throws Exception {
		return dao.selectUserWonAuction(idUser);
	}
	
	public List<Article> selectUserSales(int idUser) throws Exception {
		return dao.selectUserSales(idUser);
	}
	
	public List<Article> selectUserEndedSales(int idUser) throws Exception {
		return dao.selectUserEndedSales(idUser);
	}
	
}
