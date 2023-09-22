package fr.eni.OSNA.dal;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.eni.OSNA.bo.Article;
import fr.eni.OSNA.messages.ErrorCode;
import fr.eni.OSNA.messages.MessageReader;

public class ArticleDaoJdbcImpl implements ArticleDAO {

	@Override
	/** SELECT id, idSeller, name, categorie, image, description, startingPrice, bestOffer, idUserBestOffer, startDate, endDate, street, postalCode and city WHERE id*/
	public Article selectById(int id) throws Exception {
		String Sql = "SELECT * FROM articles WHERE id = ?";
		Article article = null;
		
		try(Connection cnx = ConnectionProvider.getConnection(); PreparedStatement ps = cnx.prepareStatement(Sql)) {
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				article = new Article(
								rs.getInt("id"),
								rs.getInt("idSeller"),
								rs.getString("name"),
								rs.getString("categorie"),
								rs.getBlob("image"),
								rs.getString("description"),
								rs.getInt("startingPrice"),
								rs.getInt("bestOffer"),
								rs.getInt("idUserBestOffer"),
								rs.getDate("startDate").toLocalDate(),
								rs.getDate("endDate").toLocalDate(),
								rs.getString("street"),
								rs.getInt("postalCode"),
								rs.getString("city")
							);
			} 
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception(MessageReader.getMessage(ErrorCode.ERROR_SELECT));
		}
		
		return article;
	}

	@Override
	/** SELECT id, idSeller, name, categorie, image, description, startingPrice, bestOffer, idUserBestOffer, startDate, endDate, street, postalCode and city */
	public List<Article> selectAll() throws Exception {
	    String sql = "SELECT * FROM articles WHERE endDate > GETDATE()";
	    List<Article> articles = new ArrayList<>();

	    try (Connection cnx = ConnectionProvider.getConnection(); PreparedStatement ps = cnx.prepareStatement(sql)) {
	        ResultSet rs = ps.executeQuery();

	        while (rs.next()) {
	            Article article = new Article(
	                rs.getInt("id"),
	                rs.getInt("idSeller"),
	                rs.getString("name"),
	                rs.getString("categorie"),
	                rs.getBlob("image"),
	                rs.getString("description"),
	                rs.getInt("startingPrice"),
	                rs.getInt("bestOffer"),
	                rs.getInt("idUserBestOffer"),
	                rs.getDate("startDate").toLocalDate(),
	                rs.getDate("endDate").toLocalDate(),
	                rs.getString("street"),
	                rs.getInt("postalCode"),
	                rs.getString("city")
	            );
	            articles.add(article);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        throw new Exception(MessageReader.getMessage(ErrorCode.ERROR_SELECT));
	    }

	    return articles;
	}


	@Override
	/** UPDATE idSeller, name, categorie, image, description, startingPrice, startDate, endDate, street, postalCode and city WHERE id*/
	public void update(Article article) throws Exception {
		String Sql = "UPDATE articles SET idSeller = ?, name = ?, categorie = ?, image = ?, description = ?, startingPrice = ?, startDate = ?, endDate = ?, street = ?, postalCode = ?, city = ? WHERE id = ?";
		
		try(Connection cnx = ConnectionProvider.getConnection(); PreparedStatement ps = cnx.prepareStatement(Sql)) {
			 ps.setInt(1, article.getIdSeller());
			 ps.setString(2, article.getName());
			 ps.setString(3, article.getCategorie());
			 ps.setBlob(4, article.getImage());
			 ps.setString(5, article.getDescription());
			 ps.setInt(6, article.getStartingPrice());
			 ps.setDate(7, Date.valueOf(article.getStartDate()));
			 ps.setDate(8, Date.valueOf(article.getEndDate()));
			 ps.setString(9, article.getStreet());
			 ps.setInt(10, article.getPostalCode());
			 ps.setString(11, article.getCity());
			 ps.setInt(12, article.getId());
			 ps.executeUpdate();
			 

		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception(MessageReader.getMessage(ErrorCode.ERROR_UPDATE));
		}

	}

	@Override
	/** INSERT idSeller, name, categorie, image, description, startingPrice, startDate, endDate, street, postalCode and city */
	public void insert(Article article) throws Exception {
		String Sql = "INSERT INTO articles(idSeller, name, categorie, image, description, startingPrice, startDate, endDate, street, postalCode, city) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		
		try(Connection cnx = ConnectionProvider.getConnection(); PreparedStatement ps = cnx.prepareStatement(Sql, java.sql.Statement.RETURN_GENERATED_KEYS)) {
			 ps.setInt(1, article.getIdSeller());
			 ps.setString(2, article.getName());
			 ps.setString(3, article.getCategorie());
			 ps.setBlob(4, article.getImage());
			 ps.setString(5, article.getDescription());
			 ps.setInt(6, article.getStartingPrice());
			 ps.setDate(7, Date.valueOf(article.getStartDate()));
			 ps.setDate(8, Date.valueOf(article.getEndDate()));
			 ps.setString(9, article.getStreet());
			 ps.setInt(10, article.getPostalCode());
			 ps.setString(11, article.getCity());
			 ps.executeUpdate();
			 
			 ResultSet generatedKeys = ps.getGeneratedKeys();
				if (generatedKeys.next()) {
					article.setId(generatedKeys.getInt(1));
				}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception(MessageReader.getMessage(ErrorCode.ERROR_INSERT));
		}

	}

	@Override
	/** DELETE WHERE id */
	public void deleteById(int id) throws Exception {
		String Sql = "DELETE FROM articles WHERE id = ?";
		
		try(Connection cnx = ConnectionProvider.getConnection(); PreparedStatement ps = cnx.prepareStatement(Sql)) {
			 ps.setInt(1, id);
			 ps.executeUpdate();
			 
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception(MessageReader.getMessage(ErrorCode.ERROR_DELETE));
		}

	}

	@Override
	public void updateOffer(int bestOffer, int idUserOffer, int idArticle) throws Exception {
		String Sql = "UPDATE articles SET bestOffer = ?, idUserBestOffer = ? WHERE id = ?";
		
		try(Connection cnx = ConnectionProvider.getConnection(); PreparedStatement ps = cnx.prepareStatement(Sql)) {
			 ps.setInt(1, bestOffer);
			 ps.setInt(2, idUserOffer);
			 ps.setInt(3, idArticle);
			 ps.executeUpdate();
			 

		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception(MessageReader.getMessage(ErrorCode.ERROR_UPDATE));
		}
		
	}

	@Override
	/** SELECT id, idSeller, name, categorie, image, description, startingPrice, bestOffer, idUserBestOffer, startDate, endDate, street, postalCode and city WHERE categorie*/
	public List<Article> selectByCategorie(String categorie) throws Exception {
		String sql = "SELECT * FROM articles WHERE categorie = ? AND endDate > GETDATE()";
	    List<Article> articles = new ArrayList<>();

	    try (Connection cnx = ConnectionProvider.getConnection(); PreparedStatement ps = cnx.prepareStatement(sql)) {
	        ps.setString(1, categorie);
	    	ResultSet rs = ps.executeQuery();

	        while (rs.next()) {
	            Article article = new Article(
	                rs.getInt("id"),
	                rs.getInt("idSeller"),
	                rs.getString("name"),
	                rs.getString("categorie"),
	                rs.getBlob("image"),
	                rs.getString("description"),
	                rs.getInt("startingPrice"),
	                rs.getInt("bestOffer"),
	                rs.getInt("idUserBestOffer"),
	                rs.getDate("startDate").toLocalDate(),
	                rs.getDate("endDate").toLocalDate(),
	                rs.getString("street"),
	                rs.getInt("postalCode"),
	                rs.getString("city")
	            );
	            articles.add(article);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        throw new Exception(MessageReader.getMessage(ErrorCode.ERROR_SELECT));
	    }

	    return articles;
	}

	@Override
	/** SELECT id, idSeller, name, categorie, image, description, startingPrice, bestOffer, idUserBestOffer, startDate, endDate, street, postalCode and city WHERE keyword */
	public List<Article> selectByKeyword(String keyword) throws Exception {
		String sql = "SELECT * FROM articles WHERE name LIKE ?  AND endDate > GETDATE()";
	    List<Article> articles = new ArrayList<>();

	    try (Connection cnx = ConnectionProvider.getConnection(); PreparedStatement ps = cnx.prepareStatement(sql)) {
	        ps.setString(1, "%" + keyword + "%");
	    	ResultSet rs = ps.executeQuery();

	        while (rs.next()) {
	            Article article = new Article(
	                rs.getInt("id"),
	                rs.getInt("idSeller"),
	                rs.getString("name"),
	                rs.getString("categorie"),
	                rs.getBlob("image"),
	                rs.getString("description"),
	                rs.getInt("startingPrice"),
	                rs.getInt("bestOffer"),
	                rs.getInt("idUserBestOffer"),
	                rs.getDate("startDate").toLocalDate(),
	                rs.getDate("endDate").toLocalDate(),
	                rs.getString("street"),
	                rs.getInt("postalCode"),
	                rs.getString("city")
	            );
	            articles.add(article);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        throw new Exception(MessageReader.getMessage(ErrorCode.ERROR_SELECT));
	    }

	    return articles;
	}

	@Override
	/** SELECT id, idSeller, name, categorie, image, description, startingPrice, bestOffer, idUserBestOffer, startDate, endDate, street, postalCode and city WHERE id = idUserBestOffer AND endDate < Now*/
	public List<Article> selectUserCurrentAuction(int idUser) throws Exception {
		String sql = "SELECT * FROM articles WHERE idUserBestOffer = ? AND endDate > GETDATE()";
	    List<Article> articles = new ArrayList<>();

	    try (Connection cnx = ConnectionProvider.getConnection(); PreparedStatement ps = cnx.prepareStatement(sql)) {
	        ps.setInt(1, idUser);
	    	ResultSet rs = ps.executeQuery();

	        while (rs.next()) {
	            Article article = new Article(
	                rs.getInt("id"),
	                rs.getInt("idSeller"),
	                rs.getString("name"),
	                rs.getString("categorie"),
	                rs.getBlob("image"),
	                rs.getString("description"),
	                rs.getInt("startingPrice"),
	                rs.getInt("bestOffer"),
	                rs.getInt("idUserBestOffer"),
	                rs.getDate("startDate").toLocalDate(),
	                rs.getDate("endDate").toLocalDate(),
	                rs.getString("street"),
	                rs.getInt("postalCode"),
	                rs.getString("city")
	            );
	            articles.add(article);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        throw new Exception(MessageReader.getMessage(ErrorCode.ERROR_SELECT));
	    }

	    return articles;
	}

	@Override
	/** SELECT id, idSeller, name, categorie, image, description, startingPrice, bestOffer, idUserBestOffer, startDate, endDate, street, postalCode and city WHERE id = idUserBestOffer AND endDate > Now*/
	public List<Article> selectUserWonAuction(int idUser) throws Exception {
		String sql = "SELECT * FROM articles WHERE idUserBestOffer = ? AND endDate < GETDATE()";
	    List<Article> articles = new ArrayList<>();

	    try (Connection cnx = ConnectionProvider.getConnection(); PreparedStatement ps = cnx.prepareStatement(sql)) {
	        ps.setInt(1, idUser);
	    	ResultSet rs = ps.executeQuery();

	        while (rs.next()) {
	            Article article = new Article(
	                rs.getInt("id"),
	                rs.getInt("idSeller"),
	                rs.getString("name"),
	                rs.getString("categorie"),
	                rs.getBlob("image"),
	                rs.getString("description"),
	                rs.getInt("startingPrice"),
	                rs.getInt("bestOffer"),
	                rs.getInt("idUserBestOffer"),
	                rs.getDate("startDate").toLocalDate(),
	                rs.getDate("endDate").toLocalDate(),
	                rs.getString("street"),
	                rs.getInt("postalCode"),
	                rs.getString("city")
	            );
	            articles.add(article);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        throw new Exception(MessageReader.getMessage(ErrorCode.ERROR_SELECT));
	    }

	    return articles;
	}

	@Override
	/** SELECT id, idSeller, name, categorie, image, description, startingPrice, bestOffer, idUserBestOffer, startDate, endDate, street, postalCode and city WHERE id = idSeller AND endDate < Now*/
	public List<Article> selectUserSales(int idUser) throws Exception {
		String sql = "SELECT * FROM articles WHERE idSeller = ? AND endDate > GETDATE()";
	    List<Article> articles = new ArrayList<>();

	    try (Connection cnx = ConnectionProvider.getConnection(); PreparedStatement ps = cnx.prepareStatement(sql)) {
	        ps.setInt(1, idUser);
	    	ResultSet rs = ps.executeQuery();

	        while (rs.next()) {
	            Article article = new Article(
	                rs.getInt("id"),
	                rs.getInt("idSeller"),
	                rs.getString("name"),
	                rs.getString("categorie"),
	                rs.getBlob("image"),
	                rs.getString("description"),
	                rs.getInt("startingPrice"),
	                rs.getInt("bestOffer"),
	                rs.getInt("idUserBestOffer"),
	                rs.getDate("startDate").toLocalDate(),
	                rs.getDate("endDate").toLocalDate(),
	                rs.getString("street"),
	                rs.getInt("postalCode"),
	                rs.getString("city")
	            );
	            articles.add(article);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        throw new Exception(MessageReader.getMessage(ErrorCode.ERROR_SELECT));
	    }

	    return articles;
	}

	@Override
	/** SELECT id, idSeller, name, categorie, image, description, startingPrice, bestOffer, idUserBestOffer, startDate, endDate, street, postalCode and city WHERE id = idSeller AND endDate > Now*/
	public List<Article> selectUserEndedSales(int idUser) throws Exception {
		String sql = "SELECT * FROM articles WHERE idSeller = ? AND endDate < GETDATE()";
	    List<Article> articles = new ArrayList<>();

	    try (Connection cnx = ConnectionProvider.getConnection(); PreparedStatement ps = cnx.prepareStatement(sql)) {
	        ps.setInt(1, idUser);
	    	ResultSet rs = ps.executeQuery();

	        while (rs.next()) {
	            Article article = new Article(
	                rs.getInt("id"),
	                rs.getInt("idSeller"),
	                rs.getString("name"),
	                rs.getString("categorie"),
	                rs.getBlob("image"),
	                rs.getString("description"),
	                rs.getInt("startingPrice"),
	                rs.getInt("bestOffer"),
	                rs.getInt("idUserBestOffer"),
	                rs.getDate("startDate").toLocalDate(),
	                rs.getDate("endDate").toLocalDate(),
	                rs.getString("street"),
	                rs.getInt("postalCode"),
	                rs.getString("city")
	            );
	            articles.add(article);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        throw new Exception(MessageReader.getMessage(ErrorCode.ERROR_SELECT));
	    }

	    return articles;
	}

}
