package configuration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import frameworkexception.FrameworkException;

public class ConfigurationManager {

	private Properties prop;
	private FileInputStream ip;

	public Properties initProp() {

		// mvn clean -Denv="qa"
		prop = new Properties();

		String envName = System.getProperty("env");

		try {
			if (envName == null) {
				System.out.println("env name cannot be null");
				ip = new FileInputStream("./src/test/resources/properties/qa.config.properties");
			} else {
				switch (envName.toLowerCase().trim()) {
				case "qa":
					ip = new FileInputStream("./src/test/resources/properties/qa.config.properties");
					break;
				case "stage":
					ip = new FileInputStream("./src/test/resources/properties/stage.config.properties");
					break;
				case "prod":
					ip = new FileInputStream("./src/test/resources/properties/config.properties");
					break;
				default:
					System.out.println("Please pass the right env name..." + envName);
					throw new FrameworkException("WRONG ENV IS Given");	
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new FrameworkException("please pass the correct envname:: " + envName);
		}
		try {
			prop.load(ip);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return prop;
	}
}
