package fr.eni.OSNA.dal;

import fr.eni.OSNA.bo.User;

public interface UserDAO extends DAO<User> {
	User login(String id, String password) throws Exception;
	void updatePoints(User user) throws Exception;
	Boolean checkUniqueMail(User user) throws Exception;
	Boolean checkUniquePseudo(User user) throws Exception;
	String getPseudo(int id) throws Exception;
}
