package database;

import java.security.KeyStore.Entry;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class DbLogin {
	
	
	private final Map <String, String>LOGINPASSWORD=new HashMap<>();
	
	public DbLogin(){
		LOGINPASSWORD.put("asalinas","1234");
		LOGINPASSWORD.put("bsalinas","5678");
		LOGINPASSWORD.put("csalinas","9876");
		LOGINPASSWORD.put("dsalinas","5432");
		LOGINPASSWORD.put("esalinas","1234");
		LOGINPASSWORD.put("fsalinas","5678");
		LOGINPASSWORD.put("gsalinas","9876");
		LOGINPASSWORD.put("hsalinas","5432");
		LOGINPASSWORD.put("isalinas","1234");
		LOGINPASSWORD.put("jsalinas","5678");	

	}
	public boolean loginUser(String login, String constrasena) {
		try {
			if(LOGINPASSWORD.get(login).equals(constrasena)){
				return true;
			}
		}catch(NullPointerException e) {
			return false;
			
		}
		
	return false;
	}
}
