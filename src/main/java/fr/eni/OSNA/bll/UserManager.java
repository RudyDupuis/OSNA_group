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
		StringBuilder error = new StringBuilder();
		boolean hasError = false;
		
		StringBuilder errorCheckInput = checkInputFields(user);
		
		if(errorCheckInput != null) {
			hasError = true;
			error.append(errorCheckInput);
		}
		
		if(!dao.checkUniqueMail(user)) {
			hasError = true;
			error.append(MessageReader.getMessage(ErrorCode.ERROR_MAIL_NOTUNIQUE)).append("\n");
		}
		
		if(!dao.checkUniquePseudo(user)) {
			hasError = true;
			error.append(MessageReader.getMessage(ErrorCode.ERROR_PSEUDO_NOTUNIQUE)).append("\n");
		}
		
		if(hasError) {
			throw new Exception(error.toString());
		} else {
			dao.insert(user);
		}
	}
	
	public void update(User user, User userSession) throws Exception {
		StringBuilder error = new StringBuilder();
		boolean hasError = false;
		
		StringBuilder errorCheckInput = checkInputFields(user);
		
		if(errorCheckInput != null) {
			hasError = true;
			error.append(errorCheckInput);
		}
		
		if(!user.getMail().equals(userSession.getMail()) && !dao.checkUniqueMail(user)) {
			hasError = true;
			error.append(MessageReader.getMessage(ErrorCode.ERROR_MAIL_NOTUNIQUE)).append("\n");
		}
		
		if(!user.getMail().equals(userSession.getMail()) && !dao.checkUniquePseudo(user)) {
			hasError = true;
			error.append(MessageReader.getMessage(ErrorCode.ERROR_PSEUDO_NOTUNIQUE)).append("\n");
		}
		
		if(hasError) {
			throw new Exception(error.toString());
		} else {
			dao.update(user);
		}
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
	
	private StringBuilder checkInputFields(User user) {
		StringBuilder error = new StringBuilder();
		boolean hasError = false;
		
		if (!user.getFirstName().matches("[\\p{L}]{3,}") || !user.getLastName().matches("[\\p{L}]{3,}")) {
		    hasError = true;
		    error.append("Le champ 'Prénom' et 'Nom' ne doivent contenir que des lettres et avoir au moins 3 caractères.\n");
		}

		if(!user.getMail().matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
			hasError = true;
		    error.append("Le format du mail n'est pas valide.\n");
		}
		
		if(!user.getPhone().matches("^(\\+(33|0)[1-9](?:[0-9]{2}){4}|0[1-9][0-9]{8})$")) {
			hasError = true;
		    error.append("Le format du téléphone n'est pas valide.\n");
		}
		
		if(!user.getPseudo().matches("^[a-zA-Z0-9_-]{3,}")) {
			hasError = true;
		    error.append("Le pseudo doit faire plus de 3 caractères et contenir des chiffres et des lettres uniquement.\n");
		}
		
		if(!user.getPassword().matches("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@#$%^&+=!.]).{8,}$")) {
			hasError = true;
		    error.append("Le mot de passe doit faire plus de 8 caractères et contenir au moins un chiffre, une minuscule, une majuscule et un caractère spécial.\n");
		}
		
		if(hasError) {
			return error;
		} else {
			return null;
		}
		
	}
	
	
}
