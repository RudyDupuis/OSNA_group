package fr.eni.OSNA.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import fr.eni.OSNA.bo.User;
import fr.eni.OSNA.messages.ErrorCode;
import fr.eni.OSNA.messages.MessageReader;

public class UserDaoJdbcImpl implements UserDAO {
	
	@Override
	public void insert(User user) throws Exception {

		String sqlInsert = "INSERT INTO users "
				+ "(pseudo, firstName, lastName, mail, phone, street, postalCode, city, password, points)"
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		try (Connection cnx = ConnectionProvider.getConnection(); PreparedStatement ps = cnx.prepareStatement(sqlInsert, java.sql.Statement.RETURN_GENERATED_KEYS)) {

			int index = 1;
			ps.setString(index++, user.getPseudo());
			ps.setString(index++, user.getFirstName());
			ps.setString(index++, user.getLastName());
			ps.setString(index++, user.getMail());
			ps.setString(index++, user.getPhone());
			ps.setString(index++, user.getStreet());
			ps.setInt	(index++, user.getPostalCode());
			ps.setString(index++, user.getCity());
			ps.setString(index++, user.getPassword());
			ps.setInt	(index++, 0);
			
			ps.executeUpdate();

			ResultSet generatedKeys = ps.getGeneratedKeys();
			if (generatedKeys.next()) {
				user.setId(generatedKeys.getInt(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception(MessageReader.getMessage(ErrorCode.ERROR_INSERT));
		}
	}

	@Override
	public void update(User user) throws Exception {

		String sqlUpdate = " UPDATE users SET " 
				+ "pseudo = ? , firstName = ?, lastName = ?, mail = ?, phone = ?, street = ? , postalCode = ?, city = ?, password = ? " 
				+ "WHERE id = ? ";

		try (Connection cnx = ConnectionProvider.getConnection(); PreparedStatement ps = cnx.prepareStatement(sqlUpdate)) {

			int index = 1;
			ps.setString(index++, user.getPseudo());
			ps.setString(index++, user.getFirstName());
			ps.setString(index++, user.getLastName());
			ps.setString(index++, user.getMail());
			ps.setString(index++, user.getPhone());
			ps.setString(index++, user.getStreet());
			ps.setInt(index++, user.getPostalCode());
			ps.setString(index++, user.getCity());
			ps.setString(index++, user.getPassword());
			
			ps.setInt(index++, user.getId());
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception(MessageReader.getMessage(ErrorCode.ERROR_UPDATE));
		}
	}

	@Override
	public void deleteById(int id) throws Exception {

		String sqlDeleteById = "DELETE FROM users WHERE id = ?";

		try (Connection cnx = ConnectionProvider.getConnection(); PreparedStatement ps = cnx.prepareStatement(sqlDeleteById)) {

			ps.setInt(1, id);
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception(MessageReader.getMessage(ErrorCode.ERROR_DELETE));
		}
	}

	@Override
	public User selectById(int id) throws Exception {

		String sqlSelectById = " SELECT * FROM users WHERE id = ? ";

		User user = null;
		
		try (Connection cnx = ConnectionProvider.getConnection(); PreparedStatement ps = cnx.prepareStatement(sqlSelectById)) {

			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {

				user = new User(rs.getInt("id"), rs.getString("firstName"),
						rs.getString("lastName"), rs.getString("pseudo"), rs.getString("mail"), rs.getString("phone"),
						rs.getString("street"), rs.getInt("postalCode"), rs.getString("city"),
						rs.getString("password"), rs.getInt("points"));
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception(MessageReader.getMessage(ErrorCode.ERROR_SELECT));
		}
		
		return user;
	}

	@Override
	public List<User> selectAll() throws Exception {
		return null;
	}

	@Override
	public User login(String id, String password) throws Exception {

		String sqlLogin = "SELECT * FROM users WHERE (mail = ? AND password = ?) OR (pseudo = ? AND password = ?)";

		User user = null;
		
		try (Connection cnx = ConnectionProvider.getConnection();
				PreparedStatement ps = cnx.prepareStatement(sqlLogin)) {

			int index = 1;
			ps.setString(index++, id);
			ps.setString(index++, password);
			ps.setString(index++, id);
			ps.setString(index++, password);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				
				user = new User(rs.getInt("id"), rs.getString("firstName"),
						rs.getString("lastName"), rs.getString("pseudo"), rs.getString("mail"), rs.getString("phone"),
						rs.getString("street"), rs.getInt("postalCode"), rs.getString("city"),
						rs.getString("password"), rs.getInt("points"));
				
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception(MessageReader.getMessage(ErrorCode.ERROR_SELECT));
		}
		
		return user;
	}
	
	@Override
public void updatePoints(User user) throws Exception {
		
		String sqlUpdatePoints = "UPDATE users SET points = ? WHERE id = ?";

		try (Connection cnx = ConnectionProvider.getConnection();
				PreparedStatement preparedStatement = cnx.prepareStatement(sqlUpdatePoints)) {
			
			preparedStatement.setInt(1, user.getPoints());
			preparedStatement.setInt(2, user.getId());
			preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception(MessageReader.getMessage(ErrorCode.ERROR_UPDATE));
		}
	}

	@Override
	public Boolean checkUniqueMail(User user) throws Exception {
		
		String sqlCheckUniqueMail = "SELECT COUNT(*) FROM users WHERE mail = ?";

		try (Connection cnx = ConnectionProvider.getConnection();
				PreparedStatement preparedStatement = cnx.prepareStatement(sqlCheckUniqueMail)) {
			
			preparedStatement.setString(1, user.getMail());
		    ResultSet resultSet = preparedStatement.executeQuery();
		    
		        if (resultSet.next()) {
					return false;
				} else {
					return true;
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
				throw new Exception(MessageReader.getMessage(ErrorCode.ERROR_SELECT));
			}
		}

	@Override
	public Boolean checkUniquePseudo(User user) throws Exception {
		
		String sqlCheckUniquePseudo = "SELECT COUNT(*) FROM users WHERE pseudo = ?";

		try (Connection cnx = ConnectionProvider.getConnection();
				PreparedStatement preparedStatement = cnx.prepareStatement(sqlCheckUniquePseudo)) {
			
			preparedStatement.setString(1, user.getPseudo());
		    ResultSet resultSet = preparedStatement.executeQuery();
		    
		        if (resultSet.next()) {
					return false;
				} else {
					return true;
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
				throw new Exception(MessageReader.getMessage(ErrorCode.ERROR_SELECT));
			}
		}

	@Override
	public String getPseudo(int id) throws Exception {
		String Sql = "SELECT pseudo FROM users WHERE id = ?";
		String pseudo = null;
		
		try(Connection cnx = ConnectionProvider.getConnection(); PreparedStatement ps = cnx.prepareStatement(Sql)) {
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				pseudo = rs.getString("pseudo");
			} 
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception(MessageReader.getMessage(ErrorCode.ERROR_SELECT));
		}
		
		return pseudo;
	}
}
