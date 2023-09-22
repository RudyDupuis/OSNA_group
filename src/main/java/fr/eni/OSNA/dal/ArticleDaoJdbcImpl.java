package fr.eni.OSNA.dal;

import java.awt.image.BufferedImage;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import fr.eni.OSNA.bo.Article;
import fr.eni.OSNA.messages.ErrorCode;
import fr.eni.OSNA.messages.MessageReader;

public class ArticleDaoJdbcImpl implements ArticleDAO {

	@Override
	public void insert(Article article) throws Exception {
		
		String sqlInsert = " INSERT INTO articles ( name, description, categorie, image, startdate, endDate, startingPrice, street, postalCode, city ) VALUES (?,?,?) ";

		try (
			// 1. Connexion
			Connection connection = ConnectionProvider.getConnection();
			// 2. Requête SQL
			PreparedStatement statement = connection.prepareStatement( sqlInsert );	) {

			// 3. Paramètres de la requête (si besoin)


	//		statement.setString(1, article. );
	//		statement.setString(2, article. );
	//		statement.setDate(3, article. );

			// 4. Exécution
			statement.executeUpdate();

		} catch ( SQLException e ) {
			e.printStackTrace();
			throw new Exception(MessageReader.getMessage(ErrorCode.ERROR_INSERT));
			
		} // close automatiques par les biais des parentheses du try
	}
		
	

	@Override
	public void update(Article t) throws Exception {
		
		
	}

	@Override
	public void deleteById(Integer id) throws Exception {
		
		
	}

	@Override
	public Article selectById(Integer id) throws Exception {
		
		return null;
	}

	@Override
	public List<Article> selectAll() throws Exception {
		
		return null;
	}


}
