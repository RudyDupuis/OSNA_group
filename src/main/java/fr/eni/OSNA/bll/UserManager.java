package fr.eni.OSNA.bll;

import fr.eni.OSNA.bo.User;
import fr.eni.OSNA.dal.DAOFactory;
import fr.eni.OSNA.dal.UserDAO;
import fr.eni.OSNA.messages.ErrorCode;
import fr.eni.OSNA.messages.MessageReader;

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
		User user = dao.login(id, password);
		
		if(user != null) {
			return user;
		} else {
			throw new Exception(MessageReader.getMessage(ErrorCode.ERROR_LOGIN));
		}
	}
	
	public String getPseudo(int id) throws Exception {
		return dao.getPseudo(id);
	}
	
	public void updatePoints(User user, int offer, String action) throws Exception {
		if(action.equals("pay")) {	
			if(user.getPoints() < offer) {
				throw new Exception("Vous n'avez pas assez de points pour faire cette offre");
			}
			
			user.setPoints(user.getPoints() - offer);
		}
		
		if(action.equals("repay")) {
			user.setPoints(user.getPoints() + offer);
		}
		
		dao.updatePoints(user);
	}
	
	
}
