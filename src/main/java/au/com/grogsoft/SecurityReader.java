package au.com.grogsoft;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class SecurityReader {
    private static SecurityReader instance;

    private final boolean useSecurity;
    private Map<String, String> apiKeyToBotName = null;

    private SecurityReader() {
        String filename = "apikeys.properties";
        InputStream input;
        try {
            input = new FileInputStream(filename);
        } catch (FileNotFoundException e) {
            System.out.println("Can not find api key properties file, using default no security");
            useSecurity = false;
            return;
        }

        Properties prop = new Properties();
        //load a properties file from class path, inside static method
        try {
            prop.load(input);
        } catch (IOException e) {
            System.out.println("Invalid api key properties file");
            //In this case, don't continue, because we wanted security, but we couldn't initialize it.
            //This will end up in a 500 response.
            throw new RuntimeException("Invalid api key properties file, server config invalid");
        }
        useSecurity = true;
        apiKeyToBotName = new HashMap<String, String>();
        for (String s : prop.stringPropertyNames()) {
            apiKeyToBotName.put(s, prop.getProperty(s));
        }
    }

    public static SecurityReader getInstance() {
        if(null == instance)
            instance = new SecurityReader();
        return instance;
    }

    public boolean useSecurity() {
        return useSecurity;
    }

    public Map<String, String> getApiKeyToBotName() {
        return apiKeyToBotName;
    }
}
