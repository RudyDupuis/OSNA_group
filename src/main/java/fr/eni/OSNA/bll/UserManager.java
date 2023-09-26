package fr.eni.OSNA.bll;

import fr.eni.OSNA.bo.User;
import fr.eni.OSNA.dal.DAOFactory;
import fr.eni.OSNA.dal.UserDAO;

public class UserManager {

	private static UserManager instance;
	
	public static UserManager getInstance() {
		if(instance == null) {
			instance = new UserManager();
		}
		return instance;
	}
	
	private UserDAO dao = DAOFactory.getUserDAO();
	
	public void insert(User user) throws Exception {
		dao.insert(user);
	}
	
	public void update(User user) throws Exception {
		dao.update(user);
	}
	
	public void deleteById(int id) throws Exception {
		dao.deleteById(id);
	}
	
	public User selectById(int id) throws Exception {
		return dao.selectById(id);
	}

	public User login(String id, String password) throws Exception {
		return dao.login(id, password);
	}
	
	public String getPseudo(int id) throws Exception {
		return dao.getPseudo(id);
	}
	
	public void updatePoints(User user, int Offer, String action) throws Exception {
		dao.updatePoints(user);
	}
	
	
}
