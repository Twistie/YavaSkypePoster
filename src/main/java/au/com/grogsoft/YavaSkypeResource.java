package au.com.grogsoft;

import com.skype.ChatProxy;
import com.skype.SkypeException;
import com.sun.jersey.core.spi.factory.ResponseBuilderImpl;
import com.sun.jersey.core.spi.factory.ResponseImpl;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

/**
 * Created by Twistie on 12/09/2015.
 */
@Path("/skype")

public class YavaSkypeResource {

    @GET
    @Path("/status")
    public String getStatus() {
        return "JovieSux";
    }
    @POST
    @Produces("text/html")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getMessage(Message message){
        try {
            ChatProxy.getChatInstance(message.chatId).send("[JovialBot2]: " + message.message);
        } catch (SkypeException e) {
            return new ResponseBuilderImpl().status(500).build();
        }
        return new ResponseBuilderImpl().status(204).build();
    }

    public static class Message {
        public String chatId;
        public String message;
        public Message() {}
    }
}
