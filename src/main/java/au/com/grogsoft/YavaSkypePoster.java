package au.com.grogsoft;

import com.sun.jersey.api.container.grizzly2.GrizzlyServerFactory;
import org.glassfish.grizzly.http.server.HttpServer;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class YavaSkypePoster {
    private static final String BIND_ADDRESS_KEY = "address";
    private static String bindAddress = "http://localhost:5555";

    public static void main(String[] args) {
        setBindAddress();

        HttpServer server = null;
        try {
            server = GrizzlyServerFactory.createHttpServer(bindAddress);
            System.out.println("Press any key to stop the service...");
            System.in.read();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (server != null) {
                    server.stop();
                }
            } finally {
                // FINALLY
            }
        }
    }
    private static void setBindAddress() {
        String filename = "config.properties";
        InputStream input;
        try {
            input = new FileInputStream(filename);
        } catch (FileNotFoundException e) {
            System.out.println("Can not find properties file, using default: " + bindAddress);
            return;
        }

        Properties prop = new Properties();
        //load a properties file from class path, inside static method
        try {
            prop.load(input);
        } catch (IOException e) {
            System.out.println("Invalid properties file");
            return;
        }
        String address = prop.getProperty(BIND_ADDRESS_KEY);
        if(null == address) {
            System.out.println("Can't find address in properties file");
            return;
        }

        bindAddress = address;
        System.out.println("Bind address set to: " + bindAddress);
    }
}
