package fr.eni.OSNA.dal;

import java.util.List;

import fr.eni.OSNA.bo.Article;

public interface ArticleDAO extends DAO<Article> {
	void updateOffer(int bestOffer, int idUserOffer, int idArticle) throws Exception;
	List<Article> selectByCategorie(String categorie) throws Exception;
	List<Article> selectByKeyword(String keyword) throws Exception;
	List<Article> selectUserCurrentAuction(int idUser) throws Exception;
	List<Article> selectUserWonAuction(int idUser) throws Exception;
	List<Article> selectUserSales(int idUser) throws Exception;
	List<Article> selectUserEndedSales(int idUser) throws Exception;
}
