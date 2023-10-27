package Parameters;

import org.testng.annotations.BeforeSuite;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class BaseClass {
    protected static String apiHost;
    protected static String webHost;

    @BeforeSuite
    public void beforeSuite(){
        Properties properties = new Properties();
        try (FileInputStream fileInputStream = new FileInputStream("src/test/resources/config.properties")) {
            properties.load(fileInputStream);
            apiHost = properties.getProperty("apiHost");
            webHost = properties.getProperty("webHost");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
