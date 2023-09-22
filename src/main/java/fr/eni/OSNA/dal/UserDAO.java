package fr.eni.OSNA.dal;

import fr.eni.OSNA.bo.User;

public interface UserDAO extends DAO<User> {

	User login(String id, String password) throws Exception;

}
