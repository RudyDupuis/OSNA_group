package fr.eni.OSNA.bll;

import java.time.LocalDate;
import java.util.List;

import fr.eni.OSNA.bo.Article;
import fr.eni.OSNA.bo.User;
import fr.eni.OSNA.dal.ArticleDAO;
import fr.eni.OSNA.dal.DAOFactory;
import fr.eni.OSNA.messages.ErrorCode;
import fr.eni.OSNA.messages.MessageReader;

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
		StringBuilder error = checkInputFields(article);
		
		if(error != null) {
			throw new Exception(error.toString());
		} else {
			dao.insert(article);
		}
	}
	
	public void update(Article article) throws Exception {
		StringBuilder error = checkInputFields(article);
		
		if(error != null) {
			throw new Exception(error.toString());
		} else {
			dao.update(article);
		}
	}
	
	public void delete(int id) throws Exception {
		dao.deleteById(id);
	}
	
	public void updateOffer(int offer, User userOffer, int idArticle) throws Exception {
		UserManager userManager = UserManager.getInstance();
		
		Article article = selectById(idArticle);
		
		if(article.getStartingPrice() >= offer) {
			throw new Exception(MessageReader.getMessage(ErrorCode.OFFER_HIGHER_STARTING_PRICE));
		}
		
		if(article.getBestOffer() >= offer) {
			throw new Exception(MessageReader.getMessage(ErrorCode.OFFER_HIGHER_BEST_OFFER));
		}
		
		if(article.getBestOffer() != 0) {
			User oldUser = userManager.selectById(article.getIdUserBestOffer());
			userManager.updatePoints(oldUser, article.getBestOffer(), "repay");
		}
		
		if(article.getIdUserBestOffer() == userOffer.getId()) {
			throw new Exception(MessageReader.getMessage(ErrorCode.OFFER_IS_BEST_OFFER));
		}
		
		userManager.updatePoints(userOffer, offer, "pay");
		
		dao.updateOffer(offer, userOffer, idArticle);
	}
	
	public void updatePickedUp(int articleId) throws Exception {
		dao.updatePickedUp(articleId);
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
	
	private StringBuilder checkInputFields(Article article) {
		StringBuilder error = new StringBuilder();
		boolean hasError = false;
		
		if (article.getStartDate().isBefore(LocalDate.now())) {
		    hasError = true;
		    error.append(MessageReader.getMessage(ErrorCode.STARTDATE_SMALLER_TODAY));
		}

		if (article.getEndDate().isBefore(article.getStartDate())) {
		    hasError = true;
		    error.append(MessageReader.getMessage(ErrorCode.ENDDATE_SMALLER_STARTDATE));
		}
		
		
		if(hasError) {
			return error;
		} else {
			return null;
		}
	
	}
	
}
