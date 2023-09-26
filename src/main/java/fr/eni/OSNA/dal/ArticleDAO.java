package fr.eni.OSNA.dal;

import java.util.List;

import fr.eni.OSNA.bo.Article;
import fr.eni.OSNA.bo.User;

public interface ArticleDAO extends DAO<Article> {
	void updateOffer(int offer, User userOffer, int idArticle) throws Exception;
	void updatePickedUp(int articleId) throws Exception;
	List<Article> selectByCategorie(String categorie) throws Exception;
	List<Article> selectByKeyword(String keyword) throws Exception;
	List<Article> selectUserCurrentAuction(int idUser) throws Exception;
	List<Article> selectUserWonAuction(int idUser) throws Exception;
	List<Article> selectUserSales(int idUser) throws Exception;
	List<Article> selectUserEndedSales(int idUser) throws Exception;
}
