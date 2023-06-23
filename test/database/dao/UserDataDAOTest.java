package database.dao;

import java.sql.SQLException;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import database.dto.UserDataDTO;
import junit.framework.Assert;

@RunWith(JUnit4.class)
public class UserDataDAOTest {
	
	private UserDataDAO userDataDAO = new UserDataDAO();

	@Test
	public void testgetUserByLogin() throws SQLException {
		List<UserDataDTO> userByLogin = userDataDAO.getUserByLogin("cbeltran");
		
		UserDataDTO userData = userByLogin.get(0);	
		Assert.assertNotNull(userData);	
	}
}
