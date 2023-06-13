package database;
import java.security.KeyStore.Entry;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class DbLogin {
	
	
	Map <String, String>loginPassword=new HashMap<>();
	
	public DbLogin(){
			loginPassword.put("asalinas","1234");
			loginPassword.put("bsalinas","5678");
			loginPassword.put("csalinas","9876");
			loginPassword.put("dsalinas","5432");
			loginPassword.put("esalinas","1234");
			loginPassword.put("fsalinas","5678");
			loginPassword.put("gsalinas","9876");
			loginPassword.put("hsalinas","5432");
			loginPassword.put("isalinas","1234");
			loginPassword.put("jsalinas","5678");	

	}
	public boolean esIgual(String login, String constrasena) {
		if(loginPassword.get(login).equals(constrasena)){
			return true;
		}
		return false;
	}

		

}
