package config;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;


public class Setting {
	
	private final Properties properties;
	private static final String CONFIG_PROPERTIES = "config.properties";
	
	public Setting() {
		properties=new Properties();
		loadProperties(CONFIG_PROPERTIES);
	}
	private void loadProperties(String filePath) {
		FileInputStream fileInputStream;
		
		try {
			fileInputStream=new FileInputStream(filePath);
			properties.load(fileInputStream);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException er) {
			er.printStackTrace();	
		}
	}
	
	public String getUrl(String name) {
		return properties.getProperty(name);
	}
}
	


	
/*	private Properties properties=new Properties();
	
	public void configurarUrl(String name) {
		try {
			properties.load(new FileInputStream(new File("config.properties")));
			System.out.println(properties.get(name));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException er) {
			er.printStackTrace();	
		}
	}

	public String getProperties(String name) {
	return properties.getProperty(name);
	}


	

	public void setProperties(Properties properties) {
		this.properties = properties;
	}
	

}*/