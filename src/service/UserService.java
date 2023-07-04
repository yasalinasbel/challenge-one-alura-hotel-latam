package service;

import database.dao.UserDataDAO;
import database.dto.UserDataDTO;

public class UserService {
	
	private final UserDataDAO userDataDAO;
	
	public UserService() {
		userDataDAO = new UserDataDAO();
	}
	
	public UserService(UserDataDAO userDataDAO) {
		this.userDataDAO = userDataDAO;
	}
	
	public boolean loginUser(String login, String constrasena) {
		UserDataDTO userByLogin = userDataDAO.getUserByLogin(login);
		
		if(userByLogin!= null){
			if(userByLogin.getPassword().equals(constrasena)){
				return true;
			}
		}
		return false;
	}
}