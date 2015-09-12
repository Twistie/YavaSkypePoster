package au.com.grogsoft;

import com.skype.*;
import com.sun.jersey.api.container.grizzly2.GrizzlyServerFactory;
import org.glassfish.grizzly.http.server.HttpServer;

import java.io.IOException;


public class YavaSkypePoster {
    public static void main(String[] args) {
        HttpServer server = null;


        try {
            server = GrizzlyServerFactory.createHttpServer("http://192.168.1.100:5555");
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


}
